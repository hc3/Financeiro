/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.dao;

import br.com.financeiro.pojo.Conta;
import br.com.financeiro.pojo.Usuario;
import java.util.List;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */

public interface ContaDAO {
    
    public void salvar(Conta conta);
    public void excluir(Conta conta);
    public Conta carregar(Integer conta);
    public List<Conta> listar(Usuario usuario);
    public Conta buscarFavorita(Usuario usuario);
}
