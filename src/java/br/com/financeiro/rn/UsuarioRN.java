/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.rn;

import br.com.financeiro.dao.DAOFactory;
import br.com.financeiro.dao.UsuarioDAO;
import br.com.financeiro.pojo.Usuario;
import java.util.List;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 * this class performs all methods the UsuarioDAO
 *
 */


public class UsuarioRN {

    private UsuarioDAO usuarioDAO;
    
    public UsuarioRN(){
        this.usuarioDAO = DAOFactory.criarUsuarioDAO();
    }
    
    public Usuario carregar(Integer codigo) {
        return this.usuarioDAO.carregar(codigo);
    }
    
    public Usuario buscaPorLogin(String login) {
        return this.usuarioDAO.buscaPorLogin(login);
    }
    
    public void salvar(Usuario usuario) {
        Integer codigo = usuario.getCodigo();
        if(codigo == null || codigo == 0) {
            this.usuarioDAO.salvar(usuario);
        }else {
            this.usuarioDAO.atualizar(usuario);
        }
    }
    
    public void excluir(Usuario usuario) {
        this.usuarioDAO.excluir(usuario);
    }
    
    public List<Usuario> listar() {
        return this.usuarioDAO.listar();
    }
}
