package com.wifi_server;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;



public class server extends JFrame {

	//UI components
	JButton open;
	JButton close;
	JButton save;
	JButton IP;
	JButton add;
	
	static TextArea textview;
	static TextField ipAddress;
	
	FileService file=new FileService();
	
	static Box topLeftLeft=Box.createVerticalBox();
	Box topLeft=Box.createVerticalBox();
	Box topRight=Box.createVerticalBox();
	static JFrame f=new JFrame();

	static Mytable p=null;
	static JTable table=null;


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
				new server();
				ServerSocket ss=new ServerSocket(3000);
				System.out.println("Listening at 3000");
				while(true)
				{
					Socket s=ss.accept();
					System.out.println("connect");
					new Thread(new ServerThread(s)).start();
				}
	}
	
	//initialize the UI
	public server()
	{
		open=new JButton("open  ");
		save=new JButton("save");
		IP=new JButton("ip      ");
		add=new JButton("add  ");
		textview=new TextArea(); 
		ipAddress=new TextField();
		
		ipAddress.setText("Welcome");
		ipAddress.setFont(new Font("宋体",Font.BOLD,20));
		ipAddress.setSize(16, 1);
		
		IP.addActionListener(new ipListener());
		save.addActionListener(new SaveFile());
		add.addActionListener(new add());
		
		
		p=new Mytable();
		table=new JTable(p);
		
		ImageIcon icon=new ImageIcon("image/LOGO.jpg");
		JLabel Imagelabel=new JLabel(icon);
		add(Imagelabel);
		
		
		

		
		String[] n={"Node","Temp","Battery"};
		table.setPreferredScrollableViewportSize(new Dimension(200,100));
		JScrollPane s=new JScrollPane(table);
		
		

		topLeft.add(textview);
		topLeft.add(Box.createVerticalStrut(10));
		topLeft.add(ipAddress);

		
		topRight.add(IP);
		topRight.add(save);

		
		
		Container contentPane=f.getContentPane();		
		contentPane.add(Imagelabel,BorderLayout.NORTH);
		contentPane.add(s,BorderLayout.WEST);
		contentPane.add(topLeft,BorderLayout.CENTER);
		contentPane.add(topRight,BorderLayout.EAST);

		
		f.pack();
		f.setVisible(true);
		f.setLocation(100, 100);
		f.setSize(917, 600);
		f.setTitle("Tianjin University Information Gathering System");
		f.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		});

	}

	//Get Ip Address
	class ipListener implements ActionListener
	{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			try {
				String s=InetAddress.getLocalHost().getHostAddress();
				ipAddress.setText("本机IP地址为"+s);
				System.out.println(s);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//Save data
	class SaveFile implements ActionListener
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				
				String s=textview.getText().toString();
				String ss="";
				textview.setText(ss);
				ipAddress.setText("Set IP address"+s);
				try {
					file.saveToDisk("Data",s);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
		}
	
}
