<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>登录</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}../../assets/images/favicon.png">
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
    
    
</head>
<%  String quit=null;
	quit=request.getParameter("quit");
	if(quit!=null){
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie:cookies){
			cookie.setMaxAge(0);
			response.addCookie(cookie);
		}
		System.out.println("cookie cleard");
	}
%>

<body>
<body class="h-100">
    
    <!--*******************
        Preloader start
    ********************-->
    <div id="preloader">
        <div class="loader">
            <svg class="circular" viewBox="25 25 50 50">
                <circle class="path" cx="50" cy="50" r="20" fill="none" stroke-width="3" stroke-miterlimit="10" />
            </svg>
        </div>
    </div>
    <!--*******************
        Preloader end
    ********************-->
<div class="login-form-bg h-100">
        <div class="container h-100">
            <div class="row justify-content-center h-100">
                <div class="col-xl-6">
                    <div class="form-input-content">
                        <div class="card login-form mb-0">
                            <div class="card-body pt-5">
                                <a class="text-center" href="index.html"> <h4>人群疏散保护演练系统</h4></a>
        
                                <form class="mt-5 mb-5 login-input" action="LoginServlet" method="post">
                                    <div class="form-group">
                                        <input type="number" name="username"  class="form-control" placeholder="policeid">
                                    </div>
                                    <div class="form-group">
                                        <input type="password" name="password" class="form-control" placeholder="Password">
                                    </div>
<!--                                         <input type="button" value="注册用户" onclick="javascrtpt:window.location.href='Register.jsp'"> -->
<!-- 	                                    <input type="button" value="忘记密码" onclick="javascrtpt:window.location.href='Forget.jsp'"> -->
<!-- 	                                    <input type="button" value="修改密码" onclick="javascrtpt:window.location.href='Modify.jsp'"> -->
<!-- 	                                    <input type="submit" value="登录" > -->
                                    <button class="btn login-form__btn submit w-100">登录</button>
                                    
                                </form>
                                <p class="mt-5 login-form__footer"><a href="Register.jsp" class="text-primary">注册</a> 
                                <a href="Forget.jsp" class="text-primary">找回密码</a>
                                 <a href="Modify.jsp" class="text-primary">修改密码</a></p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    

    <!--**********************************
        Scripts
    ***********************************-->
    <script src="${pageContext.request.contextPath}/plugins/common/common.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/custom.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/settings.js"></script>
    <script src="${pageContext.request.contextPath}/js/gleek.js"></script>
    <script src="${pageContext.request.contextPath}/js/styleSwitcher.js"></script>
</body>
</html>