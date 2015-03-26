/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.bean;

import br.com.financeiro.pojo.Conta;
import br.com.financeiro.rn.ContaRN;
import br.com.financeiro.util.ContextoUtil;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */
@ManagedBean(name= "contaBean")
@RequestScoped
public class ContaBean {
    private Conta selecionada = new Conta();
    private List<Conta> lista = new LinkedList<Conta>();
    
    public ContaBean(){
        
    }
    
    public void salvar(){
        ContextoBean contextoBean = ContextoUtil.getContextBean();
        this.selecionada.setUsuario(contextoBean.getUsuarioLogado());
        ContaRN contaRN = new ContaRN();
        contaRN.salvar(this.selecionada);
        this.selecionada = new Conta();
        this.lista = null;
    }
    
    public void editar(){

    }
    
    public void excluir(){
        ContaRN contaRN = new ContaRN();
        contaRN.excluir(this.selecionada);
        this.selecionada = new Conta();
        this.lista = null;
    }
    
    public void tornarFavorita(){
        ContaRN contaRN = new ContaRN();
        contaRN.tornarFavorita(this.selecionada);
        this.selecionada = new Conta();
    }

    public Conta getSelecionada() {
        return selecionada;
    }

    public void setSelecionada(Conta selecionada) {
        this.selecionada = selecionada;
    }

    public List<Conta> getLista() {
        if(this.lista == null) {
            ContextoBean contextoBean = ContextoUtil.getContextBean();
            
            ContaRN contaRN = new ContaRN();
            this.lista = contaRN.listar(contextoBean.getUsuarioLogado());
        }
        return this.lista;
    }
    
    

    public void setLista(List<Conta> lista) {
        this.lista = lista;
    }
    
    
    
}
