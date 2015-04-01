/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.dao;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */

public class DAOFactory {
 
    public static UsuarioDAO criarUsuarioDAO() {
        UsuarioDAOHibernate usuarioDAO = new UsuarioDAOHibernate();
        usuarioDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return usuarioDAO;
    }
    
    public static ContaDAO criarContaDAO(){
        ContaDAOHibernate contaDAO = new ContaDAOHibernate();
        contaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return contaDAO;
    }
    
    public static CategoriaDAO criarCategoria(){
        CategoriaDAOHibernate categoriaDAO = new CategoriaDAOHibernate();
        categoriaDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return categoriaDAO;
    }
    
    public static LancamentoDAO criarLancamento(){
        LancamentoDAOHibernate lancamentoDAO = new LancamentoDAOHibernate();
        lancamentoDAO.setSession(HibernateUtil.getSessionFactory().getCurrentSession());
        return lancamentoDAO;
    }
    
}
