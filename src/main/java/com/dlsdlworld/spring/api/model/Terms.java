package com.dlsdlworld.spring.api.model;

/**
 */

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "terms_def")
public class Terms extends BaseTerms {

    /**
     * 사용자약관동의
     */
    @OneToMany(mappedBy = "terms")
    private Set<UserAgrmnt> userAgrmnts;

}
