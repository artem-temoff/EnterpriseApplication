package com.EnterpriseApplication1.beans;

import com.EnterpriseApplication1.jpa.Users;
import com.EnterpriseApplication1.ejb.UsersMansgerLocal;
import com.EnterpriseApplication1.util.JSFHelper;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    public String login() throws ServletException {
        user = (Users) um.login(name, password);
        if (user != null) {
            JSFHelper helper = new JSFHelper();
            HttpSession session = helper.getSession(false);
            session.setAttribute("login", name);
            return "signIn.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Username or password is incorrect", "Please Try Again"));
            return null;
        }
    }

    public String logout() {
        JSFHelper helper = new JSFHelper();
        HttpSession session = helper.getSession(false);
        session.invalidate();
        return "index.xhtml?faces-redirect=true";
    }

    public Boolean isLogin() {
        JSFHelper helper = new JSFHelper();
        HttpServletRequest req = helper.getRequest();
        String login = (String) req.getSession().getAttribute("login");
        return login != null;
    }

    public List getList() {
        return um.getList();
    }
}
