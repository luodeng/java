import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class City {		
	 public static void main(String[] args) throws Exception {
		 Map<String,String> map= getData();		 
		Connection conn = null;		
		Class.forName("com.mysql.jdbc.Driver");
		conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1/roden?characterEncoding=utf8&zeroDateTimeBehavior=convertToNull", "root", "");	

		ResultSet rs = conn.createStatement().executeQuery("select * from city");
		List<String> errors=new ArrayList<String>();
		
		while (rs.next()) {			
			int id=rs.getInt("id");
			String name=rs.getString("CityName");			
			String code=map.get(name);
			
			/*if(code==null){
				code=map.get(name+"市");
			}*/
			
			if(code==null){
				errors.add(name);
			}else{
				String sql="update city set CityCode='"+code+"' where CityName='"+name+"';";
				System.out.println(sql);
			}
			
			
		}
		System.out.println(errors);
	}
	
	
    private static Map<String,String> getData() throws Exception {
        Map<String,String> map=new HashMap<>();
        Document doc = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/201608/t20160809_1386477.html").get();
        Elements ps = doc.select(".TRS_PreAppend p");
        for (Element e : ps) {
            Elements spans = e.select(">span"); 
	            Element firstE = spans.first();
	            Element secondE = spans.get(1);	            
	            String key = firstE.text().trim();		          
	            key = key.substring(0, 6);	                 
	            if (key.endsWith("00")&&!key.endsWith("0000")) {
	            	String value=secondE.text().trim();	  
	            	value=value.substring(2,value.length());
	            	map.put(value, key);
	            }
        }
        return map;
    }
}
