<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
			
<!DOCTYPE html >
<html>
<header class="clearfix" id="header-sec">
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="top-line">
		<div class="container">
			<div class="row">
				<div class="col-md-8 col-sm-9 topbar-left">
					<ul class="info-list">
					
					</ul>
				</div>
				<div class="col-md-4 col-sm-3 topbar-right">
					
				</div>
			</div>
		</div>
	</div>
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-toggle collapsed mmenu-btn" href="#mmenu"> <span class="sr-only"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span> </a>
			<a class="navbar-brand" href="index.do"> <img src="skin/images/logo.png" alt="" class="logo" /> <img src="skin/images/logo.png" alt="" class="logo-m" /> </a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right" id="navigation">
				<li class="Lev1">
					<a href="index.do" class='menu1 active'>首页 </a>
				</li>
				<li class="Lev1">
					<a href="news.do" class="menu1 ">医院动态
					</a>
				</li>
				<li class="Lev1">
					<a href="doctor.do" class="menu1 ">医生信息</a>
				</li>
				
				<li class="Lev1" >
					<a href='javascript:;' class="menu1 ">科室<i class="fa fa-caret-down"></i></a>
					<ul class="drop-down sub-menu2">
					<c:forEach items="${flist }" var="f" >
						<li class="Lev2">
							<a href="yiShengTypeList.do?fid=${f.id }" class="menu2">${f.name }</a>
						</li>
					</c:forEach>
					</ul>
				</li>
				
				<li class="Lev1"><!-- contact.html -->
					<a href="bbsList.do" class="menu1 ">建议反馈
					</a>
				</li>
				
				<c:if test="${sessionScope.user==null }">
				<li class="Lev1">
					<a href="login.do" class="menu1 ">登录
					</a>
				</li>
				<li class="Lev1">
					<a href="regist.jsp" class="menu1 ">注册
					</a>
				</li>
				</c:if>
				<li class="Lev1">
					<a href="admin/login.jsp" class="menu1 " target="_blank;"> 后台</a>
				</li>
					<c:if test="${sessionScope.user!=null }">
				<li class="Lev1">
					<a href="showInfo.do" class="menu1 " style="color:#ff0;">${sessionScope.user.uname }
					<i class="fa fa-caret-down"></i></a>
					<ul class="drop-down sub-menu2">
						<li class="Lev2">
							<a href="showAllOrder.do" class="menu2">预约查询</a>
						</li>
						<li class="Lev2">
							<a href="" class="menu2"></a>
						</li>

					</ul>
				</li>
				
				<li class="Lev1"><!-- contact.html -->
					<a href="loginout.do" class="menu1 ">注销
					</a>
				</li>
				</c:if>
				
			</ul>
		</div>
	</div>
</nav>
</header>
			
