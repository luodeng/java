package com.roden.java.util;


import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import jxl.Sheet;
import jxl.Workbook;
/*
 *  create table `roden`.`2009gkcj`(
        `id` int not null,
       `class` int,
       `ksh` int,
       `kh` int,
       `name` varchar(20),
       `sex` varchar(5),
       `jb` varchar(10),
       `total` int,
       `math` int,
       `chinese` int,
       `english` int,
       `zh` int,
       `yhf` int,
       `zylkcj` varchar(20),
        primary key (`id`)
    );

    create unique index `PRIMARY` on `roden`.`2009gkcj`(`id`);

 */

public class LoadJxl {
	public static void main(String args[]) throws Exception {
		File f = new File("E:/java/input/2009年高考应届往届cj.xls");
		Connection con;
		Statement st;
		//ResultSet rs;
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		con = DriverManager
				.getConnection("jdbc:mysql://localhost:3306/roden?useUnicode=true&characterEncoding=utf-8",
						"root", "");

		st = con.createStatement();
		Workbook w = Workbook.getWorkbook(f);
		Sheet sheet[] = w.getSheets();
		for (int x = 0; x < sheet.length; x++)
			for (int y = 0; y < sheet[x].getRows(); y++) {
				
					// String content = sheet[x].getCell(z, y).getContents();
					// System.out.print(content + "t");
					String str = "insert into 2009gkcj values("
							+ sheet[x].getCell(1, y).getContents()+","
							+ sheet[x].getCell(0, y).getContents()+","
							+ sheet[x].getCell(2, y).getContents()+","
							+ sheet[x].getCell(3, y).getContents()+",'"
							+ sheet[x].getCell(4, y).getContents()+"','"
							+ sheet[x].getCell(5, y).getContents()+"','"
							+ sheet[x].getCell(6, y).getContents()+"',"
							+ sheet[x].getCell(7, y).getContents()+","
							+ sheet[x].getCell(8, y).getContents()+","
							+ sheet[x].getCell(9, y).getContents()+","
							+ sheet[x].getCell(10, y).getContents()+","
							+ sheet[x].getCell(11, y).getContents()+",'"
							+ sheet[x].getCell(12, y).getContents()+"','"
							+ sheet[x].getCell(13, y).getContents()+"')";
					System.out.println(str);
                   st.executeUpdate(str);
				
				System.out.println();
			}
	}

}