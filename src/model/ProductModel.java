package model;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

import model.DaoInterface;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.Criteria;
import org.hibernate.Session;

import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import entities.Products;

//import entities.Products;


public class ProductModel implements DaoInterface<Products, Integer> {
	
	private Session currentSession;
	private Transaction currentTransaction;
	
	public ProductModel() {
		// TODO Auto-generated constructor stub
	}
	
	public Session openCurrentSession() {
        currentSession = getSessionFactory().openSession();
        return currentSession;
    }
 
    public Session openCurrentSessionwithTransaction() {
        currentSession = getSessionFactory().openSession();
        currentTransaction = currentSession.beginTransaction();
        return currentSession;
    }
     
    public void closeCurrentSession() {
        currentSession.close();
    }
     
    public void closeCurrentSessionwithTransaction() {
        currentTransaction.commit();
        currentSession.close();
    }
    private static SessionFactory getSessionFactory() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(entities.Products.class); //add
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
    }
 
    public Session getCurrentSession() {
        return currentSession;
    }
 
    public void setCurrentSession(Session currentSession) {
        this.currentSession = currentSession;
    }
 
    public Transaction getCurrentTransaction() {
        return currentTransaction;
    }
 
    public void setCurrentTransaction(Transaction currentTransaction) {
        this.currentTransaction = currentTransaction;
    }

    public void persist(Products entity) {
        getCurrentSession().save(entity);
    }
 
    public void update(Products entity) {
        getCurrentSession().update(entity);
    }
 
    public Products findById(Integer id) {
        Products response = (Products) getCurrentSession().get(Products.class, id);
        return response; 
    }
 
    public void delete(Products entity) {
        getCurrentSession().delete(entity);
    }
 
    @SuppressWarnings("unchecked")
    public List<entities.Products> findAll() {
        List<entities.Products> products = (List<entities.Products>) getCurrentSession().createQuery("from Products").list();
        return products;
    }
 
    public void deleteAll() {
        List<Products> entityList = findAll();
        for (Products entity : entityList) {
            delete(entity);
        }
    }

}
