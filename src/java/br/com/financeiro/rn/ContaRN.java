/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.rn;

import br.com.financeiro.dao.ContaDAO;
import br.com.financeiro.dao.DAOFactory;
import br.com.financeiro.pojo.Conta;
import br.com.financeiro.pojo.Usuario;
import java.util.Date;
import java.util.List;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */

public class ContaRN {
    
    private ContaDAO contaDAO;
    
    public ContaRN(){
        this.contaDAO = DAOFactory.criarContaDAO();
    }
    
    public List<Conta> listar(Usuario usuario) {
        return this.contaDAO.listar(usuario);
    }
    
    public Conta carregar(Integer conta) {
        return this.contaDAO.carregar(conta);
    }
    
    public void salvar(Conta conta) {
        conta.setDataCadastro(new Date());
        this.contaDAO.salvar(conta);
    }
    
    public void excluir(Conta conta) {
        this.contaDAO.excluir(conta);
    }
    
    public void tornarFavorita(Conta contaFavorita) {
        Conta conta = this.buscarFavorita(contaFavorita.getUsuario());
        if(conta != null) {
            conta.setFavorita(false);
            this.contaDAO.salvar(conta);
        }
        contaFavorita.setFavorita(true);
        this.contaDAO.salvar(contaFavorita);
    }
    
    public Conta buscarFavorita(Usuario usuario) {
        return this.contaDAO.buscarFavorita(usuario);
    }
}
