package security;
 
import java.util.Optional;
 
public class ClientHost {
  private String ipAddress;
  private Optional<String> hostName;
  
  /**
   * Positive only.
   */
  private int infractionsCount;
  
  
  ClientHost(Optional<String> hostName, String ipAddress, int infractionsCount) throws IllegalArgumentException {
    if (infractionsCount <= 0)
      throw new IllegalArgumentException("Line skipped: infractions count must be positive");
    
    this.ipAddress = ipAddress;
    this.hostName = hostName;
    this.infractionsCount = infractionsCount;
  }
  
  String getIpAddress() {
    return ipAddress;
  }
  
  int getInfractionsCount() {
    return infractionsCount;
  }
  
  ClientHost combineHostData(ClientHost other) {
    if (other == null || ipAddress != other.ipAddress) {
      throw new IllegalArgumentException("Invalid other Host; Other Host should match this Host's IP Address");
    }
    
    Optional<String> hostName = this.hostName.isPresent() ? this.hostName : other.hostName;
    
    int combinedInfractionsCount = this.infractionsCount + other.infractionsCount;
    
    return new ClientHost(hostName, ipAddress, combinedInfractionsCount);
  }
  
  String name() {
    return hostName.isPresent() ? hostName.get() : ipAddress;
  }
}
