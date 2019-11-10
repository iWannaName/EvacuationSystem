<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>人群疏散系统</title>
    <!-- Bootstrap Styles-->
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <!-- FontAwesome Styles-->
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <!-- Morris Chart Styles-->
    <link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
    <!-- Custom Styles-->
    <link href="assets/css/custom-styles.css" rel="stylesheet" />
    <style> 
    .becenter{text-align:center} 
    </style> 
</head>

<body>
    <div id="wrapper">
        <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar">
                <p class="navbar-brand">人群疏散系统</p>
            </div>
        </nav>
        <!--/. NAV TOP  -->
        <div id="login">
            <div id="page-inner">
                <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-header">
                        </h1>
                    </div>
                </div>
                <!-- /. ROW  -->
                <div class="row">
                    <div class="col-md-4 col-sm-12 col-xs-12">
                    </div>
                    <div class="col-md-4 col-sm-12 col-xs-12">
                        <div class="panel panel-default">
                            <div class="panel-heading important">
                                忘记密码验证
                            </div>
                            <form action="ForgetServlet" method="post">
                            <div class="panel-body" >
                                  用户名:<br>
                                  <input type="text" name="username" placeholder="Username">
                                  <br>
                                  身份证号:<br>
                                  <input type="text" name="id" placeholder="ID—number">
                                  <br>
                                  手机号:<br>
                                  <input type="text" name="tel" placeholder="Tel—number">
                                  <br>
                                  邮箱:<br>
                                  <input type="text" name="email" placeholder="E-mail">
                                  <br>
                                  <br><br>
                                  <input type="submit" value="确认">
                            </div>
                            </form>
                            <%
								HttpSession sess = request.getSession();
								String message="";
								message=(String)sess.getAttribute("verify");
								if(message!=""&&message!=null){
										out.print(message);
								}
								session.invalidate(); 
							 %>
                            <footer><p class="becenter">Copyright &copy; 2016.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p></footer>
                        </div>
                    </div>
            <!-- /. PAGE INNER  -->
        </div>
        <!-- /. PAGE WRAPPER  -->
    </div>
    <!-- /. WRAPPER  -->
    <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="assets/js/jquery-1.10.2.js"></script>
    <!-- Bootstrap Js -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- Metis Menu Js -->
    <script src="assets/js/jquery.metisMenu.js"></script>
    <!-- Morris Chart Js -->
    <script src="assets/js/morris/raphael-2.1.0.min.js"></script>
    <script src="assets/js/morris/morris.js"></script>
    <!-- Custom Js -->
    <script src="assets/js/custom-scripts.js"></script>


</body>

</html>