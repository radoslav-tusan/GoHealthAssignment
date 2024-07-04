package com.gohealth.assignment.impl.jpa.dao;

import com.gohealth.assignment.shared.EntityBase;

public interface DataRepository<ENTITY extends EntityBase, ID> extends CreateRepository<ENTITY>, ReadRepository<ENTITY, ID>, UpdateRepository<ENTITY, ID> {}
