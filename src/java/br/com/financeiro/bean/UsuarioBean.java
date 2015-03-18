/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.bean;

import br.com.financeiro.pojo.Usuario;
import br.com.financeiro.rn.UsuarioRN;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */
@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean {
   
   private Usuario usuario = new Usuario();
   private String confirmaSenha;

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
    
    public String novo(){
        this.usuario = new Usuario();
        this.usuario.setAtivo(true);
        return "usuario";
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
       
        return "usuarioSucesso";
    }
  
   
}
