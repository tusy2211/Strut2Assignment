/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.StrutProductsJpaController;
import DAO.StrutUserJpaController;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import model.StrutProducts;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author khanh
 */
public class LoginActionSupport extends ActionSupport {

    private String username;
    private String password;
    private String message;
    private List<StrutProducts> listsp = new ArrayList<StrutProducts>();
    private Map<String, Object> sessionMap;

    public Map<String, Object> getSessionMap() {
        return sessionMap;
    }

    public void setSessionMap(Map<String, Object> sessionMap) {
        this.sessionMap = sessionMap;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<StrutProducts> getListsp() {
        return listsp;
    }

    public void setListsp(List<StrutProducts> listsp) {
        this.listsp = listsp;
    }

    public LoginActionSupport(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginActionSupport() {
    }

    public String execute() throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("AssignmentStrut2PU");
        StrutUserJpaController profileJpaController = new StrutUserJpaController(emf);
        if (profileJpaController.login(username, password) != null) {
            sessionMap =  ActionContext.getContext().getSession();
            sessionMap.put("username", username);
            StrutProductsJpaController sanphamJpaController = new StrutProductsJpaController(emf);
            listsp = sanphamJpaController.findStrutProductsEntities();
            return SUCCESS;

        }
        message = "Wrong username or password";
        return INPUT;
    }
    
    public String logout(){
        sessionMap =  ActionContext.getContext().getSession();
        sessionMap.remove("username");
        return SUCCESS;
    }

}
