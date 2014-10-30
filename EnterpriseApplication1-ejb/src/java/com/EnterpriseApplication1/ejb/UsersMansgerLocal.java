/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.EnterpriseApplication1.ejb;

import com.EnterpriseApplication.jpa.Users;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author artem
 */
@Local
public interface UsersMansgerLocal {
    Users login(String name, String password);
    List getList();
    void activate();
}
