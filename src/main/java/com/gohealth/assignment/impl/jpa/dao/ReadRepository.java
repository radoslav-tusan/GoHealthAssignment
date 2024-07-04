package com.gohealth.assignment.impl.jpa.dao;

import com.gohealth.assignment.shared.EntityBase;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public interface ReadRepository<ENTITY extends EntityBase, ID> {

    ENTITY findByIssueId(ID id) throws Exception;

    List<ENTITY> listOpenIssues() throws Exception;
}
