import com.taobao.ddd.second.domain.entity.Order;
import com.taobao.ddd.second.repository.diff.EntityDiff;
import com.taobao.ddd.second.type.OrderId;
import com.taobao.ddd.second.util.DiffUtils;
import org.junit.Assert;
import org.junit.Test;

public class DiffUtilsTest {
  @Test
  public void diff() throws IllegalAccessException {
    Order oldObj = new Order();
    oldObj.setId(new OrderId(1L));

    oldObj.setTitle("old title");

    Order newObj = new Order();
    newObj.setId(new OrderId(2L));
    newObj.setTitle("old title");

    EntityDiff diff = DiffUtils.diff(oldObj, newObj);
    assert diff.isSelfModified() == true;
    System.out.println("校验通过");
  }
}
