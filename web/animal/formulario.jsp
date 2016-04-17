<%-- 
    Document   : formulario
    Created on : 11/04/2016, 21:02:32
    Author     : douglas
--%>

<%@page import="br.edu.ifsul.modelo.Cliente"%>
<%@page import="br.edu.ifsul.dao.AnimalDAO"%>
<%@page import="br.edu.ifsul.dao.ClienteDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="clienteDao" scope="session" type="ClienteDAO"/>
<jsp:useBean id="animalDao" scope="session" type="AnimalDAO"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Editar Animal</title>
    </head>
    <script>
        function doSalvar() {
            document.getElementById("acao").value = 'salvar';
            document.getElementById("form").submit();
        }
        function doCancelar() {
            document.getElementById("acao").value = 'cancelar';
            document.getElementById("form").submit();
        }
    </script>
    <body>
        <h1>Editar Animal</h1>
        <h2><%=animalDao.getMensagem()%></h2>
        <form name="form" id="form" action="ServletAnimal" method="POST">
            <input id="acao" name="acao" type="hidden">

                      Id:<input type="text" name="id" value="<%= animalDao.getObjetoSelecionado().getId() == null ? ""
                              : animalDao.getObjetoSelecionado().getId()%>" readonly size="6">
            <br>
            <br>

                        Nome:<input type="text" name="nome" value="<%= animalDao.getObjetoSelecionado().getNome() == null ? ""
                               : animalDao.getObjetoSelecionado().getNome()%>"  size="40">

            <br>
            <br>

                           Espécie:<input type="text" name="especie" value="<%= animalDao.getObjetoSelecionado().getEspecie() == null ? ""
                               : animalDao.getObjetoSelecionado().getEspecie()%>"  size="40">

            <br>
            <br>

                        Raça:<input type="text" name="raca" value="<%= animalDao.getObjetoSelecionado().getRaca() == null ? ""
                               : animalDao.getObjetoSelecionado().getRaca()%>"  size="40">

            <br>
            <br>

                           Pelagem:<input type="text" name="pelagem" value="<%= animalDao.getObjetoSelecionado().getPelagem() == null ? ""
                               : animalDao.getObjetoSelecionado().getPelagem()%>"  size="40">

            <br>
            <br>


            Cliente:<select name="idCliente" id="idCliente">
                <%
                    for (Cliente c : clienteDao.getLista()) {
                        String selected = "";

                        if (animalDao.getObjetoSelecionado().getCliente() != null) {

                            selected
                                    = animalDao.getObjetoSelecionado().getCliente().getId().equals(c.getId())
                                    ? "selected" : "";
                        }

                %>

                <option value="<%=c.getId()%>"<%=selected%>><%=c.getNome()%></option>

                <%
                    }
                %>              
            </select>

            <br>
            <br>
            <input type="button" value="Salvar" name="btnSalvar" onclick="doSalvar()"/>
            <input type="button" value="Cancelar" name="btnCancelar" onclick="doCancelar()"/>
        </form>
    </body>
</html>
