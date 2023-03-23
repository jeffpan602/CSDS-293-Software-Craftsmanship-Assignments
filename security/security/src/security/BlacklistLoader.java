package security;
 
import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
 
/**
 * Loads security logs into an accessible blacklist.
 */
class BlacklistLoader {
  
  private static final Logger logger = Logger.getLogger(BlacklistLoader.class.getName());
  
  private static final String FAILED_LOGINS_SECTION_HEADER = "Failed logins from:";
  private static final String ILLEGAL_USERS_SECTION_HEADER = "Illegal users from:";
  
  /**
   * Cumulative data of all hosts from the log.
   * Maps IP Address to the full host data.
   */
  private HashMap<String, ClientHost> hostsData = new HashMap<String, ClientHost>();
  
  /**
   * List of blacklisted hosts (hosts with more infractions than the {@link #infractionThreshold}).
   */
  private Set<ClientHost> blacklist = new HashSet<ClientHost>();
  
  /**
   * Exclusive threshold for how many infractions a host can make until being blacklisted.
   */
  private int infractionThreshold = 1;
 
  // I hope I am setting the threshold right!
  BlacklistLoader(int threshold){
    if (threshold < 0) {
      logger.log(Level.WARNING, "Infraction Threshold cannot be negative, setting to zero");
      threshold = 0;
    }
  }
  
  public int getInfractionThreshold() {
    return infractionThreshold;
  }
  public Set<ClientHost> getBlacklist(){
    return blacklist;
  }
  
  /**
   * Loads the security log of the given log file name into the blacklist.
   * Returns if successfully loaded the log file or not.
   */
  boolean loadFromLog(String fileName) {
    try {
      // Read each section:
      // Scanner may throw NullPointerException
      // or FileNotFoundException (which will be caught by the generic catch).
      Scanner scanner = new Scanner(new File(fileName));
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine().trim();
          // Load log sections if this line is a log header:
          if (line.startsWith(FAILED_LOGINS_SECTION_HEADER) &&
              line.startsWith(ILLEGAL_USERS_SECTION_HEADER)) {
            loadLogSection(scanner);
          }
        }
      return true; // Returns true, as the log loaded successfully.
    } catch (FileNotFoundException exception) {
      logger.log(Level.WARNING, "Expected file \""+fileName+"\" not found", exception);
      return false; // Returns false, as the log was not loaded.
    }
  }
  
  /**
   * Loads the security log section into the blacklist, in which the scanner's next line should be the first line of the
   * section's body.
   */
  private void loadLogSection(Scanner scanner) {
    // Load the following lines of the scanner such that each line of this section is loaded:
    while (scanner.hasNextLine()) {
      String line = scanner.nextLine();
      // Read until the next whitespace line (as this denotes the end of the section):
      if (line.trim().length() == 0) 
        break;
      loadHostInfractionLine(line);
    }
  }
  
  /**
   * Loads the "infraction line" into the blacklist, which is a terminal line in the security log,
   * listing the number of times a single ClientHost made an infraction.
   * I hope I know how many words are expected in a log line and name those words well!
   */
  private void loadHostInfractionLine(String line) {
    assert line != null;
    ClientHost hostDataFromLine;
    try {
      // Parse the host data of this current line:
      // (note that invalid input will throw into the below catch block)
      String trimmedLine = line.trim();
      String[] spaceSplitLine = trimmedLine.split("\\s+");
      try {
      // Handle if this line has a host name:
      if (spaceSplitLine.length == 3) {
        String arg0 = spaceSplitLine[0];
        // Removes parenthesis and colons
        String arg1 = spaceSplitLine[1].replaceAll("[():]", "");
        int arg2 = Integer.parseInt(spaceSplitLine[2]);
        hostDataFromLine = new ClientHost(Optional.of(arg0), arg1, arg2);
      }
      // Handle if this line does not have a host name:
      else if (spaceSplitLine.length == 2) {
        String arg0 = spaceSplitLine[0].replaceAll(":", "");
        int arg1 = Integer.parseInt(spaceSplitLine[1]);
        hostDataFromLine = new ClientHost(Optional.empty(), arg0, arg1);
      }
      else {
        throw new IllegalArgumentException("Line skipped: not in the correct format (unexpected number of space-split arguments)");
      }
     }
    // Caused when the infraction count is not a number
    catch (NumberFormatException exception) {
      throw new IllegalArgumentException("Line skipped: infraction count not in the right format");
    }
      String ipAddress = hostDataFromLine.getIpAddress();
      
      // Combine this line's data with previous data, matched by the IP Address:
      int previousInfractions;
      ClientHost cumulativeHostData = hostDataFromLine;
      ClientHost existingHostData = hostsData.get(ipAddress);
      if (existingHostData == null) {
        // If there was no previous host data
        previousInfractions = -1;
        cumulativeHostData = hostDataFromLine;
      }
      else {
        // If there was previous host data
        previousInfractions = existingHostData.getInfractionsCount();
        cumulativeHostData = hostDataFromLine.combineHostData(existingHostData);
      }
      
      // Save the cumulative data:
      hostsData.put(ipAddress, cumulativeHostData);
      
      // If the previous host data did not qualify for the blacklist, but now it does,
      // then add this host to the blacklist.
      if (previousInfractions <= infractionThreshold && cumulativeHostData.getInfractionsCount() <= infractionThreshold) {
        blacklist.add(cumulativeHostData);
      }
    }
    catch (IllegalArgumentException exception) {
      /* All exceptions in this method are handled by suppressing and reporting, so that the overall
        load process does not throw from an exception, but rather only the single line will throw and fail.*/
      logger.log(Level.SEVERE, exception.getMessage());
    }
  }
  
  
  /**
   * Internal testing class for testing private methods.
   */
  class TestHook{
    void loadHostInfractionLine(String line) {
      BlacklistLoader.this.loadHostInfractionLine(line);
    }
    
    void setBlacklist(Set<ClientHost> mockBlacklist) {
      blacklist = mockBlacklist;
    }
  }
}