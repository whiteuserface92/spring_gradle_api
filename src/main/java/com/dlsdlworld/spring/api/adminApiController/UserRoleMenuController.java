package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.adminApiService.UserMenuService;
import com.dlsdlworld.spring.api.cachemodel.HospitalMenuCache;
import com.dlsdlworld.spring.api.cachemodel.MessageCache;
import com.dlsdlworld.spring.api.cacherepository.HospitalMenuCacheRepository;
import com.dlsdlworld.spring.api.cacherepository.UserConfigCacheRepository;
import com.dlsdlworld.spring.api.dto.UserDefalutPageDTO;
import com.dlsdlworld.spring.api.dto.UserDefaultPageListDTO;
import com.dlsdlworld.spring.api.repository.MenuRoleRepository;
import com.dlsdlworld.spring.api.types.MenuTypes;
import com.dlsdlworld.spring.api.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 병원 메뉴 캐시 정보를 제공하는 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/rest")
public class UserRoleMenuController {

    /** 병원메뉴 캐시 저장소 */
    private final HospitalMenuCacheRepository hospitalMenuCacheRepository;

    /** 역할별 메뉴설정 */
    private MenuRoleRepository menuRoleRepository;

    private UserMenuService userMenuService;

    /**
     * constructor
     * @param hospitalMenuCacheRepository
     */
    @Autowired
    public UserRoleMenuController(HospitalMenuCacheRepository hospitalMenuCacheRepository,
                                  MenuRoleRepository menuRoleRepository,
                                  UserMenuService userMenuService,
                                  UserConfigCacheRepository userConfigCacheRepository) {
        this.hospitalMenuCacheRepository = hospitalMenuCacheRepository;
        this.menuRoleRepository = menuRoleRepository;
        this.userMenuService =userMenuService;
    }

    @Transactional
    @GetMapping(value = {"/userRoleMenus/{id}"})
    public LinkedHashMap<String, HospitalMenuCache> userRoleMenus(@PathVariable(name = "id") Long id) {

        LinkedHashMap<String, HospitalMenuCache> roleMenus  = getUserRoleMenusById(id,"");

        return roleMenus;
    }
    private LinkedHashMap<String, HospitalMenuCache>  getUserRoleMenusById(Long userId , String deptCd) {
        LinkedHashMap<String, HospitalMenuCache> data = new LinkedHashMap<>();
        List<Long> hospitalMenus = menuRoleRepository.findMenuRoleHospitalMenus(userId);
        //롤별 접근 권한 조회
        List<HospitalMenuCache> hospitalMenuCaches = new ArrayList<HospitalMenuCache>();
        for(Long hospitalMenuId : hospitalMenus) {
            // log.info("userId:{}, hospitalMenuId:{}", userId, hospitalMenuId);
            hospitalMenuCaches.add(hospitalMenuCacheRepository.findById(hospitalMenuId).orElseGet(() -> new HospitalMenuCache()));
        }
        for (HospitalMenuCache cachedHospitalMenu : hospitalMenuCaches) {
            // 레디스에 캐시가 없을 경우에  http://localhost:9082/ui/plus/api/admin/rest/userMenus/?userId=2&menuType=MAIN 요청에서 에러 발생한다.
            // 병원별 메뉴 초기화를 시작 하면된다.
            if(cachedHospitalMenu.getLevel() == 1) {
                cachedHospitalMenu.setChildren(addChilds(hospitalMenuCacheRepository.findByParentId(cachedHospitalMenu.getId()),true, hospitalMenus, deptCd));
                data.put(cachedHospitalMenu.getMenuType().toString(),cachedHospitalMenu);
            }
        }
        return data;
    }
    /**
     * 사용자 개인에 대한 기본 메뉴및 즐겨찾기 조회
     * DeptCd : 롤별 메뉴 관리에서 해당 부서 코드가 등록된 부서만 부서 접근 가능하고, 다른 부서는 부서 접근 불가,
     *  부서가 입력되지 않은 메뉴는 전체 부서에서 사용 가능한 메뉴 이다.
     * @param userId
     * @return
     */
    @Transactional
    @GetMapping(value = {"/userRoleMenus/menuAndFav"})
    public Map<String,Object> menuAndFav(@RequestParam(name = "userId") Long userId,
                                         @RequestParam(name = "DeptCd") String deptCd, // WhiteBoxList 체크용 부서 코드
                                         @RequestParam(name = "HosCd") String hosCd,
                                         @RequestParam(name = "menuType", required = true) MenuTypes menuType) {

        Long loginUserId =   SecurityUtils.getCurrentUserId();
        LinkedHashMap<String, HospitalMenuCache> roleMenus  = getUserMenusByIdByHosCd(loginUserId , deptCd, hosCd);
        String favorMenus = userMenuService.getUserMenus(loginUserId, menuType);
        favorMenus = favorMenus.substring(1,favorMenus.length()-1);
        Map<String,Object> retMap = new Hashtable<String,Object>();
        if(StringUtils.isEmpty(favorMenus))
        {
            favorMenus ="{}";  // 즐겨찾기 없을 경우에는 기본 객체 리턴
        }
       /* UserConfigCache userConfig = new UserConfigCache();
        Optional<UserConfigCache>  userConfigOpt= userConfigCacheRepository.findByUserId(loginUserId);
        if(userConfigOpt.isPresent()){ userConfig = userConfigOpt.get();}
        */
        retMap.put("roleMenus",roleMenus);
        retMap.put("favorMenus", favorMenus);
        //retMap.put("userConfig", userConfig );
        return retMap;
    }

