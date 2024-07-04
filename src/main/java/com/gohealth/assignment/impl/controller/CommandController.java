package com.gohealth.assignment.impl.controller;

import com.gohealth.assignment.shared.CommandBase;
import com.gohealth.assignment.utils.RepositoryType;

import java.io.IOException;

public interface CommandController {

    Boolean validateIssue(String id, RepositoryType repositoryType) throws Exception;

    Object processCommand(CommandBase cmd) throws Exception;
}
