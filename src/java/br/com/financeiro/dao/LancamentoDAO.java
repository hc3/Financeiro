/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.dao;

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

public interface LancamentoDAO {
    
    public void salvar(Lancamento lancamento);
    public void excluir(Lancamento lancamento);
    public Lancamento carregar(Integer lancamento);
    public List<Lancamento> listar(Conta conta , Date dataInicio , Date dataFim);
    public float saldo(Conta conta , Date data);
}
