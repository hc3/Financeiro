/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.financeiro.util;

import br.com.financeiro.bean.ContextoBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


/**
 *
 * @date 
 * @author Eliel
 * @email eliel.floyd@bol.com.br
 *
 */

public class ContextoUtil {
    
    public static ContextoBean getContextBean(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();
        HttpSession session = (HttpSession) external.getSession(true);
        ContextoBean contextoBean = (ContextoBean) session.getAttribute("contextoBean");
        return contextoBean;
    }
}
