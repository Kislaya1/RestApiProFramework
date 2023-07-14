package com.auto.rest.api.reporting;

import org.junit.jupiter.api.extension.*;

public class FrameworkListener
    implements BeforeAllCallback,
        BeforeTestExecutionCallback,
        AfterAllCallback,
        AfterTestExecutionCallback {

  @Override
  public void beforeAll(ExtensionContext context) {
    ExtentReport.report().init(context);
  }

  @Override
  public void beforeTestExecution(ExtensionContext context) {
    String testName = context.getTestMethod().orElseThrow().getName();
    ExtentReport.report().createTest(testName);
  }

  @Override
  public void afterTestExecution(ExtensionContext context) {
    if (context.getExecutionException().isPresent())
      ExtentLogger.fail(context.getExecutionException().orElseThrow().getLocalizedMessage());
    else ExtentLogger.pass(context.getDisplayName());
  }

  @Override
  public void afterAll(ExtensionContext context) {
    ExtentReport.report().tearDownReport();
  }
}
