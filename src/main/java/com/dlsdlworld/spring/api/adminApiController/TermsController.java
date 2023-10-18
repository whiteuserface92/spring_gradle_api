package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.param.TermsParam;
import com.dlsdlworld.spring.api.projection.TermsProjection;
import com.dlsdlworld.spring.api.repository.TermsRepository;
import com.google.common.collect.ImmutableMap;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 */
@Slf4j
@RequiredArgsConstructor
@RestController
public class TermsController {

    private final TermsRepository termsRepository;

    /**
     * 약관조회 (파라미터에 해당하는 최신 약관)
     * @param param
     * @return
     */
    @PostMapping(value = {"/terms"})
    public List<TermsProjection> getTerms(@RequestBody @Valid TermsParam param) {
        return termsRepository.findByTerms(param.getTermsOwnerCds());
    }

    /***
     * 약관 조회 표준 "content"하위에 조회
     * @param param
     * @return
     */
    @PostMapping(value = {"/termscontent"})
    @ResponseBody
    public Map<String, Object>  getTermsContent(@RequestBody @Valid TermsParam param) {
        List<TermsProjection>  lst = termsRepository.findByTerms(param.getTermsOwnerCds()) ;

        return ImmutableMap.of("content", lst);
    }
}
