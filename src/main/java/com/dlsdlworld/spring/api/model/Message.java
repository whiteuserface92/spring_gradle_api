package com.dlsdlworld.spring.api.model;

import com.dlsdlworld.spring.api.basemodel.BaseMessage;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 */
@Getter
@Setter
@Entity
@Table(name = Tables.Message)
public class Message extends BaseMessage {
}
