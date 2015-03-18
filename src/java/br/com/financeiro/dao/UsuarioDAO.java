/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.dao;

import br.com.financeiro.pojo.Usuario;
import java.util.List;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */

public interface UsuarioDAO {
    
    public void salvar(Usuario usuario);
    public void atualizar(Usuario usuario);
    public void excluir (Usuario usuario);
    public Usuario carregar(Integer codigo);
    public Usuario buscaPorLogin(String login);
    public List<Usuario> listar();
    
}
