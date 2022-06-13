package com.appmonster.anchor.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "APPLICATION_NAMES")
public class Applications {

	@Id
	@Column(name = "APP_ID")
	String appId;
	
	@Column(name = "CREATED_DATE")
	Date createdDate;
	
}
