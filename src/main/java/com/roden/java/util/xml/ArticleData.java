package com.roden.java.util.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="articles")
public class ArticleData {
   List<Article> article=new ArrayList<Article>();

public List<Article> getArticle() {
	return article;
}

public void setArticle(List<Article> article) {
	this.article = article;
}
   public static void main(String[] args) {
	   File xmlFile=new File("E:/java/input/article.xml");
		JAXBContext context;
		try{
			context=JAXBContext.newInstance(ArticleData.class);
			Unmarshaller u=context.createUnmarshaller();
			ArticleData data=(ArticleData)u.unmarshal(xmlFile);
			List<Article> articles=data.getArticle();
			for(Article a:articles){
			System.out.println("--------------------------");
			System.out.println(a.getAuthor());
			System.out.println(a.getDate());
			System.out.println(a.getEmail());
			System.out.println(a.getTitle());	
			}
		}catch(JAXBException e){
			e.printStackTrace();
		}

}
}
