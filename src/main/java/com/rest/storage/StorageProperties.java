package com.rest.storage;

import org.springframework.context.annotation.Configuration;

/**
 * Created by bruce.ge on 2016/11/9.
 */
@Configuration("storage")
public class StorageProperties {
    private String location = "/opt/imageloc/";

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
