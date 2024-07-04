package com.gohealth.assignment;

import com.gohealth.assignment.impl.controller.CloseCommandDto;
import com.gohealth.assignment.impl.controller.CommandController;
import com.gohealth.assignment.impl.controller.Commands.APPLICATION;
import com.gohealth.assignment.impl.controller.Commands.OPERATION;
import com.gohealth.assignment.impl.controller.Commands.REPOSITORY;
import com.gohealth.assignment.impl.controller.CreateCommandDto;
import com.gohealth.assignment.shared.CommandBase;
import com.gohealth.assignment.utils.RepositoryType;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GoHealthAssignmentApplication implements CommandLineRunner {

  @Autowired private CommandController controller;

  public static void main(String[] args) {
    SpringApplication.run(GoHealthAssignmentApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

    Scanner scanner = new Scanner(System.in);
    String command = "";
    String repository = "";

    boolean repoSelected = false;
    while (!repoSelected) {
      System.out.println("\n");
      System.out.println("SELECT REPOSITORY:");
      System.out.println(
          "(" + REPOSITORY.SQL + ", " + REPOSITORY.CSV + ", " + APPLICATION.EXIT + ")");

      repository = scanner.nextLine();

      if (REPOSITORY.commands.contains(repository)) {
        repoSelected = true;
      } else {
        System.err.println("Repository does not exist");
        System.out.println("\n");
      }
    }

    while (true) {
      System.out.println("\n");
      System.out.println("SELECT OPERATION:");
      System.out.println(
          "("
              + OPERATION.CREATE
              + ", "
              + OPERATION.CLOSE
              + ", "
              + OPERATION.LIST
              + ", "
              + APPLICATION.EXIT
              + ")");

      command = scanner.nextLine();

      switch (command) {
        case OPERATION.CREATE -> {
          boolean validParent = false;
          String parentIdInput = "";
          while (!validParent) {
            System.out.println("SET PARENT ID:");
            System.out.println("(Leave blank if parent does not exist)");
            parentIdInput = scanner.nextLine();
            if (parentIdInput.isBlank()) validParent = true;
            else {
              if (controller.validateIssue(
                  parentIdInput, RepositoryType.valueOf(repository.toUpperCase())))
                validParent = true;
              else {
                System.err.println("Parent does not exist");
                System.out.println("\n");
              }
            }
          }
          System.out.println("SET DESCRIPTION:");
          String descriptionInput = scanner.nextLine();
          System.out.println("SET LOG URL:");
          String logUrlInput = scanner.nextLine();

          System.out.println(
              controller.processCommand(
                  CreateCommandDto.builder()
                      .parentId(parentIdInput)
                      .description(descriptionInput)
                      .logUrl(logUrlInput)
                      .operation(OPERATION.CREATE)
                      .repositoryType(RepositoryType.valueOf(repository.toUpperCase()))
                      .build()));
        }
        case OPERATION.CLOSE -> {
          boolean validIssue = false;
          String issueIdInput = "";
          while (!validIssue) {
            System.out.println("SET ISSUE ID TO CLOSE:");
            issueIdInput = scanner.nextLine();
            if (controller.validateIssue(
                issueIdInput, RepositoryType.valueOf(repository.toUpperCase()))) validIssue = true;
            else {
              System.err.println("Issue does not exist");
              System.out.println("\n");
            }
          }

          Boolean result =
              (Boolean)
                  controller.processCommand(
                      CloseCommandDto.builder()
                          .id(issueIdInput)
                          .operation(OPERATION.CLOSE)
                          .repositoryType(RepositoryType.valueOf(repository.toUpperCase()))
                          .build());

          if (result) {
            System.out.println("\n");
            System.out.printf("ISSUE %s CLOSED", issueIdInput);
          }
        }
        case OPERATION.LIST -> {
          controller.processCommand(
              CommandBase.builder()
                  .operation(OPERATION.LIST)
                  .repositoryType(RepositoryType.valueOf(repository.toUpperCase()))
                  .build());
        }
        case APPLICATION.EXIT -> {
          System.exit(0);
        }
      }
    }
  }
}
