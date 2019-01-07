<%-- 
    Document   : header
    Created on : 10 sept. 2018, 18:17:28
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!--journalisation log4j imports-->
<%@page import="org.apache.logging.log4j.Logger" %>
<%@page import="org.apache.logging.log4j.LogManager" %>

<!--taglibs-->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 


<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>PROXYBANQUE</title>
        <!-- Core CSS - Include with every page -->
        <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap/bootstrap.css" rel="stylesheet" />
        <link href="<%=request.getContextPath()%>/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="<%=request.getContextPath()%>/assets/plugins/pace/pace-theme-big-counter.css" rel="stylesheet" />
        <link href="<%=request.getContextPath()%>/assets/css/style.css" rel="stylesheet" />
        <link href="<%=request.getContextPath()%>/assets/css/main-style.css" rel="stylesheet" />
        <!-- Page-Level CSS -->
        <link href="<%=request.getContextPath()%>/assets/plugins/morris/morris-0.4.3.min.css" rel="stylesheet" />
    </head>
    <body>
        <!--  wrapper -->
        <div id="wrapper">
            <!-- navbar top -->
            <nav class="navbar navbar-default navbar-fixed-top" role="navigation" id="navbar">
                <!-- navbar-header -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".sidebar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <!--                <a class="navbar-brand" href="index.html">
                                        <img src="assets/img/logo.png" alt="" />
                                    </a>-->
                </div>
                <!-- end navbar-header -->
                <!-- navbar-top-links -->
                <ul class="nav navbar-top-links navbar-right">
                    <!-- main dropdown -->

                    <li class="dropdown">
                        <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                            <i class="fa fa-user fa-3x"></i>
                        </a>
                        <!-- dropdown user-->
                        <ul class="dropdown-menu dropdown-user">
                            <li>
                                <a href="#"><i class="fa fa-user fa-fw"></i>User Profile</a>
                            </li>
                            <li class="divider"></li>
                            <li>
                                <a href="login.html"><i class="fa fa-sign-out fa-fw"></i>Logout</a>
                            </li>
                            <li>
                                <a href="#"><i class="fa fa-gear fa-fw"></i>Deconnexion</a>
                            </li>

                        </ul>
                        <!-- end dropdown-user -->
                    </li>
                    <!-- end main dropdown -->
                </ul>
                <!-- end navbar-top-links -->

            </nav>
            <!-- end navbar top -->

            <!-- navbar side -->
            <nav class="navbar-default navbar-static-side" role="navigation">
                <!-- sidebar-collapse -->
                <div class="sidebar-collapse">
                    <!-- side-menu -->
                    <ul class="nav" id="side-menu">
                        <li>
                            <!-- user image section-->
                            <div class="user-section">
                                <div class="user-section-inner">
                                    <img src="<%=request.getContextPath()%>/assets/img/user.jpg" alt="">
                                </div>
                                <div class="user-info">
                                    <div>SOULAMA <strong>Hassane</strong></div>
                                    <div class="user-text-online">
                                        <span class="user-circle-online btn btn-success btn-circle "></span>&nbsp;Online
                                    </div>
                                </div>
                            </div>
                            <!--end user image section-->
                        </li>
                        <li class="sidebar-search">
                            <!-- search section-->
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                    <button class="btn btn-default" type="button">
                                        <i class="fa fa-search"></i>
                                    </button>
                                </span>
                            </div>
                            <!--end search section-->
                        </li>
                        <li class="selected">
                            <a href="<c:url value="/admin/accueil"></c:url>"><i class="fa fa-dashboard fa-fw"></i>Accueil</a>
                            </li>

                            <li>
                                <a href="<c:url value="/admin/tableclient"></c:url>"><i class="fa fa-table fa-fw"></i>Liste Client</a>
                            </li>
                            <li>
                                <a href="<c:url value="/admin/formeenregistrementclient"></c:url>"><i class="fa fa-edit fa-fw"></i>Enregistrement Client</a>
                        </li>



                    </ul>
                    <!-- end side-menu -->
                </div>
                <!-- end sidebar-collapse -->
            </nav>
            <!-- end navbar side -->
            <!--  page-wrapper -->
            <div id="page-wrapper">

                <div class="row">
                    <!-- Page Header -->
                    <div class="col-lg-12">
                        <h1 class="page-header">ProxiBanque</h1>
                    </div>
                    <!--End Page Header -->
                </div>

