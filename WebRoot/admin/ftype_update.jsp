<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="zh-cn">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
<meta name="renderer" content="webkit">
<title></title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<%-- <script src="js/pintuer.js"></script> --%>
</head>
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<script type="text/javascript">
	function checkName() {
		var name = $("#name").val();
		var tid = $("#tid").val();
		if(name==""||(name.length<1||name.length>12)){
			 $("#checku").html("名称不能为空且长度在1～12位之间！");
			 $("input[id=name]").focus();
		} else {
			$("#checku").html("");
			$.ajax({
				url : "updateFtype.do",
				type : "post",
				data : {"name":name,"id":tid},
				dataType : "json",
				success:function(result){
				if(result.info=="ok"){
				$("#checku").html("名称存在，请更换！");
				return false;
				}else{
				$("#checku").html("更新成功！");
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
	</script>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>更新内容</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="updateFtype.do"  > <!--  enctype="multipart/form-data" -->
      <div class="form-group">
        <div class="label">
          <label>名称：</label>
        </div>
        <div class="field">
          <input type="text" id="name" name="name" class="input w50" value="${ftype.name }" onblur="return checkName();"/>
          <input type="hidden"  name="id" id="tid" class="input tips" value="${ftype.id }" />
          <div id="checku"></div>
        </div>
        </div>
        <div class="form-group">
        <div class="label">
          <label></label>
        </div>
    	<div class="field">
          <!-- <button class="button bg-main icon-check-square-o" type="submit"> 提交</button> -->
        </div>
        </div>
    </form>
  </div>
</div>

</body></html>