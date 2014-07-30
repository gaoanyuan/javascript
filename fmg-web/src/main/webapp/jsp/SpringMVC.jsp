<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
        <script type="text/javascript">
            function sleep(seconds) {
                var start = new Date().getTime();
                while(true){
                    if ((new Date().getTime() - start) > seconds * 1000){
                        break;
                    }
                }
            }
        </script>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webresource/css/ui-lightness/jquery-ui-1.9.2.custom.css">
        <script type="text/javascript" src="${pageContext.request.contextPath}/webresource/js/jquery-1.8.3.js" ></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/webresource/js/jquery-ui-1.9.2.custom.js" ></script>






  </head>
  <body >
    This is my JSP page. <br>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webresource/css/asyncTest1.css">
    <script type="text/javascript">
       sleep(3);
        console.log("11");
    </script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/webresource/css/asyncTest.css">
  </body>
</html>
