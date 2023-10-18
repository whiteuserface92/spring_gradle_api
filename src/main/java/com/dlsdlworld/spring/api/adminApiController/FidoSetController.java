package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.adminApiService.FidoSetService;
import com.dlsdlworld.spring.api.dto.FidoHospitalDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 그룹정보 커스텀 컨트롤러
 */
@Slf4j
@RestController
public class FidoSetController {

    private FidoSetService fidoSetService;

    @Autowired
    public FidoSetController(FidoSetService fidoSetService) {
        this.fidoSetService = fidoSetService;
    }

    @Transactional
//    @LogAdminExecution(code = ActionTypes.READ, descriptions = "FIDO 정보 조회")
    @GetMapping(value = {"/rest/fidoSets/findAllByKeyword"})
    public Page<FidoHospitalDto> findAllByKeyword(Pageable page, @RequestParam(name = "hospitalId", required = false) Long hospitalId) {
        return fidoSetService.findAllByKeyword(hospitalId, page);
    }

}
