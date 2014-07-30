<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="date" uri="/dateTrans" %>
<%@ taglib prefix="number" uri="/numberConvert" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE >
<html>
    <head>
        <base href="<%=basePath%>">
        <title>My JSP 'MyJsp.jsp' starting page</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <!--
        <link rel="stylesheet" type="text/css" href="styles.css">
        -->
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webresource/css/ui-lightness/jquery-ui-1.9.2.custom.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/webresource/js/jquery-1.8.3.js" ></script>
        <script type="text/javascript"  src="${pageContext.request.contextPath}/webresource/js/jquery-ui-1.9.2.custom.js" ></script>
        <script type="text/javascript" onload="">
            $(function(){
                alert("jquery is read!");
            })
        </script>
  </head>
  <body>
    This is my JSP page. <br>
  </body>
</html>
