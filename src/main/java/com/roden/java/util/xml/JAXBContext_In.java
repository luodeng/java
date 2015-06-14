package com.roden.java.util.xml;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class JAXBContext_In {

	public static void main(String[] args) {
		File xmlFile=new File("E:/java/input/test.xml");
		JAXBContext context;
		try{
			context=JAXBContext.newInstance(Article.class);
			Unmarshaller u=context.createUnmarshaller();
			Article article=(Article)u.unmarshal(xmlFile);
			System.out.println(article.getAuthor());
			System.out.println(article.getDate());
			System.out.println(article.getEmail());
			System.out.println(article.getTitle());			
		}catch(JAXBException e){
			e.printStackTrace();
		}

	}

}
