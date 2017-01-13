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
import model.StrutUser;

/**
 *
 * @author khanh
 */
public class StrutUserJpaController implements Serializable {

    public StrutUserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    
    public StrutUser login(String username, String password) {
        EntityManager em = getEntityManager();
        Query q = em.createQuery("SELECT t from StrutUser t  where t.username = :username  AND t.password = :password");
        q.setParameter("username", username);
        q.setParameter("password", password);
        List<StrutUser> ls = q.getResultList();
        if (ls.size() == 1) {
            return ls.get(0);
        }
        return null;
    }
    
    public void create(StrutUser strutUser) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(strutUser);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findStrutUser(strutUser.getUsername()) != null) {
                throw new PreexistingEntityException("StrutUser " + strutUser + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(StrutUser strutUser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            strutUser = em.merge(strutUser);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = strutUser.getUsername();
                if (findStrutUser(id) == null) {
                    throw new NonexistentEntityException("The strutUser with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            StrutUser strutUser;
            try {
                strutUser = em.getReference(StrutUser.class, id);
                strutUser.getUsername();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The strutUser with id " + id + " no longer exists.", enfe);
            }
            em.remove(strutUser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<StrutUser> findStrutUserEntities() {
        return findStrutUserEntities(true, -1, -1);
    }

    public List<StrutUser> findStrutUserEntities(int maxResults, int firstResult) {
        return findStrutUserEntities(false, maxResults, firstResult);
    }

    private List<StrutUser> findStrutUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(StrutUser.class));
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

    public StrutUser findStrutUser(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(StrutUser.class, id);
        } finally {
            em.close();
        }
    }

    public int getStrutUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<StrutUser> rt = cq.from(StrutUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
