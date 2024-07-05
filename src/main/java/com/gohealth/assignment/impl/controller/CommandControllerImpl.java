package com.gohealth.assignment.impl.controller;

import com.gohealth.assignment.api.CloseCommandDto;
import com.gohealth.assignment.api.CommandController;
import com.gohealth.assignment.api.Commands;
import com.gohealth.assignment.api.CreateCommandDto;
import com.gohealth.assignment.api.model.*;
import com.gohealth.assignment.impl.jpa.model.Status;
import com.gohealth.assignment.impl.service.IssueService;
import com.gohealth.assignment.shared.CommandBase;
import com.gohealth.assignment.utils.RepositoryType;
import com.gohealth.assignment.utils.TableGenerator;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class CommandControllerImpl implements CommandController {

  private final IssueService<CreateIssueDto, IssueDto, String> issueService;
  private final TableGenerator tableGenerator;

  public CommandControllerImpl(
      IssueService<CreateIssueDto, IssueDto, String> issueService, TableGenerator tableGenerator) {
    this.issueService = issueService;
    this.tableGenerator = tableGenerator;
  }

  @Override
  public Boolean validateIssue(String id, RepositoryType repositoryType) throws Exception {
    return issueService.findByIssueId(id, repositoryType) != null;
  }

  @Override
  public Object processCommand(CommandBase cmd) throws Exception {
    switch (cmd.getOperation()) {
      case Commands.OPERATION.CREATE -> {
        CreateCommandDto createCommand = (CreateCommandDto) cmd;
        return issueService.createIssue(
            CreateIssueDto.builder()
                .description(createCommand.getDescription())
                .parentId(createCommand.getParentId())
                .logUrl(createCommand.getLogUrl())
                .status(Status.OPEN)
                .build(),
            cmd.getRepositoryType());
      }
      case Commands.OPERATION.CLOSE -> {
        CloseCommandDto closeCommand = (CloseCommandDto) cmd;
        return issueService.closeIssue(closeCommand.getId(), closeCommand.getRepositoryType());
      }
      case Commands.OPERATION.LIST -> {
        List<IssueDto> issues = issueService.listOpenIssues(cmd.getRepositoryType());
        processDataAndPrint(issues);
      }
      default -> {
        return null;
      }
    }

    return null;
  }

  private void processDataAndPrint(List<IssueDto> data) {
    List<String> headers = new ArrayList<>();
    headers.add("ID");
    headers.add("Description");
    headers.add("ParentId");
    headers.add("Status");
    headers.add("Timestamp");
    headers.add("Link");

    List<List<String>> rows = new ArrayList<>();
    data.forEach(
        entry -> {
          List<String> row = new ArrayList<>();
          row.add(entry.getId() == null ? "" : entry.getId());
          row.add(entry.getDescription() == null ? "" : entry.getDescription());
          row.add(entry.getParentId() == null ? "" : entry.getParentId());
          row.add(entry.getStatus() == null ? "" : entry.getStatus().toString());
          row.add(entry.getCreatedAt() == null ? "" : entry.getCreatedAt().toString());
          row.add(entry.getLogUrl() == null ? "" : entry.getLogUrl());
          rows.add(row);
        });

    tableGenerator.printTable(headers, rows);
  }
}
