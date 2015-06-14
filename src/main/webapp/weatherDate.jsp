<%@ page language="java" import="java.net.*,java.io.*" pageEncoding="utf-8"%>
  <%
    String cid = request.getParameter("cid");
	URL url = new URL("http://m.weather.com.cn/data/"+cid+".html");
	HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
	httpConn.connect();
	InputStream cin = httpConn.getInputStream();
	BufferedReader reader = new BufferedReader(new InputStreamReader(cin,"UTF-8"));
	StringBuffer sb = new StringBuffer();
	String rl = null;
	while ((rl = reader.readLine()) != null)
		sb.append(rl);	
	out.println(sb);
  %>
  