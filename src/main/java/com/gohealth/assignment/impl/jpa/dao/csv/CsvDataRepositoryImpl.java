package com.gohealth.assignment.impl.jpa.dao.csv;

import static com.gohealth.assignment.shared.DaoBase.CSV_TYPE;

import com.gohealth.assignment.impl.jpa.model.IssueEntity;
import com.gohealth.assignment.impl.jpa.model.Status;
import com.gohealth.assignment.shared.CsvDaoBase;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
@Qualifier(CSV_TYPE) public class CsvDataRepositoryImpl extends CsvDaoBase
    implements CsvDataRepository<IssueEntity, String> {

  @Override
  public IssueEntity createIssue(IssueEntity entity) throws IOException {
    File csvOutputFile = new File(getFilePath());
    String newIssueId;
    try (PrintWriter pw = new PrintWriter(new FileWriter(csvOutputFile, true))) {
      newIssueId = createIssueId();
      String line =
          convertToCSV(
              new String[] {
                String.valueOf(createRecordId()),
                newIssueId,
                entity.getParentId(),
                entity.getDescription(),
                entity.getLogUrl(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern(DATETIME_FORMAT)),
                String.valueOf(entity.getStatus())
              });
      pw.println(line);
    }

    return findByIssueId(newIssueId);
  }

  @Override
  public IssueEntity findByIssueId(String issueId) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        String id = values[1];
        if (id.equals(issueId)) {
          return createEntity(values);
        }
      }
    }
    return null;
  }

  @Override
  public List<IssueEntity> listOpenIssues() throws IOException {
    List<IssueEntity> issues = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(getFilePath()))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] values = line.split(",");
        if (values[6].equals(Status.OPEN.toString())) {
          issues.add(createEntity(values));
        }
      }
    }
    return issues;
  }

  @Override
  public Boolean closeIssue(String id) throws IOException {
    IssueEntity entity = findByIssueId(id);
    entity.setStatus(Status.CLOSED);

    File inputFile = new File(getFilePath());
    File tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

      String currentLine;
      while ((currentLine = reader.readLine()) != null) {
        String[] values = currentLine.split(",");
        if (values[1].equals(id)) {
          String updatedLine =
              String.join(
                  ",",
                  String.valueOf(entity.getId()),
                  entity.getIssueId(),
                  entity.getParentId(),
                  entity.getDescription(),
                  entity.getLogUrl(),
                  String.valueOf(entity.getCreatedAt()),
                  String.valueOf(entity.getStatus()));
          writer.write(updatedLine);
        } else {
          writer.write(currentLine);
        }
        writer.newLine();
      }
    }

    inputFile.delete();
    tempFile.renameTo(inputFile);

    IssueEntity updatedEntity = findByIssueId(id);
    return updatedEntity.getStatus().equals(Status.CLOSED);
  }
}
