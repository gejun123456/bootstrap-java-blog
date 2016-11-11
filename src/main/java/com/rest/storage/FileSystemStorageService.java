package com.rest.storage;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;

/**
 * Created by bruce.ge on 2016/11/9.
 */
@Service
public class FileSystemStorageService implements StorageService{

    private final Path rootLocation;

    @Autowired
    public FileSystemStorageService(StorageProperties properties){
        this.rootLocation = Paths.get(properties.getLocation());
    }
    @Override
    public void init() {
        try{
            Files.createDirectories(rootLocation);
        }catch (IOException e){
            throw new StorageExceptioin("Could not initialize storage",e);
        }
    }

    @Override
    public void store(MultipartFile file) {
        try{
            if(file.isEmpty()){
                throw new StorageExceptioin("Failed to store empty file "+file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(),this.rootLocation.resolve(file.getOriginalFilename()));
        }catch (IOException e){
            throw new StorageExceptioin("Failed to store file "  + file.getOriginalFilename());
        }
    }

    @Override
    public void storeFileWithName(MultipartFile file, String name) {
        try{
            if(file.isEmpty()){
                throw new StorageExceptioin("Failed to store empty file "+file.getOriginalFilename());
            }
            Files.copy(file.getInputStream(),this.rootLocation.resolve(name));
        }catch (IOException e){
            throw new StorageExceptioin("Failed to store file "  + file.getOriginalFilename());
        }
    }

    @Override
    public List<String> loadAll() {
        try{
            List<String> all = Lists.newArrayList();
            DirectoryStream<Path> paths = Files.newDirectoryStream(rootLocation);
            for (Path a : paths){
                all.add(a.getFileName().toString());
            }
            return all;
        }catch (Exception e){
            throw new StorageExceptioin("Failed to read stored file",e);
        }
    }

    @Override
    public Path load(String fileName) {
        return rootLocation.resolve(fileName);
    }

    @Override
    public Resource loadAsResource(String filename) {
        try{
            Path file = load(filename);
            UrlResource urlResource = new UrlResource(file.toUri());
            if(urlResource.exists()||urlResource.isReadable()){
                return urlResource;
            } else {
                throw new StorageFileNotFoundException("Could not read file: "+filename);
            }
        }catch (MalformedURLException e){
            throw new StorageFileNotFoundException("Could not read file: "+filename,e);
        }
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }
}
