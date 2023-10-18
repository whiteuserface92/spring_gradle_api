package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.adminApiService.UserAccessHistoryService;
import com.dlsdlworld.spring.api.dto.InsertUserAccessHistoryResult;
import com.dlsdlworld.spring.api.param.UserAccessHistoryParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/rest")
public class UserAccessHistoryController {

    @Autowired
    UserAccessHistoryService userAccessHistoryService;

    @PostMapping(value = {"/getUserAccessHistory"})
    public List<UserAccessHistoryParam> getUserAccessHistory(@RequestBody UserAccessHistoryParam userAccessHistoryParam){
        return userAccessHistoryService.getUserAccessHistory(userAccessHistoryParam);
    }

    @PostMapping(value = {"/insertUserAccessHistory"})
    public InsertUserAccessHistoryResult insertUserAccessHistory(@RequestBody UserAccessHistoryParam userAccessHistoryParam){
        InsertUserAccessHistoryResult result = userAccessHistoryService.insertUserAccessHistory(userAccessHistoryParam);


        return result;
    }
}
