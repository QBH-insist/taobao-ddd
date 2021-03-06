package com.taobao.ddd.third.util;

import com.taobao.ddd.third.type.mark.Aggregate;

import java.io.*;

public class SnapshotUtils {

  public static <T extends Aggregate> T snapshot(T aggregate)
      throws IOException, ClassNotFoundException {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    ObjectOutputStream oos = new ObjectOutputStream(bos);
    oos.writeObject(aggregate);

    ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
    ObjectInputStream ois = new ObjectInputStream(bis);
    return (T) ois.readObject();
  }
}
