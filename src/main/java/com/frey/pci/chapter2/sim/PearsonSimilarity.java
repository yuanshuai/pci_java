package com.frey.pci.chapter2.sim;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * DistanceSimilarity
 *
 * @author Shuai YUAN
 * @date 2016/5/4
 */
public class PearsonSimilarity extends AbstractSimilarity {
  public double getSim(JSONObject prefsJsonObject, String person1, String person2) {

    Map<String, Boolean> commonFlagMap = getCommonElement(prefsJsonObject, person1, person2);
    if (commonFlagMap.size() == 0) {
      return 1;
    }

    JSONObject person1JsonObject = prefsJsonObject.getJSONObject(person1);
    JSONObject person2JsonObject = prefsJsonObject.getJSONObject(person2);

    // 分数之和
    double sumScorePerson1 = getSumOfPerson(person1JsonObject, commonFlagMap);
    double sumScorePerson2 = getSumOfPerson(person2JsonObject, commonFlagMap);

    // 平方和
    double powSumScorePerson1 = getPowSumOfPerson(person1JsonObject, commonFlagMap);
    double powSumScorePerson2 = getPowSumOfPerson(person2JsonObject, commonFlagMap);

    // 乘积之和
    double pSum = 0;
    for (String key : commonFlagMap.keySet()) {
      pSum += (person1JsonObject.getFloatValue(key) * person2JsonObject.getFloatValue(key));
    }

    // 计算皮尔逊评价值
    int commonSize = commonFlagMap.size();
    double num = pSum - (sumScorePerson1 * sumScorePerson2 / commonSize);
    double den = Math.sqrt(
        (powSumScorePerson1 - Math.pow(sumScorePerson1, 2)/commonSize)
            *
            (powSumScorePerson2 - Math.pow(sumScorePerson2, 2)/commonSize)
    );

    if (den == 0) {
      return 0;
    }
    double r = num / den;

    return r;
  }
}
