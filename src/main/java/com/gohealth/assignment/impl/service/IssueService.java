package com.gohealth.assignment.impl.service;

import com.gohealth.assignment.shared.CreateDtoBase;
import com.gohealth.assignment.shared.DtoBase;
import com.gohealth.assignment.utils.RepositoryType;

import java.io.IOException;
import java.util.List;

public interface IssueService<CREATE_DTO extends CreateDtoBase, DTO extends DtoBase, ID> {

    String createIssue(CREATE_DTO dto, RepositoryType repositoryType) throws IOException;

    DTO findByIssueId(ID id, RepositoryType repositoryType) throws Exception;

    List<DTO> listOpenIssues(RepositoryType repositoryType) throws Exception;

    Boolean closeIssue(ID id, RepositoryType repositoryType) throws IOException;
}
