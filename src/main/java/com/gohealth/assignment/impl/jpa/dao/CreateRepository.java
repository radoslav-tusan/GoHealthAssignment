package com.gohealth.assignment.impl.jpa.dao;

import com.gohealth.assignment.shared.EntityBase;
import java.io.IOException;

public interface CreateRepository<ENTITY extends EntityBase> {

  ENTITY createIssue(ENTITY entity) throws IOException;
}
