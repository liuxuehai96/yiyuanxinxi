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
<form method="post" action="orderList_zdSearch.do" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
     <!--   <li> <a class="button border-main icon-plus-square-o" href="doAddForder.do"> 添加</a> </li> -->
        <li> <select name="uid" class="input" >
        <option value=""> 请选择病人...</option>
        <c:forEach items="${blist }" var="b">
        <c:forEach items="${ulist }" var="u">
        <c:if test="${b.uid==u.id }">
        <option value="${u.id }"> ${u.tname }</option>
        </c:if>
        </c:forEach></c:forEach></select> </li>
       <li>
         <input type="submit"  class="button border-main icon-search" value="搜索" ></li>
      </ul>
    </div>
    <table class="table table-hover text-center">
      <tr>
        <th>挂号单号</th>
        <th>病志号</th>
        <th>病人姓名</th>
        <th>预约医生</th>
        <th>性别</th>
        <th>年龄</th>
        <th>科室</th>
        <th>过敏史</th>
        <th>既往史</th>
        <th>价格</th>
        <th>挂号时间</th>
        <th>预约时间</th>
        <th>目前状态</th>
        <th width="300">操作</th>
      </tr>
       <c:forEach items="${list }" var="o">
        <tr>
         <td>${o.id}</td>
         <td>${o.uid}</td>
         <td> <c:forEach items="${ulist }" var="u">
         <c:if test="${u.id==o.uid }">
          ${u.tname}</c:if></c:forEach></td>
          <td> <c:forEach items="${ulist }" var="u">
         <c:if test="${u.id==o.fwid }">
          ${u.tname}</c:if></c:forEach></td>
         <td> <c:forEach items="${ulist }" var="u">
         <c:if test="${u.id==o.uid }">
         ${u.sex}</c:if></c:forEach></td>
         <td> <c:forEach items="${ulist }" var="u">
         <c:if test="${u.id==o.uid }">
         ${u.age}</c:if></c:forEach></td>
         <td> <c:forEach items="${flist }" var="f">
         	<c:if test="${f.id==o.fid }">
          ${f.name}</c:if></c:forEach></td>
         <td> <c:forEach items="${ulist }" var="u">
         <c:if test="${u.id==o.uid }">
         ${u.guoming}</c:if></c:forEach></td>
         <td> <c:forEach items="${ulist }" var="u">
         <c:if test="${u.id==o.uid }">
         ${u.note}</c:if></c:forEach></td>
         <td>${o.price}</td>
         <td>${o.pubtime}</td>
         <td>${o.etime}</td>
         <td>${o.status}</td>
          <td><div class="button-group"> 
            <c:if test="${o.status=='待诊断' }">
           <%--  <a class="button border-main" href="liShi.do?fid=${o.fid }"><span class="icon-edit"></span>历史记录</a>  --%>
            <a class="button border-main" href="doAddPic.do?fid=${o.id }"><span class="icon-edit"></span>添加影像</a> 
            <a class="button border-main" href="doAddpj.do?id=${o.id }"><span class="icon-edit"></span>添加结果</a> 
            <a class="button border-red" href="deleteForder.do?id=${o.id }" onclick="return del(1,1,1)"><span class="icon-trash-o"></span>删除</a>
            </c:if>
           </div></td>
       
        </tr>
       </c:forEach>
       
       
   		 
      <tr>
     <c:if test="${sessionScope.p==1 }">
		<td colspan="15"> <c:if test="${page.page>1}">
              <a href="forderList.do?page=1" >首页</a>
             <a href="forderList.do?page=${page.page-1 }"> 上一页</a> 
             </c:if>
    	     <c:if test="${page.page<page.totalPage}">
			<a href="forderList.do?page=${page.page+1 }">下一页</a>
			<a href="forderList.do?page=${page.totalPage }">末页</a>
		    </c:if> 
		</td>
	</c:if>
	<c:if test="${sessionScope.p==2 }">
	<td colspan="15"> <c:if test="${page.page>1}">
    <a href="orderList_zdSearch.do?page=1" >首页</a>
    <a href="orderList_zdSearch.do?page=${page.page-1 }"> 上一页</a> 
    </c:if>
    	<c:if test="${page.page<page.totalPage}">
			<a href="orderList_zdSearch.do?page=${page.page+1 }">下一页</a>
			<a href="orderList_zdSearch.do?page=${page.totalPage }">末页</a>
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