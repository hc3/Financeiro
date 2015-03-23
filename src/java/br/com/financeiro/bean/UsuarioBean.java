/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.bean;

import br.com.financeiro.pojo.Usuario;
import br.com.financeiro.rn.UsuarioRN;
import java.util.List;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 * this class makes interation between viewer and business rules
 *
 */
@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean {
   
   private Usuario usuario = new Usuario();
   private String confirmaSenha;
   private List<Usuario> lista;
   private String destinoSalvar;
   
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String getConfirmaSenha() {
        return confirmaSenha;
    }

    public void setConfirmaSenha(String confirmaSenha) {
        this.confirmaSenha = confirmaSenha;
    }

    public String getDestinoSalvar() {
        return destinoSalvar;
    }

    public void setDestinoSalvar(String destinoSalvar) {
        this.destinoSalvar = destinoSalvar;
    }
    
    
    
    public String novo(){
        this.destinoSalvar = "usuarioSucesso";
        this.usuario = new Usuario();
        this.usuario.setAtivo(true);
        return "usuario";
    }
    
    public String editar(){
        this.confirmaSenha = this.usuario.getSenha();
        return "/publico/usuario";
    }
    
    public String salvar() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        String senha = this.usuario.getSenha();
        if(!senha.equals(this.confirmaSenha)) {
            FacesMessage facesMessage = new FacesMessage("A senha não foi confirmada corretamente");
            context.addMessage(null, facesMessage);
            return null;
        }
        
        UsuarioRN usuarioRN = new UsuarioRN();
        usuarioRN.salvar(this.usuario);
       
        return this.destinoSalvar;
    }
    
    public String excluir(){
        UsuarioRN usuarioRN = new UsuarioRN();
        usuarioRN.excluir(usuario);
        this.lista = null;
        return null;
    }
    
    public String ativar(){
        if(this.usuario.isAtivo()){
            this.usuario.setAtivo(false);
        }else{
            this.usuario.setAtivo(true);
        }
        UsuarioRN usuarioRN = new UsuarioRN();
        usuarioRN.salvar(usuario);
        return null;
    }
    
    public List<Usuario> getLista(){
        if(this.lista == null) {
            UsuarioRN usuarioRN = new UsuarioRN();
            this.lista = usuarioRN.listar();
        }
        return this.lista;
    }
  
    public String atribuiPermissao(Usuario usuario , String permissao) {
        this.usuario = usuario;
        Set<String> permissoes = this.usuario.getPermissao();
        if(permissoes.contains(permissao)){
            permissoes.remove(permissao);
        }else{
            permissoes.add(permissao);
        }
        return null;
    }
    
   
}
