package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.adminApiService.UserMenuService;
import com.dlsdlworld.spring.api.param.PostUserMenuParam;
import com.dlsdlworld.spring.api.types.MenuTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@Slf4j
@RestController
@RequestMapping("/rest")
public class UserMenuController {

    private UserMenuService userMenuService;
    @Autowired
    public UserMenuController( UserMenuService userMenuService ) {
        this.userMenuService = userMenuService;
    }

    /**
     * <p>
     *   사용자 즐겨 찾기 등록
     * </p>
     * @param param
     * @return
     */
    @PostMapping(value = {"/userMenus"})
    public ResponseEntity postUserMenu(@RequestBody @Valid PostUserMenuParam param) {
        log.info("사용자 즐겨잧기 등록 시작");

        log.trace("postUserMenu : {}", param);

        URI uri = this.userMenuService.postUserMenus(param);

        log.info("사용자 즐겨잧기 등록 끝");

        return ResponseEntity.created(uri).build();
    }

    /**
     * <p>
     *   사용자 즐겨찾기 메뉴 트리 조회
     * </p>
     * @param userId 사용자 아이디
     * @param menuType 메뉴 타입
     * @return
     * @throws Exception
     */
    @GetMapping(value={"/userMenus"})
    public String getUserMenus(@RequestParam(name = "userId") Long userId,
                               @RequestParam(name = "menuType", required = true) MenuTypes menuType){
        log.info("사용자 즐겨찾기 메뉴 조회 시작");

        String ret = userMenuService.getUserMenus(userId, menuType);

        log.info("사용자 즐겨찾기 메뉴 조회 끝");
        return ret;
    }

    @DeleteMapping(value={"/userMenus/{userMenuId}"})
    public ResponseEntity deleteUserMenus(@PathVariable Long userMenuId){
        log.info("사용자 즐겨찾기 메뉴 삭제 시작");
         userMenuService.deleteUserMenus(userMenuId);
        log.info("사용자 즐겨찾기 메뉴 삭제 끝");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 병원 기본정보 조회 호출
     * 이전 병원 통합용 api , 일산병원, 한양대, 을지대 병원 3개 병원 지원용
     * @return
     * @throws Exception
     */
    @GetMapping(value={"/userMenus/{id}"})
    public String getUserMenusOld(@PathVariable(name = "id") Long id,
                               @RequestParam(name = "menuType") MenuTypes menuType) throws Exception {
        return userMenuService.getUserMenus(id, menuType);
    }
}
