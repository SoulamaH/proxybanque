<%-- 
    Document   : index
    Created on : Sep 27, 2018, 8:40:33 PM
    Author     : Djabi
--%>

<%@include file="header.jsp"%>


<div class="row">
    <!--quick info section -->

    <div class="col-lg-6">

        <div class="alert alert-info text-center">
            <i class="fa  fa-pencil fa-3x"></i>&nbsp;<b> </b>EFFECTUER UN VERSEMENT

        </div>
    </div>
</div>
<div class="row">
    <div class="col-lg-6">
        <!--   Kitchen Sink -->
        <div class="panel panel-default">
            <div class="panel-heading">
                <marquee>VeRsEmEnT</marquee>
            </div>
            <div class="panel-body">
                <div class="table-responsive">


                    <form action="enregistrementVersement" method="POST">
                        <table class="table table-striped table-bordered table-hover">

                            <div class="form-group">
                                <label for="numCompte">Entrer No Compte :</label>
                                <input type="text" value="<c:out value="${numeroCompte}"/>" class="form-control" id="numCompte" name="numcompte" placeholder="Entrer le numero du compte pour le versement" required="true">
                            </div>
                            <div class="form-group">
                                <label for="montant">Entrer Le Montnat </label>
                                <input type="number" value="<c:out value="${montant}"/>" class="form-control" id="montant" name="montant" placeholder="Entrer le montant a verser" required="true">
                            </div>

                            <input type="submit" value="Valider" name="btnValider" />

                            <c:if test="${!empty confirme}">
                                <div class="alert alert-success" role="alert"><c:out value="${confirme}"/> </div>
                            </c:if>

                        </table>
                    </form>
                </div>
            </div>
        </div>
        <!-- End  Kitchen Sink -->
    </div>
</div>
<!-- end page-wrapper -->

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