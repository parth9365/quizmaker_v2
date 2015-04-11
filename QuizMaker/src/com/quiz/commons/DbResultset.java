package com.quiz.commons;

import java.util.*;


public class DbResultset
{
	private Hashtable columnNames=new Hashtable();
	private Vector data=new Vector();
	private int maxMatchedRecords = 0;
   public DbResultset(){}

	public DbResultset(Hashtable columnNames , Vector data)
	{
		this.columnNames=columnNames;
		this.data=data;
  	}
	public DbResultset(Hashtable columnNames , Vector data, int maxMatchRecords)
	{
		this.columnNames=columnNames;
		this.data=data;
		this.maxMatchedRecords=maxMatchRecords;
  	}
	public String get(int rowNo,String columnName)
	{

		Vector row=(Vector)data.elementAt(rowNo);
		int index=((Integer)columnNames.get(columnName)).intValue();
  		return (String)row.elementAt(index);

  	}
	public int getRowCount()
	{
		//System.out.println("in dbresultset " + data.size());
		return data.size();
	}
	public int getMaxMatchedRecords()
	{
		return maxMatchedRecords;
	}
	public Enumeration<String> getColumnNames()
	  {
	    return this.columnNames.keys();
	  }

	  public void clearDbResultset()
	  {
	    try {
	      if (this.columnNames != null) {
	        this.columnNames.clear();
	        this.columnNames = null;
	      }
	      if (this.data != null) {
	        this.data.clear();
	        this.data = null;
	      }
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	  }
}


