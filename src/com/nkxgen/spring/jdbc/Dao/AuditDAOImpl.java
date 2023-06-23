package com.nkxgen.spring.jdbc.Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.nkxgen.spring.jdbc.model.AuditLogs;


@Repository
public class AuditDAOImpl implements AuditLogDAO
{
	@PersistenceContext
	private EntityManager entityManager;
	
	@Transactional
	public void saveAudit(AuditLogs audits)
	{
		System.out.println("Audit Saved "+audits);
		entityManager.persist(audits);
	}
	
	@Transactional
	public List<AuditLogs> getAllAuditLogs() {
	    TypedQuery<AuditLogs> query = entityManager.createQuery(
	        "SELECT a FROM AuditLogs a ORDER BY a.id DESC", AuditLogs.class);

	    return query.getResultList();
	}
	
	public List<AuditLogs> getAuditLogs(int startIndex, int pageSize) {
		 
	    Query query = entityManager.createQuery("SELECT a FROM AuditLogs a ORDER BY a.timestamp DESC");
	    query.setFirstResult(startIndex);
	    query.setMaxResults(pageSize);
	    
	    return query.getResultList();
	}
	
	public int getTotalLogs() {	    
	    TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(a) FROM AuditLogs a", Long.class);
	    Long totalLogs = query.getSingleResult();
	    
	    return totalLogs.intValue();
	}

}
