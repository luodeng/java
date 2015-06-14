package com.roden.java.util.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

public class JAXBDemo_Out {

	public static void main(String[] args) {
		File xmlFile=new File("E:/java/output/test.xml");
		JAXBContext context;
		try{
			context=JAXBContext.newInstance(Article.class);
			Marshaller m=context.createMarshaller();
			Article article=new Article();
			article.setAuthor("Janet");
			article.setDate("20140301");
			article.setEmail("luo_deng@qq.com");
			article.setTitle("xml");
			m.marshal(article, xmlFile);
		}catch(JAXBException e){
			e.printStackTrace();
		}

	}

}
