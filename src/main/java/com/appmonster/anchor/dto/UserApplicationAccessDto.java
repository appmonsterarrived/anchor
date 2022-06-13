package com.appmonster.anchor.dto;

import java.util.List;

import lombok.Data;

@Data
public class UserApplicationAccessDto {

	
	private String applicationName;
	private List<String> files;
}
