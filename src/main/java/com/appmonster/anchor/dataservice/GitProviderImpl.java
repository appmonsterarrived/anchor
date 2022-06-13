package com.appmonster.anchor.dataservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.appmonster.anchor.dto.GitResponseDto;


@Component
public class GitProviderImpl implements IGitProvider {

	@Autowired
	public RestTemplate template;

	@Override
	public ResponseEntity<List<GitResponseDto>> getAllFilesFromGit() {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", "application/vnd.github.v3+json");
	    headers.set("Content-Type", "application/vnd.github.v3+json");
		HttpEntity<String> entity = new HttpEntity<>(headers);
		
		return template.exchange("https://api.github.com/repos/appmonsterarrived/configrepo/contents", HttpMethod.GET,entity,
				new ParameterizedTypeReference<List<GitResponseDto>>() {});

	}

}
