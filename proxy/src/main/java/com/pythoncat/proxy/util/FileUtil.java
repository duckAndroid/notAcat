package com.pythoncat.proxy.util;

import com.apkfuns.logutils.LogUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by pythonCat on 2016/8/14 0014.
 */
public class FileUtil {

    /**
     * 读取文本文件的内容
     *
     * @param f file
     * @return string
     */
    public static String readFile(File f) {
        if (f == null || !f.isFile())
            throw new RuntimeException("target file is not a readable file !!! ");
        BufferedReader reader = null;
        StringBuilder sbRead = new StringBuilder();
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(f));
            String tempString;
            int line = 1;
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                LogUtils.i("file line " + line + ": " + tempString);
                sbRead.append(tempString);
                line++;
            }
            return sbRead.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException ignore) {
                }
            }
        }
    }
}
