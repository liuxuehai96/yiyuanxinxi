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
<title>更新用户信息</title>
<link rel="stylesheet" href="css/pintuer.css">
<link rel="stylesheet" href="css/admin.css">
<script src="js/jquery.js"></script>
<%-- <script src="js/pintuer.js"></script> --%>
</head>
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
<body>
<div class="panel admin-panel">
  <div class="panel-head" id="add"><strong><span class="icon-pencil-square-o"></span>更新内容</strong></div>
  <div class="body-content">
    <form method="post" class="form-x" action="updateUser_ys.do" enctype="multipart/form-data"  >  
      <div class="form-group">
        <div class="label">
          <label>用户名：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${user.uname }" name="uname"  readonly="readonly"/>
          <input type="hidden"  value="${user.id }" name="id" />
          <div class="tips"></div>
        </div>
      </div>
      <div class="form-group">
        <div class="label">
          <label>科室：</label>
        </div>
        <div class="field">
         <select name="fid" class="input w50" >
         <c:forEach items="${flist }" var="f">
         <c:if test="${f.id==user.fid }">
         <option value="${f.id }">${f.name }</option>
         </c:if>
         </c:forEach>
         <c:forEach items="${flist }" var="f">
         <c:if test="${f.id!=user.fid }">
         <option value="${f.id }">${f.name }</option>
         </c:if>
         </c:forEach>
         </select>
          <div id='' class="tips"></div>
        </div>
      </div>
      <div class="form-group">
          <div class="label">
            <label>职位：</label>
          </div>
          <div class="field">
           <input  type="text" name="zhiwei" class="input w50" value="${user.zhiwei }" />
            <div  id='' class="tips"></div>
          </div>
        </div>
       <div class="form-group">
        <div class="label">
          <label>密码：</label>
        </div>
        <div class="field">
          <input type="text" class="input w50" value="${user.pwd }" name="pwd"  />
          <div class="tips"></div>
        </div>
      </div>
       <div class="form-group">
        <div class="label">
          <label>电话：</label>
        </div>
        <div class="field">
          <input type="text" id="url1" name="tel" class="input w50" value="${user.tel}" />
        </div>
      </div> 
      <div class="form-group">
        <div class="label">
          <label>性别：</label>
        </div>
        <div class="field">
          <input type="text" id="url1" name="sex" class="input w50" value="${user.sex}" />
        </div>
      </div> 
       <div class="form-group">
        <div class="label">
          <label class="form-label col-xs-4 col-sm-2" >图片上传：</label>
        </div>
        <div class="field" class="formControls col-xs-8 col-sm-9">
        		<input type="file" id="url1" name="file" class="input tips" style="width:25%; float:left;" 
					 value="图片上传"  data-toggle="hover" data-place="right" data-image="" />
        </div>
      </div>
     <div class="form-group">
          <div class="label">
            <label>学历：</label>
          </div>
          <div class="field">
           <input  type="text" name="xueli" class="input w50" value="${user.xueli }"  />
            <div  id='' class="tips"></div>
          </div>
        </div>
       <div class="form-group">
          <div class="label">
            <label>专业：</label>
          </div>
          <div class="field">
           <input  type="text" name="profession" class="input w50"  value="${user.profession }"  />
            <div  id='' class="tips"></div>
          </div>
        </div>
        <div class="form-group">
          <div class="label">
            <label>工作经验：</label>
          </div>
          <div class="field">
           <input  type="text" name="experience" class="input w50"  value="${user.experience }" />
            <div  id='' class="tips"></div>
          </div>
        </div>
        
         <div class="form-group">
          <div class="label">
            <label>介绍：</label>
          </div>
          <div class="field" style="padding-top:8px;"> 
           <textarea rows="10" cols="80" name="note"> ${user.note }
           </textarea>
          </div>
        </div>
      <div class="form-group">
        <div class="label">
          <label></label>
        </div>
        <div class="field">
          <button class="button bg-main icon-check-square-o" type="submit"> 提交</button>
        </div>
      </div>
    </form>
  </div>
</div>

</body></html>