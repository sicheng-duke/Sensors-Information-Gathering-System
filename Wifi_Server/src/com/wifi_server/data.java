package com.wifi_server;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
public class data {
	// sensor node array
	String[] NodeArray=new String[]{
			"HUB0",
			"0001",
			"0002",
			"0003",
			"0004",
			"0005",
			"0006",
			"0007",
			"0008",
			"0009",
			"0010",
			"0011",
			"0012",
			"0013",
			"0014",
			"0015",
			"0016",
			"0017",
			"0018",
			"0019",
	};
	int i=0;
	
	//Update data for each node
	 public void Create(String Node,String Temp, String Battery)
	{


		 for(i=0;i<=NodeArray.length-1;i++)
		
				if(Node.equals(NodeArray[i]))
				{
					
					System.out.println(NodeArray.length);
					server.p.setValueAt(Temp, i, 1);
					server.p.setValueAt(Battery,i,2);
					server.table.repaint();
				}
			

	}

}
