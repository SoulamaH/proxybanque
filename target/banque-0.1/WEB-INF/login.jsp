<!DOCTYPE html>
<html>

    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Bootsrtap Free Admin Template - SIMINTA | Admin Dashboad Template</title>
        <!-- Core CSS - Include with every page -->
        <link href="<%=request.getContextPath()%>/assets/plugins/bootstrap/bootstrap.css" rel="stylesheet" />
        <link href="<%=request.getContextPath()%>/assets/font-awesome/css/font-awesome.css" rel="stylesheet" />
        <link href="<%=request.getContextPath()%>/assets/plugins/pace/pace-theme-big-counter.css" rel="stylesheet" />
        <link href="<%=request.getContextPath()%>/assets/css/style.css" rel="stylesheet" />
        <link href="<%=request.getContextPath()%>/assets/css/main-style.css" rel="stylesheet" />

    </head>

    <body class="body-Login-back">

        <div class="container">

            <div class="row">
                <div class="col-md-4 col-md-offset-4 text-center logo-margin ">
                    <img src="<%=request.getContextPath()%>/assets/img/logo.png" alt=""/>
                </div>
                <div class="col-md-4 col-md-offset-4">
                    <div class="login-panel panel panel-default">                  
                        <div class="panel-heading">
                            <h3 class="panel-title">Please Sign In</h3>
                        </div>
                        <div class="panel-body">
                            <form role="form" method="POST" action="j_security_check">
                                <fieldset>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="UserName" name="j_username" type="text" autofocus>
                                    </div>
                                    <div class="form-group">
                                        <input class="form-control" placeholder="Password" name="j_password" type="password" value="">
                                    </div>
                                    <div class="checkbox">
                                        <label>
                                            <input name="remember" type="checkbox" value="Remember Me">Remember Me
                                        </label>
                                    </div>
                                    <!-- Change this to a button or input when using this as a form -->
                                    <button type="submit" class="btn btn-lg btn-success btn-block">Valider</button>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- Core Scripts - Include with every page -->
        <script src="<%=request.getContextPath()%>/assets/plugins/jquery-1.10.2.js"></script>
        <script src="<%=request.getContextPath()%>/assets/plugins/bootstrap/bootstrap.min.js"></script>
        <script src="<%=request.getContextPath()%>/assets/plugins/metisMenu/jquery.metisMenu.js"></script>

    </body>

</html>
