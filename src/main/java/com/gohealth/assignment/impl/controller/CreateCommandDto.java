package com.gohealth.assignment.impl.controller;

import com.gohealth.assignment.shared.CommandBase;
import com.gohealth.assignment.utils.RepositoryType;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class CreateCommandDto extends CommandBase {

    private String parentId;

    private String description;

    private String logUrl;

    private RepositoryType storageType;

}
