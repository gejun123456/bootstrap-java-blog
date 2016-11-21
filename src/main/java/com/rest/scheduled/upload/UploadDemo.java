package com.rest.scheduled.upload;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;

import java.io.IOException;

/**
 * Created by bruce.ge on 2016/11/21.
 */
public class UploadDemo {
    String ACCESS_KEY = "ES7baUBANzDwCSUT23nHBgoBZIy2zBWwjxYak9L3";
    String SECRET_KEY = "*****************";
    String bucketname = "brucegedatabasebackup";
    Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    Zone z = Zone.autoZone();

    Configuration c = new Configuration(z);
    UploadManager uploadManager = new UploadManager(c);

    public String getUpToken() {
        return auth.uploadToken(bucketname);
    }

    public void upload(String path, String savedname) throws IOException {
        try {
            Response res = uploadManager.put(path, savedname, getUpToken());
            System.out.println(res.bodyStream());
        } catch (QiniuException e) {
            Response r = e.response;
            System.out.println(r.toString());
            try {
                System.out.println(r.bodyString());
            } catch (QiniuException f) {
                //do nothing.
            }
        }
    }

//    public static void main(String[] args) throws IOException {
//        String path = UploadDemo.class.getClassLoader().getResource("logback-spring.xml").getPath().toString();
//        System.out.println(path);
//        new UploadDemo().upload(path, "a.xml");
//    }


}
