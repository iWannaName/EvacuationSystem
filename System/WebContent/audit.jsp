<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>人群疏散系统</title>
    <link href="assets/css/bootstrap.css" rel="stylesheet" />
    <link href="assets/css/font-awesome.css" rel="stylesheet" />
    <link href="assets/js/morris/morris-0.4.3.min.css" rel="stylesheet" />
    <link href="assets/css/custom-styles.css" rel="stylesheet" />
    <link href='assets/fonts/OpenSans-Regular.ttf' rel='stylesheet' type='text/css' />
    <link href='assets/css/SchemeMake/custom.css' rel='stylesheet' type='text/css' />
</head>
<style>
    .botton1{
    	float:left;
    }
    .botton2{
    	float:right;
    }
</style>
<body>
    <div id="wrapper">
    <%
	String username = null;
	Cookie[] cookies = request.getCookies();
	for (Cookie cookie : cookies) {
		if (cookie.getName().equals("username")) {
			username = cookie.getValue();
		}
	}
	if(username==null){
		out.println("<script>alert('没有权限 ')</script>");
		return;
	}
	%>
		<%!
			String power;
			String status;
			String subtitle;
		%>
		<%
			for (Cookie cookie : cookies) {
				if (cookie.getName().equals("power")) {
					power = cookie.getValue();
				}
				if (cookie.getName().equals("status")) {
					status = cookie.getValue();
				}
			}
		%>
        <nav class="navbar navbar-default top-navbar" role="navigation">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">人群疏散系统</a>
            </div>
            <ul class="nav navbar-top-links navbar-right">
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#" aria-expanded="false">
                        <i class="fa fa-user fa-fw"></i> <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="#"><i class="fa fa-user fa-fw"></i>用户名:<%=username %></a>
                        </li>
                        <li><a href="PersonalAreaServlet"><i class="fa fa-gear fa-fw"></i>个人设置</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="Login.jsp?quit=1"><i class="fa fa-sign-out fa-fw"></i>退出登录</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
        </nav>
        <!--/. NAV TOP  -->
        <nav class="navbar-default navbar-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav" id="main-menu">
                    <li>
                        <a class="active-menu" href="audit.jsp"><i class="fa fa-desktop"></i> 演练事件审核</a>
                    </li>
					<li>
                        <a href="displayer.jsp?Project_id=1"><i class="fa fa-bar-chart-o"></i> 显示疏散路径</a>
                    </li>
                    <li>
                        <a href="SchemeMake.jsp"><i class="fa fa-qrcode"></i> 人群疏散方案制定</a>
                    </li>
                    
                    <li>
                        <a href="#"><i class="fa fa-table"></i> 保护演练<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a href="#">人群保护方案制定</a>
                            </li>
                            <li>
                                <a href="#">人群保护方案查看</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a  href="Evaluator.jsp"><i class="fa fa-edit"></i> 演练评估 </a>
                    </li>
                    <li>
                        <a href="Querier.jsp"><i class="fa fa-sitemap"></i> 演练事件查询</a>
                        
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-fw fa-file"></i> 系统管理<span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <%
                            	if((Integer.parseInt(power)&4)==4){
                            		subtitle="可修改账户信息";
                            		out.print("<li><a href='AccountManageServlet'>账户管理</a></li>");
                            	}
                            %>
                            <%
                            	if((Integer.parseInt(power)&2)==2 && status.equals("1")){
                            		subtitle="可修改角色信息";
                            		out.print("<li><a href='RoleManageServlet'>角色管理</a></li>");
                            	}
                            %>
                            <li>
                                <a href="PersonalAreaServlet">个人主页</a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </nav>
        <!-- /. NAV SIDE  -->
        <div id="page-wrapper" >
            <div id="page-inner">
			 <div class="row">
                    <div class="col-md-12">
                        <h1 class="page-header">
                            演练事件审核<small>Nice tabs and panels</small>
                        </h1>
                    </div>
                </div> 
                
		<%String isIniting = "1"; %>
	       	<div class="row">
	        	<div class="col-md-12">
				<form action="AuditServlet" method="GET">
                                        <div class="panel-body becenter">
                                            <table border="0" class="becenter">
                                                <tr>
                                                  <th class="tr_block2"></th>
                                                  <th class="tr_name">事件ID</th>
                                                  <th class="tr_block"></th>
                                                  <th class="tr_input"><input type="text" class="form-control" name="EventID" value=""></th>
                                                </tr>

                                                <tr>
                                                  <th></th>
                                                  <th>事件名称</th>
                                                  <th></th>
                                                  <th><input type="text" class="form-control"id="EventName" name="EventName" value="" title="地址（'xx市xx街道xx号'）或坐标（'40.321231,139.098765'）不含引号。经纬度最多支持小数点后六位"></th>
                                                </tr>
                                                <tr>
                                                  <th></th>
                                                  <th>审核结果</th>
                                                  <th></th>
                                                  <th>
                                                  		<select class="form-control" name ="isPassed" >
													        <option>通过</option>
													        <option>未通过</option>
												        </select>  
											      </th>
                                                </tr>
                                                <tr>
                                                  <th></th>
                                                  <th>审核意见</th>
                                                  <th></th>
                                                  <th><textarea class="form-control" name="Suggestion" value=""></textarea></th>
                                                </tr>
                                                <tr>
                                                <th><small><br></small></th>
                                                </tr>
                                                <tr>
                                                  <th></th>
                                                  <th><input type="button" class="btn btn-default botton1"value="清除内容" onclick="javascrtpt:window.location.href='SchemeMake.jsp'"></th>
                                                  <th></th>
                                                  <th><input type="submit"class="btn btn-default botton2"  value="填写完成" ></th>
                                                </tr>
                                                </table>      
                                        </div>
                                        </form>     
	        	</div>
	        	<small></small>
	        	<div class="col-md-2">
		        <%
		        	

		        %>
		        <small>　</small>
		        </div>
	        </div>
        
				<footer><p>Copyright &copy; 2016.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p></footer>
					</div>
			 <!-- /. PAGE INNER  -->
            </div>
         <!-- /. PAGE WRAPPER  -->
        </div>
     <!-- /. WRAPPER  -->
    <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="assets/js/jquery-3.1.0.js"></script>
      <!-- Bootstrap Js -->
    <script src="assets/js/bootstrap.min.js"></script>
    <!-- Metis Menu Js -->
    <script src="assets/js/jquery.metisMenu.js"></script>
      <!-- Custom Js -->
    <script src="assets/js/custom-scripts.js"></script>
    <script src="assets/js/morris/morris.js"></script>

   
</body>
</html>
