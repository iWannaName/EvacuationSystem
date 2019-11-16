<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language = "java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" %>
<html class="h-100" lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>注册</title>
    <!-- Favicon icon -->
     <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/images/favicon.png">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <link href="css/style.css" rel="stylesheet">
    
</head>

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
        
                                <form class="mt-5 mb-5 login-input" action="RegisterServlet" method="post">
                                    <div class="form-group">
                                        <input type="text" class="form-control"  name="username" placeholder="警号" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="password" class="form-control" name="password" placeholder="密码" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control"  name="realname" placeholder="姓名" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control"  name="unit" placeholder="单位" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control"  name="position" placeholder="职务" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="text" class="form-control"  name="id" placeholder="身份证号" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="date" class="form-control"  name="birthdate" id="bir" placeholder="出生日期" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="date" class="form-control"  name="jointime" id="joi" placeholder="入队日期" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="tel" class="form-control"  name="tel" id="tel" placeholder="手机号" required>
                                    </div>
                                    <div class="form-group">
                                        <input type="email" class="form-control"  name="email" id="ema" placeholder="邮箱" required>
                                    </div>
                                    <button class="btn login-form__btn submit w-100">注册</button>
                                </form>
                                    <p class="mt-5 login-form__footer">有账户？ <a href="Login.jsp" class="text-primary">现在登录 </a></p>
                                    <%
									HttpSession sess = request.getSession();
									String verify = null;
									verify = (String) sess.getAttribute("message");
									if (verify != null) {
										out.print(verify);
									}
									session.invalidate();
								%>
                                </div>
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





