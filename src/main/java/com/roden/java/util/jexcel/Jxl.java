package com.roden.java.util.jexcel;

import java.io.*;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class Jxl {

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception {
		String [][]user={{"张三","24","男"},{"李四","32","女"}};
		File f=new File("E:/java/output/jxl.xls");
      WritableWorkbook wwb=Workbook.createWorkbook(f);
      WritableSheet sheet=wwb.createSheet("用户", 0);
      Label lab=null;
      for(int x=0;x<user.length;x++)
    	  for(int y=0;y<user[x].length;y++){
    		  lab=new Label(y,x,user[x][y]);
    		  sheet.addCell(lab);
      }
      wwb.write();
      wwb.close();
      
	}

}
