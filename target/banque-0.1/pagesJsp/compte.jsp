<%-- 
    Document   : compte
    Created on : Oct 2, 2018, 2:47:04 PM
    Author     : Djabi
--%>

<%@include file="header.jsp"%>


            <div class="row">
                <!--quick info section -->
<!--                <div class="col-lg-3">
                    <div class="alert alert-danger text-center">
                        <i class="fa fa-calendar fa-3x"></i>&nbsp;TYPE DE COMMPTE

                    </div>
                </div>-->
                <div class="col-lg-6">
                    <a href="<c:url value="/admin/courantservlet"/>">
                    <div class="alert alert-success text-center">
                        <i class="fa  fa-pencil fa-3x"></i>&nbsp;<b>LISTE DES COMPTES COURANTS 
                    </div>
                    </a>
                </div>
                <div class="col-lg-6">
                    <a href="<c:url value="/admin/epargneservlet"/>">
                    <div class="alert alert-info text-center">
                        <i class="fa  fa-pencil fa-3x"></i>&nbsp;<b> </b>LISTE DES COMPTES EPARGNE

                    </div>
                    </a>
                </div>
                
                <!--end quick info section -->
            </div>

            

           


         


        </div>
        <!-- end page-wrapper -->

    </div>
    <!-- end wrapper -->

    <!-- Core Scripts - Include with every page -->
    <script src="assets/plugins/jquery-1.10.2.js"></script>
    <script src="assets/plugins/bootstrap/bootstrap.min.js"></script>
    <script src="assets/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="assets/plugins/pace/pace.js"></script>
    <script src="assets/scripts/siminta.js"></script>
    <!-- Page-Level Plugin Scripts-->
    <script src="assets/plugins/morris/raphael-2.1.0.min.js"></script>
    <script src="assets/plugins/morris/morris.js"></script>
    <script src="assets/scripts/dashboard-demo.js"></script>

</body>

</html>
