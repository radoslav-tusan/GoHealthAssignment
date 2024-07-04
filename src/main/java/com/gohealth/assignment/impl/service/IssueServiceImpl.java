package com.gohealth.assignment.impl.service;

import com.gohealth.assignment.api.model.CreateIssueDto;
import com.gohealth.assignment.api.model.IssueDto;
import com.gohealth.assignment.impl.jpa.dao.DataRepository;
import com.gohealth.assignment.impl.jpa.model.IssueEntity;
import com.gohealth.assignment.impl.mapper.IssueMapper;
import com.gohealth.assignment.shared.ServiceBase;
import com.gohealth.assignment.utils.RepositoryType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class IssueServiceImpl extends ServiceBase implements IssueService<CreateIssueDto, IssueDto, String> {

    private final Map<String, DataRepository<IssueEntity, String>> repositories;
    private final IssueMapper issueMapper;

    public IssueServiceImpl(Map<String, DataRepository<IssueEntity, String>> repositories, IssueMapper issueMapper) {
        this.repositories = repositories;
        this.issueMapper = issueMapper;
    }

    @Override
    public String createIssue(CreateIssueDto dto, RepositoryType repositoryType) throws IOException {
        DataRepository<IssueEntity, String> repository = repositories.get(repositoryType.getValue());
        return repository.createIssue(issueMapper.toEntity(dto)).getIssueId();
    }

    @Override
    public IssueDto findByIssueId(String id, RepositoryType repositoryType) throws Exception {
        DataRepository<IssueEntity, String> repository = repositories.get(repositoryType.getValue());
        return issueMapper.toDto(repository.findByIssueId(id));
    }

    @Override
    public Boolean closeIssue(String id, RepositoryType repositoryType) throws IOException {
        DataRepository<IssueEntity, String> repository = repositories.get(repositoryType.getValue());
        return repository.closeIssue(id);
    }

    @Override
    public List<IssueDto> listOpenIssues(RepositoryType repositoryType) throws Exception {
        DataRepository<IssueEntity, String> repository = repositories.get(repositoryType.getValue());
        return issueMapper.toDtoList(repository.listOpenIssues());
    }

}
