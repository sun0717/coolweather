package com.sun.coolweather.util;

import android.text.TextUtils;

import com.sun.coolweather.db.City;
import com.sun.coolweather.db.County;
import com.sun.coolweather.db.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility {
    public static boolean handleProvinceResponse(String response) {
        //    解析和处理服务器返回的省级数据
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allPronvices = new JSONArray(response) ;
                for (int i = 0 ; i < allPronvices.length() ;i++) {
                    JSONObject provinceObject = allPronvices.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleCityResponse(String response, int provinceId) {
        //    解析和处理服务器返回的市级数据
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCities = new JSONArray(response) ;
                for (int i = 0 ; i < allCities.length() ;i++) {
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleCountyResponse(String response, int cityId) {
        //    解析和处理服务器返回的县级数据
        if (!TextUtils.isEmpty(response)) {
            try {
                JSONArray allCounties = new JSONArray(response) ;
                for (int i = 0 ; i < allCounties.length() ;i++) {
                    JSONObject countyObject = allCounties.getJSONObject(i);
                    County county = new County();
                   county.setCountryName(countyObject.getString("name"));
                   county.setWeatherId(countyObject.getString("weather_id"));
                   county.setCityId(cityId);
                   county.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