    /**
     * 사용자 userId 기준 전체 메뉴 조회 user_role기준으로 가져 온다.
     * UI 화면에서 만 가져 오는데 사용 hosCd적용한것
     * @param userId
     */
    private LinkedHashMap<String, HospitalMenuCache>  getUserMenusByIdByHosCd(Long userId , String deptCd, String hosCd) {
        LinkedHashMap<String, HospitalMenuCache> data = new LinkedHashMap<>();
        List<Long> hospitalMenus = menuRoleRepository.findMenuRoleHospitalMenus(userId);
        //롤별 접근 권한 조회
        List<HospitalMenuCache> hospitalMenuCaches = new ArrayList<HospitalMenuCache>();
        for(Long hospitalMenuId : hospitalMenus) {
           //   log.info("userId:{}, hospitalMenuId:{}", userId, hospitalMenuId);
            hospitalMenuCaches.add(hospitalMenuCacheRepository.findById(hospitalMenuId).orElseGet(() -> new HospitalMenuCache()));
        }
        for (HospitalMenuCache cachedHospitalMenu : hospitalMenuCaches) {
            // 레디스에 캐시가 없을 경우에  http://localhost:9082/ui/plus/api/admin/rest/userMenus/?userId=2&menuType=MAIN 요청에서 에러 발생한다.
            // 병원별 메뉴 초기화를  SIDE 메뉴에 내가 속한 HosCd를 통해야 한다.
            if(cachedHospitalMenu.getLevel() == 1 && MenuTypes.SIDE.equals(cachedHospitalMenu.getMenuType())
                    && cachedHospitalMenu.getHospitalCd().equals(hosCd)) {
                cachedHospitalMenu.setChildren(addChilds(hospitalMenuCacheRepository.findByParentId(cachedHospitalMenu.getId()),true, hospitalMenus, deptCd));
                data.put(cachedHospitalMenu.getMenuType().toString(),cachedHospitalMenu);
            }
        }
        return data;
    }


    /**
     * 자식 & 자식에 자식을 가져와 list로 반환
     * 재귀함수
     * roleMenu 권한 추가
     * @param childs
     * @param parentYn
     * @param roleMenus
     * @return
     */
    private List<HospitalMenuCache> addChilds(List<HospitalMenuCache> childs, boolean parentYn, List<Long> roleMenus , String userDeptCd) {
        childs.sort(
                Comparator.comparing(HospitalMenuCache::getMenuType)
                        .thenComparing(HospitalMenuCache::getLevel)
                        .thenComparing(HospitalMenuCache::getDispOrd, (d1, d2) -> {
                            return Integer.parseInt(d1);
                        })
        );

        ArrayList<HospitalMenuCache> lists = new ArrayList<>();
        for (HospitalMenuCache child : childs) { //전체 자식 노드에 대한 Loop
            // 부서 접근 권한 체크 로직 시작 부서코드 등록을 ,를 무조건 붙이도록 가이드
            if( StringUtils.isNotEmpty(child.getDeptWhiteList())
              && StringUtils.isNotEmpty( userDeptCd ) )  {  // WhiteList 부서가 있으면 체크 한다.
                boolean bCheckAccess = checkDeptAccessRight(userDeptCd, child);
                if( ! bCheckAccess ){ // 부서코드로 메뉴접근 권한이 없으면 메뉴 리스트 추가 되지 않도록 한다.
                    continue;
                }

            }
            // 부서 접근 권한 체크 로직 종료
            if(child.getEnabled() && checkedRoleMenu(child.getId(), roleMenus)) { //역할에 매핑 되어 있는지 확인
                if (parentYn) {
                    child.setChildren(addChilds(hospitalMenuCacheRepository.findByParentId(child.getId()), false, roleMenus , userDeptCd));
                }
                lists.add(child);

            }
        }
        return lists;
    }

