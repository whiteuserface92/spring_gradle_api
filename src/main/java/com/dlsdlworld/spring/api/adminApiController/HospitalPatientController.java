package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.exception.EntityNotFoundException;
import com.dlsdlworld.spring.api.model.Hospital;
import com.dlsdlworld.spring.api.model.User;
import com.dlsdlworld.spring.api.model.UserPatient;
import com.dlsdlworld.spring.api.param.ReqApiInfoParam;
import com.dlsdlworld.spring.api.param.ReqHospitalInfoParam;
import com.dlsdlworld.spring.api.repository.*;
import com.dlsdlworld.spring.api.service.ApiService;
import com.dlsdlworld.spring.api.service.SecurityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Validated
@RestController
public class HospitalPatientController {

    @Resource
    private RestTemplate restTemplate;

    private HospitalRepository hospitalRepository;
    private UserPatientRepository userPatientRepository;
    private UserRepository userRepository;

    @Autowired
    public HospitalPatientController(
            HospitalRepository hospitalRepository,
            GroupHospitalRepository groupHospitalRepository,
            HospitalMenuRepository hospitalMenuRepository,
            GroupRepository groupRepository,
            SecurityService securityService,
            UserPatientRepository userPatientRepository,
            UserRepository userRepository,
            ApiService apiService
    ) {
        this.hospitalRepository = hospitalRepository;
        this.userPatientRepository = userPatientRepository;
        this.userRepository = userRepository;
    }

    /**
     * 병원정보와 환자번호를 가져와서 등록한다.
     * @param param
     * @return
     */
    @Transactional
    @PostMapping(value={"/rest/setPatientHospital"})
    public ResponseEntity<Map<String, Object>> setPatientHospital(@RequestBody @Valid List<ReqHospitalInfoParam> param) {

        Map<String, Object> resMap = new HashMap<>();
        Map<String, Object> body = null;
        Long userId = 0L;

        //병원별 유저정보가 존재하면 환자정보를 가져온다.
        if (param != null)
        {
            List<UserPatient> userPatients = new ArrayList<>();

            for(ReqHospitalInfoParam resHospitalInfo : param) {

                User user = userRepository.findById(resHospitalInfo.getUserId())
                        .orElseThrow(() -> new EntityNotFoundException("User does not exist.", resHospitalInfo.getUserId()));

                userId = user.getId();

                log.info("UserId : " +userId);

                log.debug(resHospitalInfo.getUserId().toString());
                log.debug(resHospitalInfo.getHospitalCd());
                log.debug(resHospitalInfo.getPatientId());

                String patientId = resHospitalInfo.getPatientId();

                final Hospital hospital = hospitalRepository.findByHospitalCd(resHospitalInfo.getHospitalCd());
                final UserPatient userPatient = new UserPatient();
                userPatient.setHospital(hospital);
                userPatient.setUser(user);
                userPatient.setPatientNo(patientId);
                userPatient.setPatientRel("10");
                userPatient.setPatientNm(user.getUserNm());
                userPatient.setAgreedOn(LocalDateTime.now());
                userPatient.setEncAlgo("N/A");
                userPatient.setCreatedOn(LocalDateTime.now());
                userPatients.add(userPatient);

            }

            // 환자정보가 존재하면 환자를 삭제후 등록한다.
            if(userPatients != null || !userId.equals(0L)) {
                // 테스트유저 삭제안되도록 막음!!!
                if(userId > 1) {
                    //기존정보 삭제
                    userPatientRepository.deleteByUserId(userId);

                    //신규정보 추가
                    userPatientRepository.saveAll(userPatients);

                    resMap.put("result", true);
                    return new ResponseEntity<>(resMap, HttpStatus.OK);
                }
            }
        }

        resMap.put("result", false);
        return new ResponseEntity<>(resMap, HttpStatus.OK);
    }

    /**
     * 병원정보와 환자번호를 가져와서 등록한다.
     * @param param
     * @return
     */
    @Transactional
    @PostMapping(value={"/rest/deletePatientHospital"})
    public ResponseEntity<Map<String, Object>> delPatientHospital(@RequestBody @Valid List<ReqHospitalInfoParam> param) {

        Map<String, Object> resMap = new HashMap<>();
        Map<String, Object> body = null;
        Long userId = 0L;

        //병원별 유저정보가 존재하면 환자정보를 가져온다.
        if (param != null)
        {
            List<UserPatient> userPatients = new ArrayList<>();

            for(ReqHospitalInfoParam resHospitalInfo : param) {
                // 1. 병원정보를 가져옴
                Hospital hospital = hospitalRepository.findByHospitalCd(resHospitalInfo.getHospitalCd());
                // 2. 병원정보와 환자번호 아이디를 기준으로 환자 테이블을 삭제한다.
                userPatientRepository.deleteByUserIdAndPatientId(resHospitalInfo.getUserId(), resHospitalInfo.getPatientId(), hospital.getId());
            }

            resMap.put("result", true);
            return new ResponseEntity<>(resMap, HttpStatus.OK);
        }

        resMap.put("result", false);
        return new ResponseEntity<>(resMap, HttpStatus.OK);
    }

    @PostMapping(value={"/rest/callApiService"})
    public String CallRestApi(@RequestBody @Valid ReqApiInfoParam param) {

        try {

        String url = param.getUrl();
        HttpMethod method = param.getMethod();
        Map<String, Object> paramMap = param.getParamMap();

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON_UTF8));
        String restUrl = param.getUrl();
        HttpEntity requestEntity = null;
        if (method == HttpMethod.GET || method == HttpMethod.DELETE) {
            if (!paramMap.isEmpty()) {
                MultiValueMap<String, String> multiValueMap = new LinkedMultiValueMap<>();
                for (Map.Entry<String, Object> entry : paramMap.entrySet())
                    multiValueMap.add(entry.getKey(), entry.getValue().toString());

                restUrl = UriComponentsBuilder.fromUriString(url)
                        .queryParams(multiValueMap)
                        .toUriString();
                requestEntity = new HttpEntity(null, headers);
            }
        } else if (method == HttpMethod.POST || method == HttpMethod.PUT) {
            final String json = new ObjectMapper().writeValueAsString(paramMap);
            requestEntity = new HttpEntity(json, headers);
        }

        log.trace("restUrl:{}, method:{}, request:{}", restUrl, method, requestEntity);
        ResponseEntity<String> responseEntity = restTemplate.exchange(restUrl, method, requestEntity, String.class);

        return responseEntity.getBody();

        } catch (Exception ex) {
            return ex.getMessage();
        }

    }
}


