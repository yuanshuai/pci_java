package com.frey.pci.chapter2.sim;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;

import java.util.Map;

/**
 * AbstractSimilarity
 *
 * @author Shuai YUAN
 * @date 2016/5/4
 */
abstract public class AbstractSimilarity implements Similarity {
  public abstract double getSim(JSONObject prefsJsonObject, String person1, String person2);

  /**
   * 得到一个人的评分总和
   * @param personPrefs
   * @return
   */
  protected float getSumOfPerson(JSONObject personPrefs, Map<String, Boolean> commonKey) {
    float sumScore = 0;
    for (String key : commonKey.keySet()) {
      sumScore += personPrefs.getFloatValue(key);
    }
    return sumScore;
  }

  /**
   * 计算一个人评分的平方和
   * @param personPrefs
   * @return
   */
  protected float getPowSumOfPerson(JSONObject personPrefs, Map<String, Boolean> commonKey) {
    float powSumScore = 0;
    for (String key : commonKey.keySet()) {
      powSumScore += Math.pow(personPrefs.getFloatValue(key), 2);
    }
    return powSumScore;
  }

  /**
   * 得到common的评价列表
   * @param prefsJsonObject
   * @param person1
   * @param person2
   * @return
   */
  protected Map<String, Boolean> getCommonElement(JSONObject prefsJsonObject, String person1, String person2) {
    Map<String, Boolean> commonFlagMap = Maps.newHashMap();
    JSONObject person1JsonObject = prefsJsonObject.getJSONObject(person1);
    JSONObject person2JsonObject = prefsJsonObject.getJSONObject(person2);
    for (String key: person1JsonObject.keySet()) {
      if (person2JsonObject.containsKey(key)) {
        commonFlagMap.put(key, true);
      }
    }
    return commonFlagMap;
  }
}
