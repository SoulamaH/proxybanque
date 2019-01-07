<%-- 
    Document   : index
    Created on : Sep 27, 2018, 8:40:33 PM
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
                <div class="col-lg-4">
                    <a href="<c:url value="/admin/lescomptes"/>">
                    <div class="alert alert-success text-center">
                        <i class="fa  fa-pencil fa-3x"></i>&nbsp;<b>LES COMPTES  
                    </div>
                    </a>
                </div>
                <div class="col-lg-4">
                    <a href="<c:url value="/admin/listeoperation"/>">
                    <div class="alert alert-info text-center">
                        <i class="fa  fa-pencil fa-3x"></i>&nbsp;<b> </b>Liste des operations

                    </div>
                    </a>
                </div>
                
                <div class="col-lg-4">
                    <a href="<c:url value="/admin/effectueroperation"/>">
                    <div class="alert alert-warning text-center">
                        <i class="fa  fa-pencil fa-3x"></i>&nbsp;<b> </b>Effectuer une operation
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
    <script src="<%=request.getContextPath()%>/assets/plugins/jquery-1.10.2.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/bootstrap/bootstrap.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/pace/pace.js"></script>
    <script src="<%=request.getContextPath()%>/assets/scripts/siminta.js"></script>
    <!-- Page-Level Plugin Scripts-->
    <script src="<%=request.getContextPath()%>/assets/plugins/morris/raphael-2.1.0.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/plugins/morris/morris.js"></script>
    <script src="<%=request.getContextPath()%>/assets/scripts/dashboard-demo.js"></script>

</body>

</html>
