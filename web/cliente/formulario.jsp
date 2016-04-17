<%-- 
    Document   : formulario
    Created on : 11/04/2016, 21:02:32
    Author     : douglas
--%>

<%@page import="br.edu.ifsul.dao.ClienteDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="clienteDao" scope="session" type="ClienteDAO"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Editar Cliente</title>
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
        <h1>Editar Cliente</h1>
        <h2><%=clienteDao.getMensagem()%></h2>
        <form name="form" id="form" action="ServletCliente" method="POST">
            <input id="acao" name="acao" type="hidden">

                      Id:<input type="text" name="id" value="<%= clienteDao.getObjetoSelecionado().getId() == null ? ""
                              : clienteDao.getObjetoSelecionado().getId()%>" readonly size="6">
            <br>
            <br>

                        Nome:<input type="text" name="nome" value="<%= clienteDao.getObjetoSelecionado().getNome() == null ? ""
                                : clienteDao.getObjetoSelecionado().getNome()%>"  size="40">
            <br>
            <br>


                            Telefone:<input type="text" name="telefone" value="<%= clienteDao.getObjetoSelecionado().getTelefone() == null ? ""
                                    : clienteDao.getObjetoSelecionado().getTelefone()%>"  size="20">
            <br>
            <br>

                         Email:<input type="text" name="email" value="<%= clienteDao.getObjetoSelecionado().getEmail() == null ? ""
                                 : clienteDao.getObjetoSelecionado().getEmail()%>"  size="40">
            <br>
            <br>

                            Endereço:<input type="text" name="endereco" value="<%= clienteDao.getObjetoSelecionado().getEndereco() == null ? ""
                                    : clienteDao.getObjetoSelecionado().getEndereco()%>"  size="40">
            <br>
            <br>

                      RG:<input type="text" name="rg" value="<%= clienteDao.getObjetoSelecionado().getRg() == null ? ""
                              : clienteDao.getObjetoSelecionado().getRg()%>"  size="40">
            <br>
            <br>

                       CPF:<input type="text" name="cpf" value="<%= clienteDao.getObjetoSelecionado().getCpf() == null ? ""
                               : clienteDao.getObjetoSelecionado().getCpf()%>"  size="40">
            <br>
            <br>                 


            <input type="button" value="Salvar" name="btnSalvar" onclick="doSalvar()"/>
            <input type="button" value="Cancelar" name="btnCancelar" onclick="doCancelar()"/>
        </form>
    </body>
</html>
