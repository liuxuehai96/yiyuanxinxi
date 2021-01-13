<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>


<!DOCTYPE html>
<html>

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta name="renderer" content="webkit" />
	<meta name="robots" content="index, follow" />
	<title>历史记录</title>
	<meta name="keywords" content="" />
	<meta name="description" content="" />
	<link rel="stylesheet" type="text/css" href="skin/css/font-awesome.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="skin/css/bootstrap.min.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="skin/css/owl.carousel.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="skin/css/owl.theme.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="skin/css/settings.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="skin/css/style-red.css" media="screen" />
	<link rel="stylesheet" type="text/css" href="skin/css/tk.css" media="screen" />
	<script type="text/javascript" src="skin/js/jquery.min.js"></script>
	<script type="text/javascript" src="skin/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="skin/js/owl.carousel.min.js"></script>
</head>
<script type="text/javascript">
function down1(fujianPath)
{
   var url="updown.jsp?fujianPath="+fujianPath;
   url=encodeURI(url); 
   url=encodeURI(url); 
   window.open(url,"_self");
}
</script>

	<body>
		<div id="container">
		
			<jsp:include page="top.jsp"></jsp:include>

			<div class="breadcrumb-wrapper">
				<div class="container">
					<div class="row">
						<div class="col-md-3 col-sm-4 bcid-cat">就诊记录详情</div>
						<div class="col-md-9 col-sm-8 location"><i class="fa fa-map-marker"></i> &nbsp;<span>当前位置： <a href='javascript:;'>主页</a> > <a href='javascript:;'>就诊记录详情</a></span></div>
					</div>
				</div>
			</div>
			<div class="page-container" id="innerpage-wrap">
				<div class="container">
					<div class="row">
						<div class="main col-md-9 inner-left" role="main">
							<div class="about-page-wrap">
								<div id="contact-wrap">
									<h3 class="msg-title">就诊记录详情</h3>
									<form class="add-msg-form" action="addBbs.do"   method="post" ><!-- enctype="multipart/form-data" -->
										
										<div class="row">
											
											<div class="cf-column col-md-12"> 
												订单号：${order.cno }
											</div>
											<div class="cf-column col-md-12"> 
												订单时间：${order.etime }
											</div>
											<div class="cf-column col-md-12"> 
												病人姓名：${user.tname }
											</div>
											<div class="cf-column col-md-12"> 
												病人年龄：${user.age }
											</div>
											<div class="cf-column col-md-12"> 
												病人性别：${user.sex }
											</div>
											<div class="cf-column col-md-12"> 
												过敏史：${user.guoming }
											</div>
											<div class="cf-column col-md-12"> 
												既往史：${user.note }
											</div>
											<div class="cf-column col-md-12"> 
												诊断医生：${doc.tname }(职位：${doc.zhiwei })
											</div>
											<h4>===影像记录===</h4>
											<c:forEach items="${plist }" var="p">
											<div class="cf-column col-md-12"> 
												器官名称：${p.name }
											</div>
											<div class="cf-column col-md-12"> 
												器官类型：${p.ctype }
											</div>
											<div class="cf-column col-md-12"> 
												器官切面：${p.qiemian }
											</div>
											<div class="cf-column col-md-12"> 
												器官图片：<img alt="" src="./upload/${p.img }" width="100px" height="100px">
												<c:if test="${p.img!=null }"><!-- class="btn btn-xs btn-info" -->
											<a title="下载文件"  href="#" onclick="down1('./upload/${p.img }')"  style="color:#f00;">
											点击下载</a>
											</c:if>
											</div>
											<div class="cf-column col-md-12"> 
												影像说明：${p.note }
											</div>
											</c:forEach>
											
											<div class="cf-column col-md-12"> 
												<b>诊断分析：</b> ${order.note }
											</div>
										</div>
									</form>
									<script type="text/javascript" src="skin/js/jquery.form.js"></script>
									<script type="text/javascript" src="skin/js/jquery.artdialog.js"></script>
									<script type="text/javascript" src="skin/js/iframetools.js"></script>
								</div>
							</div>
						</div>
						<aside class="sidebar col-md-3 inner-right" role="complementary">
							
						</aside>
					</div>
				</div>
			</div>
			<div class="for-bottom-padding"></div>
			 <jsp:include page="foot.jsp"></jsp:include>
		</div>
		 <jsp:include page="foot2.jsp"></jsp:include>