package com.roden.java.util.jexcel;

import java.io.File;
import java.io.IOException;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;

public class LoadJxl {
	public static void main(String args[]) throws Exception{
		File f=new File("E:/java/input/jxl.xls");
		Workbook w=Workbook.getWorkbook(f);
		Sheet sheet[]=w.getSheets();
		 for(int x=0;x<sheet.length;x++)
	    	  for(int y=0;y<sheet[x].getRows();y++)
	    	  for(int z=0;z<sheet[x].getColumns();z++){
	    		  String content=sheet[x].getCell(z,y).getContents();
	    		 System.out.print(content+"\t");
	      }
		 System.out.println();
	}

}
