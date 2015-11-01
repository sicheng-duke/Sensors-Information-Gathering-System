package com.wifi_server;
import com.wifi_server.*;

import java.io.*;
import java.net.*;

public class ServerThread implements Runnable {

	Socket s=null;
	BufferedReader br=null;
	FileService NewFile=new FileService();
	String ss="";
	
	data new_data=new data();
	public ServerThread(Socket s) throws IOException
	{
		this.s=s;
		
		
		br=new BufferedReader(new InputStreamReader(s.getInputStream(),"utf-8"));
	}
	
	public void run()
	{
			
			String content=null;
			
			
			while((content=readFromClient())!=null)
			{
				
				content=content.replace("No","\r\nNo");
				



				if(!content.contains("No"))
					{
						content=content.replace("\r\n", "");
						ss=(ss+content).trim();		
					}


				if(content.contains("No"))
				{
					
					if(ss.contains("de:"))
					{	
						
						String Node;
						Node=ss.substring(3,7);
						String Temp;
						Temp=ss.substring(14,19);
						String Battery;
						Battery=ss.substring(28, 32);
						new_data.Create(Node,Temp,Battery);
						
						//System.out.println(Node);
						System.out.println(ss);
						ss="";
					}	
					

				}
			

//				System.out.println(content);
				
				server.textview.append(content);
			}
			
		

	}
	private String readFromClient()
	{
		try {
			return br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			e.printStackTrace();
		}
		return null;
	}


}
