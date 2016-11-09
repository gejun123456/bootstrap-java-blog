package com.rest;

import org.junit.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.util.FileSystemUtils;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by bruce.ge on 2016/11/9.
 */
public class FileTest {
    @Test
    public void test() throws IOException {
        Files.createDirectories(Paths.get("/inta/nimei"));
    }
}
