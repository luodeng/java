<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script>  
function load(cid)  
{  
var xmlhttp;  
if (window.XMLHttpRequest)  
  {// code for IE7+, Firefox, Chrome, Opera, Safari  
  xmlhttp=new XMLHttpRequest();  
  }  
else  
  {// code for IE6, IE5  
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");  
  }  
xmlhttp.open("GET","weatherDate.jsp?cid="+cid,false);  
xmlhttp.send();  
var obj = eval("("+ xmlhttp.responseText+")");  
//var obj=JSON.parse(xmlhttp.responseText);  //IE8以上   
document.getElementById("test").innerHTML=obj.weatherinfo.city+":"+obj.weatherinfo.weather1+"   "+obj.weatherinfo.temp1;  
} 
window.onload=function(){load("101250511");}
</script>  
  </head>
  
  <body>
    <p id="test">天气情况</p>  
   <button id="btn1" onClick='load("101280601")'>深圳天气</button>  
   <button id="btn2" onClick='load("101250501")'>郴州天气</button>
   <button id="btn3" onClick='load("101250511")'>桂东天气</button>    
   <!--   
         城市id获取：http://blog.csdn.net/zgyulongfei/article/details/7956118  
    -->  
  </body>
</html>
