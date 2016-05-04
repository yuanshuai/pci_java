package com.frey.pci.commons;

/**
 * Tuple
 *
 * @author Shuai YUAN
 * @date 2016/5/4
 */
public class Tuple<A, B> {
  public final A _1;
  public final B _2;

  public Tuple(A a, B b) {
    this._1 = a;
    this._2 = b;
  }
}
