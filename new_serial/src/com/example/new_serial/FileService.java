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

    //---------------------------------------------------------------------
    //save the message in the textview to SD card
      

    public void saveToSDCard(String fileName, String content) throws IOException {  

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