package com.gohealth.assignment.impl.jpa.model;

import com.gohealth.assignment.shared.EntityBase;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;
import lombok.Data;

@Data
@Entity
@Table(name = "issue")
public class IssueEntity extends EntityBase {

  @Size(max = 8)
  @Column(name = "issue_id", nullable = false)
  private String issueId;

  @Size(max = 8)
  @Column(name = "parent_id")
  private String parentId;

  @Column(name = "description", nullable = false)
  private String description;

  @Column(name = "log_url", nullable = false)
  private String logUrl;

  @Column(name = "created_at", nullable = false)
  private LocalDateTime createdAt;

  @Enumerated(EnumType.STRING)
  @Column(name = "status", nullable = false)
  private Status status;
}
