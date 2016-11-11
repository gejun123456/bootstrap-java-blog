package com.rest.controller;

import com.google.common.collect.Lists;
import com.rest.storage.StorageFileNotFoundException;
import com.rest.storage.StorageService;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by bruce.ge on 2016/11/9.
 */
@Controller
public class UploadImageController {
    private final StorageService storageService;

    @Autowired
    public UploadImageController(StorageService storageService){
        this.storageService = storageService;
    }

    @GetMapping("/listFile")
    public String listuploadFile(Model model) throws IOException{
        List<String>  urls = Lists.newArrayList();
        for(String file: storageService.loadAll()){
            urls.add(MvcUriComponentsBuilder.fromMethodName(UploadImageController.class,"serveFile",file).build().toString());
        }
        model.addAttribute("files",urls);

        return "uploadForm";
    }


    @GetMapping("/files/{fileName:.+}")
    @ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String fileName){
        Resource resource = storageService.loadAsResource(fileName);
        //remove the header let browser view it.
        return ResponseEntity.ok().body(resource);
//                header(HttpHeaders.CONTENT_DISPOSITION,"attachment; filename=\""+resource.getFilename()+"\"")
    }


    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file")MultipartFile file, RedirectAttributes redirectAttributes){
        storageService.store(file);
        redirectAttributes.addFlashAttribute("message","You successfully uploaded "+file.getOriginalFilename() + "!");
        return "redirect:/listFile";
    }


    @PostMapping("/uploadImage")
    @ResponseBody
    public String uploadImage(@RequestParam("image_file")MultipartFile file, HttpServletRequest request){
        String newName = String.valueOf(System.currentTimeMillis())+file.getOriginalFilename();
        storageService.storeFileWithName(file,newName);
        return "http://"+request.getHeader("Host")
                +"/files/"+newName;
    }

    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity handleStorageFileNotFound(StorageFileNotFoundException e){
        return ResponseEntity.notFound().build();
    }
}
