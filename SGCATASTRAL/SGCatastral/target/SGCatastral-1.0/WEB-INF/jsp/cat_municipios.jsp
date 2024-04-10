<%-- 
    Document   : cat_municipios
    Created on : 9/11/2017, 04:03:23 PM
    Author     : Fernando
--%>

<%@ page language="java" contentType="application/json" %>

<%@ page import="edomex.gob.mx.sgcatastral.DAO.*" %>
<%
	cat_municipios mun=new cat_municipios();
	out.println(mun.result);
%>
