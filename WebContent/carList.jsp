<%@page import="model.Car"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> <!DOCTYPE html>
<html>
<head>
<meta charset="utf-8"/>
<title>二手车后台管理系统-所有二手列表</title>
<meta name="author" content="DeathGhost" />
<link rel="stylesheet" type="text/css" href="css/style.css">
<script src="js/jquery.js"></script>
<script src="js/jquery.mCustomScrollbar.concat.min.js"></script>
<script>

	(function($){
		$(window).load(function(){
			
			$("a[rel='load-content']").click(function(e){
				e.preventDefault();
				var url=$(this).attr("href");
				$.get(url,function(data){
					$(".content .mCSB_container").append(data); //load new content inside .mCSB_container
					//scroll-to appended content 
					$(".content").mCustomScrollbar("scrollTo","h2:last");
				});
			});
			
			$(".content").delegate("a[href='top']","click",function(e){
				e.preventDefault();
				$(".content").mCustomScrollbar("scrollTo",$(this).attr("href"));
			});
			
		});
	})(jQuery);
</script>
</head>
<body>
<form method="post" action="" id="listform">
  <div class="panel admin-panel">
    <div class="panel-head"><strong class="icon-reorder"> 内容列表</strong> <a href="" style="float:right; display:none;">添加字段</a></div>
    <div class="padding border-bottom">
      <ul class="search" style="padding-left:10px;">
        <li> <a class="button border-main icon-plus-square-o" href="add.html"> 添加内容</a> </li>
        <li>首页
        <li>
          <input type="text" placeholder="请输入搜索关键字" name="keywords" class="input" style="width:250px; line-height:17px;display:inline-block" />
          <a href="javascript:void(0)" class="button border-main icon-search" onclick="changesearch()" > 搜索</a></li>
      </ul>
    </div>
   <table class="table">
       <tr bgcolor=lightblue>
        <th>图片</th>
        <th>品牌</th>
        <th>系列</th>
        <th>公里数</th>
        <th>售价</th>
        <th>颜色</th>
        <th>排量</th>
        <th>操作</th>
       </tr>
       <%  List<Car>  cars=(List<Car>)request.getAttribute("cars");
       		for(Car c:cars){
       %>
	       <tr>
	        <td class="center"><img src="<%=c.getZhaopian() %>"  style="width: 100px;height: 80px;border:1px solid black;"/> </td>
	        <td class="center"><%=c.getPinpaiming() %> </td>
	        <td class="center"><%=c.getXilie() %></td>
	        <td class="center"><%=c.getGonglishu() %></td>
	        <td class="center"><%=c.getShoujia() %></td>
	        <td class="center"><%=c.getYanse() %></td>
	        <td class="center"><%=c.getPailiang() %></td>
	        <td class="center">
	         <a href="CarServlet?method=getCarInfo&carid=<%=c.getCarid() %>" title="编辑" class="link_icon">&#101;</a>
	         <a href="javascript:if(window.confirm('您确认要删除这条数据吗?')){location.href='CarServlet?method=delete&carid=<%=c.getCarid() %>'}" title="删除" class="link_icon">&#100;</a>
	        </td>
	       </tr>
       <%} %>
    
      </table>
      <aside class="paging">
       <a>第一页</a>
       <a>1</a>
       <a>2</a>
       <a>3</a>
       <a>…</a>
       <a>1004</a>
       <a>最后一页</a>
      </aside>
  </div>
</form>
<%  if(request.getAttribute("deleteResult")!=null){
	if(request.getAttribute("deleteResult").toString().equals("true")){
		%>
		<script type="text/javascript">
		alert("删除成功!");
		</script>
		<%
	}else{
		%>
		<script type="text/javascript">
		alert("删除失败!");
		</script>
		<%
	}
} %>
</body>
</html>