package com.gohealth.assignment.utils;

import java.util.List;

public interface TableGenerator {

  void printTable(List<String> headersList, List<List<String>> rowsList, int... headerHeight);
}
