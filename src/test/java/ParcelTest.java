import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class ParcelTest {

  @Test
  public void newParcel_instansiatesCorrectly(){
    Parcel testParcel = new Parcel(3,3,3,3);
    assertEquals(true, testParcel instanceof Parcel);
  }

}
