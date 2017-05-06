<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
      <base href="<%=basePath%>">
      
    <title>添加商品</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
  </head>
  
  <body>
  <div><font color="red">${error }</font></div>
    <form action="<c:url value ='/goods/add'/>" method="post">
    	商品名称：<input type="text" id="name" name="name" value="${goods.name }"><font color="red">${errors.name }</font><br>
    	商品描述：<input type="text" id="detail" name="detail" value="${goods.detail }"><font color="red">${errors.detail }</font><br>
    	商品图片:<input type="text" id="url" name="url" value="${goods.url }"><font color="red">${errors.url }</font><br>
    	商品类别：<input type="text" id="catalog" name="catalog" value="${goods.catalog }"><font color="red">${errors.catalog }</font><br>
    	商品价格:<input type="text" id="price" name="price" value="${goods.price }"><font color="red">${errors.price }</font><br>
    	上架日期:<input type="text" id="addTime" name="addTime" value="${goods.addTime }"><font color="red">${errors.addTime }</font><br>
    	<input type="submit" value="添加">
    </form>
  </body>
</html>
