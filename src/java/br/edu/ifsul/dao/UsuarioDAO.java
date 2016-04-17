/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Usuario;
import br.edu.ifsul.util.Util;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

/**
 *
 * @author douglas
 */
public class UsuarioDAO {
    
    private Usuario objetoSelecionado;
    private String mensagem = ""; 
    private EntityManager em;

    public UsuarioDAO() {
        em = EntityManagerUtil.getEntityManager();
    }

    public boolean validaObjeto(Usuario obj) {
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<Usuario>> erros = validador.validate(obj);
        if (erros.size() > 0) {
            mensagem = "";
            mensagem += "Objeto com erros \n ";
            for (ConstraintViolation<Usuario> erro : erros) {
                mensagem += "erro>" + erro.getMessage() + "<br>";
            }
            return false;
        } else {
            return true;
        }
    }

    public List<Usuario> getLista() {
        return em.createQuery(" from Usuario order by nome").getResultList();
    }

    public boolean salvar(Usuario obj) {
        try {
            em.getTransaction().begin();
            if (obj.getId() == null) {
                em.persist(obj);
            } else {
                em.merge(obj);
            }
             mensagem = "Salvo com sucesso";
            em.getTransaction().commit();
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "erro ao persistir :" + Util.getMensagemErro(e);
            return false;
        }
    }
    public Usuario localizar(Integer id){
        return em.find(Usuario.class,id);
    }
    public boolean remover(Usuario obj) { 
        try {
            em.getTransaction().begin();
            em.remove(obj);
            em.getTransaction().commit();
            mensagem = "Removido com sucesso";
            return true;

        } catch (Exception e) {
            if (em.getTransaction().isActive() == false) {
                em.getTransaction().begin();
            }
            em.getTransaction().rollback();
            mensagem = "erro ao excluir :" + Util.getMensagemErro(e);
            return false;
        }
    }

    public Usuario getObjetoSelecionado() {
        return objetoSelecionado;
    }

    public void setObjetoSelecionado(Usuario objetoSeleciona) {
        this.objetoSelecionado = objetoSeleciona;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    } 

    public EntityManager getEm() {
        return em;
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
    
}
