package com.dlsdlworld.spring.api.basemodel;

import com.dlsdlworld.spring.api.types.BoardTypes;
import com.dlsdlworld.spring.api.types.QNATypes;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public abstract class BaseBoardPost extends BaseModifiable {

    @Enumerated(EnumType.STRING)
    @Column(length = Columns.postType, nullable = false)
    private BoardTypes postType;

    @Column(nullable = false)
    private LocalDateTime startedOn;

    @Column(nullable = false)
    private LocalDateTime endedOn;

    @Column(length = Columns.postTitle, nullable = false)
    private String postTitle;

    @Lob
    @Type(type = "org.hibernate.type.TextType")
    @Column
    private String postContent;

    @Column(nullable = false)
    //@Size(min = 1, max = 5)
    private Short score;

    @Enumerated(EnumType.STRING)
    @Column(length = Columns.qnaType)
    private QNATypes qnaType;

    @Column(length = Columns.faqCls)
    private String faqCls;

    @Column(length = Columns.dispOrd)
    private String dispOrd;

}
