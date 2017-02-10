<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String basepath=(String)application.getAttribute("basepath");
if(basepath==null) {
	basepath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
}
if(session.getAttribute("username")==null) {
	response.sendRedirect(basepath+"/login");
	return;
}
%>