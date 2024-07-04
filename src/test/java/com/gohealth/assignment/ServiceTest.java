package com.gohealth.assignment;

import static com.gohealth.assignment.shared.DaoBase.SQL_TYPE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

import com.gohealth.assignment.api.model.CreateIssueDto;
import com.gohealth.assignment.api.model.IssueDto;
import com.gohealth.assignment.impl.jpa.dao.DataRepository;
import com.gohealth.assignment.impl.jpa.dao.sql.SqlDataRepositoryImpl;
import com.gohealth.assignment.impl.jpa.model.IssueEntity;
import com.gohealth.assignment.impl.jpa.model.Status;
import com.gohealth.assignment.impl.mapper.IssueMapper;
import com.gohealth.assignment.impl.service.IssueServiceImpl;
import com.gohealth.assignment.utils.RepositoryType;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(classes = {IssueServiceImpl.class, SqlDataRepositoryImpl.class})
public class ServiceTest {

  @Autowired private IssueServiceImpl issueService;

  @MockBean
  @Qualifier(SQL_TYPE) private DataRepository<IssueEntity, String> sqlRepository;

  @MockBean private IssueMapper mapper;

  @Test
  void createIssueTest() throws IOException {
    CreateIssueDto createDto = mock(CreateIssueDto.class);
    IssueEntity entity = new IssueEntity();
    entity.setIssueId("I-1");
    entity.setDescription("desc");
    entity.setLogUrl("url");
    entity.setStatus(Status.OPEN);

    IssueDto dto = new IssueDto();
    dto.setId("I-1");
    dto.setDescription("desc");
    dto.setLogUrl("url");
    dto.setCreatedAt(LocalDateTime.now());
    dto.setStatus(Status.OPEN);

    when(mapper.toEntity(createDto)).thenReturn(entity);
    when(sqlRepository.createIssue(entity)).thenReturn(entity);

    String result = issueService.createIssue(createDto, RepositoryType.SQL);

    assertNotNull(result);
    assertEquals(result, dto.getId());

    verify(mapper, times(1)).toEntity(createDto);
    verify(sqlRepository, times(1)).createIssue(entity);
    verifyNoMoreInteractions(sqlRepository, mapper);
  }

  @Test
  void findIssueByIdTest() throws Exception {
    final String id = "I-1";

    IssueEntity entity = new IssueEntity();
    entity.setIssueId(id);
    entity.setDescription("desc");
    entity.setLogUrl("url");
    entity.setStatus(Status.OPEN);

    IssueDto dto = new IssueDto();
    dto.setId(id);
    dto.setDescription("desc");
    dto.setLogUrl("url");
    dto.setCreatedAt(LocalDateTime.now());
    dto.setStatus(Status.OPEN);

    when(sqlRepository.findByIssueId(id)).thenReturn(entity);
    when(mapper.toDto(entity)).thenReturn(dto);

    IssueDto result = issueService.findByIssueId(id, RepositoryType.SQL);

    assertNotNull(result);
    assertEquals(result.getId(), id);

    verify(mapper, times(1)).toDto(entity);
    verify(sqlRepository, times(1)).findByIssueId(id);
    verifyNoMoreInteractions(sqlRepository, mapper);
  }

  @Test
  void listOpenIssuesTest() throws Exception {
    List<IssueEntity> mockList = List.of(new IssueEntity(), new IssueEntity(), new IssueEntity());

    when(sqlRepository.listOpenIssues()).thenReturn(mockList);
    when(mapper.toDtoList(mockList))
        .thenReturn(List.of(new IssueDto(), new IssueDto(), new IssueDto()));

    List<IssueDto> result = issueService.listOpenIssues(RepositoryType.SQL);

    assertNotNull(result);
    assertEquals(result.size(), mockList.size());

    verify(sqlRepository, times(1)).listOpenIssues();
    verify(mapper, times(1)).toDtoList(mockList);
    verifyNoMoreInteractions(sqlRepository, mapper);
  }

  @Test
  void closeIssueTest() throws Exception {
    final String id = "I-1";

    IssueEntity entity = new IssueEntity();
    entity.setIssueId(id);
    entity.setDescription("desc");
    entity.setLogUrl("url");
    entity.setStatus(Status.OPEN);

    when(sqlRepository.findByIssueId(id)).thenReturn(entity);
    when(sqlRepository.closeIssue(id)).thenReturn(true);

    Boolean result = issueService.closeIssue(id, RepositoryType.SQL);

    assertNotNull(result);
    assertEquals(result, true);
  }
}
