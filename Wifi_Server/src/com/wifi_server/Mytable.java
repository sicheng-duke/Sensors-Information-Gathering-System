package com.wifi_server;

import javax.swing.table.AbstractTableModel;

public class Mytable extends AbstractTableModel{
	
	
	Object[][] p=
		{
		{"HUB0","NULL","NULL"},
		{"0001","NULL","NULL"},
		{"0002","NULL","NULL"},
		{"0003","NULL","NULL"},
		{"0004","NULL","NULL"},
		{"0005","NULL","NULL"},
		{"0006","NULL","NULL"},
		{"0007","NULL","NULL"},
		{"0008","NULL","NULL"},
		{"0009","NULL","NULL"},
		{"0010","NULL","NULL"},
		{"0011","NULL","NULL"},
		{"0012","NULL","NULL"},
		{"0013","NULL","NULL"},
		{"0014","NULL","NULL"},
		{"0015","NULL","NULL"},
		{"0016","NULL","NULL"},
		{"0017","NULL","NULL"},
		{"0018","NULL","NULL"},
		{"0019","NULL","NULL"},
	};
	
	String[] n={"Node","Temp","Battery"};
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return n.length;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return p.length;
	}
	
	public String getColumnName(int col)
	{
		return n[col];
	}

	@Override
	public Object getValueAt(int row, int col) {
		// TODO Auto-generated method stub
		return p[row][col];
	}
	public void setValueAt(Object value,int row,int col)
	{
		p[row][col]=value;
		fireTableCellUpdated(row,col);
	}
	
}
