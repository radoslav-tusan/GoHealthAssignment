package com.gohealth.assignment.utils;

import static com.gohealth.assignment.shared.DaoBase.CSV_TYPE;
import static com.gohealth.assignment.shared.DaoBase.SQL_TYPE;

public enum RepositoryType {

    SQL(SQL_TYPE), CSV(CSV_TYPE);

    private final String value;

    RepositoryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
