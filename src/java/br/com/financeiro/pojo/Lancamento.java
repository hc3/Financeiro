/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.pojo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.ForeignKey;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */
@Entity
@Table(name="lancamento")
public class Lancamento implements Serializable{
    
    @Id
    @GeneratedValue
    @Column(name="codigo")
    private Integer lancamento;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action=OnDeleteAction.CASCADE)
    @JoinColumn(name="usuario",nullable=false)
    @ForeignKey(name="fk_lancamento_usuario")
    private Usuario usuario;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action=OnDeleteAction.CASCADE)
    @JoinColumn(name="conta" , nullable=true)
    @ForeignKey(name="fk_lancamento_conta")
    private Conta conta;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action=OnDeleteAction.CASCADE)
    @JoinColumn(name="categoria",nullable=true)
    @ForeignKey(name="fk_lancamento_categoria")
    private Categoria categoria;
    
    @Temporal(TemporalType.DATE)
    private Date data;
    
    private String descricao;
    
    @Column(precision = 10 , scale=2)
    private BigDecimal valor;

    public Integer getLancamento() {
        return lancamento;
    }

    public void setLancamento(Integer lancamento) {
        this.lancamento = lancamento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Conta getConta() {
        return conta;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.lancamento);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Lancamento other = (Lancamento) obj;
        if (!Objects.equals(this.lancamento, other.lancamento)) {
            return false;
        }
        return true;
    }
    
    
    
}
