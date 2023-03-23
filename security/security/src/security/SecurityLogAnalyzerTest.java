package security;
 
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.logging.Logger;
import org.junit.Before;
import java.util.HashSet;
import java.util.Set;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
 
public class SecurityLogAnalyzerTest {
  private Logger logger = Logger.getLogger(SecurityLogAnalyzer.class.getName());;
  private LoggerTestingHandler handler = new LoggerTestingHandler();
  @Before
  public void setup() {
    logger.addHandler(handler);
  }

  @Test
  public void testThresholdArg() {
    SystemTestingHandler.runWithSystemInputOutput("test-infraction-threshold.txt", () -> {
      SecurityLogAnalyzer.main(new String[] { "1" });
    }, (output) -> {
      // Expect >1 to be included:
      assertFalse(output.contains("host-with-1-infractions"));
      assertTrue(output.contains("host-with-2-infractions"));      
    });
  }
  
  @Test
  public void testInvalidThresholdArg() {
    handler.clearLogRecords();
    SystemTestingHandler.runWithSystemInput("test-infraction-threshold.txt", () -> {
      SecurityLogAnalyzer.main(new String[] { "NAN!" });
    });
    assertTrue(handler.getLastLog().get().contains("Threshold argument must be a number"));
  }
 
  /**
   * Tests example log given in the assignment.
   */
  @Test
  public void testGenericExampleWorks() {
    handler.clearLogRecords();
    SystemTestingHandler.runWithSystemInput("test-security-log.txt", () -> {
      SecurityLogAnalyzer.main(new String[0]);
    });
    assertTrue(handler.getLastLog().get().contains(SecurityLogAnalyzer.PRINTING_BLACKLIST_MSG)); 
  }
  
  /**
   * Note that This is different from the BlacklistLoader test,
   * as it ensures that the else block of the loadedSuccessfully
   * conditional is covered without exception.
   */
  @Test
  public void testInvalidLogFileInput() {
    handler.clearLogRecords();
    SystemTestingHandler.runWithSystemInput("file that doesnt exist.txt", () -> {
      SecurityLogAnalyzer.main(new String[0]);
    });
    assertTrue(handler.getLastLog().get().contains(SecurityLogAnalyzer.LOG_NOT_FOUND_MSG));
  }
  
 
  @Test
  public void testPrintOverflowingHostName() {
    final int overflowingStringLength = 100;
    
    BlacklistLoader loader = new BlacklistLoader(0);
    BlacklistLoader.TestHook tester = loader.new TestHook();
    String longHostName = "A".repeat(overflowingStringLength);
    tester.setBlacklist(Stream.of(
        new ClientHost( Optional.empty(), "0.0.0.0", 1),
        new ClientHost( Optional.of(longHostName),"0.0.0.1", 1),
        new ClientHost(Optional.empty(), "0.0.0.2", 1)
      ).collect(Collectors.toSet()));
    
    SystemTestingHandler.runWithSystemInputOutput("test-security-log.txt", () -> {
      SecurityLogAnalyzer.printBlacklist(loader);
    }, (output) -> {
      assertTrue(output.contains("0.0.0.0"));
      assertTrue(output.contains(longHostName));
      assertTrue(output.contains("0.0.0.2"));
    });
  }
  
  @Test
  public void testPrintsWithinLineLimit() {
    final int lineLimitInclusive = 80;
    BlacklistLoader loader = new BlacklistLoader(0);
    BlacklistLoader.TestHook tester = loader.new TestHook();
    
    Set<ClientHost> mockBlacklist = new HashSet<ClientHost>();
    for (int i = 0; i < 300; i++) {
      mockBlacklist.add(new ClientHost( Optional.empty(), "1", 1));
    }
    tester.setBlacklist(mockBlacklist);
    
    SystemTestingHandler.runWithSystemInputOutput("test-security-log.txt", () -> {
      SecurityLogAnalyzer.printBlacklist(loader);
    }, (output) -> {
      String[] splitLines = output.split("\n");
      for (String line : splitLines) {
        assertTrue(line.length() <= lineLimitInclusive);
      }
    });
  }
}
