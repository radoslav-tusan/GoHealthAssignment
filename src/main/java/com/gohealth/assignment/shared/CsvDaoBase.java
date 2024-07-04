package com.gohealth.assignment.shared;

import com.gohealth.assignment.impl.jpa.model.IssueEntity;
import com.gohealth.assignment.impl.jpa.model.Status;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class CsvDaoBase extends DaoBase {

  protected final String DATETIME_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS";

  protected String getFilePath() throws IOException {
    final String filePath = "storage.csv";
    File file = new File(filePath);

    if (!file.exists()) {
      file.createNewFile();
    }

    return filePath;
  }

  protected IssueEntity createEntity(String[] values) {
    IssueEntity issue = new IssueEntity();
    issue.setId(Long.parseLong(values[0]));
    issue.setIssueId(values[1]);
    issue.setParentId(values[2]);
    issue.setDescription(values[3]);
    issue.setLogUrl(values[4]);
    issue.setCreatedAt(
        LocalDateTime.parse(values[5], DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS")));
    issue.setStatus(Status.valueOf(values[6]));
    return issue;
  }

  protected String convertToCSV(String[] data) {
    return String.join(",", data);
  }

  @Override
  protected String createIssueId() throws IOException {
    return "I-" + (countRows());
  }

  protected String createRecordId() throws IOException {
    return String.valueOf(countRows());
  }

  private int countRows() throws IOException {
    int rowCount = 0;
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(getFilePath()))) {
      while (bufferedReader.readLine() != null) {
        rowCount++;
      }
    }
    return rowCount;
  }
}
