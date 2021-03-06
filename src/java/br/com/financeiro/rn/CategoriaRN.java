/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.rn;

import br.com.financeiro.dao.CategoriaDAO;
import br.com.financeiro.dao.DAOFactory;
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

public class CategoriaRN {
    private CategoriaDAO categoriaDAO;
    
    public CategoriaRN(){
        this.categoriaDAO = DAOFactory.criarCategoria();
    }
    
    public List<Categoria> listar(Usuario usuario){
        return this.categoriaDAO.listar(usuario);
    }
    
    public Categoria salvar(Categoria categoria) {
        Categoria pai = categoria.getPai();
        
        if (pai == null) {
            String msg = "A Categoria " + categoria.getDescricao() + "deve ter um pai definido";
            throw new IllegalArgumentException(msg);
        }
        
        boolean mudouFator = pai.getFator() != categoria.getFator();
        
        categoria.setFator(pai.getFator());
        categoria = this.categoriaDAO.salvar(categoria);
        
        if(mudouFator) {
            categoria = this.carregar(categoria.getCodigo());
            this.replicarFator(categoria , categoria.getFator());
        }
        return categoria;
    }
    
    private void replicarFator(Categoria categoria , int fator) {
        if(categoria.getFilhos() != null){
            for(Categoria filho : categoria.getFilhos()){
                filho.setFator(fator);
                this.categoriaDAO.salvar(filho);
                this.replicarFator(filho,fator);
            }
        }
    }
    
    public void excluir(Categoria categoria) {
        this.categoriaDAO.excluir(categoria);
    }
    
    public void excluir(Usuario usuario) {
        List<Categoria> lista = this.listar(usuario);
        for(Categoria categoria : lista) {
            this.categoriaDAO.excluir(categoria);
        }
    }
    
    public Categoria carregar(Integer categoria) {
        return this.categoriaDAO.carregar(categoria);
    }
    
    public void salvaEstruturaPadrao(Usuario usuario) {
        Categoria despesas = new Categoria(null , usuario , "DESPESAS" , -1);
        despesas = this.categoriaDAO.salvar(despesas);
        this.categoriaDAO.salvar(new Categoria(despesas , usuario , "Moradia" , -1));
        this.categoriaDAO.salvar(new Categoria(despesas , usuario , "Alimentação" , -1));
        this.categoriaDAO.salvar(new Categoria(despesas , usuario , "Vestuário" , -1));
        this.categoriaDAO.salvar(new Categoria(despesas , usuario , "Deslocamento" , -1));
        this.categoriaDAO.salvar(new Categoria(despesas , usuario , "Cuidados Pessoais" , -1));
        this.categoriaDAO.salvar(new Categoria(despesas , usuario , "Educação" , -1));
        this.categoriaDAO.salvar(new Categoria(despesas , usuario , "Saúde" , -1)); 
        this.categoriaDAO.salvar(new Categoria(despesas , usuario , "Lazer" , -1));
        this.categoriaDAO.salvar(new Categoria(despesas , usuario , "Despesas Financeiras" , -1));
        
        Categoria receitas = new Categoria(null , usuario , "RECEITAS" , 1);
        receitas = this.categoriaDAO.salvar(receitas);
        this.categoriaDAO.salvar(new Categoria(receitas , usuario , "Salário" , 1));
        this.categoriaDAO.salvar(new Categoria(receitas , usuario , "Restituições" , 1));
        this.categoriaDAO.salvar(new Categoria(receitas , usuario , "Rendimentos" , 1));
    }
}
