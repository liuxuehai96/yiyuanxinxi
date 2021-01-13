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
<script type="text/javascript" src="js/jquery-1.11.0.min.js"></script>
<%-- <script src="js/pintuer.js"></script> --%>
<script charset="utf-8" src="/yiyuanxinxi/kindeditor/kindeditor.js"></script>
<script charset="utf-8" src="/yiyuanxinxi/kindeditor/lang/zh-CN.js"></script>
<script>
	        KindEditor.ready(function(K) {
	                window.editor = K.create('#editor_id');
	        });
	</script>
	<script>

KindEditor.ready(function(K) {

K.create('textarea[name="note"]', {

uploadJson : '/yiyuanxinxi/kindeditor/jsp/upload_json.jsp',

                fileManagerJson : '/yiyuanxinxi/kindeditor/jsp/file_manager_json.jsp',

                allowFileManager : true,

                allowImageUpload : true, 

autoHeightMode : true,

afterCreate : function() {this.loadPlugin('autoheight');},

afterBlur : function(){ this.sync(); }  //Kindeditor下获取文本框信息

});

});

</script>

</head>
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>增加内容</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="addPaiBan.do"  >  
      
       <div class="form-group">
        <div class="label">
          <label>医生：</label>
        </div>
        <div class="field">
         <select name="uid" class="input w50" >
         <c:forEach items="${ulist }" var="u">
         <option value="${u.id }">${u.tname }</option>
         </c:forEach>
         </select>
          <div id='' class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>星期一：</label>
        </div>
        <div class="field">
        <input type="radio" name="yi" value="3" >全天坐诊
        
        <input type="radio" name="yi" value="1" >上午坐诊
        <input type="radio" name="yi" value="2" >下午坐诊
        <input type="radio" name="yi" value="0" >全天休息
          <div id='' class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>星期二：</label>
        </div>
        <div class="field">
        <input type="radio" name="er" value="3" >全天坐诊
        <input type="radio" name="er" value="1" >上午坐诊
        <input type="radio" name="er" value="2" >下午坐诊
        <input type="radio" name="er" value="0" >全天休息
          <div id='' class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>星期三：</label>
        </div>
        <div class="field">
        <input type="radio" name="san" value="3" >全天坐诊
        <input type="radio" name="san" value="1" >上午坐诊
        <input type="radio" name="san" value="2" >下午坐诊
        <input type="radio" name="san" value="0" >全天休息
          <div id='' class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>星期四：</label>
        </div>
        <div class="field">
        <input type="radio" name="si" value="3" >全天坐诊
        <input type="radio" name="si" value="1" >上午坐诊
        <input type="radio" name="si" value="2" >下午坐诊
        <input type="radio" name="si" value="0" >全天休息
          <div id='' class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>星期五：</label>
        </div>
        <div class="field">
        <input type="radio" name="wu" value="3" >全天坐诊
        <input type="radio" name="wu" value="1" >上午坐诊
        <input type="radio" name="wu" value="2" >下午坐诊
        <input type="radio" name="wu" value="0" >全天休息
          <div id='' class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>星期六：</label>
        </div>
        <div class="field">
        <input type="radio" name="liu" value="3" >全天坐诊
        <input type="radio" name="liu" value="1" >上午坐诊
        <input type="radio" name="liu" value="2" >下午坐诊
        <input type="radio" name="liu" value="0" >全天休息
          <div id='' class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>星期天：</label>
        </div>
        <div class="field">
        <input type="radio" name="tian" value="3" >全天坐诊
        <input type="radio" name="tian" value="1" >上午坐诊
        <input type="radio" name="tian" value="2" >下午坐诊
        <input type="radio" name="tian" value="0" >全天休息
          <div id='' class="tips"></div>
        </div>
      </div>
      <!-- <div class="form-group">
          <div class="label">
            <label>职位：</label>
          </div>
          <div class="field">
           <input  type="text" name="zhiwei" class="input w50"  />
            <div  id='' class="tips"></div>
          </div>
        </div> -->
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit" onclick="return checkAll()"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body></html>