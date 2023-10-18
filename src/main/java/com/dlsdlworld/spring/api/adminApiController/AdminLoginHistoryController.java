package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.model.AdminLoginHistory;
import com.dlsdlworld.spring.api.repository.AdminLoginHistoryRepository;
import com.dlsdlworld.spring.api.utils.IPCheckUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Project : lemoncare-plus-parent
 * Created by IntelliJ IDEA
 * Developer : seonguk.moon
 * Date : 2020-11-27
 * Time : 오후 2:42
 */
@Slf4j
@RestController
@RequestMapping("/rest")
public class AdminLoginHistoryController {

    private final AdminLoginHistoryRepository adminLoginHistoryRepository;

    public AdminLoginHistoryController(AdminLoginHistoryRepository adminLoginHistoryRepository) {
        this.adminLoginHistoryRepository = adminLoginHistoryRepository;
    }

    @PostMapping(value = {"/loginHistory"})
        public AdminLoginHistory insertLoginHistory(@RequestBody AdminLoginHistory param) throws Exception {

        AdminLoginHistory adminLoginHistory = new AdminLoginHistory();
        adminLoginHistory.setUserAccnt(param.getUserAccnt());
        adminLoginHistory.setSuccess(param.getSuccess());
        adminLoginHistory.setDetails(param.getDetails());
        adminLoginHistory.setIp(IPCheckUtils.getUserIp());
        //디버그 로그 추가
        log.debug("adminLoginHistory : {}", adminLoginHistory);

        return adminLoginHistoryRepository.save(adminLoginHistory);
    }



}