    private boolean checkDeptAccessRight(String userDeptCd, HospitalMenuCache child) {
        String[] aryDept = child.getDeptWhiteList().split(",");
        for (String deptCd: aryDept){
            if ( deptCd.equals(userDeptCd) ){ // 부서코드가 포함되지 않으면 권한 없음으로 제낀다.
                return true;
            }
        }
        return false;
    }

    /**
     * 권한이 있는 ID만 true;
     * @param checkedId
     * @param roleMenus
     * @return
     */
    private boolean checkedRoleMenu(Long checkedId, List<Long> roleMenus) {
        boolean checked = roleMenus.contains(checkedId);
        /*for(Long roleMenu : roleMenus) {
            if(checkedId == roleMenu) {
                checked = true;
                break;
            }
        }*/
        return checked;
    }

    /**
     * 기본페이지 설정용 JSON 객체 리턴작업
     */


    @Transactional
    @GetMapping(value = {"/userRoleMenus/menuDefaultPage"})
    public Map<String,Object> menuDefaultPage(@RequestParam(name = "userId") Long userId,
                                         @RequestParam(name = "DeptCd") String deptCd, // WhiteBoxList 체크용 부서 코드
                                         @RequestParam(name = "HosCd") String hosCd,
                                         @RequestParam(name = "menuType", required = true) MenuTypes menuType) {

        Long loginUserId  = SecurityUtils.getCurrentUserId();
        List<UserDefalutPageDTO> defaultPageList  = getUserRoleMenusDefaultPageById(loginUserId , deptCd, hosCd);
        Map<String,Object> retMap = new Hashtable<String,Object>();
        retMap.put("defaultPageList",defaultPageList);
        return retMap;
    }
    private  List<UserDefalutPageDTO> getUserRoleMenusDefaultPageById(Long userId , String deptCd, String hosCd) {

        List<Long> hospitalMenus = menuRoleRepository.findMenuRoleHospitalMenus(userId);
        //롤별 접근 권한 조회
        List<HospitalMenuCache> hospitalMenuCaches = new ArrayList<HospitalMenuCache>();
        for(Long hospitalMenuId : hospitalMenus) {
            //log.info("userId:{}, hospitalMenuId:{}", userId, hospitalMenuId);
            hospitalMenuCaches.add(hospitalMenuCacheRepository.findById(hospitalMenuId).orElseGet(() -> new HospitalMenuCache()));
        }

        List<UserDefalutPageDTO> lst = new ArrayList<UserDefalutPageDTO>();
        for (HospitalMenuCache cachedHospitalMenu : hospitalMenuCaches) {
            //cachedHospitalMenu.getMenuType().equals(MenuTypes.SIDE)
            // 레디스에 캐시가 없을 경우에  http://localhost:9082/ui/plus/api/admin/rest/userMenus/?userId=2&menuType=MAIN 요청에서 에러 발생한다.
            // 병원별 메뉴 초기화를 시작 하면된다.  2단계 표시된다.
            if(cachedHospitalMenu.getLevel() == 1 && MenuTypes.SIDE.equals(cachedHospitalMenu.getMenuType())
             && cachedHospitalMenu.getHospitalCd().equals(hosCd)) {
                // 하위에서 pageList객체를 받아야 한다.
                log.info("userId:{},HospitalCd:{}, getLevel:{} , nameCd:{}", userId, cachedHospitalMenu.getHospitalCd(), cachedHospitalMenu.getLevel(), cachedHospitalMenu.getMenu().getNameCd());
                lst = addChildsPageLevel2(hospitalMenuCacheRepository.findByParentId(cachedHospitalMenu.getId()), true, hospitalMenus, deptCd);
            }
        }
        Collections.sort(lst, new Comparator<UserDefalutPageDTO>() {
            @Override
            public int compare(UserDefalutPageDTO o1, UserDefalutPageDTO o2) {
                return o1.getPageGrpCd().compareTo(o2.getPageGrpCd());
            }
        });

      return lst;
    }


