package com.gohealth.assignment.shared;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.PersistenceUnit;
import jakarta.persistence.Query;

public abstract class SqlDaoBase extends DaoBase {

  public static final String SEQUENCE_NAME = "entity_id_seq";

  protected EntityManager entityManager;

  @PersistenceUnit protected EntityManagerFactory entityManagerFactory;

  public SqlDaoBase(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  protected String createIssueId() {
    return "I-" + (getSequenceValue() + 1);
  }

  private Long getSequenceValue() {
    Query query = entityManager.createNativeQuery("SELECT nextval(:seqName)");
    query.setParameter("seqName", SEQUENCE_NAME);
    return ((Long) query.getSingleResult());
  }
}
