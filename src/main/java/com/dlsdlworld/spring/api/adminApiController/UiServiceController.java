package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.dto.HospitalUiDTO;
import com.dlsdlworld.spring.api.repository.HospitalRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/rest/ui/service")
//@PreAuthorize("permitAll()")
public class UiServiceController {

    private final HospitalRepository hospitalRepository;

    /**
     * 사용자 UI에서 사용할 병원정보 조회
     * 사용가능한 병원중에서 // cd가 lemonhc가 아닌것 리스트
     * @return
     */
    @PostMapping(value = {"/hosInfoList"})
    //@PreAuthorize("permitAll()")
    public Map<String,Object> getHospitalInfoList() {

        List<HospitalUiDTO> lst = hospitalRepository.findByHospitalCdAndNmCustom();
        log.info("======== hospital info call lst size:{}", lst.size());
        Map<String,Object> m = new HashMap<String,Object>();
        m.put("body", lst);
        return m;
    }

}
