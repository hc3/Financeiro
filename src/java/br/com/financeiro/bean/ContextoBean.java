/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.bean;

import br.com.financeiro.pojo.Conta;
import br.com.financeiro.pojo.Usuario;
import br.com.financeiro.rn.ContaRN;
import br.com.financeiro.rn.UsuarioRN;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */
@ManagedBean(name = "contextBean")
@SessionScoped
public class ContextoBean {

   private Usuario usuarioLogado = null;
   private Conta contaAtiva = null;
   
   public Usuario getUsuarioLogado(){
       
       FacesContext context = FacesContext.getCurrentInstance();
       ExternalContext external = context.getExternalContext();
       String login = external.getRemoteUser();
       if(this.usuarioLogado == null || !login.equals(this.usuarioLogado.getLogin())){
           if(login != null){
               UsuarioRN usuarioRN = new UsuarioRN();
               this.usuarioLogado = usuarioRN.buscaPorLogin(login);
               this.contaAtiva = null;
           }
       }
       return usuarioLogado;
   }
   
   public void setUsuarioLogado(Usuario usuario) {
       this.usuarioLogado = usuario;
   }
   
   public Conta getContaAtiva() {
       if(this.contaAtiva == null){
           Usuario usuario = this.getUsuarioLogado();
           ContaRN contaRN = new ContaRN();
           this.contaAtiva = contaRN.buscarFavorita(usuario);
           if ( this.contaAtiva == null) {
               List<Conta> contas = contaRN.listar(usuario);
               if(contas != null) {
               for (Conta conta : contas) {
                   this.contaAtiva = conta;
                   break;
                }
              }
           }
       }
       return contaAtiva;
   }
   
   public void setContaAtiva(ValueChangeEvent event) {
       Integer conta = (Integer) event.getNewValue();
       ContaRN contaRN = new ContaRN();
       this.contaAtiva = contaRN.carregar(conta);
   }
}
