import com.taobao.ddd.third.domain.entity.LineItem;
import com.taobao.ddd.third.domain.entity.Order;
import com.taobao.ddd.third.repository.diff.EntityDiff;
import com.taobao.ddd.third.type.OrderId;
import com.taobao.ddd.third.util.DiffUtils;
import com.taobao.ddd.third.util.SnapshotUtils;
import org.junit.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DiffUtilsTest {
  @Test
  public void diff() throws IllegalAccessException, IOException, ClassNotFoundException {
    // 实时对象
    Order realObj = new Order();
    realObj.setId(new OrderId(1L));
    realObj.setTitle("old title");

    // 快照对象
    Order snapshotObj = SnapshotUtils.snapshot(realObj);
    snapshotObj.setId(new OrderId(2L));
    snapshotObj.setTitle("old title");

    EntityDiff diff = DiffUtils.diff(realObj, snapshotObj);
    assert diff.isSelfModified() == true;
    assert diff.getDiffs().size() == 1;
    System.out.println("========非集合类型 Diff 校验通过========");

    List<LineItem> lineItems = new ArrayList<>();
    lineItems.add(new LineItem(new OrderId(11L), new BigDecimal(111)));
    lineItems.add(new LineItem(new OrderId(22L), new BigDecimal(222)));
    realObj.setLineItems(lineItems);

    snapshotObj = SnapshotUtils.snapshot(realObj);
    realObj.getLineItems().get(0).setMoney(new BigDecimal(123));

    diff = DiffUtils.diff(snapshotObj, realObj);

    assert diff.isSelfModified() == true;
    assert diff.getDiffs().size() == 1;
    System.out.println("========集合类型 Diff 校验通过========");
  }
}
