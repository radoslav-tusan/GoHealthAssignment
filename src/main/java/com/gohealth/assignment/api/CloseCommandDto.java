package com.gohealth.assignment.api;

import com.gohealth.assignment.shared.CommandBase;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CloseCommandDto extends CommandBase {

  private String id;
}
