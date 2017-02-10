<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String basepath=(String)application.getAttribute("path");
if(basepath==null) {
	basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
}
if(session.getAttribute("user")==null) {
	response.sendRedirect(basepath+"/front/login");
	return;
}
%>