<%--
  Created by IntelliJ IDEA.
  User: 25157
  Date: 2020/9/9
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript">
        $(document).ready(function(){
            $(".click").click(function(){
                $(".tip").fadeIn(200);
            });

            $(".tiptop a").click(function(){
                $(".tip").fadeOut(200);
            });

            $(".sure").click(function(){
                $(".tip").fadeOut(100);
            });

            $(".cancel").click(function(){
                $(".tip").fadeOut(100);
            });

        });
    </script>


</head>


<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="${pageContext.request.contextPath}/work/queryWorkList">工作管理</a></li>
        <li><a href="#">添加</a></li>
    </ul>
</div>

<form action="${pageContext.request.contextPath}/work/insertWork" method="post">
    <div class="formbody">
        <div class="formtitle"><span>工作记录</span></div>

        <ul class="forminfo">
            <li><label>客户维护</label><textarea name="cmManagement" cols="" rows="" class="textinput"></textarea></li>
            <li><label>产品信息</label><textarea name="cmProduct" cols="" rows="" class="textinput"></textarea></li>
            <li><label>货后管理</label><textarea name="cmLoan" cols="" rows="" class="textinput"></textarea></li>
            <li><label>风险预警</label><textarea name="cmWarning" cols="" rows="" class="textinput"></textarea></li>
            <li><label>发现问题</label><textarea name="cmIssue" cols="" rows="" class="textinput"></textarea></li>
            <li><label>工作建议及感悟</label><textarea name="cmWorkProposal" cols="" rows="" class="textinput"></textarea></li>
            <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="添加"/>&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="reset" class="btn" value="重置"/></li>
        </ul>

    </div>
</form>


</body>

</html>
