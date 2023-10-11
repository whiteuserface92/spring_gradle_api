package com.dlsdlworld.spring.api.event;

import com.dlsdlworld.spring.api.cache.AppCache;
import com.dlsdlworld.spring.api.cache.AppCacheRepository;
import com.dlsdlworld.spring.api.exception.CacheOperationException;
import com.dlsdlworld.spring.api.model.App;
import com.dlsdlworld.spring.api.model.AppPlatform;
import com.dlsdlworld.spring.api.model.AppVersion;
import com.dlsdlworld.spring.api.repository.AppVersionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

/**
 */
@Slf4j
public class AppCacheListener implements ApplicationContextAware{

    private static AppVersionRepository appVersionRepository;

    private static AppCacheRepository appCacheRepository;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        AppCacheListener.appVersionRepository = applicationContext.getBean(AppVersionRepository.class);
        AppCacheListener.appCacheRepository= applicationContext.getBean(AppCacheRepository.class);
    }

    protected void saveCache(App entity) {
        Set<AppPlatform> appPlatforms = entity.getAppPlatforms();
        if(appPlatforms != null){
            for(AppPlatform appPlatform : appPlatforms){
                saveCache(appPlatform);
            }
        }

    }

    public void saveCache(AppPlatform entity){
        try{
            Optional<AppCache> appCache =  appCacheRepository.findById(entity.getId());
            if(appCache.isPresent()){
                appCacheRepository.deleteById(appCache.get().getId());
            }
            App app = entity.getApp();

            final Comparator<AppVersion> comparator = (av1, av2) ->  av1.getVersionCd().compareTo(av2.getVersionCd());

            AppVersion appVersion = null;

            if(entity.getAppVersions() != null){
                Optional<AppVersion> maxAppVersion = entity.getAppVersions().stream().max(comparator);
                if(maxAppVersion.isPresent()){
                    appVersion = maxAppVersion.get();
                }
            }

            if(appVersion == null){
                appVersion = new AppVersion();
            }

            final AppCache cache = AppCache.builder()
                    .id(entity.getId())
                    .platformType(entity.getPlatformType().name())
                    .pkgNm(entity.getPkgNm())
                    .deployType(entity.getDeployType().name())
                    .isProcessed(entity.getIosProcessed())
                    .hashKey(entity.getHashKey())
                    .storeUrl(entity.getStoreUrl())
                    .appId(app.getId())
                    .hospitalId(app.getHospital().getId())
                    .appNm(app.getAppNm())
                    .appState(app.getAppState().name())
                    .fcmKey(app.getFcmKey())
                    .fidoApiKey(app.getFidoApiKey())
                    .versionCd(appVersion.getVersionCd() == null?  app.getAppVer(): appVersion.getVersionCd())
                    .isRequired(appVersion.getRequired())
                    .multiEnabled(app.getMultiEnabled())
                    .prodCd(app.getProdCd())
                    .niceSiteCd(app.getNiceSiteCd())
                    .niceSitePwd(app.getNiceSitePwd())
                    .releaseNote(appVersion.getReleaseNote())
                    .releaseOn(appVersion.getReleasedOn()).build();

            AppCache storedCache = appCacheRepository.save(cache);

            log.trace("storedCache:{}", storedCache);
        } catch (Exception e) {
            throw new CacheOperationException(e);
        };
    }

    @Transactional
    public void deleteCaches(){
        ArrayList<AppCache> appCaches = (ArrayList<AppCache>) appCacheRepository.findAll();
        for(AppCache appCache:appCaches){
            appCacheRepository.delete(appCache);
        }
    }

    protected void deleteCache(AppPlatform entity) {
        try{
            Optional<AppCache> appCache =  appCacheRepository.findById(entity.getId());
            if(appCache.isPresent()){
                appCacheRepository.deleteById(appCache.get().getId());
            }
        }catch(Exception e){
            throw new CacheOperationException(e);
        }
    }

}
