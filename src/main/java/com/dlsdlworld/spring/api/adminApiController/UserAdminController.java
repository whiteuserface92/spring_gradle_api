package com.dlsdlworld.spring.api.adminApiController;

import com.dlsdlworld.spring.api.adminApiService.UserAdminService;
import com.dlsdlworld.spring.api.aop.LogPrivacyAccess;
import com.dlsdlworld.spring.api.dto.UpdateUserAdminDTO;
import com.dlsdlworld.spring.api.types.ActionTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 */
@Slf4j
@RestController
@RequestMapping("/rest")
public class UserAdminController {

    private UserAdminService userAdminService;

    @Autowired
    public UserAdminController(UserAdminService userAdminService) {
        this.userAdminService = userAdminService;
    }

    @LogPrivacyAccess(code = ActionTypes.UPDATE, descriptions = "관리자 회원 수정")
    @PutMapping(value = {"/updateAdmin/{id}"})
    public ResponseEntity updateAdmin(@PathVariable(name = "id") Long id, @RequestBody @Valid UpdateUserAdminDTO updateUserAdminDTO) {
        userAdminService.updateAdmin(id, updateUserAdminDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
