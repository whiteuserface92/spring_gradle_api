package com.dlsdlworld.spring.api.model;


import com.dlsdlworld.spring.api.basemodel.BaseClientLog;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = Tables.ClientLog)
public class ClientLog extends BaseClientLog {



}