package com.gohealth.assignment.shared;

import org.springframework.stereotype.Repository;

import java.io.IOException;

@Repository
public abstract class DaoBase {

    public static final String SQL_TYPE = "sqlDataRepositoryImpl";
    public static final String CSV_TYPE = "csvDataRepositoryImpl";

    protected abstract String createIssueId() throws IOException;

}
