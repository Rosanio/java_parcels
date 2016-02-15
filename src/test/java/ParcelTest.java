import org.junit.*;
import static org.junit.Assert.*;

import java.util.ArrayList;

public class ParcelTest {

  @Test
  public void newParcel_instansiatesCorrectly(){
    Parcel testParcel = new Parcel(3,3,3,3);
    assertEquals(true, testParcel instanceof Parcel);
  }

  @Test
  public void newParcel_getVolumeReturnsVolume_27(){
    Parcel testParcel = new Parcel(3,3,3,3);
    assertEquals(27, testParcel.getVolume());
  }

  @Test
  public void costToShip_calculateCostToShip_1415(){
    Parcel testParcel = new Parcel(3,3,3,3);
    assertEquals(14.15, testParcel.costToShip(1, 40), 0.002);
  }

}
