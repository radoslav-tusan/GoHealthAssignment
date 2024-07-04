package com.gohealth.assignment.shared;

import static com.gohealth.assignment.shared.SqlDaoBase.SEQUENCE_NAME;

import jakarta.persistence.*;
import lombok.Data;

@Data
@MappedSuperclass
@SequenceGenerator(name = "entity_seq_generator", sequenceName = SEQUENCE_NAME, allocationSize = 1)
public class EntityBase {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "entity_seq_generator")
  @Column(name = "id", nullable = false)
  private Long id;
}
