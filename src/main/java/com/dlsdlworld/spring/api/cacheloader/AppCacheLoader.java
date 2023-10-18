package com.dlsdlworld.spring.api.cacheloader;

import com.dlsdlworld.spring.api.event.AppPlatformListener;
import com.dlsdlworld.spring.api.model.AppPlatform;
import com.dlsdlworld.spring.api.repository.AppPlatformRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 */
@Slf4j
@Service
public class AppCacheLoader {

    private AppPlatformRepository appPlatformRepository;
    private AppPlatformListener appPlatformListener;

    @Autowired
    public AppCacheLoader(AppPlatformRepository appPlatformRepository, AppPlatformListener appPlatformListener) {
        this.appPlatformRepository = appPlatformRepository;
        this.appPlatformListener = appPlatformListener;
    }

    /**
     * 앱 캐시 리로딩
     */
    public void load(){

        Set<AppPlatform> appPlatformSet = appPlatformRepository.findAll();
        for(AppPlatform appPlatform : appPlatformSet){
            try{
                appPlatformListener.saveCache(appPlatform);
            }catch(Exception e){
              log.error(e.getMessage());
            }
        }
    }

}
