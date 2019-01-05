package com.edwin.dto;

/**
 * Created by wenpuzhao on 2019/1/5.
 */
public class FileInfo {
    public FileInfo(String path){
        this.path = path;
    }

    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
