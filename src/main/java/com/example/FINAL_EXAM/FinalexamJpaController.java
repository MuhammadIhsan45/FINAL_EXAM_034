/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.FINAL_EXAM;

import com.example.FINAL_EXAM.exceptions.NonexistentEntityException;
import com.example.FINAL_EXAM.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author hp
 */
public class FinalexamJpaController implements Serializable {

    public FinalexamJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("com.example_FINAL_EXAM_jar_0.0.1-SNAPSHOTPU");
    
    public FinalexamJpaController(){
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Finalexam finalexam) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(finalexam);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findFinalexam(finalexam.getId()) != null) {
                throw new PreexistingEntityException("Finalexam " + finalexam + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Finalexam finalexam) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            finalexam = em.merge(finalexam);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = finalexam.getId();
                if (findFinalexam(id) == null) {
                    throw new NonexistentEntityException("The finalexam with id " + id + " no longer exists.");
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
            Finalexam finalexam;
            try {
                finalexam = em.getReference(Finalexam.class, id);
                finalexam.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The finalexam with id " + id + " no longer exists.", enfe);
            }
            em.remove(finalexam);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Finalexam> findFinalexamEntities() {
        return findFinalexamEntities(true, -1, -1);
    }

    public List<Finalexam> findFinalexamEntities(int maxResults, int firstResult) {
        return findFinalexamEntities(false, maxResults, firstResult);
    }

    private List<Finalexam> findFinalexamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Finalexam.class));
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

    public Finalexam findFinalexam(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Finalexam.class, id);
        } finally {
            em.close();
        }
    }

    public int getFinalexamCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Finalexam> rt = cq.from(Finalexam.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
