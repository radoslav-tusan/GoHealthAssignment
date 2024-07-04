package com.gohealth.assignment.impl.mapper;

import com.gohealth.assignment.api.model.CreateIssueDto;
import com.gohealth.assignment.api.model.IssueDto;
import com.gohealth.assignment.impl.jpa.model.IssueEntity;
import com.gohealth.assignment.shared.MapperBase;
import com.gohealth.assignment.shared.MapperConfig;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class, builder = @Builder(disableBuilder = true))
public interface IssueMapper extends MapperBase<CreateIssueDto, IssueDto, IssueEntity> {

  @Mapping(target = "issueId", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  IssueEntity toEntity(CreateIssueDto dto);

  @Mapping(target = "id", source = "issueId")
  IssueDto toDto(IssueEntity view);
}
