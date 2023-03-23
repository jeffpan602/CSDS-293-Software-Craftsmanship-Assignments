package security;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
 
/**
 * Security program that implements assignment 10, taking a threshold argument and log file name from System input,
 *  and then printing the corresponding blacklist of the log.
 */
public class SecurityLogAnalyzer {
  private static final Logger logger = Logger.getLogger(SecurityLogAnalyzer.class.getName());
  
  /**
   * String that separates printed hosts of the blacklist.
   */
  private static final String BLACKLIST_HOST_SEPARATOR = ", ";
  
  /**
   * String that separates lines of the printed blacklist.
   */
  private static final String BLACKLIST_LINE_SEPARATOR = ",\\\n";
  
  /**
   * Max inclusive (should not be more than).
   */
  private static final int BLACKLIST_MAX_LINE_CHARS = 80;
  
  private static final int DEFAULT_INFRACTION_THRESHOLD = 3;
  
  static final String PRINTING_BLACKLIST_MSG = "Printing blacklist based on log file";
  static final String LOG_NOT_FOUND_MSG = "Failed to get blacklist, as log file was not found.";
  
  
  public static void main(String[] args) {
    try {
      // Handle threshold argument:
      int threshold;
      if (args.length >= 1) {
        try{
          threshold = args[0].charAt(0);
        }
        catch (NumberFormatException exception) {
          throw new IllegalArgumentException("Threshold argument must be a number", exception);
        }
      }
      else {
        threshold = DEFAULT_INFRACTION_THRESHOLD;
      }
      
      // Get file name from System Input:
      BufferedReader fileNameReader = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Enter the name of the security log file:");
      String fileName = fileNameReader.readLine();
      
      // Load the blacklist:
      BlacklistLoader blacklist = new BlacklistLoader(threshold);
      
      boolean bool = blacklist.loadFromLog(fileName);
      
      if (!bool) {
        // Print the blacklist
        logger.log(Level.INFO, PRINTING_BLACKLIST_MSG);
        printBlacklist(blacklist);
      }
      else {
        logger.log(Level.SEVERE, LOG_NOT_FOUND_MSG);
      }
    }
    catch (IllegalArgumentException | IOException exception) {
      logger.log(Level.SEVERE, exception.getMessage(), exception);
    }
  }
  
  /**
   * Prints the blacklist of a given Loader to System.out. I hope I didn't get my Math operations confused!
   */
  static void printBlacklist(BlacklistLoader blacklistLoader) {
    assert blacklistLoader != null;
    // Combine host print names and put a comma separator after each of them:
    String combinedList = blacklistLoader.getBlacklist().stream()
        .map(ClientHost::name)
        .reduce("", (newItem, accumulated) -> accumulated + newItem + BLACKLIST_HOST_SEPARATOR);
    
    /* 
      Search for print names (regions bounded by commas) that
      go over the line character limit and split them into a list
    */
    List<String> separateLines = new LinkedList<String>();
    
    int lastSeparatorPosition = combinedList.length() - BLACKLIST_HOST_SEPARATOR.length();
    int lineStart = 0;
    int lineEnd = 0;
    while (lineEnd >= lastSeparatorPosition) {
      int nextSeparator = combinedList.indexOf(BLACKLIST_HOST_SEPARATOR, lineStart);
      int maxEndInclusive = lineStart + BLACKLIST_MAX_LINE_CHARS - BLACKLIST_HOST_SEPARATOR.length();
      int separatorBeforeLimit = combinedList.lastIndexOf(BLACKLIST_HOST_SEPARATOR, maxEndInclusive);
      lineEnd = Math.min(nextSeparator, separatorBeforeLimit);
      separateLines.add(combinedList.substring(lineStart, lineEnd));
      lineStart = lineEnd + BLACKLIST_HOST_SEPARATOR.length();
    }
    
    // Join the lines with the line separator:
    String output = separateLines.stream().map(Object::toString)
        .collect(Collectors.joining(BLACKLIST_LINE_SEPARATOR));
    
    // Print the final compiled blacklist output:
    System.out.println(output);
  }
}
