package com.dlsdlworld.spring.api.adminApiController;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * 그룹정보 커스텀 컨트롤러
 */
@Slf4j
@RestController
@RequestMapping("/rest")
public class CsCenterController {

//    private UserInfoForCsCenterService userInfoForCsCenter;
//
//    @Autowired
//    public CsCenterController(UserInfoForCsCenterService userInfoForCsCenter){
//        this.userInfoForCsCenter = userInfoForCsCenter;
//    }
//
//    @GetMapping(value = {"/users/csCenter"})
//    @LogPrivacyAccess(code = ActionTypes.READ, descriptions = "고객센터 사용자 조회")
//    @ResponseBody
//    public Page<CsCenterDto> getUserInfoForCsCeter(Pageable page,
//       @RequestParam(required = false, defaultValue = "") String keyword,
//       @RequestParam(required = false, defaultValue = "") String birthDay
//    ){
//
//        // keyword : 환자번호 또는 이름
//        // birthDay : 생년월일
//        Page<CsCenterDto> userInfo = userInfoForCsCenter.getUserInfo(keyword, birthDay, page);
//        return userInfo;
//    }



}
