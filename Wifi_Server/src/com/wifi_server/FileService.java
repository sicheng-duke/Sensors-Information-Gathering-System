package com.wifi_server;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;


public class FileService {
	

    public void saveToDisk(String fileName, String content) throws IOException {  
        //File file = new File(new File("/mnt/sdcard"),fileName);  
        
        Calendar c = Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH)+1;
        int day=c.get(Calendar.DAY_OF_MONTH);
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        File fileDir = new File("C:\\data");
        if(!fileDir.exists()&&!fileDir.isDirectory())
        {
        	fileDir.mkdirs();
        }
        
        fileName=fileName+" "+year+" "+month+" "+day+" "+hour+" "+minute+".txt";
        File file=new File("C:\\data",fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(file);  
        fileOutputStream.write(content.getBytes());  
        fileOutputStream.close(); 

    } 
}
