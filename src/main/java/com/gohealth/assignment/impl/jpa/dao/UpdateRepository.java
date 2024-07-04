package com.gohealth.assignment.impl.jpa.dao;

import com.gohealth.assignment.shared.EntityBase;

import java.io.IOException;

public interface UpdateRepository<ENTITY extends EntityBase, ID> {

    Boolean closeIssue(ID id) throws IOException;
}
