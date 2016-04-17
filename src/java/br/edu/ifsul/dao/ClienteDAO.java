/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifsul.dao;

import br.edu.ifsul.jpa.EntityManagerUtil;
import br.edu.ifsul.modelo.Cliente;
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
public class ClienteDAO {
    
    private Cliente objetoSelecionado;
    private String mensagem = "";
    private EntityManager em;

    public ClienteDAO() {
        em = EntityManagerUtil.getEntityManager();
    }

    public boolean validaObjeto(Cliente obj) {
        Validator validador = Validation.buildDefaultValidatorFactory().getValidator();

        Set<ConstraintViolation<Cliente>> erros = validador.validate(obj);
        if (erros.size() > 0) {
            mensagem = "";
            mensagem += "Objeto com erros \n ";
            for (ConstraintViolation<Cliente> erro : erros) {
                mensagem += "erro>" + erro.getMessage() + "<br>";
            }
            return false;
        } else {
            return true;
        }
    }

    public List<Cliente> getLista() {
        return em.createQuery(" from Cliente order by nome").getResultList();
    }

    public boolean salvar(Cliente obj) {
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
    public Cliente localizar(Integer id){
        return em.find(Cliente.class,id);
    }
    public boolean remover(Cliente obj) {
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

    public Cliente getObjetoSelecionado() {
        return objetoSelecionado;
    }

    public void setObjetoSelecionado(Cliente objetoSeleciona) {
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
