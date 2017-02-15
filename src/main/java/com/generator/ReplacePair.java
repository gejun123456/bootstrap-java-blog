package com.generator;

/**
 * @Author bruce.ge
 * @Date 2017/1/23
 * @Description
 */
public class ReplacePair {
    String devPath;

    String cdnPath;


    public ReplacePair(String devPath, String cdnPath) {
        this.devPath = devPath;
        this.cdnPath = cdnPath;
    }

    public String getDevPath() {
        return devPath;
    }

    public void setDevPath(String devPath) {
        this.devPath = devPath;
    }

    public String getCdnPath() {
        return cdnPath;
    }

    public void setCdnPath(String cdnPath) {
        this.cdnPath = cdnPath;
    }
}
