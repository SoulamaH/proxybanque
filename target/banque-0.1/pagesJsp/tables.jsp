<%-- 
    Document   : tables
    Created on : Sep 27, 2018, 8:45:00 PM
    Author     : Djabi & SOULAMA
--%>

<%@include file="header.jsp"%>


<div class="row">
    <!--  page header -->
    <div class="col-lg-12">
        <h1 class="page-header">Liste client </h1>
    </div>
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
                                <!--<th colspan="2">ACTIONS</th>-->
                            </tr>
                        </thead>

                        <tbody>

                            <!-- COURANT parcour de la BD "table compte" pour l'affichage dans le tableau (page jsp) -->
                            <c:forEach items="${listeCourantClient}" var="c"   >

                                <tr class="odd gradeX">
                                    <td class="center">${c.client.nom}</td>
                                    <td class="center">${c.client.prenom}</td>
                                    <td class="center">${c.client.tel}</td>

                                    <td class="center">${c.numeroCompte}</td>
                                    <td class="center">Compte Courant</td>

                                    <td class="center">${c.dateCreation}</td>
                                    <td class="center">${c.solde}</td>
                                    <td class="center">${c.client.id}</td>
                                    <td class="center">        
                                        <a href="<c:url value="/admin/formeenregistrementclient?idClient=${c.client.id}"/>">
                                            <button type="button" class="btn btn-info">editer</button>
                                        </a>
                                    </td>
                                    <td class="center"> <input type="submit" value="supprimer"  class="btn btn-danger"/></td>
                                </tr>

                            </c:forEach>

                            <c:forEach items="${listeEpargneClient}" var="c"   >

                                <tr class="odd gradeX">
                                    <td class="center">${c.client.nom}</td>
                                    <td class="center">${c.client.prenom}</td>
                                    <td class="center">${c.client.tel}</td>

                                    <td class="center">${c.numeroCompte}</td>
                                    <td class="center">Compte Epargne</td>

                                    <td class="center">${c.dateCreation}</td>
                                    <td class="center">${c.solde}</td>
                                    <td class="center">${c.client.id}</td>
                                    <td class="center"> <input type="submit" value="editer"  class="btn btn-info"/></td>
                                    <td class="center"> <input type="submit" value="supprmier"  class="btn btn-danger"/></td>

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
<script src="assets/plugins/dataTables/jquery.dataTables.js"></script>
<script src="assets/plugins/dataTables/dataTables.bootstrap.js"></script>
<script>
    $(document).ready(function () {
        $('#dataTables-example').dataTable();
    });
</script>

</body>

</html>
