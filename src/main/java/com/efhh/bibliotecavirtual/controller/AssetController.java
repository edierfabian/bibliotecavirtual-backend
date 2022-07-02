package com.efhh.bibliotecavirtual.controller;


import com.efhh.bibliotecavirtual.service.impl.FileSystemStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/assets")
public class AssetController {

    @Autowired
    private FileSystemStorageService fileSystemStorageService;

    @PostMapping("upload")
    public Map<String,String> upload(@RequestParam MultipartFile file){
        String filename= fileSystemStorageService.store(file);
        Map<String, String> result=new HashMap<>();
        result.put("filename",filename);
        return result;
    }

    @GetMapping("{filename}")
    public ResponseEntity<Resource> load(@PathVariable String filename) throws IOException {
        Resource resource= fileSystemStorageService.loadAsResource(filename);
        String contenType= Files.probeContentType(resource.getFile().toPath());

        return  ResponseEntity.ok()
                .header("Content-Type",contenType)
                .body(resource);
    }

}
