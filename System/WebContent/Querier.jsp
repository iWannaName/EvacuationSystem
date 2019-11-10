﻿<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <title>演练任务查询</title>
    <!-- Favicon icon -->
    <link rel="icon" type="image/png" sizes="16x16" href="${pageContext.request.contextPath}/images/favicon.png">
    <!-- Custom Stylesheet -->
    <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet">

</head>

<%@page import="org.model.SchemeMake.PathApi.PathRequest"%>
<%@page import="org.model.BaiduMap"%>
<%@page import="org.model.settings"%>
<%@page import="org.model.SchemeMake.PathApi.PathReturn"%>
<%@page import="org.model.SchemeMake.Units.vertex"%>
<%@page import="org.model.SchemeMake.Units.edge"%>
<%@page import="org.model.SchemeMake.Units.capacity"%>
<%@page import="org.model.SchemeMake.PathApi.result.Route"%>
<%@page import="org.model.Query"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.TreeSet"%>
<%@page import="java.util.Iterator"%>


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
                            <li><a href="${pageContext.request.contextPath}/index.jsp">Home 1</a></li>
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
                        <li class="breadcrumb-item active"><a href="javascript:void(0)">演练事件查询</a></li>
                    </ol>
                </div>
            </div>
            <!-- row -->

            <div class="container-fluid">
            <%
				String EventID = null;
				String EventName = null;
				String UserID = null;
				String DrillTime = null;
				String pageNum = null;
				String URL = "Querier.jsp?";
				int pages=1;
				int CurrPage=1;
				int nums=1;
				EventID=request.getParameter("EventID");
				EventName=request.getParameter("EventName");
				UserID=request.getParameter("UserID");
				DrillTime=request.getParameter("DrillTime");
				pageNum=request.getParameter("pageNum");
				String sql = "select * from event ";
				String sqlnum = "select count(*) from event ";
				if(EventID!=null&&!EventID.equals("")&&!EventID.equals("null")){
					sql+="where event_id='"+EventID+"'";
					sqlnum+="where event_id='"+EventID+"'";
					URL+="EventID="+EventID;
				}
				if(EventName!=null&&!EventName.equals("")&&!EventName.equals("null")){
					sql+="where e_name='"+EventName+"'";
					sqlnum+="where e_name='"+EventName+"'";
					URL+="EventName="+EventName;
				}
				if(UserID!=null&&!UserID.equals("")&&!UserID.equals("null")){
					sql+="where recorder='"+UserID+"'";
					sqlnum+="where recorder='"+UserID+"'";
					URL+="UserID="+UserID;
				}
				if(DrillTime!=null&&!DrillTime.equals("")&&!DrillTime.equals("null")){
					sql+="where DrillTime='"+DrillTime+"'";
					sqlnum+="where DrillTime='"+DrillTime+"'";
					URL+="DrillTime="+DrillTime;
				}
				System.out.println(pageNum);
				if(pageNum!=null&&!pageNum.equals("")&&!pageNum.equals("null")){
					CurrPage=Integer.parseInt(pageNum);
					sql += " limit "+Integer.toString((CurrPage-1)*10)+", 10";
				}
				else{
					sql += " limit 0, 10";
				}
				System.out.println(sql);
				System.out.println(URL);
				ArrayList<String[]>ret = Query.runSql(9,sql);
				ArrayList<String[]>num = Query.runSql(1,sqlnum);
				
				if(ret!=null&&ret.size()!=0)
					nums=Integer.parseInt(num.get(0)[1]);
				else{
					nums=1;
				}
				pages=nums/10+1;
				String id1=null;
				String id2=null;
				for(int i=0;i<ret.size();i++){
					id1 = ret.get(i)[4];
					id2 = ret.get(i)[5];
					String sql1 = "select x_coordinate,y_coordinate from point where p_id='"+id1+"'";
					String sql2 = "select x_coordinate,y_coordinate from point where p_id='"+id2+"'";

					ArrayList<String[]>ret1 = Query.runSql(2,sql1);
					ArrayList<String[]>ret2 = Query.runSql(2,sql2);
					if(ret1==null||ret2==null||ret1.size()==0||ret2.size()==0){
			        	out.print("<script>alert('未知记录'); window.location='Querier.jsp'</script>");
			        	out.flush();
			        	return;
					}
					ret.get(i)[4]=ret1.get(0)[1]+","+ret1.get(0)[2];
					ret.get(i)[5]=ret2.get(0)[1]+","+ret1.get(0)[2];
				}
				%>
				 <div class="col-lg-12">
                        <div class="card">
                            <div class="card-body">
                                <h4 class="card-title">查询演练事件</h4>
                                <div class="basic-form">
                                    <form>
                                        <div class="form-row align-items-center">
                                            <div class="col-auto">
                                                <label class="sr-only">事件id</label>
                                                <input type="text" class="form-control mb-2" placeholder="事件id">
                                            </div>
                                            <div class="col-auto">
                                                <label class="sr-only">事件名称</label>
                                                <input type="text" class="form-control mb-2" placeholder="事件名称">
                                            </div>
                                            <div class="col-auto">
                                                <label class="sr-only">录入人警号</label>
                                                <input type="text" class="form-control mb-2" placeholder="录入人警号">
                                            </div>
                                            <div class="col-auto">
                                                <label class="sr-only">演练时间</label>
                                                <input type="text" class="form-control mb-2" placeholder="演练时间">
                                            </div>
                                            <div class="col-auto">
                                                <button type="submit" class="btn mb-2 btn-primary">查询</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="col-lg-12">
                                <div class="table-responsive">
                                    <table class="table table-hover">
                                        <thead>
                                            <tr>
                                                <th>事件ID</th>
                                                <th>事件名称</th>
                                                <th>事件类型</th>
                                                <th>疏散点</th>
                                                <th>避难点</th>
                                                <th>录入人</th>
                                                <th>演练时间</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <%
											for(int i=0;i<ret.size();i++){
												String[] line = ret.get(i);
											%>
											 <tr>
                                                <th><%=line[1]%></th>
                                                <td><%=line[2]%></td>
                                                <td><%=line[3]%></td>
                                                <td><%=line[4]%></td>
                                                <td><%=line[5]%></td>
                                                <td><%=line[8]%></td>
                                                <td><%=line[9]%></td>
                                            </tr>
												
										<% 
											}
											
											%> 
                                        </tbody>
                                    </table>
                                    </div>
                                    </div>
                                    <div class="row">
										<div class="col-sm-6">
											<div class="dataTables_info" id="dataTables-example_info"
											<%
											int curr=0;
											if(nums>0){
												curr=(pages-1)*10+1;
											}
											int M = 0;
											if(nums>10){
												M=10;
											}
											else M = nums;
											%>
												role="alert" aria-live="polite" aria-relevant="all">Showing
											<%=M %> of <%=nums %> entries</div>
										</div>
										<div class="col-sm-6">
											<div class="dataTables_paginate paging_simple_numbers"
												id="dataTables-example_paginate">
												<ul class="pagination">
                                        
                                        <%

												if(CurrPage==1){
													out.println("<li class=\"paginate_button previous disabled\"");
													out.println("aria-controls=\"dataTables-example\" tabindex=\"0\"");
													out.println("id=\"dataTables-example_previous\"><a href=\"#\">Previous</a></li>");
												}
												else{
													out.println("<li class=\"paginate_button previous\"");
													out.println("aria-controls=\"dataTables-example\" tabindex=\"0\"");
													out.println("id=\"dataTables-example_previous\"><a href=\""+URL+"&PageNum="+Integer.toString(CurrPage-1)+"\">Previous</a></li>");
													
												}
												
												for(int i=1;i<=pages;i++){
													if(i==CurrPage){
														out.println("<li class=\"page-item active\"");
														out.println("aria-controls=\"dataTables-example\" tabindex=\""+Integer.toString(i)+"\"><a");
													}
													else{
														out.println("<li class=\"paginate_button\"");
														out.println("aria-controls=\"dataTables-example\" tabindex=\""+Integer.toString(i)+"\"><a");
													}
													out.println("href=\""+URL+"&pageNum="+Integer.toString(i)+"\">"+Integer.toString(i)+"</a></li>");
												}%><% 
													
													if(CurrPage==pages){
														out.println("<li class=\"paginate_button next disabled\"");
													}
													else{
														out.println("<li class=\"paginate_button next\"");
													}
														out.println("aria-controls=\"dataTables-example\" tabindex=\"0\"");
														out.println("id=\"dataTables-example_next\"><a href=\""+URL+"&pageNum="+Integer.toString(CurrPage+1)+"\">Next</a></li>");
														
													%>
<!--                                             <li class="page-item disabled"><a class="page-link" href="#">Previous</a> -->
<!--                                             </li> -->
<!--                                             <li class="page-item"><a class="page-link" href="#">1</a> -->
<!--                                             </li> -->
<!--                                             <li class="page-item active"><a class="page-link" href="#">2 <span class="sr-only">(current)</span></a> -->
<!--                                             </li> -->
<!--                                             <li class="page-item"><a class="page-link" href="#">3</a> -->
<!--                                             </li> -->
<!--                                             <li class="page-item"><a class="page-link" href="#">Next</a> -->
<!--                                             </li> -->
                                        </ul>
                                      
                                     
                               </div>
                            </div>
                                </div>
                            
                            
                        </div>
                        <!-- /# card -->
                    </div>
             <!-- /# colume -->
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