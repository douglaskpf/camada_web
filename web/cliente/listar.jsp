<%-- 
    Document   : listar
    Created on : 11/04/2016, 21:01:59
    Author     : douglas
--%>

<%@page import="br.edu.ifsul.modelo.Cliente"%>
<%@page import="br.edu.ifsul.dao.ClienteDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<jsp:useBean id="clienteDao" scope="session" type="ClienteDAO"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Lista de Clientes</title>
    </head>
    <body>
        <h1>Lista de Clientes</h1>
        <a href="../index.html">Início</a>
        <h2><%=clienteDao.getMensagem()%></h2>
        <div style="float:left;"><a href="ServletCliente?acao=incluir">Novo Cliente</a></div>
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
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <% for (Cliente c : clienteDao.getLista()) {%>
                <tr>
                    <td><%=c.getId()%></td>
                    <td><%=c.getNome()%></td>
                    <td><%=c.getTelefone()%></td>
                    <td><%=c.getEmail()%></td>
                    <td><%=c.getEndereco()%></td>
                    <td><%=c.getRg()%></td>
                    <td><%=c.getCpf()%></td>
             

                    <td><a href="ServletCliente?acao=alterar&id=<%=c.getId()%>">Alterar</a>
                        <a href="ServletCliente?acao=excluir&id=<%=c.getId()%>">Excluir</a></td>
                </tr>
                <% }%>
            </tbody>
        </table>
    </body>
</html>