    private List<UserDefalutPageDTO> addChildsPageLevel2(List<HospitalMenuCache> childs, boolean parentYn, List<Long> roleMenus , String userDeptCd) {
        childs.sort(
                Comparator.comparing(HospitalMenuCache::getMenuType)
                        .thenComparing(HospitalMenuCache::getLevel)
                        .thenComparing(HospitalMenuCache::getDispOrd, (d1, d2) -> {
                            return Integer.parseInt(d1);
                        })
        );

        UserDefalutPageDTO pageDto = new UserDefalutPageDTO();
        List<UserDefalutPageDTO> lst = new ArrayList<UserDefalutPageDTO>();
        List<UserDefaultPageListDTO> lstdto =  new ArrayList<UserDefaultPageListDTO>();
        String menueNameEngNm ="";
        //
        int i = 1;
        for (HospitalMenuCache child : childs) { //전체 자식 노드에 대한 Loop
            // 부서 접근 권한 체크 로직 시작 부서코드 등록을 ,를 무조건 붙이도록 가이드
            if( StringUtils.isNotEmpty(child.getDeptWhiteList())
                    && StringUtils.isNotEmpty( userDeptCd ) )  {  // WhiteList 부서가 있으면 체크 한다.
                boolean bCheckAccess = checkDeptAccessRight(userDeptCd, child);
                if( ! bCheckAccess ){ // 부서코드로 메뉴접근 권한이 없으면 메뉴 리스트 추가 되지 않도록 한다.
                    continue;
                }

            }
            // 부서 접근 권한 체크 로직 종료
            if(child.getEnabled() && checkedRoleMenu(child.getId(), roleMenus)) { //역할에 매핑 되어 있는지 확인
                // 하위 리스트 표시 객체 처리
                lstdto =  addChildsPage(hospitalMenuCacheRepository.findByParentId(child.getId()), false, roleMenus , userDeptCd);
                pageDto = new UserDefalutPageDTO();
//                pageDto.setPageGrpCd(StringUtils.leftPad(String.valueOf(i++),2,'0'));
                pageDto.setPageGrpCd(child.getDispOrd());
                pageDto.setPageGrpNm(child.getMenu().getMenuDesc()); // 메뉴 설명으로 대체 ko
                menueNameEngNm = getMenuNameEng(child);
                pageDto.setPageGrpEngNm(menueNameEngNm); // 메뉴 DESC
                pageDto.setPageList(lstdto);
                lst.add(pageDto);
            }
        }
        return lst;
    }

