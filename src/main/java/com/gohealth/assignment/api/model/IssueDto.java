package com.gohealth.assignment.api.model;

import com.gohealth.assignment.impl.jpa.model.Status;
import com.gohealth.assignment.shared.DtoBase;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.Data;

@Data
public class IssueDto extends DtoBase {

  private String parentId;

  @NotNull private String description;

  @NotNull private String logUrl;

  @NotNull private LocalDateTime createdAt;

  @NotNull private Status status;
}
