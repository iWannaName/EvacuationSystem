<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/ TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language = "java" contentType="text/html;charset=UTF-8" pageEncoding="utf-8" import="java.util.ArrayList"%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>角色管理</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/images/favicon.png">
    <!-- Custom Stylesheet -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">
     <%!
    public static String toBinary(int num, int digits) {
		    String cover = Integer.toBinaryString(1 << digits).substring(1);
		    String s = Integer.toBinaryString(num);
		    return s.length() < digits ? cover.substring(s.length()) + s : s;
		}
	%>
</head>
       
<body>
   
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

    
    <!--**********************************
        Main wrapper start
    ***********************************-->
    <div id="main-wrapper">

        <!--**********************************
            Nav header start
        ***********************************-->
        <div class="nav-header">
            <div class="brand-logo">
                <a href="index.html">
                    <b class="logo-abbr"><img src="${pageContext.request.contextPath}/images/logo.png" alt=""> </b>
                    <span class="logo-compact"><img src="${pageContext.request.contextPath}/images/logo-compact.png" alt=""></span>
                    <span class="brand-title">
                        <img src="${pageContext.request.contextPath}/images/logo-text.png" alt="">
                    </span>
                </a>
            </div>
        </div>
        <!--**********************************
            Nav header end
        ***********************************-->

        <!--**********************************
            Header start
        ***********************************-->
        <div class="header">    
            <div class="header-content clearfix">
                
                <div class="nav-control">
                    <div class="hamburger">
                        <span class="toggle-icon"><i class="icon-menu"></i></span>
                    </div>
                </div>
                <div class="header-left">
                    <div class="input-group icons">
                        <div class="input-group-prepend">
                            <span class="input-group-text bg-transparent border-0 pr-2 pr-sm-3" id="basic-addon1"><i class="mdi mdi-magnify"></i></span>
                        </div>
                        <input type="search" class="form-control" placeholder="Search Dashboard" aria-label="Search Dashboard">
                        <div class="drop-down   d-md-none">
							<form action="#">
								<input type="text" class="form-control" placeholder="Search">
							</form>
                        </div>
                    </div>
                </div>
                <div class="header-right">
                    <ul class="clearfix">
                        <li class="icons dropdown"><a href="javascript:void(0)" data-toggle="dropdown">
                                <i class="mdi mdi-email-outline"></i>
                                <span class="badge gradient-1 badge-pill badge-primary">3</span>
                            </a>
                            <div class="drop-down animated fadeIn dropdown-menu">
                                <div class="dropdown-content-heading d-flex justify-content-between">
                                    <span class="">3 New Messages</span>  
                                    
                                </div>
                                <div class="dropdown-content-body">
                                    <ul>
                                        <li class="notification-unread">
                                            <a href="javascript:void()">
                                                <img class="float-left mr-3 avatar-img" src="images/avatar/1.jpg" alt="">
                                                <div class="notification-content">
                                                    <div class="notification-heading">Saiful Islam</div>
                                                    <div class="notification-timestamp">08 Hours ago</div>
                                                    <div class="notification-text">Hi Teddy, Just wanted to let you ...</div>
                                                </div>
                                            </a>
                                        </li>
                                        <li class="notification-unread">
                                            <a href="javascript:void()">
                                                <img class="float-left mr-3 avatar-img" src="images/avatar/2.jpg" alt="">
                                                <div class="notification-content">
                                                    <div class="notification-heading">Adam Smith</div>
                                                    <div class="notification-timestamp">08 Hours ago</div>
                                                    <div class="notification-text">Can you do me a favour?</div>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void()">
                                                <img class="float-left mr-3 avatar-img" src="images/avatar/3.jpg" alt="">
                                                <div class="notification-content">
                                                    <div class="notification-heading">Barak Obama</div>
                                                    <div class="notification-timestamp">08 Hours ago</div>
                                                    <div class="notification-text">Hi Teddy, Just wanted to let you ...</div>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void()">
                                                <img class="float-left mr-3 avatar-img" src="images/avatar/4.jpg" alt="">
                                                <div class="notification-content">
                                                    <div class="notification-heading">Hilari Clinton</div>
                                                    <div class="notification-timestamp">08 Hours ago</div>
                                                    <div class="notification-text">Hello</div>
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                    
                                </div>
                            </div>
                        </li>
                        <li class="icons dropdown"><a href="javascript:void(0)" data-toggle="dropdown">
                                <i class="mdi mdi-bell-outline"></i>
                                <span class="badge badge-pill gradient-2 badge-primary">3</span>
                            </a>
                            <div class="drop-down animated fadeIn dropdown-menu dropdown-notfication">
                                <div class="dropdown-content-heading d-flex justify-content-between">
                                    <span class="">2 New Notifications</span>  
                                    
                                </div>
                                <div class="dropdown-content-body">
                                    <ul>
                                        <li>
                                            <a href="javascript:void()">
                                                <span class="mr-3 avatar-icon bg-success-lighten-2"><i class="icon-present"></i></span>
                                                <div class="notification-content">
                                                    <h6 class="notification-heading">Events near you</h6>
                                                    <span class="notification-text">Within next 5 days</span> 
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void()">
                                                <span class="mr-3 avatar-icon bg-danger-lighten-2"><i class="icon-present"></i></span>
                                                <div class="notification-content">
                                                    <h6 class="notification-heading">Event Started</h6>
                                                    <span class="notification-text">One hour ago</span> 
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void()">
                                                <span class="mr-3 avatar-icon bg-success-lighten-2"><i class="icon-present"></i></span>
                                                <div class="notification-content">
                                                    <h6 class="notification-heading">Event Ended Successfully</h6>
                                                    <span class="notification-text">One hour ago</span>
                                                </div>
                                            </a>
                                        </li>
                                        <li>
                                            <a href="javascript:void()">
                                                <span class="mr-3 avatar-icon bg-danger-lighten-2"><i class="icon-present"></i></span>
                                                <div class="notification-content">
                                                    <h6 class="notification-heading">Events to Join</h6>
                                                    <span class="notification-text">After two days</span> 
                                                </div>
                                            </a>
                                        </li>
                                    </ul>
                                    
                                </div>
                            </div>
                        </li>
                         <li class="icons dropdown d-none d-md-flex">
                            <a href="javascript:void(0)" class="log-user"  data-toggle="dropdown">
                                <span>中文</span>  <i class="fa fa-angle-down f-s-14" aria-hidden="true"></i>
                            </a>
                            <div class="drop-down dropdown-language animated fadeIn  dropdown-menu">
                                <div class="dropdown-content-body">
                                    <ul>
                                        <li><a href="javascript:void()">中文</a></li>
                                        <li><a href="javascript:void()">English</a></li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                        <li class="icons dropdown">
                            <div class="user-img c-pointer position-relative"   data-toggle="dropdown">
                                <span class="activity active"></span>
                                <img src="${pageContext.request.contextPath}/images/user/1.png" height="40" width="40" alt="">
                            </div>
                            <div class="drop-down dropdown-profile   dropdown-menu">
                                <div class="dropdown-content-body">
                                    <ul>
                                        <li>
                                            <a href="PersonalArea.jsp"><i class="icon-user"></i> <span>个人中心</span></a>
                                        </li>
                                        <li>
                                            <a href="email-inbox.html"><i class="icon-envelope-open"></i> <span>邮件</span> <div class="badge gradient-3 badge-pill badge-primary">3</div></a>
                                        </li>
                                        
                                        <hr class="my-2">
                                        <li><a href="Login.jsp"><i class="icon-key"></i> <span>退出</span></a></li>
                                    </ul>
                                </div>
                            </div>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <!--**********************************
            Header end ti-comment-alt
        ***********************************-->

         <!--**********************************
            Sidebar start
        ***********************************-->
        <div class="nk-sidebar">           
            <div class="nk-nav-scroll">
                <ul class="metismenu" id="menu">
                    <li class="nav-label">主页</li>
                    <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-speedometer menu-icon"></i><span class="nav-text">首页</span>
                        </a>
                        <ul aria-expanded="false">
                            <li><a href="${pageContext.request.contextPath}/welcome.jsp">Home 1</a></li>
                            <!-- <li><a href="./index-2.html">Home 2</a></li> -->
                        </ul>
                    </li>
                    <li class="mega-menu mega-menu-sm">
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-globe-alt menu-icon"></i><span class="nav-text">演练事件管理</span>
                        </a>
                        <ul aria-expanded="false">
                            <li><a href="SchemeMake.jsp">演练任务制定</a></li>
                            <li><a href="taskView.jsp">演练任务查询</a></li>
                        </ul>
                    </li>
                    <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-envelope menu-icon"></i> <span class="nav-text">疏散演练</span>
                        </a>
                        <ul aria-expanded="false">
                            <li><a href="#">人群疏散方案制定</a></li>
                            <li><a href="#">人群疏散方案查看</a></li>
                            <li><a href="${pageContext.request.contextPath}/email-compose.html">Compose</a></li>
                        </ul>
                    </li>
                    <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-screen-tablet menu-icon"></i><span class="nav-text">保护演练</span>
                        </a>
                        <ul aria-expanded="false">
                            <li><a href="#">人群保护方案制定</a></li>
                            <li><a href="#">人群保护方案查看</a></li>
                        </ul>
                    </li>
                    <li>
                       <a  href="Evaluator.jsp" >
                            <i class="icon-graph menu-icon">&nbsp;<span class="nav-text">演练评估</span></i>
                       </a>
                    </li>
                    
                    <li>
                       <a  href="Querier.jsp" >
                            <i class="icon-grid menu-icon">&nbsp;<span class="nav-text">演练事件查询</span></i>
                       </a>
                    </li>
                    <li>
                        <a class="has-arrow" href="javascript:void()" aria-expanded="false">
                            <i class="icon-note menu-icon"></i><span class="nav-text">系统管理</span>
                        </a>
                        <ul aria-expanded="false">
                         <%
                            	if((Integer.parseInt(power)&4)==4){
                            		subtitle="可修改账户信息";
                            		out.print("<li><a class='active-menu' href='AccountManageServlet'>账户管理</a></li>");
                            	}
                            %>
                            <%
                            	if((Integer.parseInt(power)&2)==2 && status.equals("1")){
                            		subtitle="可修改角色信息";
                            		out.print("<li><a href='RoleManageServlet'>角色管理</a></li>");
                            	}
                            %>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
        <!--**********************************
            Sidebar end
        ***********************************-->

        <!--**********************************
            Content body start
        ***********************************-->
         <div class="content-body">

            <div class="row page-titles mx-0">
                <div class="col p-md-0">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="javascript:void(0)">系统管理</a></li>
                        <li class="breadcrumb-item active"><a href="javascript:void(0)">角色管理</a></li>
                    </ol>
                </div>
            </div>
            <!-- row -->

            <div class="container-fluid">
            <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <div class="card-title">
                                    <h4>角色信息</h4>
                                </div>
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>警号</th>
                                                <th>姓名</th>
                                                <th>单位</th>
                                                <th>职务</th>
                                                <th>出生日期</th>
                                                <th>入队日期</th>
                                                <th>角色名称</th>
                                                <th>角色状态</th>
                                                <th>权限码</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                        <%
	                                    request.setCharacterEncoding("utf-8");
	                                    response.setContentType("text/html;charset=utf-8");	
										HttpSession sess = request.getSession();
	                                    ArrayList<String[]> ret = new ArrayList<String[]>();
										ret = (ArrayList<String[]>)sess.getAttribute("ret");
										if (ret.size()!=0) {
											for(int i=0;i<ret.size();i++)
											{
												out.print("<tr>");
												for(int j=1;j<9;j++){
													out.print("<th>");
													out.print(ret.get(i)[j]);
													out.print("</th>");
												}
												if((Integer.parseInt(power)&8)==8){
													String k = toBinary(Integer.parseInt(ret.get(i)[9]),11);
													out.print("<th>");
													out.print(k);
													out.print("</th>");
												}
												out.print("</tr>");
											}
										}
										session.invalidate();
									%>
                                        </tbody>
                                    </table>
                                </div>
                                
                            </div>
                        </div>
                        <!-- /# card -->
                        <div class="card">
                            <div class="card-body">
                                <p class="text-muted"><code></code>
                                </p>
                                <div id="accordion-one" class="accordion">
                                
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="mb-0" data-toggle="collapse" data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne"><i class="fa" aria-hidden="true"></i> 新增角色</h5>
                                        </div>
                                        <div id="collapseOne" class="collapse show" data-parent="#accordion-one">
                                            <div class="card-body">
                                            
                                            <form action="RoleInsertServlet" method="post">
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label>警号</label>
                                                <input type="text" class="form-control" name="username">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label>姓名</label>
                                                <input type="text" class="form-control" name="realname">
                                            </div>
                                        </div>
                                         <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label>单位</label>
                                                <input type="text" class="form-control" name="unit">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label>职务</label>
                                                <input type="text" class="form-control" name="position">
                                            </div>
                                        </div>
                                         <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label>出生日期</label>
                                                <input type="date" class="form-control" name="birthdate">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label>入队日期</label>
                                                <input type="date" class="form-control" name="jointime">
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label>角色名称</label>
                                                <input type="text" class="form-control" name="role_name">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <div class="basic-form">
                                      <form>
                                        <div class="form-group">
                                            <label class="radio-inline mr-3">
                                                <input type="radio" name="status" value="1"> 启用</label>
                                            <label class="radio-inline mr-3">
                                                <input type="radio" name="status" value="0"> 不启用</label>
                                        </div>
                                    </form>
                                </div>
                                            </div>
                                        </div> 
                                        <button type="submit" class="btn btn-dark">确定</button>
                                    </form>
                                     </div>
                                            </div>
                                        </div>
                                    
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="mb-0 collapsed" data-toggle="collapse" data-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo"><i class="fa" aria-hidden="true"></i> 删除角色</h5>
                                        </div>
                                        <div id="collapseTwo" class="collapse" data-parent="#accordion-one">
                                            <div class="card-body">
                                            <form action="RoleDeleteServlet" method="post">
                                            <div class="form-group row">
                                            <label class="col-sm-2 col-form-label">您要删除的警号：</label>
                                            <div class="col-sm-4">
                                                <input type="text" class="form-control" name="usernameD" placeholder="警号">
                                            </div>
                                            <button type="submit" class="btn btn-dark">确定</button>
                                        </div>
                                        </form>
                                            </div>
                                        </div>
                                    </div> 
                                    
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="mb-0 collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree"><i class="fa" aria-hidden="true"></i> 修改角色信息</h5>
                                        </div>
                                        <div id="collapseThree" class="collapse" data-parent="#accordion-one">
                                            <div class="card-body">
                                            
                                             <form action="RoleModifyServlet" method="post">
                                        <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label>警号</label>
                                                <input type="number" class="form-control" name="usernameM">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label>姓名</label>
                                                <input type="text" class="form-control" name="realnameM">
                                            </div>
                                        </div>
                                         <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label>单位</label>
                                                <input type="text" class="form-control" name="unitM">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label>职务</label>
                                                <input type="text" class="form-control" name="positionM">
                                            </div>
                                        </div>
                                         <div class="form-row">
                                            <div class="form-group col-md-6">
                                                <label>出生日期</label>
                                                <input type="date" class="form-control" name="birthdateM">
                                            </div>
                                            <div class="form-group col-md-6">
                                                <label>入队日期</label>
                                                <input type="date" class="form-control" name="jointimeM">
                                            </div>
                                        </div>
                                        <div class="form-row">
                                            <div class="form-group col-md-12">
                                                <label>角色名称</label>
                                                <input type="text" class="form-control" name="role_nameM">
                                            </div>
                                      
                                       </div>
                                       <button type="submit" class="btn btn-dark">修改完成</button>
                                       </form>
                                        </div>
                                    </div>
                                    </div>
                                    
                                    <div class="card">
                                        <div class="card-header">
                                            <h5 class="mb-0 collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree"><i class="fa" aria-hidden="true"></i> 启用/停用角色</h5>
                                        </div>
                                        <div id="collapseThree" class="collapse" data-parent="#accordion-one">
                                            <div class="card-body">
                                            
                                            <form action="RoleEnableServlet" method="post">
                                            
                                            <div class="form-group col-md-12">
                                                <label>警号：</label>
                                                <input type="text" class="form-control" name="usernameC">
                                            </div>
                                            <div class="form-group col-md-12">
                                                <div class="basic-form">
                                      <form>
                                        <div class="form-group">
                                             <label>角色状态：</label>
                                            <label class="radio-inline mr-3">
                                                <input type="radio" name="statusC" value="1"> 启用</label>
                                            <label class="radio-inline mr-3">
                                                <input type="radio" name="statusC" value="0"> 停用</label>
                                        </div>
                                    </form>
                                </div>
                                            </div>
                                       
                                        <button type="submit" class="btn btn-dark">确定</button>
                                        </form>
                                            </div>
                                        </div>
                                    </div>
                                    
                                    <div clas                                                                                                                                                                                                                s="card">
                                        <div class="card-header">
                                            <h5 class="mb-0 collapsed" data-toggle="collapse" data-target="#collapseThree" aria-expanded="false" aria-controls="collapseThree"><i class="fa" aria-hidden="true"></i> 修改角色权限</h5>
                                        </div>
                                        <div id="collapseThree" class="collapse" data-parent="#accordion-one">
                                            <div class="card-body">
                                             <%
	                                if((Integer.parseInt(power)&8)==8){%>
	                            		<div class="form-group col-md-12">
                                                <label>警号：</label>
                                                <input type="text" class="form-control" name="usernameX">
                                        </div>
                                         <div class="col-lg-12">
                                    <form>
                                        <div class="form-group">
                                        <label>选择权限：</label>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input type="checkbox" class="form-check-input" name='power1' value='2'>可修改角色信息</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input type="checkbox" class="form-check-input" name='power2' value='4'>可修改账户信息</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input type="checkbox" class="form-check-input" name='power3' value='8' >可修改角色权限</label>
                                            </div>
                                            <div class="form-check form-check-inline">
                                                <label class="form-check-label">
                                                    <input type="checkbox" class="form-check-input" name='power4' value='32'>可修改方案信息</label>
                                            </div>
                                        </div>
                                        <button type="submit" class="btn btn-dark">确定</button>
                                    </form>
                                </div>
	                            		
	                            	<% }%>
                                
                                            </div>
                                        </div>
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                        
                    </div>
                    <!-- #/ column -->
            </div>
            <!-- #/ container -->
        </div>
        <!--**********************************
            Content body end
        ***********************************-->
 
	<!--**********************************
            Footer start
        ***********************************-->
        <div class="footer">
            <div class="copyright">
                <p>Copyright &copy; Designed & Developed by <a href="https://themeforest.net/user/quixlab">Quixlab</a> 2018</p>
            </div>
        </div>
        <!--**********************************
            Footer end
        ***********************************-->
    </div>
    <!--**********************************
        Main wrapper end
    ***********************************-->

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