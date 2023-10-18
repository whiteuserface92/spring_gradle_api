package com.dlsdlworld.spring.api.adminApiController;


import com.dlsdlworld.spring.api.cachemodel.HospitalOptionCodeCache;
import com.dlsdlworld.spring.api.cacherepository.HospitalOptionCodeCacheRepository;
import com.dlsdlworld.spring.api.dto.CommonCodeDto;
import com.dlsdlworld.spring.api.dto.HoOpCdDto;
import com.dlsdlworld.spring.api.dto.HospitalOptionCodeDto;
import com.dlsdlworld.spring.api.model.Hospital;
import com.dlsdlworld.spring.api.model.HospitalOptionCode;
import com.dlsdlworld.spring.api.param.HospitalOptionCodeParam;
import com.dlsdlworld.spring.api.param.PostHospitalOpcdParam;
import com.dlsdlworld.spring.api.repository.CommonCodeRepository;
import com.dlsdlworld.spring.api.repository.HospitalOptionCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * 병원 사이트별 옵션 코드 관리
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/rest")
public class HospitalOptionCodeController {


    private HospitalOptionCodeRepository hospitalOptionCodeRepository;
    private HospitalOptionCodeCacheRepository hospitalOptionCodeCacheRepository;
    private CommonCodeRepository commonCodeRepository;

    @Autowired
    public HospitalOptionCodeController(HospitalOptionCodeRepository hospitalOptionCodeRepository
            , CommonCodeRepository commonCodeRepository ,HospitalOptionCodeCacheRepository hospitalOptionCodeCacheRepository) {

        this.hospitalOptionCodeRepository      = hospitalOptionCodeRepository;
        this.commonCodeRepository              = commonCodeRepository;
        this.hospitalOptionCodeCacheRepository = hospitalOptionCodeCacheRepository;
    }


    @Transactional
    @GetMapping(value = "/hospitalOptionCode/findAllByHospitalOptionList")
    public Page<HospitalOptionCodeDto> findAllByKeyword(Pageable page  // 옵션 코드를 100개로 정한다.
            , @RequestParam(required = false, defaultValue = "") Long hospitalId
            , @RequestParam(required = false, defaultValue = "") String keyword) {

        log.trace("###hospitalId : {}", hospitalId);
       // log.trace("###page : {}", page);

        if (null == hospitalId) {
            log.info("### init Page : {}");
            return new PageImpl(new ArrayList<HospitalOptionCodeDto>());
        }

        Page<HospitalOptionCodeDto> ret = hospitalOptionCodeRepository.findAllByHospitalList(page, hospitalId, keyword);
        List<HospitalOptionCodeDto> lst = ret.getContent();
        for (HospitalOptionCodeDto dto : lst) {
            if (null != dto.getRefVal()) {
                List<CommonCodeDto> lstCode = commonCodeRepository.findByCodeClsDetails(dto.getRefVal());
                dto.setBaseCodes(lstCode);
            }
        }
        log.info("###ret : {}", ret.getContent());
        return ret;
    }

    /**
     * 병원별 옵션 정보 저장
     *
     * @param param
     * @return
     */
    @Transactional
    @PostMapping(value = {"/hospitalOptionCode/saveOptCode"})
    public ResponseEntity<?> saveOptCode(@RequestBody @Valid HospitalOptionCodeParam param) {
        log.info("###param : {}", param);
        HospitalOptionCode optCd = new HospitalOptionCode();

        org.springframework.beans.BeanUtils.copyProperties(param, optCd);
        //Hospital 값 매핑후  넣기
        Hospital hospital = new Hospital();
        hospital.setId(param.getHospitalId());
        optCd.setHospital(hospital);
        hospitalOptionCodeRepository.save(optCd);
        return ResponseEntity.ok().build();
    }


    /**
     * 테스트 용도 사용 하지 않음
     * @return
     */
    @Transactional
    @GetMapping(value = "/hospitalOptionCode/findOpCdCache")
    public Page<HospitalOptionCodeCache> findOpCdCache() {
        String hosptialCd ="lemonhc";
        Long  hospitalId = 1L;  //11100206
        List<HospitalOptionCodeCache> lstHoCdOpCd = this.hospitalOptionCodeCacheRepository.findByHospitalCd(hosptialCd);
        List<HospitalOptionCodeCache> lstHoIdOpCd = this.hospitalOptionCodeCacheRepository.findByHospitalCd("11100206");

        ArrayList aryLst = new ArrayList();
        aryLst.add(lstHoCdOpCd);
        aryLst.add(lstHoIdOpCd);
        Page<HospitalOptionCodeCache> pgRet =  new  PageImpl(aryLst ) ;
        return pgRet;
    }

