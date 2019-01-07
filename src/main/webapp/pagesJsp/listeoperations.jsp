<%-- 
    Document   : tables
    Created on : Sep 27, 2018, 8:45:00 PM
    Author     : Djabi & SOULAMA
--%>

<%@include file="header.jsp"%>


<div class="row">
    <!--page header--> 
    <div class="col-lg-12">
        <h1 class="page-header">Liste Des Opérations éffectuées</h1>
    </div>
    <!--end  page header--> 
</div>
<div class="row">
    <div class="col-lg-12">
        <div class="panel panel-default">
            <div class="panel-heading">
                <marquee><h3>Tables des différentes opérations éffectuées</h3></marquee>
            </div>
            <div class="panel-body">
                <div class="table-responsive">

                    <!--debu tableau versement-->
                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <h1>VERSEMENT</h1>
                        <thead>
                            <tr>
                                <th>DATE DE LA TRANSACTION</th>
                                <th>No DE COMPTE</th>
                                <th>MONTANT</th>
                                <th>REFERENCE</th>
                                <th >SOLDE ACTUEL DU COMPTE</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${listeVersement}" var="c">
                                <tr class="odd gradeX">
                                    <td class="center">${c.dateOperation}</td>
                                    <td class="center">${c.compte.numeroCompte}</td>
                                    <td class="center">${c.montant}</td>
                                    <td class="center">${c.reference}</td>
                                    <td class="center">${c.compte.solde}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!--fin tableau versement-->


                    <!--debut du table retrait-->
                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <br>
                        <h1>Retrait</h1>
                        <thead>
                            <tr>
                                <th>DATE DE LA TRANSACTION</th>
                                <th>No DE COMPTE</th>
                                <th>MONTANT</th>
                                <th>REFERENCE</th>
                                <th >SOLDE ACTUEL DU COMPTE</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${listeRetrait}" var="c">
                                <tr class="odd gradeX">
                                    <td class="center">${c.dateOperation}</td>
                                    <td class="center">${c.compte.numeroCompte}</td>
                                    <td class="center">${c.montant}</td>
                                    <td class="center">${c.reference}</td>
                                    <td class="center">${c.compte.solde}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <!--fin du table retrait--> 

                    <!--debut table retrait-->
                    <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                        <br>
                        <h1>VIREMENT</h1>
                        <thead>
                            <tr>
                                <th>DATE DE LA TRANSACTION</th>
                                <TH>TYPE DE TRANSACTION</TH>
                                <th>No DE COMPTE</th>
                                <th>MONTANT</th>
                                <th>REFERENCE</th>
                                <th >SOLDE ACTUEL DU COMPTE</th>
                            </tr>
                        </thead>

                        <tbody>
                            <c:forEach items="${listeVirement}" var="c">
                                <tr class="odd gradeX">
                                    <td class="center">${c.dateOperation}</td>
                                    <td class="center">${c.typeVirement}</td>
                                    <td class="center">${c.compte.numeroCompte}</td>
                                    <td class="center">${c.montant}</td>
                                    <td class="center">${c.reference}</td>
                                    <td class="center">${c.compte.solde}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    
                </div>
            </div>
        </div>
        <!--End Advanced Tables--> 
    </div>
</div>



</div>
<!--end page-wrapper--> 

</div>
<!--end wrapper--> 

<!--Core Scripts - Include with every page--> 
<script src="assets/plugins/jquery-1.10.2.js"></script>
<script src="assets/plugins/bootstrap/bootstrap.min.js"></script>
<script src="assets/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="assets/plugins/pace/pace.js"></script>
<script src="assets/scripts/siminta.js"></script>
<!--Page-Level Plugin Scripts-->
<script src="assets/plugins/dataTables/jquery.dataTables.js"></script>
<script src="assets/plugins/dataTables/dataTables.bootstrap.js"></script>
<script>
    $(document).ready(function () {
        $('#dataTables-example').dataTable();
    });
</script>

</body>

</html>
