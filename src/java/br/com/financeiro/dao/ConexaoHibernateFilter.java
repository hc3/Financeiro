/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.dao;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import org.hibernate.SessionFactory;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */

public class ConexaoHibernateFilter implements Filter{
   private SessionFactory sf;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.sf = HibernateUtil.getSessionFactory();
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            this.sf.getCurrentSession().beginTransaction();
            chain.doFilter(request, response);
            this.sf.getCurrentSession().getTransaction().commit();
            this.sf.getCurrentSession().close();
        }catch(Throwable ex) {
            
            try{
                if(this.sf.getCurrentSession().getTransaction().isActive()){
                    this.sf.getCurrentSession().getTransaction().rollback();
                }
            }catch(Throwable t){
                t.printStackTrace();
            }
            throw new ServletException(ex);
        }
    }

    @Override
    public void destroy() { }
   

}
