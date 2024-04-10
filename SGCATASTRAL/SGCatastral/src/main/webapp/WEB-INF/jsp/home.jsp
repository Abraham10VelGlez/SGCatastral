<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ page isELIgnored="false" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<sec:authorize access="isAnonymous()">    
    <% response.sendRedirect("login.do");%>
</sec:authorize>

<sec:authorize access="hasRole('ROLE_Catastro')">    
    <% response.sendRedirect("inicio.do");%>
</sec:authorize>