package com.gohealth.assignment.shared;

import com.gohealth.assignment.utils.RepositoryType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CommandBase {

  private String operation;

  private RepositoryType repositoryType;
}
