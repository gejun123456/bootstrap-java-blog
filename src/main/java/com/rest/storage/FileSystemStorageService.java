package com.rest.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

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
    public Stream<Path> loadAll() {
        try{
            return Files.walk(this.rootLocation,1).filter(path->!path.equals(this.rootLocation))
                    .map(path->this.rootLocation.relativize(path));
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
