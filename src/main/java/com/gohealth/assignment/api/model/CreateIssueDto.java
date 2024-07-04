package com.gohealth.assignment.api.model;

import com.gohealth.assignment.impl.jpa.model.Status;
import com.gohealth.assignment.shared.CreateDtoBase;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateIssueDto extends CreateDtoBase {

    private String parentId;

    @NotNull
    private String description;

    @NotNull
    private String logUrl;

    @NotNull
    private Status status;
}
