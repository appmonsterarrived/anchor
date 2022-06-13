package com.appmonster.anchor.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "USER_APP_MAPPING")
public class UserApplicationMapping {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AU_SEQ")
    @SequenceGenerator(sequenceName = "AU_SEQ", allocationSize = 1, name = "AU_SEQ")
	Long auId;

	@Column(name = "USER_NAME")
	String userName;
	
	@Column(name = "APP_ID")
	String appId;
	
	@Column(name = "CREATED_DATE")
	Date createdDate;
}
