package com.frey.pci.chapter2;

import com.alibaba.fastjson.JSONObject;
import com.frey.pci.chapter2.sim.DistanceSimilarity;
import com.frey.pci.chapter2.sim.PearsonSimilarity;
import com.frey.pci.chapter2.sim.Similarity;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.*;

/**
 * Main
 *
 * @author Shuai YUAN
 * @date 2016/5/4
 */
public class Main {
  public static void main(String[] args) {

    Similarity distanceSim = new DistanceSimilarity();

    // 欧几里得距离
    double simDistanceValue = distanceSim.getSim(Constants.criticsJsonObject, "Lisa Rose", "Gene Seymour");
    System.out.println("sim distance value of Lisa Rose and Gene Seymour: " + simDistanceValue);

    // 皮尔逊距离
    Similarity pearsonSim = new PearsonSimilarity();
    double simPearsonValue = pearsonSim.getSim(Constants.criticsJsonObject, "Lisa Rose",
        "Gene Seymour");
    System.out.println("sim pearson value of Lisa Rose and Gene Seymour: " + simPearsonValue);

    // 按照相似度降序排列

//    List<Tuple<String, Double>> topMatchesMap = topMatches(Constants.criticsJsonObject, "Toby", 3, pearsonSim);
//    System.out.println(JSONObject.toJSONString(topMatchesMap));

    List recommandationMap = getRecommandations(Constants.criticsJsonObject, "Toby", pearsonSim);
    System.out.println(JSONObject.toJSONString(recommandationMap));


  }


  public static List<Tuple<String,Double>> getRecommandations(JSONObject prefs, String person, Similarity similarity) {

    Map<String, Double> scoreSumMap = Maps.newHashMap();
    Map<String, Double> simSumMap = Maps.newHashMap();

    for (String key : prefs.keySet()) {
      if (person.equals(key)) { // 排除掉自己
        continue;
      }
      // 得到两人相似度
      double simValue = similarity.getSim(prefs, person, key);

      // 相似度大于0才进行后续操作
      if (!(simValue > 0)) {
        continue;
      }
      JSONObject personPrefsJsonObject = prefs.getJSONObject(key);

      for (String key2 : personPrefsJsonObject.keySet()) {

        // 物品评分
        double score = personPrefsJsonObject.getDoubleValue(key2);
        // 相似度 * 物品评分
        double s_simValue = simValue * score;

        // 加权评分之和
        if(scoreSumMap.get(key2) == null) {
          scoreSumMap.put(key2, 0.0);
        }
        scoreSumMap.put(key2, s_simValue+scoreSumMap.get(key2));

        // 相似度之和
        if (simSumMap.get(key2) == null) {
          simSumMap.put(key2, 0.0);
        }
        simSumMap.put(key2, simValue+simSumMap.get(key2));
      }
    }

    // 计算每个物品的 加权评分之和/相似度之和
    List<Tuple<String,Double>> resList = Lists.newArrayList();
    for (String item : scoreSumMap.keySet()) {
      resList.add(new Tuple<String, Double>(item, scoreSumMap.get(item)/simSumMap.get(item)));
    }

    // 降序排列
    Collections.sort(resList, new Comparator<Tuple<String, Double>>() {
      public int compare(Tuple<String, Double> o1, Tuple<String, Double> o2) {
        return (o1._2 - o2._2)<0 ? 1:-1;
      }
    });
    return resList;
  }


  /**
   * 得到与person相似度最大的n个人以及对应相似度
   * @param prefs
   * @param person
   * @param n
   * @param similarity
   * @return
   */
  public static List<Tuple<String, Double>> topMatches(JSONObject prefs, String person, int n, Similarity similarity) {

    // 计算所有人有参数person的距离
    List<Tuple<String, Double>> simList = Lists.newArrayList();
    for (String key : prefs.keySet()) {
      if (person.equals(key)) {
        continue;
      }
      double simValue = similarity.getSim(prefs, person, key);
      simList.add(new Tuple<String, Double>(key, simValue));
    }

    Collections.sort(simList, new Comparator<Tuple<String, Double>>() {
      public int compare(Tuple<String, Double> o1, Tuple<String, Double> o2) {
        return (o1._2 - o2._2) < 0 ? 1 : -1;
      }
    });

    return simList.subList(0,n);
  }
}

class Tuple<A, B> {
  public final A _1;
  public final B _2;

  public Tuple(A a, B b) {
    this._1 = a;
    this._2 = b;
  }
}

class Tuple3<A, B, C> extends Tuple<A, B>{
  public final C _3;

  public Tuple3(A a, B b, C c) {
    super(a, b);
    this._3 = c;
  }
}

