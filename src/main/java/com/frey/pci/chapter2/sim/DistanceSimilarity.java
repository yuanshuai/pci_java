package com.frey.pci.chapter2.sim;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * DistanceSimilarity
 *
 * @author Shuai YUAN
 * @date 2016/5/4
 */
public class DistanceSimilarity extends AbstractSimilarity {

  public double getSim(JSONObject prefsJsonObject, String person1, String person2) {
    Map<String, Boolean> commonFlagMap = getCommonElement(prefsJsonObject, person1, person2);
    if (commonFlagMap.size() == 0) {
      return 0;
    }

    JSONObject person1JsonObject = prefsJsonObject.getJSONObject(person1);
    JSONObject person2JsonObject = prefsJsonObject.getJSONObject(person2);

    float sumOfSquares = 0;
    for (String key : commonFlagMap.keySet()) {
      float person1Value = person1JsonObject.getFloatValue(key);
      float person2Value = person2JsonObject.getFloatValue(key);
      sumOfSquares += Math.pow((person2Value - person1Value), 2);
    }

    return 1 / (Math.sqrt(sumOfSquares) + 1);
  }

}
