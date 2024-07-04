package com.gohealth.assignment.shared;

import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@MappedSuperclass
public class DtoBase {

  @NotNull private String id;
}
