package com.appmonster.anchor.dataservice;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.appmonster.anchor.dto.GitResponseDto;

public interface IGitProvider {

	public ResponseEntity<List<GitResponseDto>> getAllFilesFromGit();
	
	
}
