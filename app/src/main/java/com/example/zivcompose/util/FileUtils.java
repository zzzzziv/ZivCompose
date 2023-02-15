package com.example.zivcompose.util;



import com.example.zivcompose.APP;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * author:  Ziv
 * date:    2023/1/17.
 * describe:写入文件工具类
 */
public class FileUtils {

    private String sdPath;

    public String getSdPath() {
        return sdPath;
    }

    //构造函数，得到SD卡的目录，这行函数得到的目录名其实是叫"/SDCARD"
    public FileUtils() {
        sdPath = ((APP) AppGlobalUtils.getApplication()).getExternalFilesDir(null).getAbsolutePath() + "/bookImg/";
    }

    //在SD卡上创建文件
    public File createSDFile(String fileName) throws IOException {
        File file = new File(sdPath + fileName);
        file.createNewFile();
        return file;
    }

    //在SD卡上创建目录
    public File createSDDir(String dirName) {
        File dir = new File(dirName);
        dir.mkdir();
        return dir;
    }

    //判断SD卡上的文件夹是否存在
    public boolean isFileExist(String path, String fileName) {
        File file = new File(path + fileName);
        return file.exists();
    }

    //将bytes写入到SD卡中
    //将bytes写到path这个目录中的fileName文件上
    public File writeSDFromInput(String path, String fileName, byte[] bytes) {
        File file = null;
        FileOutputStream output = null;
        try {
            createSDDir(path);
            file = createSDFile(fileName);
            //FileOutputStream写入数据，写入到file这个文件上
            output = new FileOutputStream(file);
            output.write(bytes, 0, bytes.length);
            output.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                output.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return file;
    }
}