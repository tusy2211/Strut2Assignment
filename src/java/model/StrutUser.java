/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author khanh
 */
@Entity
@Table(name = "StrutUser")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StrutUser.findAll", query = "SELECT s FROM StrutUser s"),
    @NamedQuery(name = "StrutUser.findByUsername", query = "SELECT s FROM StrutUser s WHERE s.username = :username"),
    @NamedQuery(name = "StrutUser.findByPassword", query = "SELECT s FROM StrutUser s WHERE s.password = :password")})
public class StrutUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "password")
    private String password;

    public StrutUser() {
    }

    public StrutUser(String username) {
        this.username = username;
    }

    public StrutUser(String username, String password) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (username != null ? username.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StrutUser)) {
            return false;
        }
        StrutUser other = (StrutUser) object;
        if ((this.username == null && other.username != null) || (this.username != null && !this.username.equals(other.username))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.StrutUser[ username=" + username + " ]";
    }
    
}
