/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.dao;

import br.com.financeiro.pojo.Conta;
import br.com.financeiro.pojo.Lancamento;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */

public class LancamentoDAOHibernate implements LancamentoDAO{
    
    private Session session;
    
    public void setSession(Session session) {
        this.session = session;
    }
    
    public void salvar(Lancamento lancamento) {
        this.session.saveOrUpdate(lancamento);
    }
    
    public void excluir(Lancamento lancamento){
        this.session.delete(lancamento);
    }
    
    public Lancamento carregar(Integer lancamento) {
        return (Lancamento) this.session.get(Lancamento.class, lancamento);
    }
    
    public List<Lancamento> listar(Conta conta , Date dataInicio , Date dataFim) {
        Criteria criteria = this.session.createCriteria(Lancamento.class);
        
        if(dataInicio != null && dataFim != null) {
            criteria.add(Restrictions.between("data", dataInicio, dataFim));
        }else if(dataInicio != null){
            criteria.add(Restrictions.ge("data",dataInicio));
        }else if (dataFim != null) {
            criteria.add(Restrictions.ge("data", dataFim));
        }
        
        criteria.add(Restrictions.eq("conta", conta));
        criteria.addOrder(Order.asc("data"));
        return criteria.list();
    }
    
    public float saldo(Conta conta , Date data ){
        StringBuffer sql = new StringBuffer();
        
        sql.append("select sum(l.valor * c.fator)");
        sql.append("from LANCAMENTO 1, ");
        sql.append("  CATEGORIA c");
        sql.append(" where l.categoria = c.codigo");
        sql.append("  and l.conta = :conta");
        sql.append("  and l.data <= :data");
        SQLQuery query = this.session.createSQLQuery(sql.toString());
        query.setParameter("conta" , conta.getConta());
        query.setParameter("data" , data);
        BigDecimal saldo = (BigDecimal) query.uniqueResult();
        if(saldo != null){
            return saldo.floatValue();
        }
        
        return 0f;
    }
}
