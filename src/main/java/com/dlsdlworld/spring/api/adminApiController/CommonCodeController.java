package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.dto.CommonCodeDto;
import com.dlsdlworld.spring.api.repository.CommonCodeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@Slf4j
@Validated
@RestController
@RequestMapping("/rest")
public class CommonCodeController {

    private final CommonCodeRepository commonCodeRepository;

    @Autowired
    public CommonCodeController( CommonCodeRepository commonCodeRepository) {
        this.commonCodeRepository = commonCodeRepository;
    }

    @Transactional
    @GetMapping(value = "/commonCodes/codeClses")
    public ResponseEntity<Set<String>> getCodeClses() {
        return new ResponseEntity<Set<String>>(commonCodeRepository.findDistinctCodeCls(), HttpStatus.OK);
    }

    @Transactional
    @GetMapping(value = "/commonCodes/findByCodeClsDetailList")
    public Page<CommonCodeDto> findByCodeClsDetailList(@RequestParam String codeCls) {
        List<CommonCodeDto> lst = commonCodeRepository.findByCodeClsDetailList(codeCls);
        Pageable page = PageRequest.of(0,10);
        Page pages = new PageImpl<CommonCodeDto>(lst, page, 100);
        return pages;
    }


}
