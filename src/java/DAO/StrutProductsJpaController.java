/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import model.StrutProducts;

/**
 *
 * @author khanh
 */
public class StrutProductsJpaController implements Serializable {

    public StrutProductsJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(StrutProducts strutProducts) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(strutProducts);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStrutProducts(strutProducts.getId()) != null) {
                throw new PreexistingEntityException("StrutProducts " + strutProducts + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StrutProducts strutProducts) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            strutProducts = em.merge(strutProducts);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = strutProducts.getId();
                if (findStrutProducts(id) == null) {
                    throw new NonexistentEntityException("The strutProducts with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            StrutProducts strutProducts;
            try {
                strutProducts = em.getReference(StrutProducts.class, id);
                strutProducts.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The strutProducts with id " + id + " no longer exists.", enfe);
            }
            em.remove(strutProducts);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StrutProducts> findStrutProductsEntities() {
        return findStrutProductsEntities(true, -1, -1);
    }

    public List<StrutProducts> findStrutProductsEntities(int maxResults, int firstResult) {
        return findStrutProductsEntities(false, maxResults, firstResult);
    }

    private List<StrutProducts> findStrutProductsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StrutProducts.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public StrutProducts findStrutProducts(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StrutProducts.class, id);
        } finally {
            em.close();
        }
    }

    public int getStrutProductsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StrutProducts> rt = cq.from(StrutProducts.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