    /**
     * HospitalCd로 한 병원 전체 옵션 값 조회
     * 클라이언트에서 로그인 후 한 병원에서 사용하는 병원옵션 코드 내려서 로컬 스토리지에 넣어서 사용
     * @param param
     * @return
     */
    @Transactional
    @PostMapping(value = "/hospitalOptionCode/findOpCdCacheByHoCd")
    public String findOpCdCacheByHoCd(
            @RequestBody @Valid PostHospitalOpcdParam param
    ) {

        List<HospitalOptionCodeCache> lstHoCdOpCd = this.hospitalOptionCodeCacheRepository.findByHospitalCd(param.getHospitalCd());

        String retStr = getHoOpCdString(lstHoCdOpCd);

        return retStr;
    }

    private String getHoOpCdString(List<HospitalOptionCodeCache> lstHoCdOpCd) {

        StringBuffer sb = new StringBuffer();
        int nLength = lstHoCdOpCd.size();
        sb.append("{");
        int i =0;
        for ( HospitalOptionCodeCache cache : lstHoCdOpCd){
            i++;
            sb.append("\"").append(cache.getOptCd()).append("\": \"")
                           .append(cache.getOptVal()).append("\"");
            if( i< nLength) {
                sb.append(",");
            }
        }
        sb.append("}");
        return sb.toString();
    }
    /**
     *  HospitalId로 한 병원 전체 옵션 값 조회
     * @param param
     * @return
     */
    @Transactional
    @PostMapping(value = "/hospitalOptionCode/findOpCdCacheByHoId")
    public List<HoOpCdDto> findOpCdCacheByHoId(
            @RequestBody @Valid PostHospitalOpcdParam param
    ) {
        List<HospitalOptionCodeCache> lstHoCdOpCd = this.hospitalOptionCodeCacheRepository.findByHospitalId(param.getHospitalId());
        List retLst = getHoOpCdList(lstHoCdOpCd);
        return retLst;
    }
    @NotNull
    private List getHoOpCdList(List<HospitalOptionCodeCache> lstHoCdOpCd) {
        List retLst = new ArrayList<HoOpCdDto>();
        HoOpCdDto dto = new HoOpCdDto();
        for ( HospitalOptionCodeCache cache : lstHoCdOpCd){
            dto = new HoOpCdDto();
            dto.setOpCd(cache.getOptCd());
            dto.setOpVal(cache.getOptVal());
            retLst.add(dto);
        }
        return retLst;
    }
    /**
     *  HospitalCd와 OptCd로 옵션 값 한개 조회
     * @param param
     * @return
     */
    @Transactional
    @PostMapping(value = "/hospitalOptionCode/findOpCdCacheByHoCdOne")
    public List<HoOpCdDto> findOpCdCacheByHoCdOne(
            @RequestBody @Valid PostHospitalOpcdParam param
    ) {
        List<HospitalOptionCodeCache> lstHoCdOpCd = this.hospitalOptionCodeCacheRepository.findByHospitalCdAndOptCd(param.getHospitalCd(), param.getOpCd());
        List retLst = getHoOpCdList(lstHoCdOpCd);
        return retLst;
    }
    /**
     *  HospitalId와 OptCd로 옵션 값 한개 조회
     * @param param
     * @return
     */
    @Transactional
    @PostMapping(value = "/hospitalOptionCode/findOpCdCacheByHoIdOne")
    public List<HoOpCdDto> findOpCdCacheByHoIdOne(
            @RequestBody @Valid PostHospitalOpcdParam param
    ) {
        List<HospitalOptionCodeCache> lstHoCdOpCd = this.hospitalOptionCodeCacheRepository.findByHospitalIdAndOptCd(param.getHospitalId(), param.getOpCd());
        List retLst = getHoOpCdList(lstHoCdOpCd);
        return retLst;
    }
}
