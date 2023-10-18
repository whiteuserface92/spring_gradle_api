package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.aop.LogAdminExecution;
import com.dlsdlworld.spring.api.dto.GetPushDetailDto;
import com.dlsdlworld.spring.api.dto.GetPushListDto;
import com.dlsdlworld.spring.api.param.GetPushDetailParam;
import com.dlsdlworld.spring.api.param.GetPushListParam;
import com.dlsdlworld.spring.api.repository.PushDetailRepository;
import com.dlsdlworld.spring.api.repository.PushListRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Validated
@RestController
@RequestMapping("/rest")
public class PushListAndDetailController {

    @Autowired
    PushListRepository pushListRepository;

    @Autowired
    PushDetailRepository pushDetailRepository;

    Map<String, String> errorsList = new HashMap<>();



    @Transactional
    @LogAdminExecution(descriptions = "Push Detail 조회")
    @PostMapping(value = "/PushListAndDetail/PostPushDetail")
    public GetPushDetailDto getPushDetail(@RequestBody GetPushDetailParam getPushDetailParam) {
        GetPushDetailDto getPushDetailDto = new GetPushDetailDto();
        try{
            getPushDetailDto = pushDetailRepository.getPushDetail(getPushDetailParam);

            if(getPushDetailDto.getPushRequestId() == 0 || getPushDetailDto.getUserId() == 0){
                getPushDetailDto.setStatus("Search fail");
            } else {
                getPushDetailDto.setStatus("Search Success");
            }
        } catch(Exception e){
            log.error(e.getLocalizedMessage());
        }
            return getPushDetailDto;
    }

    @Transactional
    @LogAdminExecution(descriptions = "Push List 조회")
    @PostMapping(value = "/PushListAndDetail/PostPushList")
    public List<GetPushListDto> getPushList (@RequestBody GetPushListParam getPushListParam){
        return pushListRepository.getPushListByUserId(getPushListParam.getUserId());
    }

        Map<String, String> errorList = new HashMap<>();

        @ExceptionHandler(Exception.class)
        public Map<String, String> exceptionHandler(Exception e){
            log.error(e.getLocalizedMessage());
            String errmsg = "Search fail";
            errorList.put("status",errmsg);
            return errorList;
        }

}

