<%-- 
    Document   : listar
    Created on : 11/04/2016, 21:01:59
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
        <title> Lista de Usuários </title>
    </head>
    <body>
        <h1>Lista de Usuários</h1>
        <a href="../index.html">Início</a>
        <h2><%=usuarioDao.getMensagem()%></h2>
        <div style="float:left;"><a href="ServletUsuario?acao=incluir">Novo Usuário</a></div>
        <br>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>Email</th>
                    <th>Endereço</th>
                    <th>RG</th>
                    <th>CPF</th>
                    <th>Apelido</th>
                    <th>Senha</th>
                    <th>Status</th>
                    <th>Admin</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <% for(Usuario u : usuarioDao.getLista()){ %>
            <tr>
                <td><%=u.getId()%></td>
                <td><%=u.getNome()%></td>
                <td><%=u.getTelefone()%></td>
                <td><%=u.getEmail()%></td>
                <td><%=u.getEndereco()%></td>
                <td><%=u.getRg()%></td>
                <td><%=u.getCpf()%></td>
                <td><%=u.getApelido()%></td>
                <td><%=u.getSenha()%></td>
                <td><%=u.getStatus()%></td>
                <td><%=u.getAdmin()%></td>
                
                
                <td><a href="ServletUsuario?acao=alterar&id=<%=u.getId()%>">Alterar</a>
                    <a href="ServletUsuario?acao=excluir&id=<%=u.getId()%>">Excluir</a></td>
            </tr>
            <% }%>
            </tbody>
        </table>
    </body>
</html>
