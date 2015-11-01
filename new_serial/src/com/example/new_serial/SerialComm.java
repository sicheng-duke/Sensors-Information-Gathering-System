package com.example.new_serial;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import  java.text.SimpleDateFormat;  





import com.friendlyarm.AndroidSDK.HardwareControler;

import android.os.Looper;
import android.os.Message;

import java.io.IOException;
import java.io.OutputStream;
import java.net.*;
import java.util.Timer;  
import java.util.TimerTask; 

import com.example.new_serial.SerialComm.MyThread;



import android.text.Editable;
//import com.example.new_serial.SerialComm.saveThread;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;  
import android.widget.TextView; 
import android.widget.EditText; 
import android.widget.ScrollView;
import android.widget.Toast;
/*
* This is an Android App to enable the embedded device 
* to gather and save data.
*
* Also it enables device to send data through socket to host computer.
*
*
*
*
*/


public class SerialComm extends Activity {
	 
	    //----------------------------------------------------------------------------------
		//some UI components
	    private int serial_fd=0;	   	    
	    private TextView textView;
	    private Handler handler;
	    ScrollView myscroll;
	    private Button openSerial;
	    private Button closeSerial;
	    private Button clear;
	    private Button save;
	    private Button send;
	    private EditText IPAddress;
	    Socket socket;
	    OutputStream os;
	    FileService file=new FileService(this); 
	    String s="";
	    String clc="";
	    
	    
	    //----------------------------------------------------------------------------------
	    //initialize
	    @Override
	    protected void onCreate(Bundle savedInstanceState) {
	        // TODO Auto-generated method stub
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.activity_main);
	        textView=(TextView)this.findViewById(R.id.data);
	        IPAddress=(EditText)this.findViewById(R.id.IP);
	        myscroll = (ScrollView)findViewById(R.id.scroll);
	        textView.setVerticalScrollBarEnabled(true);
	        openSerial=(Button)findViewById(R.id.open);
	        closeSerial=(Button)findViewById(R.id.close);
	        clear=(Button)findViewById(R.id.clear);
	        save=(Button)findViewById(R.id.save);
	        send=(Button)findViewById(R.id.send);

	        //set actioinListener to different button
	        //open serial
	        openSerial.setOnClickListener(new Button.OnClickListener(){
	        	   @Override
	        	   public void onClick(View v) {
	        	    // TODO Auto-generated method stub
	        	    serial_fd=HardwareControler.openSerialPort("/dev/s3c2410_serial3",9600,8,1);
	        	    Toast.makeText(getApplicationContext(), "Serial Open", Toast.LENGTH_SHORT).show();
	        	    new MyThread().start();
	        	    System.out.println("Open Serial");
	        	       }
	        	         
	        	        });

	        //close serial
	        closeSerial.setOnClickListener(new Button.OnClickListener(){
	        	   @Override
	        	   public void onClick(View v) {
	        	    // TODO Auto-generated method stub
	       	        HardwareControler.close(serial_fd);
	       	        textView.append("\r\nclose Serial");
	       	     Toast.makeText(getApplicationContext(), "Serial Close", Toast.LENGTH_SHORT).show();  
	    	         MyThread.interrupted();
	        	       }
	        	         
	        	        });

	        //clear content
	        clear.setOnClickListener(new Button.OnClickListener(){
	        	   
	        	   public void onClick(View v) {
	        	    // TODO Auto-generated method stub
	        		textView.setText(clc); 
	    	        System.out.println("clear");   
	    	        Toast.makeText(getApplicationContext(), "data clear", Toast.LENGTH_SHORT).show();

	        	       }
	        	         
	        	        });

