package com.gohealth.assignment.impl.mapper;

import com.gohealth.assignment.api.model.CreateIssueDto;
import com.gohealth.assignment.api.model.IssueDto;
import com.gohealth.assignment.impl.jpa.model.IssueEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-07-04T21:02:34+0200",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21.0.1 (Oracle Corporation)"
)
@Component
public class IssueMapperImpl implements IssueMapper {

    @Override
    public IssueEntity toEntity(CreateIssueDto dto) {
        if ( dto == null ) {
            return null;
        }

        IssueEntity issueEntity = new IssueEntity();

        issueEntity.setParentId( dto.getParentId() );
        issueEntity.setDescription( dto.getDescription() );
        issueEntity.setLogUrl( dto.getLogUrl() );
        issueEntity.setStatus( dto.getStatus() );

        return issueEntity;
    }

    @Override
    public IssueDto toDto(IssueEntity view) {
        if ( view == null ) {
            return null;
        }

        IssueDto issueDto = new IssueDto();

        issueDto.setId( view.getIssueId() );
        issueDto.setParentId( view.getParentId() );
        issueDto.setDescription( view.getDescription() );
        issueDto.setLogUrl( view.getLogUrl() );
        issueDto.setCreatedAt( view.getCreatedAt() );
        issueDto.setStatus( view.getStatus() );

        return issueDto;
    }
}
