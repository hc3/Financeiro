/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.dao;

import br.com.financeiro.pojo.Categoria;
import br.com.financeiro.pojo.Usuario;
import java.util.List;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */

public interface CategoriaDAO {
    public Categoria salvar(Categoria categoria);
    public void excluir(Categoria categoria);
    public Categoria carregar(Integer categoria);
    public List<Categoria> listar(Usuario usuario);
    
}
