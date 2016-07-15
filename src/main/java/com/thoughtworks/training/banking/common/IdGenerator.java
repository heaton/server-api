package com.thoughtworks.training.banking.common;

import java.util.concurrent.atomic.AtomicLong;

public class IdGenerator {

  private AtomicLong atomicLong = new AtomicLong();

  public long nextId() {
    return atomicLong.incrementAndGet();
  }

}
