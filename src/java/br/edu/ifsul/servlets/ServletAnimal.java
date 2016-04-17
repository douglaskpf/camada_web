/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.servlets;

import br.edu.ifsul.dao.AnimalDAO;
import br.edu.ifsul.dao.ClienteDAO;
import br.edu.ifsul.modelo.Animal;
import java.io.IOException;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author douglas
 */
@WebServlet(name = "ServletAnimal", urlPatterns = {"/animal/ServletAnimal"})
public class ServletAnimal extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AnimalDAO dao = (AnimalDAO) request.getSession().getAttribute("animalDao");
        if (dao == null) {
            dao = new AnimalDAO();
        }
        
        ClienteDAO daoCliente =(ClienteDAO) request.getSession().getAttribute("clienteDao"); //chave estrangeira
        
        if (daoCliente == null){
            
            daoCliente = new ClienteDAO();
    }
        
        
        String tela = "";
        String acao = request.getParameter("acao");
        if (acao == null) {
            tela = "listar.jsp";

        } else if (acao.equals("incluir")) {
            dao.setObjetoSelecionado(new Animal());
            dao.setMensagem("");
            tela = "formulario.jsp";
        } else if (acao.equals("alterar")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            dao.setObjetoSelecionado(dao.localizar(id));
            dao.setMensagem("");
            tela = "formulario.jsp";
        } else if (acao.equals("excluir")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Animal obj = dao.localizar(id);
            if (obj != null) {
                dao.remover(obj);
            }
           
            tela = "listar.jsp";
        } else if (acao.equals("salvar")) {
            Integer id = null;
            try {
                id = Integer.parseInt(request.getParameter("id"));
            } catch (Exception e) {
                dao.setMensagem("erro ao converter id");
            }
            
            
            Integer idCliente = null;
             try{
                 
                 idCliente = Integer.parseInt(request.getParameter("idCliente"));
             }catch(Exception e){
                 
                 dao.setMensagem("Erro ao converter o ID do cliente");
                 
                           }
            
           //transformando string em calendar             
       // String dataEmTexto = request.getParameter("nascimento");
       // Calendar nascimento;
        
         //fazendo a conversão da data
    /*    try {
            Date date = new SimpleDateFormat("dd/MM/yyyy").parse(dataEmTexto);
            nascimento = Calendar.getInstance();
            nascimento.setTime(date);
        } catch (ParseException e) {
            System.out.println("Erro de conversão da data");
            return; //para a execução do método
        }   
            */
            dao.getObjetoSelecionado().setId(id);
            dao.getObjetoSelecionado().setNome(request.getParameter("nome"));
            dao.getObjetoSelecionado().setEspecie(request.getParameter("especie"));
            dao.getObjetoSelecionado().setRaca(request.getParameter("raca"));
            dao.getObjetoSelecionado().setPelagem(request.getParameter("pelagem"));
            
      //      dao.getObjetoSelecionado().setNascimento(nascimento);
            dao.getObjetoSelecionado().setCliente(daoCliente.localizar(idCliente));

            if (dao.validaObjeto(dao.getObjetoSelecionado())) {
                dao.salvar(dao.getObjetoSelecionado());
                tela = "listar.jsp";
            } else {
                tela = "formulario.jsp";
            }
        } else if (acao.equals("cancelar")) {
            dao.setMensagem("");
            tela = "listar.jsp";
        }

        //atualizar o dao na sessão
        request.getSession().setAttribute("animalDao", dao);
        request.getSession().setAttribute("clienteDao", daoCliente);
        //redirect tela da ação.
        response.sendRedirect(tela);
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
