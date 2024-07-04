package com.gohealth.assignment.api;

import com.gohealth.assignment.shared.CommandBase;
import com.gohealth.assignment.utils.RepositoryType;

public interface CommandController {

  Boolean validateIssue(String id, RepositoryType repositoryType) throws Exception;

  Object processCommand(CommandBase cmd) throws Exception;
}
