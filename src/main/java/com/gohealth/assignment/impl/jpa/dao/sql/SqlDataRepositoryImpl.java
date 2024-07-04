package com.gohealth.assignment.impl.jpa.dao.sql;

import com.gohealth.assignment.impl.jpa.model.IssueEntity;
import com.gohealth.assignment.impl.jpa.model.Status;
import com.gohealth.assignment.shared.SqlDaoBase;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static com.gohealth.assignment.shared.DaoBase.SQL_TYPE;

@Repository
@Transactional
@Qualifier(SQL_TYPE)
public class SqlDataRepositoryImpl extends SqlDaoBase implements SqlDataRepository<IssueEntity, String> {

    public SqlDataRepositoryImpl(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public IssueEntity createIssue(IssueEntity issueEntity) {
        issueEntity.setIssueId(createIssueId());
        issueEntity.setCreatedAt(LocalDateTime.now());
        entityManager.persist(issueEntity);
        entityManager.flush();
        return issueEntity;
    }

    @Override
    public IssueEntity findByIssueId(String id) {
        final String sql = "SELECT * FROM issue i WHERE i.issue_id = :id";
        Query query = entityManager.createNativeQuery(sql, IssueEntity.class);
        query.setParameter("id", id);
        List<IssueEntity> resultList = query.getResultList();
        return resultList.size() > 0 ? resultList.get(0) : null;
    }

    @Override
    public Boolean closeIssue(String id) {
        if (findByIssueId(id) == null) {
            return false;
        }

        EntityManager em = entityManagerFactory.createEntityManager();
        final String sql = "UPDATE issue SET status = :status WHERE issue_id = :id";
        Query query = em.createNativeQuery(sql);
        query.setParameter("status", Status.CLOSED.toString());
        query.setParameter("id", id);
        em.getTransaction().begin();
        int result = query.executeUpdate();
        em.getTransaction().commit();
        return result == 1;
    }

    @Override
    public List<IssueEntity> listOpenIssues() {
        final String sql = "SELECT * FROM issue i WHERE i.status = :status";
        Query query = entityManager.createNativeQuery(sql, IssueEntity.class);
        query.setParameter("status", Status.OPEN.toString());
        return (List<IssueEntity>) query.getResultList();
    }
}
