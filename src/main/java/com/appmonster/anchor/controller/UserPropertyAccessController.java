package com.appmonster.anchor.controller;

import com.appmonster.anchor.dataservice.IGitProvider;
import com.appmonster.anchor.dto.GitResponseDto;
import com.appmonster.anchor.dto.UserApplicationAccessDto;
import com.appmonster.anchor.exception.UserAccessNotFound;
import com.appmonster.anchor.repos.UserAppMappingRepository;
import com.appmonster.anchor.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/anchor")
public class UserPropertyAccessController {

	@Autowired
	private IGitProvider githubProvider;

	@Autowired
	private UserAppMappingRepository userAppMappingDataService;
	
	@Autowired
	private FileUtil fileUtil;

	@SuppressWarnings("unchecked")
	@GetMapping("/applicationProperties")
	public ResponseEntity<List<UserApplicationAccessDto>> getAllGitPropertyFiles(Principal principal) throws Exception {
		List<GitResponseDto> gitRepos = null;
		List<UserApplicationAccessDto> userAcessDto = new ArrayList<>();
		List<String> appIds = userAppMappingDataService.fetchUserAppMapping(principal.getName());
		Map<String, List<String>> accessMap = new HashMap<>();
		List<String> files = new ArrayList<String>();
		
		if(appIds.isEmpty()) {
			throw new Exception("No User Access Found");
		}
		
		ResponseEntity<List<GitResponseDto>> responseGitRepos = githubProvider.getAllFilesFromGit();
		List<GitResponseDto> getResponseDto = responseGitRepos.getBody();
		gitRepos = getResponseDto.stream().filter(f -> appIds.stream().anyMatch(a-> f.getName().contains(a))).collect(Collectors.toList());

		for(GitResponseDto dto : gitRepos) {

			if (dto.getName().contains("-")) {
				String[] splitArray = dto.getName().split("-");
				if (accessMap.get(splitArray[0]) == null) {
					List<String> filess = fileUtil.getFile(dto, splitArray[0]);
					accessMap.put(splitArray[0], filess);

				} else {
					accessMap.get(splitArray[0]).add(dto.getName());
				}
			} else {
				String[] defaultProperty = dto.getName().split("\\.");
				if (accessMap.get(defaultProperty[0]) == null) {
					List<String> filess = fileUtil.getFile(dto, defaultProperty[0]);
					accessMap.put(defaultProperty[0], filess);
				} else {
					accessMap.get(defaultProperty[0]).add(dto.getName());
				}
			}
		}
		
		 for (Entry<String, List<String>> entry : accessMap.entrySet()) {
		        UserApplicationAccessDto dto = new UserApplicationAccessDto();
		        dto.setApplicationName(entry.getKey());
		        dto.setFiles(entry.getValue());
		        userAcessDto.add(dto);
		    }
		
		return new ResponseEntity<>(userAcessDto, HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/applicationProperties/{file}")
	public ResponseEntity<GitResponseDto> getPropertyFile(Principal principal, @PathVariable String file) throws Exception {
		List<GitResponseDto> gitRepos = null;
		GitResponseDto matchedResponse = null;
		List<String> appIds = userAppMappingDataService.fetchUserAppMapping(principal.getName());
		
		if(appIds.isEmpty()) {
			throw new UserAccessNotFound();
		}
		
		ResponseEntity<List<GitResponseDto>> responseGitRepos = githubProvider.getAllFilesFromGit();
		List<GitResponseDto> getResponseDto = responseGitRepos.getBody();
		gitRepos = getResponseDto.stream().filter(f -> appIds.stream().anyMatch(a-> f.getName().contains(a))).collect(Collectors.toList());
		
		Optional<GitResponseDto> findAnyResponseMatchedByName=gitRepos.stream().filter(f-> f.getName().equals(file)).findAny();
		
		if(findAnyResponseMatchedByName.isPresent()) {
			matchedResponse=  findAnyResponseMatchedByName.get();
		}else {
			throw new UserAccessNotFound();
		}
		return new ResponseEntity<>(matchedResponse, HttpStatus.OK);
	}

}
