package com.gohealth.assignment.impl.jpa.dao.sql;

import com.gohealth.assignment.impl.jpa.dao.DataRepository;
import com.gohealth.assignment.shared.EntityBase;

public interface SqlDataRepository<ENTITY extends EntityBase, ID> extends DataRepository<ENTITY, ID> {}
