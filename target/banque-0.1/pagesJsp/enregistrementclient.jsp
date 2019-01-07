<%-- 
    Document   : index
    Created on : 5 sept. 2018, 19:51:28
    Author     : USER
--%>
<%@include file="header.jsp"%>
<h2>GESTION CLIENTS</h2>

<div class="panel panel-info">    
    <div class="panel-heading"> AJOUTER UN NOUVEAU CLIENTS</div>

    <div class="panel-body">
        <form action="formeenregistrementclient" method="POST">


            <div class="form-group">
                <label for="nom" >NOMenregistrementjsp</label>
                <input type="test" class="form-control" id="nom" name="nom" value="<c:out value="${valnom}"></c:out>" placeholder="Entrer le nom" required="true"> 
                <c:if test="${nom == 'ok'}">
                    <small style="color: red" id="emailHelp" class="form-text text-muted"> le nom n'est pas renseigné</small>
                </c:if>
            </div>

            <div class="form-group">
                <label for="prenom" >PRENOM</label>
                <input type="prenom" class="form-control" id="prenom" name="prenom" value="<c:out value="${valprenom}"></c:out>" placeholder="Entrer le prenom" required="true">
                <c:if test="${prenom == 'ok'}">
                    <small style="color: red" id="penomHelp" class="form-text text-muted"> le prenom n'est pas renseigné</small>
                </c:if>
            </div>

            <div class="form-group">
                <label for="tel">TEL</label>
                <input type="tel" class="form-control" id="tel" name="tel" placeholder="Entrer le numero" required="true">
            </div>

            <div class="form-group">
                <label for="solde">SOLDE</label>
                <input type="number" class="form-control" id="solde" name="solde" placeholder="Entrer le solde" required="true">
            </div>


            <!-- ajout de nouvelle ligne-->

            <div class="form-group">
                <label for="nom" >Type COMPTE </label><br>
                <input type="radio" name="compte" value="CC" required="true"/> <br> <h6>Compte courant</h6>
                <input type="radio" name="compte" value="CE" required="true"/> <h6>Compte epargne</h6>
            </div>

            <!--fin de nouvelle lignes-->

            <c:if test="${!empty confirme}">
                <div class="alert alert-success" role="alert"><c:out value="${ confirme }">enregistre avec succes</c:out> </div>
            </c:if>


            <button type="submit" class="btn btn-primary"> Enregistrer</button>

        </form>
    </div>

</div>
