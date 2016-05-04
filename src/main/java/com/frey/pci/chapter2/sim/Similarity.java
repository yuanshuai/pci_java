package com.frey.pci.chapter2.sim;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by yuanshuai on 2016/5/4.
 */
public interface Similarity {
  double getSim(JSONObject prefsJsonObject, String person1, String person2);
}
