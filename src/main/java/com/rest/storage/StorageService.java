package com.rest.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by bruce.ge on 2016/11/9.
 */
public interface StorageService {
    void init();

    void store(MultipartFile file);

    void storeFileWithName(MultipartFile file,String name);

    List<String> loadAll();

    Path load(String fileName);

    Resource loadAsResource(String filename);

    void deleteAll();
}
