<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>无标题文档</title>
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.js"></script>
    <script type="text/javascript">
        function confirmMsgDel()
        {
            if(confirm("删除用户信息,您确定要删除吗?"))
                window.close();
        }
        function userOpen()
        {
            if(confirm("您确定要启用该用户吗?"))
                window.close();
        }
        function userClose()
        {
            if(confirm("您确定要禁用该用户吗?"))
                window.close();
        }
        function resetPass()
        {
            if(confirm("重置密码,您确定要恢复初始密码吗?"))
                window.close();
        }
    </script>
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
    <script>
        // 验证输入的是否为数字
        function validate() {
            var t = document.getElementById('memId').value;
            if(!isNumber(t)&&t!=""){
                alert('用户ID不是有效的数字');
                return false;
            }else {
                return true;
            }

        }
        function isNumber(value) {
            var patrn = /^(-)?\d+(\.\d+)?$/;
            if (patrn.exec(value) == null || value == "") {
                return false
            } else {
                return true
            }
        }

    </script>
    <script>
        function goPage() {
            var page = document.getElementById("textfield").value;
            var name = document.getElementById("memName").value;
            var id = document.getElementById("memId").value;
            if (!isNumber(page)){
                alert("请输入合法数字！");
                return false;
            }
            if (page == '') {
                alert("请输入页码");
                return false;
            }
            if (page > eval(${info.pages})) {
                alert("输入页码不能大于总页数");
                return false;
            }
            if (page < 1) {
                alert("输入页码不能小于1");
                return false;
            }
            var str = "/member/queryMemberList?pageNum=" + page + "&memName="+name+"&memId="+id;
            window.location.href = "${pageContext.request.contextPath}"+str;
        }
        function lastPage() {
            window.location.href="${pageContext.request.contextPath}/member/queryMemberList?memId=${memId}&memName=${memName}&pageNum=${info.pages}";
        }
        function firstPage(){
            window.location.href="${pageContext.request.contextPath}/member/queryMemberList?memId=${memId}&memName=${memName}&pageNum=1";
        }
        function nextPage() {
            window.location.href="${pageContext.request.contextPath}/member/queryMemberList?memId=${memId}&memName=${memName}&pageNum=${info.nextPage}";
        }
        function prePage() {
            window.location.href="${pageContext.request.contextPath}/member/queryMemberList?memId=${memId}&memName=${memName}&pageNum=${info.prePage}"
        }
    </script>
    <script type="text/javascript">
        function isenable(id,status){
            $.ajax({
                type:"post",
                url:"${pageContext.request.contextPath}/member/updateIsEnable",
                dataType:"josn",
                data:{memid:id,status:status},
                success:function (data) {
                    alert("用户状态更新成功");
                    window.location.href="${pageContext.request.contextPath}/member/queryMemberList?memId=${memId}&memName=${memName}&pageNum=${info.pageNum}"
                }
            })
        }
    </script>
    <%--复选框--%>
    <script>
        $(function(){
            $("#checkAll").click(function(){
                $("tbody input").prop("checked",this.checked);
            });
        });
        function checkk() {
            var allcheckbox = "";
            var becheckbox = "";
            $("input[name=checkchild]").each(function(){ //遍历table里的全部checkbox
                allcheckbox += $(this).val() + ","; //获取所有checkbox的值
                if($(this).attr("checked")) //如果被选中
                    becheckbox += $(this).val() + ","; //获取被选中的值
            });
            if(becheckbox.length > 0) //如果获取到
                becheckbox = becheckbox.substring(0, becheckbox.length - 1); //把最后一个逗号去掉

            window.location = "${pageContext.request.contextPath}/member/deleteMany?checkTnum="+becheckbox;
        }
    </script>
</head>
${msg}

<body>
<div class="place">
    <span>位置：</span>
    <ul class="placeul">
        <li><a href="#">用户管理</a></li>
    </ul>
</div>

<form action="${pageContext.request.contextPath}/member/queryMemberList" method="post">
    <div class="formbody">
        <ul class="seachform">
            <li><label>用户编号</label><input id="memId" name="memId" type="text" class="scinput" value="${memId}" /></li>
            <li><label>姓名</label><input id="memName" name="memName" type="text" class="scinput" value="${memName}"  /></li>
            <li><label>&nbsp;</label><input name="" type="submit" class="scbtn" value="查询" onclick="return validate()"/></li>
        </ul>
    </div>
