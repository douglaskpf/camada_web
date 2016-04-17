/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.servlets;

import br.edu.ifsul.dao.UsuarioDAO;
import br.edu.ifsul.modelo.Usuario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author douglas
 */
@WebServlet(name = "ServletUsuario", urlPatterns = {"/usuario/ServletUsuario"})
public class ServletUsuario extends HttpServlet {

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
        UsuarioDAO dao = (UsuarioDAO) request.getSession().getAttribute("usuarioDao");
        if (dao == null) {
            dao = new UsuarioDAO();
        }
        String tela = "";
        String acao = request.getParameter("acao");
        if (acao == null) {
            tela = "listar.jsp";

        } else if (acao.equals("incluir")) {
            dao.setObjetoSelecionado(new Usuario());
            dao.setMensagem("");
            tela = "formulario.jsp";
        } else if (acao.equals("alterar")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            dao.setObjetoSelecionado(dao.localizar(id));
            dao.setMensagem("");
            tela = "formulario.jsp";
        } else if (acao.equals("excluir")) {
            Integer id = Integer.parseInt(request.getParameter("id"));
            Usuario obj = dao.localizar(id);
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
            
            
            Boolean status = null;
            try {
                status = Boolean.parseBoolean(request.getParameter("status"));
            } catch (Exception e) {
                dao.setMensagem("erro ao converter id");
            }
            
            
             Boolean admin = null;
            try {
                admin = Boolean.parseBoolean(request.getParameter("admin"));
            } catch (Exception e) {
                dao.setMensagem("erro ao converter id");
            }
            
            
            
            dao.getObjetoSelecionado().setId(id);
            dao.getObjetoSelecionado().setNome(request.getParameter("nome"));
            dao.getObjetoSelecionado().setTelefone(request.getParameter("telefone"));
            dao.getObjetoSelecionado().setEmail(request.getParameter("email"));
            dao.getObjetoSelecionado().setEndereco(request.getParameter("endereco"));
            dao.getObjetoSelecionado().setRg(request.getParameter("rg"));
            dao.getObjetoSelecionado().setCpf(request.getParameter("cpf"));
            dao.getObjetoSelecionado().setApelido(request.getParameter("apelido"));
            dao.getObjetoSelecionado().setSenha(request.getParameter("senha"));
            dao.getObjetoSelecionado().setStatus(status);
            dao.getObjetoSelecionado().setAdmin(admin);

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

        //atualizr o dao na sessão
        request.getSession().setAttribute("usuarioDao", dao);
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
