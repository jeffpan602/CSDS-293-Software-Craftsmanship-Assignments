package security;
 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import java.util.Optional;
import org.junit.Test;
 
public class ClientHostTest {
  @Test
  public void testConstructorNullParamsThrow() {    
    assertThrows(IllegalArgumentException.class, () -> {
      new ClientHost(Optional.empty(), null, 1);
    });
    
    assertThrows(IllegalArgumentException.class, () -> {
      new ClientHost(null, "0.0.0.0", 1);
    });
  }
  
  @Test
  public void testInfractionsCountPositive() {
    assertThrows(IllegalArgumentException.class, () -> {
      new ClientHost( Optional.empty(), "0.0.0.0", -1);
    });
    
    assertThrows(IllegalArgumentException.class, () -> {
      new ClientHost(Optional.empty(), "0.0.0.0", 0);
    });
  }
  
  @Test
  public void testCombine() {
    String ip = "0.0.0.0";
    ClientHost clientHostA = new ClientHost(Optional.empty(), ip, 1);
    ClientHost clientHostB = new ClientHost(Optional.empty(), new String(ip), 1);
    ClientHost combined = clientHostA.combineHostData(clientHostB);
    assertEquals(combined.getInfractionsCount(), 2);
  }
  
}