	        //save content
	        save.setOnClickListener(new Button.OnClickListener(){
	        	   
	        	   public void onClick(View v) {
	        	    // TODO Auto-generated method stub 
	        		   String new_content=textView.getText().toString();
	        		   textView.setText("");
	        		   try {
						file.saveToSDCard("data", new_content);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	        		   Toast.makeText(getApplicationContext(), "data saved", Toast.LENGTH_SHORT).show();
	        		   System.out.println("data saved");

	        	   }
	        	         
	        	        });

	        //send button
	        send.setOnClickListener(new Button.OnClickListener(){
	        	public void onClick(View v){
	        		 // TODO Auto-generated method stub 
	        		String IP=IPAddress.getText().toString();
	        		IPAddress.setText("");
	        		 try {
	        			 socket=new Socket (IP,3000);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        	System.out.println("connect");
		        	Toast.makeText(getApplicationContext(), "connected", Toast.LENGTH_SHORT).show();

		        	   
	        		
	        	}
	        });
	    
	    }

	    //----------------------------------------------------------------------------------
	    //Destory method, when exit the App, device will end all the progresses
	    @Override
	    protected void onDestroy() {
	        // TODO Auto-generated method stub
	       HardwareControler.close(serial_fd);
	       System.out.println("close Serial");    
	       MyThread.interrupted();
	        super.onDestroy();
	    }
        


	    //----------------------------------------------------------------------------------
	    //it is a handler, handle the message send by thread
	    //it will receive the message, show it on the screen 
	    //and send them to host by socket
	    class MyHandler extends Handler{
           public MyHandler(Looper looper){
        	   super(looper);
           }

	       //handle the message
	        public void handleMessage(Message msg) {
	        	//execute handleMessage when send message
	            // TODO Auto-generated method stub
	            //receive the data, show it on the screen
	            super.handleMessage(msg);
	            Bundle bd=new Bundle();
	            bd=msg.getData();
	            byte[] buffer=new byte[65];
	            buffer=bd.getByteArray("buffer");
	            String s=new String(buffer);
	            textView.append(s);
                
                //send the data through socket
	            try {
					os = socket.getOutputStream();
					os.write((s + "\r\n")
							.getBytes("utf-8"));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	           //it is responsible for auto save
	            if(textView.getLineCount() >= 6)
	 	       {
	 	    	   myscroll.scrollBy(0, 30);
		            if(textView.getLineCount() >= 30)
			 	       {
		            		
			            	
			            	//clear and save when line number is 30
			            	String newly_content=textView.getText().toString();

			            	textView.setText("No");
			            	Toast.makeText(getApplicationContext(), "data saved", Toast.LENGTH_SHORT).show();
			        		   try {
									file.saveToSDCard("data", newly_content);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
				        		  
			 	       }
	 	       }
	        }
	    };

	    
	    //----------------------------------------------------------------------------------
	    //the thread is responsible for reading the message from sensor and 
	    //pack them into message, then send them to handler
	    class MyThread extends Thread
	    {
	        int err=0;
	        private byte[] serial_RevBuf=new byte[65];	       
	        public void run() {
	            // TODO Auto-generated method stub           	
	            while(true)
	            {
	            //it is a functioin define by the hardware,it can receive message from serial port
	            err=HardwareControler.select(serial_fd, 1, 0);
	            //if receive data, then send it to handler
	            if(err==1)
	            {
	            	for(int j=0;j<65;j++)
	            		serial_RevBuf[j]='\0';	
	                int count=HardwareControler.read(serial_fd, serial_RevBuf, 65);	                
	                Looper looper=Looper.getMainLooper();
	                handler=new MyHandler(looper);
	                Bundle bd=new Bundle();
	                bd.putByteArray("buffer",serial_RevBuf);
	                Message msg=new Message();
	                msg.setData(bd);
	                handler.sendMessage(msg);
	                
	            }

	            try {
	                Thread.sleep(140);  //sleep 2ms
	            } catch (InterruptedException e) {
	                // TODO Auto-generated catch block
	                e.printStackTrace();  //print error
	            }
	         }
	        }
	        
	    };

	}
