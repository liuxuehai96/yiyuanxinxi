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
	<title>注册</title>
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
	function checkuname() {
		var name = $("#username").val();
		if(name==""||(name.length<3||name.length>12)){
			 $("#checku").html("用户账号不能为空且长度在3～12位之间！");
			 $("input[id=username]").focus();
		} else {
			$("#checku").html("");
			$.ajax({
				url : "checkUname.do",
				type : "post",
				data : "uname=" + name,
				
				dataType : "json",
				success:function(result){
				if(result.info=="ng"){
				$("#checku").html("用户名存在，请更换！");
				$("input[id=username]").focus();
				return false;
				}else{
				$("#checku").html("可以用");
				return true;
				}
				},
				error:function(){
				alert("请求失败！");
				return false;
				}

			});
		}

	}
	 function checkpwd(){
		  var pwd=$("#txtPwd").val();
		  if(pwd==""||pwd.length<6||pwd.length>12){
		 $("#prompt_pwd").html("密码不能为空且长度在6～12位之间！");
		 $("input[id=txtPwd]").html("");
		  $("input[id=txtPwd]").focus();
		 return false;
		}else {
		 $("#prompt_pwd").html("密码格式正确！");
		return true;}

		}

		function checkpwdc(){
		var pwd=$("#txtPwd").val();
		var pwdc=$("#txtConfirmPwd").val();
		if(pwdc==""||pwdc!=pwd){
		  $("#prompt_confirmpwd").html("两次密码要一致 ！");
		  $("input[id=txtConfirmPwd]");
		   // $("input[name=passwordc]").focus();
		  return false;
		  }else{
		  $("#prompt_confirmpwd").html("密码一致 ！");
		  return true;}

		}

		 function checktel(){
		 var tel=$("#txtPhone").val();
		 var regtel=/^(13|14|15|16|17|18|19)\d{9}$/;
		 if(tel==""||!regtel.test(tel)){
		  $("#prompt_phone").html("电话号码不能为空，且以13，14，15，16，17，18,19开头的11位");
		  $("input[id=txtPhone]").html("");
		   $("input[id=txtPhone]").focus();
		  return false;
		  }else{
		   $("#prompt_phone").html("电话号码格式正确！");
		  return true;
		  }

		 }
		 function checkname1(){ 
			  var uname=$("#txtName1").val();
			  if(uname==""||uname.length<2){
			  $("#prompt_name1").html("不能为空且长度在2位以上！");
			  $("input[id=txtName1]").html("");
			   $("input[id=txtName1]").focus();
			  return false;
			  }else{
			  
			  $("#prompt_name1").html("ok");
			  return true;
			  }} 
		  
		 function checkname2(){ 
			  var uname=$("#txtName2").val();
			  if(uname==""||uname.length<2){
			  $("#prompt_name1").html("不能为空且长度在2位以上！");
			  $("input[id=txtName2]").html("");
			   $("input[id=txtName2]").focus();
			  return false;
			  }else{
			  
			  $("#prompt_name2").html("ok");
			  return true;
			  }} 
		  
		  
	function check(){
		var flag= checkuname() 
		      &&checkpwd()
			  &&checkpwdc()
			  &&checktel()
			  && checkname1() && checkname2();
		//alert(flag);
		return flag;
	}
	function checkAll(){
		//alert("kkkkk");
		var flag=false;
		var aa = $("#username").val();
		var pwd=$("#txtPwd").val();
		var pwdc=$("#txtConfirmPwd").val();
		var tel=$("#txtPhone").val();
		var name1=$("#txtName1").val();
		var name2=$("#txtName2").val();
		 var regtel=/^(13|14|15|16|17|18|19)\d{9}$/;
		 if(aa!=""&&(tel!=""||regtel.test(tel))&&(name1!=""||name1.length>2)&&(name2!=""||name2.length>2)&&
				 (pwd!=""||pwd.length>6||pwd.length<12)&&(pwdc!=""||pwdc==pwd)){
			 flag=true;
		 }else{
			 alert("注册信息不完整或不合规范！");
		 }
		
		return flag;
		
	}
</script>
	<body>
		<div id="container">
		
			<jsp:include page="top.jsp"></jsp:include>

			<div class="breadcrumb-wrapper">
				<div class="container">
					<div class="row">
						<div class="col-md-3 col-sm-4 bcid-cat">注册</div>
						<div class="col-md-9 col-sm-8 location"><i class="fa fa-map-marker"></i> &nbsp;<span>当前位置： <a href='javascript:;'>主页</a> > <a href='javascript:;'>注册</a></span></div>
					</div>
				</div>
			</div>
			<div class="page-container" id="innerpage-wrap">
				<div class="container">
					<div class="row">
						<div class="main col-md-9 inner-left" role="main">
							<div class="about-page-wrap">
								<div id="contact-wrap">
									<h3 class="msg-title">用户注册</h3>
									<form class="add-msg-form" action="reg.do" onsubmit="return check();"  method="post"><!-- enctype="multipart/form-data" -->
										<div class="row">
											<div class="cf-column col-md-12"> 
												<input name="uname" id="username" type="text" placeholder="登录名" onblur="return checkuname()" validate="minlength:2, maxlength:50, required:true" />
												<span id='checku' class="spanInit" ></span>
											</div>
											<div class="cf-column col-md-12"> 
												<input name="tname"  type="text" placeholder="真实姓名" onblur="return checkuname2()"   validate="minlength:2, maxlength:50, required:true" />
												<span id='checku2' class="spanInit" ></span>
											</div>
											
											<div class="cf-column col-md-12">
												<input name="pwd" id="txtPwd" type="password" placeholder="密码"  onblur="return checkpwd()" validate="maxlength:40, required:true, email:true" />
												<span id='prompt_pwd'  class="spanInit" ></span>
											</div>
											<div class="cf-column col-md-12">
												<input name="pwdc" id="txtConfirmPwd" type="password" placeholder="确认密码"  onblur="return checkpwdc()" validate="maxlength:40, required:true, email:true" />
												<span id='prompt_confirmpwd' class="spanInit"  ></span>
											</div>
											
											<div class="cf-column col-md-12">
											<label>性别：</label>
											<select name="sex" >
											<option value="男">男</option>
											<option value="女">女</option>
											</select>
												<span id='' class="spanInit" ></span>
											</div>
											<div class="cf-column col-md-12">
											<label>年龄：</label>
												<input name="age" id="age" type="text" placeholder="请输入年龄" required="required"   />
												<span id='age' class="spanInit" ></span>
											</div>
											<div class="cf-column col-md-12">
											<label>电话：</label>
												<input name="tel" id="txtPhone" type="text" placeholder="电话/手机"  id="txtPhone" onblur="return checktel()"  />
												<span id='prompt_phone' class="spanInit" ></span>
											</div>
											
											<div class="cf-column col-md-12">
											<label>家庭住址：</label>
												<input name="address" id="address" type="text" placeholder="地址" validate="minlength:6,maxlength:40, required:true" />
											</div>
											<div class="cf-column col-md-12">
											<label>过敏说明：</label>
												<input name="guoming" id="guoming" type="text" placeholder="过敏说明"  />
											</div>
											<div class="cf-column col-md-12">
											<label>既往史：</label>
											<textarea rows="" cols="" style="with:100%;height:10%" name="note">
											</textarea>
											</div>
											<div class="cf-column col-md-12 submit-column">
												<button type="submit" id="submit-btn" class="submit-button" onclick="return checkAll()">注册</button>
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