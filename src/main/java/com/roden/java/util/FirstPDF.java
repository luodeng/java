package com.roden.java.util;



import java.io.FileOutputStream;

import com.itextpdf.text.Document;

import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

public class FirstPDF {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Document document = new Document(PageSize.A4);
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("E:/java/output/itext.pdf"));
			document.open();
			BaseFont bfChinese = BaseFont.createFont("STSong-Light",
					"UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font FontChinese = new Font(bfChinese, 12, Font.NORMAL);
			Paragraph paragrph = new Paragraph("你好，这是中文", FontChinese);
			document.add(paragrph);
			document.add(new Paragraph("Hello,World 世界，你好"));
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
