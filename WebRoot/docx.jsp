<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<title>医生信息详情</title>
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

	<body>
	

		<div id="container">
		
			<jsp:include page="top.jsp"></jsp:include>

			<div class="breadcrumb-wrapper">
				<div class="container">
					<div class="row">
						<div class="col-md-3 col-sm-4 bcid-cat">医生信息</div>
						<div class="col-md-9 col-sm-8 location"><i class="fa fa-map-marker"></i> &nbsp;<span>当前位置： <a href='javascript:;'>主页</a> > <a href='javascript:;'>医生信息</a></span></div>
					</div>
				</div>
			</div>
			<div class="page-container" id="innerpage-wrap">
				<div class="container">
					<div class="row">
						<div class="main col-md-9 inner-left" role="main">
							<div class="about-page-wrap">
								<div class="com-cnt page-content">
									${user.tname }<br/>
									<p>&nbsp;</p>
									<p>性别：${user.sex }</p>
									<p>学历：${user.xueli }</p>
									<p>专业：${user.profession }</p>
									<p>工作经验：${user.experience }</p>
									<p>电话：${user.tel }</p>
									<p>职位：${user.zhiwei }</p>
									<p>挂号费：5元</p>
									
									<img alt="" src="./upload/${user.img }" /><br/>
									${user.note }

									<div id="pages" class="page">
									
									
									</div>
								</div>
								<div id="contact-wrap">
								    <c:if test="${sessionScope.user!=null }">
									<h3 class="msg-title">
									
							        星期一：
							        <c:if test="${fweek<=1 }">
									  	<!-- 全天坐诊 -->
									  	<c:if test="${paiBan.yi==3 }"> 全天坐诊 |
									  		
									  				<a href="addOrder.do?fwid=${user.id }&stime=11">预约上午/</a>
									  			
											<c:if test="${fhour<=17 }">
									  			<a href="addOrder.do?fwid=${user.id }&stime=12">预约下午</a>
									  		</c:if>
									  	</c:if>
									  	<!-- 上午坐诊 -->
							        	<c:if test="${paiBan.yi==1 }"> 上午坐诊 |
							        		
							        				<a href="addOrder.do?fwid=${user.id }&stime=11">预约</a>
							        			
							        	</c:if>
							        	<!-- 下午坐诊 -->
							        	<c:if test="${paiBan.yi==2 }"> 下午坐诊 |
							        		
									  				<a href="addOrder.do?fwid=${user.id }&stime=12">预约</a>
									  			
							        	</c:if>
							        	<!-- 全天休息 -->
							        	<c:if test="${paiBan.yi==0 }"> 全天休息 </c:if>
							        </c:if>
							        <br>
							        <hr/>
							          星期二：
							        
							        	<c:if test="${fweek <= 2 }">
							        		<!-- 全天坐诊 -->
											<c:if test="${paiBan.er==3 }"> 全天坐诊 |
												
														<a href="addOrder.do?fwid=${user.id }&stime=21">预约上午/</a>
													
												
														<a href="addOrder.do?fwid=${user.id }&stime=22">预约下午</a>
													
											</c:if>
											<!-- 上午坐诊 -->
									        <c:if test="${paiBan.er==1 }"> 上午坐诊 |
									        	
									        			<a href="addOrder.do?fwid=${user.id }&stime=21">预约</a>
									        		
									        </c:if>
									        <!-- 下午坐诊 -->
									        <c:if test="${paiBan.er==2 }"> 下午坐诊 |
									        	
									        			<a href="addOrder.do?fwid=${user.id }&stime=22">预约</a>
									        		
									        </c:if>
									        <!-- 全天休息 -->
									        <c:if test="${paiBan.er==0 }"> 全天休息 </c:if>
							        	</c:if>
							        	
							        	<%-- <c:when test="${fweek < 2 }">
							        		<!-- 全天坐诊 -->
											<c:if test="${paiBan.er==3 }"> 全天坐诊 |
												<a href="addOrder.do?fwid=${user.id }&stime=21">预约上午/</a>
												<a href="addOrder.do?fwid=${user.id }&stime=22">预约下午</a>
											</c:if>
											<!-- 上午坐诊 -->
									        <c:if test="${paiBan.er==1 }"> 上午坐诊 |
									        	<a href="addOrder.do?fwid=${user.id }&stime=21">预约</a>
									        </c:if>
									        <!-- 下午坐诊 -->
									        <c:if test="${paiBan.er==2 }"> 下午坐诊 |
									        	<a href="addOrder.do?fwid=${user.id }&stime=22">预约</a>
									        </c:if>
									        <!-- 全天休息 -->
									        <c:if test="${paiBan.er==0 }"> 全天休息 </c:if>
							        	</c:when> --%>
							        
							        <br>
							        <hr/>
							          星期三：
							        
							        	<%-- <c:when test="${fweek != 3}">
								        	<!-- 全天坐诊 -->
											<c:if test="${paiBan.er==3 }"> 全天坐诊 |
												<a href="addOrder.do?fwid=${user.id }&stime=31">预约上午/</a>
												<a href="addOrder.do?fwid=${user.id }&stime=32">预约下午</a>
											</c:if>
											<!-- 上午坐诊 -->
									        <c:if test="${paiBan.er==1 }"> 上午坐诊 |
									        	<a href="addOrder.do?fwid=${user.id }&stime=31">预约</a>
									        </c:if>
									        <!-- 下午坐诊 -->
									        <c:if test="${paiBan.er==2 }"> 下午坐诊 |
									        	<a href="addOrder.do?fwid=${user.id }&stime=32">预约</a>
									        </c:if>
									        <!-- 全天休息 -->
									        <c:if test="${paiBan.er==0 }"> 全天休息 </c:if>
								        </c:when> --%>
								        <c:if test="${fweek <= 3 }">
								        	<!-- 全天坐诊 -->
											<c:if test="${paiBan.san==3 }"> 全天坐诊 |
												
														<a href="addOrder.do?fwid=${user.id }&stime=31">预约上午/</a>
													
												
														<a href="addOrder.do?fwid=${user.id }&stime=32">aa预约下午</a>
													
											</c:if>
									        <!-- 上午坐诊 -->
									        <c:if test="${paiBan.san==1 }"> 上午坐诊 |
									        	
														<a href="addOrder.do?fwid=${user.id }&stime=31">预约</a>
													
									        </c:if>
									        <!-- 下午坐诊 -->
									        <c:if test="${paiBan.san==2 }"> 下午坐诊 |
									        	
														<a href="addOrder.do?fwid=${user.id }&stime=32">预约</a>
													
									        </c:if>
									        <!-- 全天休息 -->
									        <c:if test="${paiBan.san==0 }"> 全天休息</c:if>
								        </c:if>
								        
							        
							        <br>
							        <hr/>
							          星期四：
							        
								        <%-- <c:when test="${fweek < 4 }">
								        	<!-- 全天坐诊 -->
											<c:if test="${paiBan.er==3 }"> 全天坐诊 |
												<a href="addOrder.do?fwid=${user.id }&stime=41">预约上午/</a>
												<a href="addOrder.do?fwid=${user.id }&stime=42">预约下午</a>
											</c:if>
											<!-- 上午坐诊 -->
									        <c:if test="${paiBan.er==1 }"> 上午坐诊 |
									        	<a href="addOrder.do?fwid=${user.id }&stime=41">预约</a>
									        </c:if>
									        <!-- 下午坐诊 -->
									        <c:if test="${paiBan.er==2 }"> 下午坐诊 |
									        	<a href="addOrder.do?fwid=${user.id }&stime=42">预约</a>
									        </c:if>
									        <!-- 全天休息 -->
									        <c:if test="${paiBan.er==0 }"> 全天休息 </c:if>
								        </c:when> --%>
								        <c:if test="${fweek <= 4 }">
								        	<!-- 全天坐诊 -->
											<c:if test="${paiBan.si==3 }"> 全天坐诊  |
												
														<a href="addOrder.do?fwid=${user.id }&stime=41">预约上午/</a>
													
														<a href="addOrder.do?fwid=${user.id }&stime=42">预约下午</a>
													
											</c:if>
											<!-- 上午坐诊 -->
									        <c:if test="${paiBan.si==1 }"> 上午坐诊 | 
									        	
														<a href="addOrder.do?fwid=${user.id }&stime=41">预约</a>
													
									        </c:if>
									        <!-- 下午坐诊 -->
									        <c:if test="${paiBan.si==2 }"> 下午坐诊 |
									        	
														<a href="addOrder.do?fwid=${user.id }&stime=42">预约</a>
													
									        </c:if>
									        <!-- 全天休息 -->
									        <c:if test="${paiBan.si==0 }"> 全天休息</c:if>
								        </c:if>
							        
							        <br>
									<hr/>
							          星期五：
							        
								        <%-- <c:when test="${fweek < 5 }">
								        	<!-- 全天坐诊 -->
											<c:if test="${paiBan.er==3 }"> 全天坐诊 |
												<a href="addOrder.do?fwid=${user.id }&stime=51">预约上午/</a>
												<a href="addOrder.do?fwid=${user.id }&stime=52">预约下午</a>
											</c:if>
											<!-- 上午坐诊 -->
									        <c:if test="${paiBan.er==1 }"> 上午坐诊 |
									        	<a href="addOrder.do?fwid=${user.id }&stime=51">预约</a>
									        </c:if>
									        <!-- 下午坐诊 -->
									        <c:if test="${paiBan.er==2 }"> 下午坐诊 |
									        	<a href="addOrder.do?fwid=${user.id }&stime=52">预约</a>
									        </c:if>
									        <!-- 全天休息 -->
									        <c:if test="${paiBan.er==0 }"> 全天休息 </c:if>
								        </c:when> --%>
								        <c:if test="${fweek <= 5 }">
									        <!-- 全天坐诊 -->
											<c:if test="${paiBan.wu==3 }"> 全天坐诊  |
												
														<a href="addOrder.do?fwid=${user.id }&stime=51">预约上午/</a>
													
														<a href="addOrder.do?fwid=${user.id }&stime=52">预约下午</a>
													
											</c:if>
											<!-- 上午坐诊 -->
									        <c:if test="${paiBan.wu==1 }"> 上午坐诊 |
									        	
														<a href="addOrder.do?fwid=${user.id }&stime=51">预约</a>
													
									        </c:if>
									        <!-- 下午坐诊 -->
									        <c:if test="${paiBan.wu==2 }"> 下午坐诊 |
									        	
														<a href="addOrder.do?fwid=${user.id }&stime=52">预约</a>
													
									        </c:if>
									        <!-- 全天休息 -->
									        <c:if test="${paiBan.wu==0 }"> 全天休息</c:if>
								        </c:if>
							        
							        
							        <br>
									<hr/>
							          星期六：
							        
								        <%-- <c:when test="${fweek < 6 }">
								        	<!-- 全天坐诊 -->
											<c:if test="${paiBan.er==3 }"> 全天坐诊 |
												<a href="addOrder.do?fwid=${user.id }&stime=61">预约上午/</a>
												<a href="addOrder.do?fwid=${user.id }&stime=62">预约下午</a>
											</c:if>
											<!-- 上午坐诊 -->
									        <c:if test="${paiBan.er==1 }"> 上午坐诊 |
									        	<a href="addOrder.do?fwid=${user.id }&stime=61">预约</a>
									        </c:if>
									        <!-- 下午坐诊 -->
									        <c:if test="${paiBan.er==2 }"> 下午坐诊 |
									        	<a href="addOrder.do?fwid=${user.id }&stime=62">预约</a>
									        </c:if>
									        <!-- 全天休息 -->
									        <c:if test="${paiBan.er==0 }"> 全天休息 </c:if>
								        </c:when> --%>
								        <c:if test="${fweek <= 6 }">
									        <!-- 全天坐诊 -->
											<c:if test="${paiBan.liu==3 }"> 全天坐诊  |
												
														<a href="addOrder.do?fwid=${user.id }&stime=61">预约上午/</a>
													
												
														<a href="addOrder.do?fwid=${user.id }&stime=62">预约下午</a>
													
											</c:if>
											<!-- 上午坐诊 -->
									        <c:if test="${paiBan.liu==1 }"> 上午坐诊 |
									        	
														<a href="addOrder.do?fwid=${user.id }&stime=61">预约</a>
													
									        </c:if>
									        <!-- 下午坐诊 -->
									        <c:if test="${paiBan.liu==2 }"> 下午坐诊 |
									        	
														<a href="addOrder.do?fwid=${user.id }&stime=62">预约</a>
													
									        </c:if>
									        <!-- 全天休息 -->
									        <c:if test="${paiBan.liu==0 }"> 全天休息</c:if>
								        </c:if>
							        
							        
							        <br>
									<hr/>
							          星期日：
							        
								        <%-- <c:when test="${fweek < 7 }">
								        	<!-- 全天坐诊 -->
											<c:if test="${paiBan.er==3 }"> 全天坐诊 |
												<a href="addOrder.do?fwid=${user.id }&stime=71">预约上午/</a>
												<a href="addOrder.do?fwid=${user.id }&stime=72">预约下午</a>
											</c:if>
											<!-- 上午坐诊 -->
									        <c:if test="${paiBan.er==1 }"> 上午坐诊 |
									        	<a href="addOrder.do?fwid=${user.id }&stime=71">预约</a>
									        </c:if>
									        <!-- 下午坐诊 -->
									        <c:if test="${paiBan.er==2 }"> 下午坐诊 |
									        	<a href="addOrder.do?fwid=${user.id }&stime=72">预约</a>
									        </c:if>
									        <!-- 全天休息 -->
									        <c:if test="${paiBan.er==0 }"> 全天休息 </c:if>
								        </c:when> --%>
								        <c:if test="${fweek <= 7 }">
									        <!-- 全天坐诊 -->
											<c:if test="${paiBan.tian==3 }"> 全天坐诊  |
												
														<a href="addOrder.do?fwid=${user.id }&stime=71">预约上午/</a>
													
												
														<a href="addOrder.do?fwid=${user.id }&stime=72">预约下午</a>
													
											</c:if>
											<!-- 上午坐诊 -->
									        <c:if test="${paiBan.tian==1 }"> 上午坐诊 | 
									        	
														<a href="addOrder.do?fwid=${user.id }&stime=71">预约</a>
													
									        </c:if>
									        <!-- 下午坐诊 -->
									        <c:if test="${paiBan.tian==2 }"> 下午坐诊 | 
									        	
														<a href="addOrder.do?fwid=${user.id }&stime=72">预约</a>
													
									        </c:if>
									        <!-- 全天休息 -->
									        <c:if test="${paiBan.tian==0 }"> 全天休息</c:if>
								        </c:if>
							        
							        <br>
									<hr/>
									
							        </h3>
									</c:if>
									<c:if test="${sessionScope.user==null }">
										<p>请登录后预约！</p>
										<h3 class="msg-title"><a href="login.jsp">登录</a></h3>
									</c:if>
									
									<script type="text/javascript" src="skin/js/jquery.form.js"></script>
									<script type="text/javascript" src="skin/js/jquery.artdialog.js"></script>
									<script type="text/javascript" src="skin/js/iframetools.js"></script>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="for-bottom-padding"></div>
			<jsp:include page="foot.jsp"></jsp:include>
		</div>
		<jsp:include page="foot2.jsp"></jsp:include>