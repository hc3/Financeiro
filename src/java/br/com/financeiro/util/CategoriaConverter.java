/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.util;

import br.com.financeiro.pojo.Categoria;
import br.com.financeiro.rn.CategoriaRN;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */
@FacesConverter(forClass = Categoria.class)
public class CategoriaConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0){
            Integer codigo = Integer.valueOf(value);
            try{
               CategoriaRN categoriaRN = new CategoriaRN();
               return categoriaRN.carregar(codigo);
            }catch(Exception e){
                try {
                    throw new Exception(value+ "Não foi possível encontrar a categoria de código" +". "+ e.getMessage());
                } catch (Exception ex) {
                    Logger.getLogger(CategoriaConverter.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        }
        return null;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object value){
        if(value != null ) {
            Categoria categoria = (Categoria) value;
            return categoria.getCodigo().toString();
        }
        
        return "";
    }
    
}
