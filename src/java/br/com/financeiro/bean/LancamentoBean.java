/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.bean;

import br.com.financeiro.pojo.Categoria;
import br.com.financeiro.pojo.Conta;
import br.com.financeiro.pojo.Lancamento;
import br.com.financeiro.rn.LancamentoRN;
import br.com.financeiro.util.ContextoUtil;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @date 05/2015
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 * 
 *
 */
@ManagedBean(name = "lancamentoBean")
@ViewScoped
public class LancamentoBean {
    private static final long serialVersionUID = 1L;

    private List<Lancamento> lista;
    private List<Double> saldos = new ArrayList<Double>();
    private float saldoGeral;
    private Lancamento editado = new Lancamento();
    private List<Lancamento> listaAteHoje;
    private List<Lancamento> listaFuturos;
    
    public LancamentoBean(){
        this.novo();
    }
    
    public void novo(){
       this.editado = new Lancamento();
       this.editado.setData(new Date(System.currentTimeMillis()));
    }
    
    public void editar(){
        
    }
    
    public void salvar(){
        ContextoBean contextoBean = ContextoUtil.getContextBean();
        this.editado.setUsuario(contextoBean.getUsuarioLogado());
        this.editado.setConta(contextoBean.getContaAtiva());
        
        LancamentoRN lancamentoRN = new LancamentoRN();
        lancamentoRN.salvar(this.editado);
        
        this.novo();
        this.lista = null;
    }
    
    public void excluir() {
        LancamentoRN lancamentoRN = new LancamentoRN();
        this.editado = lancamentoRN.carregar(this.editado.getLancamento());
        lancamentoRN.excluir(this.editado);
        this.lista = null;
    }
    
    public List<Lancamento> getLista(){
        if(this.lista == null) {
            ContextoBean contextoBean = ContextoUtil.getContextBean();
            Conta conta = contextoBean.getContaAtiva();
            
            Calendar dataSaldo = new GregorianCalendar();
            dataSaldo.add(Calendar.MONTH , -1);
            dataSaldo.add(Calendar.DAY_OF_MONTH, -1);
            
            Calendar inicio = new GregorianCalendar();
            inicio.add(Calendar.MONTH, -1);
            
            LancamentoRN lancamentoRN = new LancamentoRN();
            this.saldoGeral = lancamentoRN.saldo(conta, dataSaldo.getTime());
            this.lista = lancamentoRN.listar(conta,inicio.getTime(),null);
            
            Categoria categoria = null;
            double saldo = this.saldoGeral;
            for(Lancamento lancamento : this.lista) {
                categoria = lancamento.getCategoria();
                saldo = saldo + (lancamento.getValor().floatValue() * categoria.getFator());
                this.saldos.add(saldo);
            }
        }
        return this.lista;
    }
    
    public List<Lancamento> getListaAteHoje(){
        if(this.listaAteHoje == null) {
            ContextoBean contextoBean = ContextoUtil.getContextBean();
            Conta conta = contextoBean.getContaAtiva();
            
            Calendar hoje = new GregorianCalendar();
            
            LancamentoRN lancamentoRN = new LancamentoRN();
            this.listaAteHoje = lancamentoRN.listar(conta, null,hoje.getTime());
        }
        return this.listaAteHoje;
    }
    
    public List<Lancamento> getListaFururos(){
        if(this.listaFuturos == null) {
            ContextoBean contextoBean = ContextoUtil.getContextBean();
            Conta conta = contextoBean.getContaAtiva();
            
            Calendar amanha = new GregorianCalendar();
            amanha.add(Calendar.DAY_OF_MONTH, 1);
            
            LancamentoRN lancamentoRN = new LancamentoRN();
            this.listaFuturos = lancamentoRN.listar(conta,amanha.getTime(),null);
        }
        return listaFuturos;
    }
    

    public void setLista(List<Lancamento> lista) {
        this.lista = lista;
    }

    public List<Double> getSaldos() {
        return saldos;
    }

    public void setSaldos(List<Double> saldos) {
        this.saldos = saldos;
    }

    public float getSaldoGeral() {
        return saldoGeral;
    }

    public void setSaldoGeral(float saldoGeral) {
        this.saldoGeral = saldoGeral;
    }

    public Lancamento getEditado() {
        return editado;
    }

    public void setEditado(Lancamento editado) {
        this.editado = editado;
    }
    
    
}
