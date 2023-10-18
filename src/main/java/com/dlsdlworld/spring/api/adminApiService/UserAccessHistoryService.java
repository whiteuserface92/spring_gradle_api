package com.dlsdlworld.spring.api.adminApiService;

import com.dlsdlworld.spring.api.dto.InsertUserAccessHistoryResult;
import com.dlsdlworld.spring.api.model.User;
import com.dlsdlworld.spring.api.param.UserAccessHistoryParam;
import com.dlsdlworld.spring.api.repository.UserAccessHistoryRepository;
import com.dlsdlworld.spring.api.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserAccessHistoryService {

    @Autowired
    UserAccessHistoryRepository userAccessHistoryRepository;

    @Autowired
    UserRepository userRepository;

    String result = "";

    public List<UserAccessHistoryParam> getUserAccessHistory(UserAccessHistoryParam userAccessHistoryParam){
        return userAccessHistoryRepository.getUserAccessHistoryList(userAccessHistoryParam);
    }

    public InsertUserAccessHistoryResult insertUserAccessHistory(UserAccessHistoryParam userAccessHistoryParam){

        InsertUserAccessHistoryResult insertUserAccessHistoryResult = new InsertUserAccessHistoryResult();

        if(userAccessHistoryParam.getUserId() == null && userAccessHistoryParam.getMyCi() == null){
            insertUserAccessHistoryResult.setCode(403);
            insertUserAccessHistoryResult.setMessage("insertUserAccessHistory failed");
            return insertUserAccessHistoryResult;
        }
        //myCi값만 있을경우 해당 myCi 조회 후 없을 시 return 403
        if(userAccessHistoryParam.getUserId() == null && userAccessHistoryParam.getMyCi() != null){
            Long myCiUserId = this.userRepository.findByMyCi(userAccessHistoryParam.getMyCi());
            log.info("myCi만 존재합니다. {}, {}",userAccessHistoryParam.getMyCi());
            log.info("myCi로 조회한 userId : {}",myCiUserId);

            userAccessHistoryParam.setUserId(myCiUserId);

            if(myCiUserId == null){
                insertUserAccessHistoryResult.setCode(403);
                insertUserAccessHistoryResult.setMessage("insertUserAccessHistory failed");
                return insertUserAccessHistoryResult;
            }
        }
        //userId값만 있을경우 해당 userId 조회 후 없을 시 return 403
        if(userAccessHistoryParam.getMyCi() == null && userAccessHistoryParam.getUserId() != null){
            log.info("userId만 존재합니다. {}, {}",userAccessHistoryParam.getUserId());
            //userId에 대한 값을 user_mst 테이블에 조회하여, 없을 시 return 403
            Optional<User> searchUserIdResult = this.userRepository.findById(userAccessHistoryParam.getUserId());
            String getMyCiResult = "";
            if(!searchUserIdResult.isPresent()){
                insertUserAccessHistoryResult.setCode(403);
                insertUserAccessHistoryResult.setMessage("insertUserAccessHistory failed");
                return insertUserAccessHistoryResult;
            } else {
                getMyCiResult = searchUserIdResult.get().getMyCi();
            }
            userAccessHistoryParam.setMyCi(getMyCiResult);
        }

        //userId와 myCi가 둘다 존재할 경우 비교 후 같은 아이디가 아닐 시 failed
        if(userAccessHistoryParam.getMyCi() != null && userAccessHistoryParam.getUserId() != null){

            log.info("userId와 myCi를 비교 {}, {}",userAccessHistoryParam.getUserId(),userAccessHistoryParam.getMyCi());

            Optional<User> searchUserIdResult = this.userRepository.findById(userAccessHistoryParam.getUserId());
            Long myCiUserId = this.userRepository.findByMyCi(userAccessHistoryParam.getMyCi());

            if(searchUserIdResult.get().getId() != myCiUserId){
                    insertUserAccessHistoryResult.setCode(403);
                    insertUserAccessHistoryResult.setMessage("insertUserAccessHistory failed");
                    return insertUserAccessHistoryResult;
            }
        }

        result = "";

        //userId와 myCi값이 두개 다 있을 시 userId값과 myCi값이 같은지 확인 후 틀릴 시 403 일치 시 진행

        result = userAccessHistoryRepository.insertUserAccessHistory(userAccessHistoryParam);


        if(result.contains("성공")){
            insertUserAccessHistoryResult.setCode(200);
            insertUserAccessHistoryResult.setMessage("insertUserAccessHistory successed");
        } else {
            insertUserAccessHistoryResult.setCode(403);
            insertUserAccessHistoryResult.setMessage("insertUserAccessHistory failed");
        }

        return insertUserAccessHistoryResult;
    }
}
