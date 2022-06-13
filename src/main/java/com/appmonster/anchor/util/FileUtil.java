package com.appmonster.anchor.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.appmonster.anchor.dto.GitResponseDto;

@Component
public class FileUtil {

	public List<String> getFile(GitResponseDto dto, String appId){
		List<String> files = new ArrayList<String>();
		if(dto.getName().contains(appId)) {
			files.add(dto.getName());
		}
		return files;
		
	}
	
	public String[] split(String input, char ch, int n) {
	    if (n <= 0)
	        throw new IllegalArgumentException("n must be >0");
	    Matcher m = Pattern.compile("(?:[^" + ch + "]*" + ch + "){" + n + "}").matcher(input);
	    if (! m.find())
	        return new String[] { input };
	    return new String[] { input.substring(0, m.end() - 1),
	                          input.substring(m.end()) };
	}
	
}
