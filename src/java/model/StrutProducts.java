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
@Table(name = "StrutProducts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StrutProducts.findAll", query = "SELECT s FROM StrutProducts s"),
    @NamedQuery(name = "StrutProducts.findById", query = "SELECT s FROM StrutProducts s WHERE s.id = :id"),
    @NamedQuery(name = "StrutProducts.findByName", query = "SELECT s FROM StrutProducts s WHERE s.name = :name"),
    @NamedQuery(name = "StrutProducts.findByPrice", query = "SELECT s FROM StrutProducts s WHERE s.price = :price"),
    @NamedQuery(name = "StrutProducts.findByAmount", query = "SELECT s FROM StrutProducts s WHERE s.amount = :amount"),
    @NamedQuery(name = "StrutProducts.findByDetails", query = "SELECT s FROM StrutProducts s WHERE s.details = :details")})
public class StrutProducts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "price")
    private double price;
    @Basic(optional = false)
    @Column(name = "amount")
    private int amount;
    @Basic(optional = false)
    @Column(name = "details")
    private String details;

    public StrutProducts() {
    }

    public StrutProducts(Integer id) {
        this.id = id;
    }

    public StrutProducts(Integer id, String name, double price, int amount, String details) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.details = details;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StrutProducts)) {
            return false;
        }
        StrutProducts other = (StrutProducts) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.StrutProducts[ id=" + id + " ]";
    }
    
}
