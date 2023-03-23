package security;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
 
public class BlacklistLoaderTest {
  private Logger logger = Logger.getLogger(BlacklistLoader.class.getName());;
  private LoggerTestingHandler handler = new LoggerTestingHandler();
  @Before
  public void setup() {
    logger.addHandler(handler);
  }

  @Test
  public void testInvalidFileName() {
    handler.clearLogRecords();
    BlacklistLoader blacklistLoader = new BlacklistLoader(3);
    blacklistLoader.loadFromLog("file that doesnt exist.txt");
    assertTrue(handler.getLastLog().get().contains("Expected file"));
  }

  @Test
  public void testEmptySectionBody() {
      handler.clearLogRecords();
      BlacklistLoader blacklistLoader = new BlacklistLoader(3);
      blacklistLoader.loadFromLog(SystemTestingHandler.testPath("test-empty-section-1.txt"));
      blacklistLoader.loadFromLog(SystemTestingHandler.testPath("test-empty-section-2.txt"));
      assertTrue(handler.getLastLog().isEmpty());
  }
  
  @Test
  public void testMalformedLineArgCount() {
    handler.clearLogRecords();
    BlacklistLoader securityLog = new BlacklistLoader(3);
    BlacklistLoader.TestHook testHook = securityLog.new TestHook();
    testHook.loadHostInfractionLine("2.115.68.148 (host-with-1-infractions)");
    assertTrue(handler.getLastLog().get().contains("Line skipped: not in the correct format (unexpected number of space-split arguments)"));
  }
  
  @Test
  public void testNegativeThreshold() {
    handler.clearLogRecords();
    BlacklistLoader blacklistLoader = new BlacklistLoader(-1);
    assertEquals(blacklistLoader.getInfractionThreshold(), 0);
  }
  @Test
  public void testDuplicatesHostNameFirst() {
    SystemTestingHandler.runWithSystemOutput(() -> {
      BlacklistLoader blacklistLoader = new BlacklistLoader(1);
      BlacklistLoader.TestHook testHook = blacklistLoader.new TestHook();
      
      testHook.loadHostInfractionLine("2.115.68.148 (host-with-1-infractions): 1 times");
      testHook.loadHostInfractionLine("2.115.68.148: 1 times");
      
      SecurityLogAnalyzer.printBlacklist(blacklistLoader);
    }, (output) -> {
      // Duplicate host should maintain optional host name in print log (rather than IP, since host name is preferable):
      assertTrue(output.contains("host-with-1-infractions"));
    });
  }

  @Test
  public void testInfractionThresholdBoundary() {
    SystemTestingHandler.runWithSystemOutput(() -> {
      BlacklistLoader blacklistLoader = new BlacklistLoader(2);
      BlacklistLoader.TestHook testHook = blacklistLoader.new TestHook();
      
      testHook.loadHostInfractionLine("1.1.1.1: 1 times");
      testHook.loadHostInfractionLine("1.1.1.1: 2 times");
      
      testHook.loadHostInfractionLine("0.0.0.0: 2 times");
      
      SecurityLogAnalyzer.printBlacklist(blacklistLoader);
    }, (output) -> {
      assertTrue(output.contains("1.1.1.1"));
      assertFalse(output.contains("0.0.0.0"));
    });
  }
  
  /**
   * Tests that if a line fails to load into the blacklist due to being invalid,
   * then lines before and after it will still be loaded properly.
   */
  @Test
  public void testWorksBeyondInvalidLine() {
    SystemTestingHandler.runWithSystemOutput(() -> {
      BlacklistLoader blacklistLoader = new BlacklistLoader(1);
      BlacklistLoader.TestHook testHook = blacklistLoader.new TestHook();
      testHook.loadHostInfractionLine("0.0.0.1 (host-1): 2 times");
      testHook.loadHostInfractionLine("0.0.0.2: wow this is invalid input!");
      testHook.loadHostInfractionLine("0.0.0.2 (host-2): 2 times");
      testHook.loadHostInfractionLine("0.0.0.3 (host-3): 2 times");
      SecurityLogAnalyzer.printBlacklist(blacklistLoader);
    }, (output) -> {
      assertTrue(output.contains("host-2"));
    });
  }
}

