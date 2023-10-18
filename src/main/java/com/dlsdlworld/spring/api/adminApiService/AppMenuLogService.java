package com.dlsdlworld.spring.api.adminApiService;

import com.dlsdlworld.spring.api.model.AppMenuLog;
import com.dlsdlworld.spring.api.repository.AppMenuLogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppMenuLogService {

    private AppMenuLogRepository appMenuLogRepository;

    @Autowired
    public AppMenuLogService(AppMenuLogRepository apiMenuLogRepository) {
        this.appMenuLogRepository = apiMenuLogRepository;
    }

    /**
     * @param appMenuLog
     * @return
     */
    public AppMenuLog insertAppMenuLog(AppMenuLog appMenuLog) {
        //todo. 없는 값은 가져와서 넣어야 함
        return appMenuLogRepository.save(appMenuLog);
    }

}
