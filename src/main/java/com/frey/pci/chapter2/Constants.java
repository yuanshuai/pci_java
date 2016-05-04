package com.frey.pci.chapter2;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * Constants
 *
 * @author Shuai YUAN
 * @date 2016/5/4
 */
public class Constants {
  static final String critics = "{'Lisa Rose': {'Lady in the Water': 2.5, 'Snakes on a Plane': 3.5,\n"
      + " 'Just My Luck': 3.0, 'Superman Returns': 3.5, 'You, Me and Dupree': 2.5, \n"
      + " 'The Night Listener': 3.0},\n"
      + "'Gene Seymour': {'Lady in the Water': 3.0, 'Snakes on a Plane': 3.5, \n"
      + " 'Just My Luck': 1.5, 'Superman Returns': 5.0, 'The Night Listener': 3.0, \n"
      + " 'You, Me and Dupree': 3.5}, \n"
      + "'Michael Phillips': {'Lady in the Water': 2.5, 'Snakes on a Plane': 3.0,\n"
      + " 'Superman Returns': 3.5, 'The Night Listener': 4.0},\n"
      + "'Claudia Puig': {'Snakes on a Plane': 3.5, 'Just My Luck': 3.0,\n"
      + " 'The Night Listener': 4.5, 'Superman Returns': 4.0, \n" + " 'You, Me and Dupree': 2.5},\n"
      + "'Mick LaSalle': {'Lady in the Water': 3.0, 'Snakes on a Plane': 4.0, \n"
      + " 'Just My Luck': 2.0, 'Superman Returns': 3.0, 'The Night Listener': 3.0,\n"
      + " 'You, Me and Dupree': 2.0}, \n"
      + "'Jack Matthews': {'Lady in the Water': 3.0, 'Snakes on a Plane': 4.0,\n"
      + " 'The Night Listener': 3.0, 'Superman Returns': 5.0, 'You, Me and Dupree': 3.5},\n"
      + "'Toby': {'Snakes on a Plane':4.5,'You, Me and Dupree':1.0,'Superman Returns':4.0}}";

  static JSONObject criticsJsonObject;
  static {
    criticsJsonObject = JSONObject.parseObject(critics);
  }


}
