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
<form method="post" action="goodsList_search.do" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
       <li> <a class="button border-main icon-plus-square-o" href="doAddGoods_admin.do"> 添加</a> </li>
        <li><input type="text" name="name" placeholder="请输入药品的名称" class="input" /></li>
        <%-- <li> <select name="fid" class="input" >
        <option value=""> 请选择类型...</option>
        <c:forEach items="${tlist }" var="t">
        <option value="${t.id }">${t.name }(${t.btype })</option>
        </c:forEach>
        </select> </li> --%>
        
       <li>
         <input type="submit"  class="button border-main icon-search" value="搜索" ></li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th>药品名称</th>
        <th>图片</th>
        <!-- <th>所属类别</th> -->
        <th>生产日期</th>
        <th>保质期</th>
        <th>库存数量</th>
        <th>发布时间</th>
        <th width="300">操作</th>
      </tr>
       <c:forEach items="${list }" var="o">
        <tr>
        <td>${o.name}</td>
        <td> <img src="../upload/${o.img}"  width="50" height="50" ></td>
       <%--  <td> <c:forEach items="${tlist }" var="t">
         <c:if test="${t.id==o.fid }">
          ${t.name}</c:if></c:forEach>
         </td> --%>
         <%--  <td>${o.saddr}--${o.eaddr}</td>
          <td>${o.hetime}</td> --%>
          <td>${o.stime}</td>
          <td>${o.etime}</td>
          <td>${o.kcnum}</td>
          <td>${o.pubtime}</td>
          <td><div class="button-group">
          <c:if test="${sesssionScope.auser.utype=='管理员' }"></c:if>
          <a class="button border-main" href="doUpdateGoods_admin.do?id=${o.id }"><span class="icon-edit"></span>编辑</a> 
          <a class="button border-red" href="deleteGoods_admin.do?id=${o.id }" ><span class="icon-trash-o"></span>删除</a>
           </div></td>
        </tr>
       </c:forEach>
      <tr>
     <c:if test="${sessionScope.p==1 }">
		<td colspan="15"> <c:if test="${page.page>1}">
              <a href="goodsList.do?page=1" >首页</a>
             <a href="goodsList.do?page=${page.page-1 }"> 上一页</a> 
             </c:if>
    	     <c:if test="${page.page<page.totalPage}">
			<a href="goodsList.do?page=${page.page+1 }">下一页</a>
			<a href="goodsList.do?page=${page.totalPage }">末页</a>
		    </c:if> 
		</td>
	</c:if>
	<c:if test="${sessionScope.p==2 }">
	<td colspan="15"> <c:if test="${page.page>1}">
    <a href="goodsList_search.do?page=1" >首页</a>
    <a href="goodsList_search.do?page=${page.page-1 }"> 上一页</a> 
    </c:if>
    	<c:if test="${page.page<page.totalPage}">
			<a href="goodsList_search.do?page=${page.page+1 }">下一页</a>
			<a href="goodsList_search.do?page=${page.totalPage }">末页</a>
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