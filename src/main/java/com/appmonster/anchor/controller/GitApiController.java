package com.appmonster.anchor.controller;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/git")
@Slf4j
public class GitApiController {

    @GetMapping("/getFiles")
    public String getFiles() {
        String jsonToReturn = "[{\"applicationName\":\"client1\",\"files\":[\"webhooktriggerfile.txt\",\"webhooktriggerfile.txt\",\"filetocreate.txt\",\"mynewfile.properties\"]},{\"applicationName\":\"client2\",\"files\":[\"filetocreate.txt\",\"filetocreate1.txt\"]}]";
        JSONArray array = null;
        try {
            array = new JSONArray(jsonToReturn);
        }catch (JSONException err){
            log.error("Error when converting to JSON Element - {}",err.getMessage());
        }
        return array.toString();
    }


    /*
  public repoFileInfo:Array<any> = [
    { applicationName: "client1",files:["webhooktriggerfile.txt"]},
    { applicationName: "client2",files:["filetocreate.txt","filetocreate1.txt"]}
  ]
  */

}
