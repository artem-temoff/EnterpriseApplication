/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EnterpriseApplication1.ejb;

import com.EnterpriseApplication1.jpa.Users;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author artem
 */
@Stateless
public class UsersMansger implements UsersMansgerLocal {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Users login(String name, String password) {
        if (name != null && password != null) {
            Query query = em.createNamedQuery("Users.findByName").setParameter("name", name);
            if (!query.getResultList().isEmpty()) {
                Users user = (Users) query.getResultList().get(0);
                String pass = user.getPassword();
                if (pass != null && pass.equals(password)) {
                    return user;
                }
            } else {
                return null;
            }
        } else {
            throw new IllegalArgumentException("Login and password can't be null");
        }
        return null;

    }

    @Override
    public List getList() {
        return em.createNamedQuery("Users.findAll").getResultList();
    }

    @Override
    public void activate() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
