package com.example.new_serial;

import java.io.ByteArrayOutputStream;  
import java.io.File;  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.content.Context;  
import android.os.Environment;  
  
public class FileService {  
    private Context context;  
    public FileService(Context context) {  
        super();  
        this.context = context;  
    }  
    /**  
     * 保存文件  
     * @param fileName 文件名称  
     * @param content  文件内容  
     * @throws IOException  
     */  

    public void save(String fileName, String content) throws IOException {  
        //以私有方式读写数据，创建出来的文件只能被该应用访问  
        FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_WORLD_READABLE);  
        fileOutputStream.write(content.getBytes());  
        fileOutputStream.close();  
    }  
      
    /**  
     * 保存文件  
     * @param fileName 文件名称  
     * @param content  文件内容  
     * @throws IOException  
     */  
    public void saveToSDCard(String fileName, String content) throws IOException {  
        //File file = new File(new File("/mnt/sdcard"),fileName);  
        //考虑不同版本的sdCard目录不同，采用系统提供的API获取SD卡的目录  
        Calendar c = Calendar.getInstance();

        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        File file = new File(Environment.getExternalStorageDirectory(),fileName);

        	fileName=fileName+hour+" "+minute+".txt";
        	file=new File(Environment.getExternalStorageDirectory(),fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);  
        fileOutputStream.write(content.getBytes());  
        fileOutputStream.close(); 

    }  

}  