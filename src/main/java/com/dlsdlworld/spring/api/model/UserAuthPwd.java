package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseUserAuth;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 */
@Getter
@Setter
@Entity
@Immutable
@Table(name = Tables.UserAuth)
@SecondaryTables({
        @SecondaryTable(name=Tables.AuthPwd, pkJoinColumns = @PrimaryKeyJoinColumn(name="user_auth_id", referencedColumnName= "id")),
})
public class UserAuthPwd extends BaseUserAuth {



    @Column(name="user_id", table=Tables.UserAuth)
    private Long userId;

    @Column(table=Tables.AuthPwd)
    private String userAccnt;


    @Column(table=Tables.AuthPwd)
    private String hospitalCd;

    @Column(table=Tables.AuthPwd)
    private LocalDateTime pwdChangedOn;


}
