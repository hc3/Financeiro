/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.dao;

import br.com.financeiro.pojo.Conta;
import br.com.financeiro.pojo.Usuario;
import java.util.LinkedList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */

public class ContaDAOHibernate implements ContaDAO{
    
    private Session session;
    
    public void setSession(Session session) {
        this.session = session;
    }

    @Override
    public void salvar(Conta conta) {
        this.session.saveOrUpdate(conta);
    }

    @Override
    public void excluir(Conta conta) {
        this.session.delete(conta);
    }

    @Override
    public Conta carregar(Integer conta) {
        return (Conta) this.session.get(Conta.class, conta);
    }

    @Override
    public List<Conta> listar(Usuario usuario) {
        
        Criteria criteria = this.session.createCriteria(Conta.class);
        criteria.add(Restrictions.eq("usuario", usuario));
        
        return criteria.list();        
    }

    @Override
    public Conta buscarFavorita(Usuario usuario) {
        Criteria criteria = this.session.createCriteria(Conta.class);
        criteria.add(Restrictions.eq("usuario", usuario));
        criteria.add(Restrictions.eq("favorita", true));
        return (Conta) criteria.uniqueResult();
    }
}
