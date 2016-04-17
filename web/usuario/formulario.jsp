<%-- 
    Document   : formulario
    Created on : 11/04/2016, 21:02:32
    Author     : douglas
--%>

<%@page import="br.edu.ifsul.modelo.Usuario"%>
<%@page import="br.edu.ifsul.dao.UsuarioDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="usuarioDao" scope="session" type="UsuarioDAO"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Editar Usuário</title>
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
        <h1>Editar Usuário</h1>
        <h2><%=usuarioDao.getMensagem()%></h2>
        <form name="form" id="form" action="ServletUsuario" method="POST">
            <input id="acao" name="acao" type="hidden">

            Id:<input type="text" name="id" value="<%=usuarioDao.getObjetoSelecionado().getId() == null ? ""
                              : usuarioDao.getObjetoSelecionado().getId()%>" readonly size="6">
            <br>
            <br>

                        Nome:<input type="text" name="nome" value="<%= usuarioDao.getObjetoSelecionado().getNome() == null ? ""
                                : usuarioDao.getObjetoSelecionado().getNome()%>"  size="40">
            <br>
            <br>


                            Telefone:<input type="text" name="telefone" value="<%= usuarioDao.getObjetoSelecionado().getTelefone() == null ? ""
                    : usuarioDao.getObjetoSelecionado().getTelefone()%>"  size="20">
            <br>
            <br>

                         Email:<input type="text" name="email" value="<%= usuarioDao.getObjetoSelecionado().getEmail() == null ? ""
                    : usuarioDao.getObjetoSelecionado().getEmail()%>"  size="40">
            <br>
            <br>

                            Endereço:<input type="text" name="endereco" value="<%= usuarioDao.getObjetoSelecionado().getEndereco() == null ? ""
                    : usuarioDao.getObjetoSelecionado().getEndereco()%>"  size="40">
            <br>
            <br>

                      RG:<input type="text" name="rg" value="<%= usuarioDao.getObjetoSelecionado().getRg() == null ? ""
                    : usuarioDao.getObjetoSelecionado().getRg()%>"  size="40">
            <br>
            <br>

                       CPF:<input type="text" name="cpf" value="<%= usuarioDao.getObjetoSelecionado().getCpf() == null ? ""
                    : usuarioDao.getObjetoSelecionado().getCpf()%>"  size="40">
            <br>
            <br>                 



            Apelido:<input type="text" name="apelido" value="<%=usuarioDao.getObjetoSelecionado().getApelido() == null ? ""
                                   : usuarioDao.getObjetoSelecionado().getApelido()%>"  size="40">
            <br>
            <br>

            Senha:<input type="text" name="senha" value="<%=usuarioDao.getObjetoSelecionado().getSenha() == null ? ""
                                 : usuarioDao.getObjetoSelecionado().getSenha()%>"  size="40">  
            <br>
            <br>

            Status:<input type="text" name="status" value="<%=usuarioDao.getObjetoSelecionado().getStatus() == null ? ""
                                  : usuarioDao.getObjetoSelecionado().getStatus()%>"  size="40">  
            <br>
            <br>

            Admin:<input type="text" name="admin" value="<%=usuarioDao.getObjetoSelecionado().getAdmin() == null ? ""
                                 : usuarioDao.getObjetoSelecionado().getAdmin()%>"  size="40">  
            <!--fazer select -->

            <br>
            <br>


            <input type="button" value="Salvar" name="btnSalvar" onclick="doSalvar()"/>
            <input type="button" value="Cancelar" name="btnCancelar" onclick="doCancelar()"/>
        </form>
    </body>
</html>
