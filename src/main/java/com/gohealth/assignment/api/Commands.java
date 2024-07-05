package com.gohealth.assignment.api;

import static com.gohealth.assignment.api.Commands.APPLICATION.EXIT;

import java.util.List;

public final class Commands {

  public static class OPERATION {

    public static final String CREATE = "create";
    public static final String CLOSE = "close";
    public static final String LIST = "list";
    public static List<String> commands = List.of(CREATE, CLOSE, LIST, EXIT);
  }

  public static class REPOSITORY {

    public static final String SQL = "sql";
    public static final String CSV = "csv";
    public static List<String> commands = List.of(SQL, CSV, EXIT);
  }

  public static class APPLICATION {

    public static final String EXIT = "exit";
  }
}