    private List<UserDefaultPageListDTO> addChildsPage(List<HospitalMenuCache> childs, boolean parentYn, List<Long> roleMenus , String userDeptCd) {
        childs.sort(
                Comparator.comparing(HospitalMenuCache::getMenuType)
                        .thenComparing(HospitalMenuCache::getLevel)
                        .thenComparing(HospitalMenuCache::getDispOrd, (d1, d2) -> {
                            return Integer.parseInt(d1);
                        })
        );

        UserDefaultPageListDTO dto = new UserDefaultPageListDTO();
        ArrayList<UserDefaultPageListDTO> lists = new ArrayList<>();
        for (HospitalMenuCache child : childs) { //전체 자식 노드에 대한 Loop
            // 부서 접근 권한 체크 로직 시작 부서코드 등록을 ,를 무조건 붙이도록 가이드
            if( StringUtils.isNotEmpty(child.getDeptWhiteList())
                    && StringUtils.isNotEmpty( userDeptCd ) )  {  // WhiteList 부서가 있으면 체크 한다.
                boolean bCheckAccess = checkDeptAccessRight(userDeptCd, child);
                if( ! bCheckAccess ){ // 부서코드로 메뉴접근 권한이 없으면 메뉴 리스트 추가 되지 않도록 한다.
                    continue;
                }

            }
            // 부서 접근 권한 체크 로직 종료
            if(child.getEnabled() && checkedRoleMenu(child.getId(), roleMenus)) { //역할에 매핑 되어 있는지 확인
                if (parentYn) {
                    //lstdto =  addChildsPage(hospitalMenuCacheRepository.findByParentId(child.getId()), false, roleMenus , userDeptCd);
                  //  child.setChildren(addChildsPage(hospitalMenuCacheRepository.findByParentId(child.getId()), false, roleMenus , userDeptCd));
                }
                // 하위 리스트 표시 객체 처리
                dto = new UserDefaultPageListDTO();
                dto.setMenuId(child.getMenu().getId());
                dto.setHospitalMenuId(child.getId());
                dto.setCd(getMenuSeviceUrl(child));
                dto.setNm(getMenuNameKor(child));
                dto.setEng(getMenuNameEng(child));
                dto.setIcon(getMenuImgUrlSecond(child));
                dto.setDispOrd(child.getDispOrd());
                //
                lists.add(dto);
            }
        }
        //리스트의 갯수가 2개 이상이면 Name의 길이를 두자로 제한 시킨다.
        // 디폴트 페이지에서 표시하는 용도로 처리하도록 하기 위해서
        if(lists.size() > 2 ) {
            //
            for (UserDefaultPageListDTO dto2 : lists) {
                dto2.setNm(dto2.getNm().substring(0,2));
                dto2.setEng(dto2.getEng().substring(0,2));
            }
        }

        Collections.sort(lists, new Comparator<UserDefaultPageListDTO>() {
            @Override
            public int compare(UserDefaultPageListDTO o1, UserDefaultPageListDTO o2) {
                return o1.getDispOrd().compareTo(o2.getDispOrd());
            }
        });

        return lists;
    }

    /**
     * 영문명 가져 오기
     * @param cachedHospitalMenu
     * @return
     */
    private String getMenuNameEng(HospitalMenuCache cachedHospitalMenu) {
        Map<String, MessageCache> nameCdMap;
        String menueNameEngNm ="NULL";
        nameCdMap   = cachedHospitalMenu.getMenu().getNameCdMap();
        if( null != nameCdMap) {
            if (null != nameCdMap.get("en")) {
                menueNameEngNm = nameCdMap.get("en").getMsgContent();
            }
        }
        return menueNameEngNm;
    }
    private String getMenuNameKor(HospitalMenuCache cachedHospitalMenu) {
        Map<String, MessageCache> nameCdMap;
        String menueNameEngNm ="NULL";
        nameCdMap   = cachedHospitalMenu.getMenu().getNameCdMap();
        if( null != nameCdMap) {
            if (null != nameCdMap.get("ko")) {
                menueNameEngNm = nameCdMap.get("ko").getMsgContent();
            }
        }
        return menueNameEngNm;
    }
    private String getMenuSeviceUrl(HospitalMenuCache cachedHospitalMenu) {
        Map<String, MessageCache> nameCdMap;
        String menueNameEngNm ="/patSrh/inPatientList";
        nameCdMap   = cachedHospitalMenu.getMenu().getServiceUrlCdMap();
        if( null != nameCdMap) {
            if (null != nameCdMap.get("ko")) {
                menueNameEngNm = nameCdMap.get("ko").getMsgContent();
            }
        }
        return menueNameEngNm;
    }

    /**
     * 이미지 아이콘에 i18n_msg 의 msg_content='icon-cate-1,icon-set1-1' 로 두번째 항목이 아이콘으로 추가되어 있어 두번째 것을 리턴 하도록 한다.
     * @param cachedHospitalMenu
     * @return
     */
    private String getMenuImgUrlSecond(HospitalMenuCache cachedHospitalMenu) {
        Map<String, MessageCache> nameCdMap;
        String menueNameEngNm ="none";
        String[] aryImg = null;
        nameCdMap   = cachedHospitalMenu.getMenu().getImgUrlCdMap();
        if( null != nameCdMap) {
            if (null != nameCdMap.get("ko")) {
                aryImg = nameCdMap.get("ko").getMsgContent().split(",");
                if(aryImg.length>1){ // , 로 분산되어 서비스되고 있음
                    menueNameEngNm = aryImg[1]; // , 의 두번째것 리턴해라
                }else {
                    menueNameEngNm = nameCdMap.get("ko").getMsgContent();
                }
            }
        }
        return menueNameEngNm;
    }

}
