package com.gohealth.assignment.impl.jpa.dao.csv;

import com.gohealth.assignment.impl.jpa.dao.DataRepository;
import com.gohealth.assignment.shared.EntityBase;

public interface CsvDataRepository<ENTITY extends EntityBase, ID> extends DataRepository<ENTITY, ID> {
}
