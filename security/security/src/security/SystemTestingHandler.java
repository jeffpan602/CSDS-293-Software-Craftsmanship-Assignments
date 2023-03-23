package security;
 
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.nio.file.Paths;
import java.util.function.Consumer;
 
/**
 * Test Helper for System Input / System Output handling, including exception handling.
 */
class SystemTestingHandler {
  private static final InputStream SYSTEM_IN = System.in;
  private static final PrintStream SYSTEM_OUT = System.out;
  private static final String TEST_LOGS_DIRECTORY = "testLogs";
  
  static void runWithSystemInputOutput(String fileName, Runnable action, Consumer<String> assertionStep) {
    System.setIn(new ByteArrayInputStream(testPath(fileName).getBytes()));
    runWithSystemOutput(action, assertionStep);
    System.setIn(SYSTEM_IN);
  }

  static void runWithSystemInput(String fileName, Runnable action) {
    System.setIn(new ByteArrayInputStream(testPath(fileName).getBytes()));
    action.run();
    System.setIn(SYSTEM_IN);
  }

  static String testPath(String testName) {
    return Paths.get(TEST_LOGS_DIRECTORY, testName).toString();
  }
  
  static void runWithSystemOutput(Runnable action, Consumer<String> assertionStep) {
    ByteArrayOutputStream testingOutputStream = new ByteArrayOutputStream();
    System.setOut(new PrintStream(testingOutputStream));
    action.run();
    
    String outStr = testingOutputStream.toString();
    assertionStep.accept(outStr);
    System.setOut(SYSTEM_OUT);
  }
}
