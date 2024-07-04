package com.gohealth.assignment.shared;

import java.io.IOException;
import org.springframework.stereotype.Repository;

@Repository
public abstract class DaoBase {

  public static final String SQL_TYPE = "sqlDataRepositoryImpl";
  public static final String CSV_TYPE = "csvDataRepositoryImpl";

  protected abstract String createIssueId() throws IOException;
}
