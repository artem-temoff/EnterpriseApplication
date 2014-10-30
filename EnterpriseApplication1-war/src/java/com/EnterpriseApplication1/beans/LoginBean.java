/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EnterpriseApplication1.beans;

import com.EnterpriseApplication.jpa.Users;
import com.EnterpriseApplication1.ejb.UsersMansgerLocal;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.servlet.ServletException;

/**
 *
 * @author artem
 */
@Named
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    
    @EJB
    private UsersMansgerLocal um;
    
    private String name;
    private String password;

    private Users user;
    
   public String getName() {
        return name;
    }

    public void setName(String uname) {
        this.name = uname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public void login() throws ServletException {
        if(null==user)
            user=(Users) um.login(name,password);
        else{
            if(null==(Users) um.login(name,password))
                    user=null;
        }
}
 
    public void logout(){
        user=null;
    }
        
    public Boolean isLogin(){
        return user!=null;
    }

    public List getList(){
       return um.getList();
    }
}
