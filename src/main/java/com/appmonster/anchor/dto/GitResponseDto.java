package com.appmonster.anchor.dto;

import lombok.Data;

@Data
public class GitResponseDto {

	private String name ;
	private String path;
	private String sha;
	private int size;
	private String url;
	private String html_url;
	private String git_url;
	private String download_url;
	private String type;
	private Links links;
	
}