</form>
    <div class="rightinfo">
        <div class="tools">
            <ul class="toolbar1">
                <li><a href="${pageContext.request.contextPath}/view/manager/userInfoAdd.jsp"><span><img src="${pageContext.request.contextPath}/images/t01.png" /></span>添加</a></li>
                <li><a href="javascript:checkk()" ><span><img src="${pageContext.request.contextPath}/images/t03.png"/></span>批量删除</a></li>
            </ul>
        </div>
        <table class="tablelist">
            <thead>
            <tr class="tablehead"><td colspan="11">客户信息列表</td></tr>
            </thead>
            <thead>
            <tr>
                <th><input id="checkAll" name="checkAll" type="checkbox" value=""/></th>
                <th>用户编号</th>
                <th>姓名</th>
                <th>状态</th>
                <th>注册时间</th>
                <th>最后登录时间</th>
                <th width="23%">操作</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${info.list}" var="mm">
                <tr>
                    <td><input name="checkchild" type="checkbox" value="${mm.memId}" /></td>
                    <td>${mm.memId}</td>
                    <td>${mm.cname}</td>
                    <c:if test="${mm.isenable=='T'}">
                        <td>启用状态</td>
                    </c:if>
                    <c:if test="${mm.isenable=='F'}">
                        <td>禁用状态</td>
                    </c:if>
                    <td><fmt:formatDate value="${mm.registerTime}" pattern="yyyy-MM-dd"/></td>
                    <td><fmt:formatDate value="${mm.lastTime}" pattern="yyyy-MM-dd"/><a></a><a></a></td>
                    <td width="20%">
                        <a href="${pageContext.request.contextPath}/member/beforeUpdate?memId=${mm.memId}"><img src="${pageContext.request.contextPath}/images/t02.png"/></a>修改&nbsp;&nbsp;
                        <a href="javascript:if(confirm('确实要删除吗?'))location='${pageContext.request.contextPath}/member/deleteMember?memId=${mm.memId}'"><img src="${pageContext.request.contextPath}/images/t03.png"/></a>删除&nbsp;&nbsp;
                        <c:if test="${mm.isenable=='T'}">
                            <img src="${pageContext.request.contextPath}/images/t08.png" height="24" width="24" onclick="isenable('${mm.memId}','${mm.isenable}')"/>禁用&nbsp;&nbsp;
                        </c:if>
                        <c:if test="${mm.isenable=='F'}">
                            <img src="${pageContext.request.contextPath}/images/t09.png" height="24" width="24" onclick="isenable('${mm.memId}','${mm.isenable}')"/>启用&nbsp;&nbsp;
                        </c:if>
                        <a href="${pageContext.request.contextPath}/member/resetPassword?memid=${mm.memId}"><img src="${pageContext.request.contextPath}/images/t07.png" height="20" width="20"/></a>重置密码
                    </td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
        <div class="pagin">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="STYLE4"><div class="message">共<i class="blue">${info.total}</i>条记录，当前显示第&nbsp;<i class="blue">${info.pageNum}&nbsp;</i>页</div>
                    </td>
                    <td><table border="0" align="right" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="45"><img src="${pageContext.request.contextPath}/images/first.gif" width="33" height="20" style="cursor:hand" onclick="firstPage()"/></td>
                            <c:if test="${info.hasPreviousPage==true}">
                                <td width="50"><img src="${pageContext.request.contextPath}/images/back.gif" width="43" height="20" style="cursor:hand" onclick="prePage()" /></td>
                            </c:if>
                            <c:if test="${info.hasPreviousPage==false}">
                                <td width="50"><img src="${pageContext.request.contextPath}/images/back.gif" width="43" height="20" style="cursor:hand" onclick="alert('已经是第一页！')"/></td>
                            </c:if>

                            <c:if test="${info.hasNextPage==true}">
                                <td width="50"><img src="${pageContext.request.contextPath}/images/next.gif" width="43" height="20" style="cursor:hand" onclick="nextPage()"/></td>
                            </c:if>
                            <c:if test="${info.hasNextPage==false}">
                                <td width="50"><img src="${pageContext.request.contextPath}/images/next.gif" width="43" height="20" style="cursor:hand" onclick="alert('已经是最后一页！')"/></td>
                            </c:if>
                            <td width="40"><img src="${pageContext.request.contextPath}/images/last.gif" width="33" height="20" style="cursor:hand" onclick="lastPage()"/></td>

                            <td width="100"><div align="center"><span class="STYLE1">转到第
	                    <input id="textfield" name="textfield" type="text" size="4" style="height:16px; width:35px; border:1px solid #999999;" />
	                    页 </span></div></td>
                            <td width="40"><img src="${pageContext.request.contextPath}/images/go.gif" width="33" height="17" style="cursor:hand" onclick="return goPage()"/></td>
                        </tr>
                    </table>
                    </td>
                </tr>
            </table>
        </div>
    </div>

<script type="text/javascript">
    $('.tablelist tbody tr:odd').addClass('odd');
</script>

</body>

</html>

