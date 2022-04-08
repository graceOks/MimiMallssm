<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String BasePath=request.getScheme()+"://"+request.getServerName()+":"+
            request.getServerPort()+request.getContextPath()+"/";
%>
<html>
<head>
    <title>index.jsp</title>
    <base href="<%=BasePath%>"/>
    <%
        System.out.println(BasePath);
    %>
    <%
        out.print(BasePath);
    %>
</head>
<body>
<div>
    <a href="register.jsp">注册学生</a>
    <a href="listStudent.jsp">浏览学生</a>
</div>
<div>
    <p>SSM框架整合项目</p>
    <form action="student/addStudent.do" method="post">
        <input type="text" name="name"/>
        <input type="number" name="age"/>
        <input type="submit" value="确认"/>
    </form>
</div>
</body>
</html>
