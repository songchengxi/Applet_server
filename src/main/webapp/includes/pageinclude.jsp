<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    /**
     * 页面中需要的绝对路径变量，用于每个页面的静态包含
     */
    //协议名：http
    String scheme = request.getScheme();
    //服务器名（IP地址）
    String serverName = request.getServerName();
    //端口号
    int serverPort = request.getServerPort();
    //项目名(/sxm-crm-esop-pro)
    String contextPath = request.getContextPath();
    //服务器路径
    String serverPath = scheme + "://" + serverName + ":" + serverPort + "/";
    //项目访问路径
    String basePath = scheme + "://" + serverName + ":" + serverPort + contextPath;
    //前端第三方库的路径
    String libsPath = basePath + "/libs";
%>
<c:set var="contextPath" value="<%=contextPath%>"/>
<c:set var="serverPath" value="<%=serverPath%>"/>
<c:set var="basePath" value="<%=basePath%>"/>
<c:set var="libsPath" value="<%=libsPath%>"/>
