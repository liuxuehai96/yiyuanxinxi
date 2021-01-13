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
	<title>医院在线预约挂号系统</title>
	<meta name="description" content="" />
	<meta name="keywords" content="" />
	<meta name="author" content="order by website" />
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

<body>
	<div id="container">
		<jsp:include page="top.jsp"></jsp:include>
			<section id="home-section" class="slider1">
				<div class="tp-banner-container">
					<div class="tp-banner">
						<ul>
							<c:forEach items="${list }" var="n">
								<li data-transition="fade" data-slotamount="7" data-masterspeed="500" data-saveperformance="on" data-title="banner1"> 
									<img src="./upload/${n.img }" alt="${n.name }" data-bgposition="center top" data-bgfit="cover" data-bgrepeat="no-repeat" /> 
								</li>
							</c:forEach>
						</ul>
						<div class="tp-bannertimer"></div>
					</div>
				</div>
			</section>
			<section class="services-section" id="index-cate">
				<div class="container">
					<h3 class="text-center section-title"><a href="doctor.do">医生队伍</a></h3>
					<div class="services-box">
						<div class="row">
							<c:forEach items="${ulist }" var="u">
								<div class="col-md-3 col-sm-6 col-xs-6 cate-itme">
									<div class="services-post">
										<a href="showUser.do?id=${u.id }" class="thumb-link"><img src="./upload/${u.img }" /></a>
										<div class="services-content">
											<h2><a href="showUser.do?id=${u.id }">${u.tname }</a></h2>
											<p>学历：${u.xueli }    经验：${u.experience } </p>
											<a href="showUser.do?id=${u.id }" class="readmore">查看更多 <i class="fa fa-angle-double-right"></i></a>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</section>
			
			<section class="news-section" id="index-news">
				<div class="container">
					<h3 class="text-center section-title"><a href="news.do">医院动态</a> </h3>
					<div class="news-box owl-wrapper">
						<div class="owl-carousel" data-num="4">
							<c:forEach items="${list2 }" var="n2">
								<div class="item news-post">
									<div class="news-gallery">
										<a href="showNew.do?id=${n2.id }" class="thumb-link">
										<img src="./upload/${n2.img }" alt="${n2.name }" width="312px" height="208px;"/></a>
										<div class="date-post">
											<p>${n2.pubtime.substring(0,7) } <span>${n2.pubtime.substring(8,10) }</span></p>
										</div>
									</div>
									<div class="news-content"><!-- .substring(0,20) -->
										<h2 class="inews-title"><a href="showNew.do?id=${n2.id }" title="${n2.name }">${n2.name }</a></h2>
										<p class="desc"><%-- ${n2.note } --%>...</p>
										<a href="showNew.do?id=${n2.id }" class="news-readmore">阅读更多 <i class="fa fa-angle-right"></i></a>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</section>
		<jsp:include page="foot.jsp"></jsp:include>	
	</div>
	<script type="text/javascript" src="skin/js/jquery.migrate.js"></script>
	<!--<script type="text/javascript" src="skin/js/jquery.bxslider.min.js"></script>-->
	<script type="text/javascript" src="skin/js/jquery.imagesloaded.min.js"></script>
	<script type="text/javascript" src="skin/js/retina-1.1.0.min.js"></script>
	<script type="text/javascript" src="skin/js/jquery.themepunch.tools.min.js"></script>
	<script type="text/javascript" src="skin/js/jquery.themepunch.revolution.min.js"></script>
	<script type="text/javascript" src="skin/js/script.js"></script>
	<nav id="mmenu" class="noDis">
		<div class="mmDiv">
			<div class="MMhead">
				<a href="#mm-0" class="closemenu noblock">X</a>	
			</div>
			<ul>
				<li class="m-Lev1 m-nav_0">
					<a href="index.do">网站首页</a>
				</li>
				<li class="m-Lev1 m-nav_4">
					<a href="news.do" class="menu1 ">医院动态</a>
				</li>
				<li class="m-Lev1 m-nav_4">
					<a href="doctor.do" class="menu1 ">医生信息</a>
				</li>
				<li class="m-Lev1 m-nav_4">
					<a href="bbsList.do" class="menu1 ">建议反馈</a>
				</li>
				<c:if test="${sessionScope.user==null }">
					<li class="m-Lev1 m-nav_4" >
						<a href="login.do" class="menu1 ">登录</a>
					</li>
					<li class="m-Lev1 m-nav_4">
						<a href="regist.jsp" class="menu1 ">注册</a>
					</li>
				</c:if>
				<c:if test="${sessionScope.user!=null }">
					<li class="m-Lev1 m-nav_4">
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
					
					<li class="m-Lev1 m-nav_4">
						<a href="loginout.do" class="menu1 ">注销</a>
					</li>
				</c:if>
			</ul>
		</div>
	</nav>
	<link type="text/css" rel="stylesheet" href="skin/css/jquery.mmenu.all.css" />
	<script type="text/javascript" src="skin/js/jquery.mmenu.all.min.js"></script>
	<script type="text/javascript">
		jQuery(document).ready(function($) {
			var mmenu = $('nav#mmenu').mmenu({
				slidingSubmenus: true,
				classes: 'mm-white', //mm-fullscreen mm-light
				extensions: ["theme-white"],
				offCanvas: {
					position: "right", //left, top, right, bottom
					zposition: "front" //back, front,next
					//modal		: true
				},
				searchfield: false,
				counters: false,
				//navbars		: {
				//content : [ "prev", "title", "next" ]
				//},
				navbar: {
					title: "网站导航"
				},
				header: {
					add: true,
					update: true,
					title: "网站导航"
				}
			});
			$(".closemenu").click(function() {
				var mmenuAPI = $("#mmenu").data("mmenu");
				mmenuAPI.close();
			});
		});
	</script>
</body>

</html>
