<%-- 
    Document   : compteEpargne
    Created on : Oct 2, 2018, 2:44:45 PM
    Author     : Djabi
--%>

<%@include file="header.jsp"%>


<div class="row">

    <div class="col-lg-12">

        <div class="alert alert-success text-center">
            <i class="fa  fa-pencil fa-3x"></i>&nbsp;<b>LISTE DES COMPTES EPARGNES 
        </div>

    </div>

    <!--end quick info section -->
</div>


<div class="row">
    <!--  page header -->

    <!-- end  page header -->
</div>
<div class="row">
    <div class="col-lg-12">
        <!-- Advanced Tables -->
        <div class="panel panel-default">
            <div class="panel-heading">
                Advanced Tables
            </div>
            <div class="panel-body">
                <div class="table-responsive">
                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <thead>
                            <tr>
                                <th>NOM</th>
                                <th>PRENOM</th>
                                <th>TEL</th>
                                <th>No COMPTE</th>
                                <TH>TYPE DE COMPTE</TH>
                                <th>DATE CREATION</th>
                                <th>SOLDE</th>
                                <th>CODE CLIENT</th>
                            </tr>
                        </thead>

                        <tbody>

                            <!-- EPARGNE parcour de la BD "table compte" pour l'affichage dans le tableau (page jsp) -->
                            <c:forEach items="${listeEpargneClient}" var="c"   >

                                <tr class="odd gradeX">
                                    <td class="center">${c.client.nom}</td>
                                    <td class="center">${c.client.prenom}</td>
                                    <td class="center">${c.client.tel}</td>

                                    <td class="center">${c.numeroCompte}</td>
                                    <td class="center">Compte epargne</td>
                                    <td class="center">${c.dateCreation}</td>
                                    <td class="center">${c.solde}</td>
                                    <td>${c.client.id}</td>
                                </tr>

                            </c:forEach>



                        </tbody>









                    </table>
                </div>

            </div>
        </div>
        <!--End Advanced Tables -->
    </div>
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
