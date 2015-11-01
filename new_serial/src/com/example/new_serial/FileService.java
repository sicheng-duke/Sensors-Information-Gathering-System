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
     * �����ļ�  
     * @param fileName �ļ�����  
     * @param content  �ļ�����  
     * @throws IOException  
     */  

    public void save(String fileName, String content) throws IOException {  
        //��˽�з�ʽ��д���ݣ������������ļ�ֻ�ܱ���Ӧ�÷���  
        FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_WORLD_READABLE);  
        fileOutputStream.write(content.getBytes());  
        fileOutputStream.close();  
    }  
      
    /**  
     * �����ļ�  
     * @param fileName �ļ�����  
     * @param content  �ļ�����  
     * @throws IOException  
     */  
    public void saveToSDCard(String fileName, String content) throws IOException {  
        //File file = new File(new File("/mnt/sdcard"),fileName);  
        //���ǲ�ͬ�汾��sdCardĿ¼��ͬ������ϵͳ�ṩ��API��ȡSD����Ŀ¼  
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