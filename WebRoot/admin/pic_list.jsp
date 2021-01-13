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
<script src="js/pintuer.js"></script>
</head>
<body>
<form method="post" action="selectPicList.do" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href="" style="float:right; display:none;"></a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
     <!--   <li> <a class="button border-main icon-plus-square-o" href="doAddForder.do"> 添加</a> </li> -->
        <li> <input type="text" name="name" class="input"  placeholder="请输入搜索的器官的名称"></li>
        <li> <input type="text" name="ctype" class="input"  placeholder="请输入搜索的图像类型"></li>
        <li><select name="uid" class="input " >
          <option value="">请选择病人</option>
          <c:forEach items="${blist }" var="b"> 
         <c:forEach items="${ulist }" var="u">
         <c:if test="${u.id==b.uid }">
          <option value="${u.id }">${u.id}（${u.tname }）</option></c:if>
          </c:forEach> </c:forEach>
        </select>
       <li>
         <input type="submit"  class="button border-main icon-search" value="搜索" ></li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th>病志号</th>
        <th>病人姓名</th>
        <th>器官名称</th>
        <th>图像类型</th>
        <th>切面</th>
        <th>图片</th>
        <th>医生编号</th>
        <th>操作</th>
      </tr>
       <c:forEach items="${list }" var="o">
        <tr>
          <td> ${o.uid}</td>
          <td> 
          <c:forEach items="${ulist }" var="u">
          <c:if test="${u.id==o.uid }">${u.tname }</c:if>
          </c:forEach></td>
          <td> ${o.name}</td>
          <td> ${o.ctype}</td>
          <td> ${o.qiemian}</td>
          <td><img src="../upload/${o.img}"  width="50" height="50" ></td>
          <td><c:forEach items="${olist }" var="d">
          <c:if test="${d.id==o.fid }">${d.fwid }</c:if></c:forEach></td>
          <td><div class="button-group"> 
          <a class="button border-main" href="doUpdatePic.do?id=${o.id }"><span class="icon-edit"></span>修改</a> 
          <a class="button border-red" href="deletepic.do?id=${o.id }" ><span class="icon-trash-o"></span>删除</a>
           </div></td>
        </tr>
       </c:forEach>
       
       
   		 
      <tr>
     <c:if test="${sessionScope.p==1 }">
		<td colspan="12"> <c:if test="${page.page>1}">
              <a href="picList.do?page=1" >首页</a>
             <a href="picList.do?page=${page.page-1 }"> 上一页</a> 
             </c:if>
    	     <c:if test="${page.page<page.totalPage}">
			<a href="picList.do?page=${page.page+1 }">下一页</a>
			<a href="picList.do?page=${page.totalPage }">末页</a>
		    </c:if> 
		</td>
	</c:if>
	<c:if test="${sessionScope.p==2 }">
	<td colspan="12"> <c:if test="${page.page>1}">
    <a href="selectPicList.do?page=1" >首页</a>
    <a href="selectPicList.do?page=${page.page-1 }"> 上一页</a> 
    </c:if>
    	<c:if test="${page.page<page.totalPage}">
			<a href="selectPicList.do?page=${page.page+1 }">下一页</a>
			<a href="selectPicList.do?page=${page.totalPage }">末页</a>
		</c:if> 
		</td>
		</c:if>
      </tr>
    </table>
  </div>
</form>
<script type="text/javascript">

//搜索
function changesearch(){	
		
}

//单个删除
function del(id,mid,iscid){
	if(confirm("您确定要删除吗?")){
		return true;
	}else{
		return false;
	}
}



</script>
</body>
</html>