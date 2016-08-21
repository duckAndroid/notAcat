package com.pythoncat.proxy.bean;

/**
 * Created by pythonCat on 2016/8/14 0014.
 * 下载信息
 */
public class Download {

    public float progress;
    public long total;
    public boolean done;
    /**
     * filePath --> like: /sdcard/aa/hello.txt
     */
    public String destPath;

    public Download(float progress, long total, boolean done, String destPath) {
        this.progress = progress;
        this.total = total;
        this.done = done;
        this.destPath = destPath;
    }
}
