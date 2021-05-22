import com.taobao.ddd.second.domain.entity.Order;
import com.taobao.ddd.second.type.OrderId;
import com.taobao.ddd.second.util.ReflectionUtils;
import org.junit.Test;

public class ReflectionUtilsTest {
  @Test
  public void testWriteField() {
    Order order = new Order();
    order.setId(new OrderId(1L));
    System.out.println("before:" + order.getId() + "  " + order.getId().getValue());

    System.out.println("----------------------------");

    ReflectionUtils.writeField("id", order, new OrderId(2L));
    System.out.println("after:" + order.getId() + "  " + order.getId().getValue());
  }
}
