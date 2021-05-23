import com.taobao.ddd.third.domain.entity.Order;
import com.taobao.ddd.third.type.Address;
import com.taobao.ddd.third.type.OrderId;
import com.taobao.ddd.third.util.SnapshotUtils;
import org.junit.Assert;
import org.junit.Test;

public class SnapshotUtilsTest {
  @Test
  public void snapshot() throws Exception {
    Order order = new Order();
    order.setId(new OrderId(1L));
    order.setAddress(new Address("CN", "浙江", "杭州", "xxxx"));
    order.setTitle("test order");
    System.out.println("metadata: " + order);

    Order snapshot = SnapshotUtils.snapshot(order);

    System.out.println("snapshot: " + snapshot);

    Assert.assertEquals(order.toString(), snapshot.toString());

    System.out.println("metadata == snapshot :" + (order == snapshot));
  }
}
