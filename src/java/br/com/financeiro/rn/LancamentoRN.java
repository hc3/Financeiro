/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.rn;

import br.com.financeiro.dao.DAOFactory;
import br.com.financeiro.dao.LancamentoDAO;
import br.com.financeiro.pojo.Conta;
import br.com.financeiro.pojo.Lancamento;
import java.util.Date;
import java.util.List;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */

public class LancamentoRN {
        
    private LancamentoDAO lancamentoDAO;
    
    public LancamentoRN(){
        this.lancamentoDAO = DAOFactory.criarLancamento();
    }
    
    public void salvar(Lancamento lancamento) {
        this.lancamentoDAO.salvar(lancamento);
    }
    
    public void excluir(Lancamento lancamento){
        this.lancamentoDAO.excluir(lancamento);
    }
    
    public Lancamento carregar(Integer lancamento){
        return this.lancamentoDAO.carregar(lancamento);
    }
    
    public float saldo(Conta conta , Date data) {
        float saldoIncial = conta.getSaldoInicial();
        float saldonaData = this.lancamentoDAO.saldo(conta, data);
        return saldoIncial + saldonaData;
    }
    
    public List<Lancamento> listar(Conta conta , Date dataInicio , Date dataFim) {
        return this.lancamentoDAO.listar(conta,dataInicio,dataFim);
    }
}
