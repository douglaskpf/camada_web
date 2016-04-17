

<%@page import="br.edu.ifsul.modelo.Animal"%>
<%@page import="br.edu.ifsul.dao.AnimalDAO"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>

<jsp:useBean id="animalDao" scope="session" type="AnimalDAO"/>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Lista de Animais</title>
    </head>
    <body>
        <h1>Lista de Animais</h1>
        <a href="../index.html">Início</a>
        <h2><%=animalDao.getMensagem()%></h2>
        <div style="float:left;"><a href="ServletAnimal?acao=incluir">Novo Animal</a></div>
        <br>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Espécie</th>
                    <th>Raça</th>
                    <th>Pelagem</th>  
                    <th>Cliente</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                <% for(Animal a : animalDao.getLista()){ %>
            <tr>
                <td><%=a.getId()%></td>
                <td><%=a.getNome()%></td>
                <td><%=a.getEspecie()%></td>
                <td><%=a.getRaca()%></td>
                <td><%=a.getPelagem()%></td>
                <td><%=a.getCliente()%></td>
                
                <td><a href="ServletAnimal?acao=alterar&id=<%=a.getId()%>">Alterar</a>
                    <a href="ServletAnimal?acao=excluir&id=<%=a.getId()%>">Excluir</a></td>
            </tr>
            <% }%>
            </tbody>
        </table>
    </body>
</html>

