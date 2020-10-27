<%--
  Created by IntelliJ IDEA.
  User: 25157
  Date: 2020/9/9
  Time: 16:35
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
        <li><a href="${pageContext.request.contextPath}/cinfo/queryCinfoList">客户信息管理</a></li>
        <li><a href="#">添加</a></li>
    </ul>
</div>

<form action="${pageContext.request.contextPath}/cinfo/insertCinfo" method="post">
    <div class="formbody">
        <div class="formtitle"><span>客户基本信息</span></div>

        <ul class="forminfo">
            <li><label>客户姓名</label><input name="cName" type="text" class="dfinput" required/><i>姓名不能超过8个字符</i></li>
            <li><label>身份证号</label><input name="cSsn" type="text" class="dfinput" required/></li>
            <li><label>联系方式</label><input name="cTel" type="text" class="dfinput" required/></li>
            <li><label>年收入（万元）</label><input name="cSalary" type="text" class="dfinput" required/></li>
            <li><label>资产（万元）</label><input name="cBalance" type="text" class="dfinput" required/></li>
            <li><label>负债（万元）</label><input name="cDebt" type="text" class="dfinput" required/></li>
            <li><label>所在单位</label><input name="cUnit" type="text" class="dfinput" /></li>
            <li><label>家庭住址</label><input name="cAdd" type="text" class="dfinput" /></li>
            <li><label>业务关联</label><input name="cBusiness" type="text" class="dfinput" /></li>
            <li><label>&nbsp;</label><input name="" type="submit" class="btn" value="添加"/>&nbsp;&nbsp;&nbsp;&nbsp;<input name="" type="reset" class="btn" value="重置"/></li>
        </ul>

    </div>
</form>
</body>
</html>
