package com.moonstudio.weather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.moonstudio.weather.adapter.CityListAdatper;
import com.thinkland.juheapi.common.JsonCallBack;
import com.thinkland.juheapi.data.weather.WeatherData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Michael on 2015/7/7.
 */
public class CityActivty extends Activity {
    ListView lv_city;
    private List<String> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_city);
        initView();
        getCities();
    }

    public void initView() {
        findViewById(R.id.iv_back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        lv_city = (ListView) findViewById(R.id.lv_city);
    }

    private void getCities() {
        WeatherData data = WeatherData.getInstance();
        data.getCities(new JsonCallBack() {

            @Override
            public void jsonLoaded(JSONObject json) {
                // TODO Auto-generated method stub
                try {
                    int code = json.getInt("resultcode");
                    int error_code = json.getInt("error_code");
                    if (error_code == 0 && code == 200) {

                        list = new ArrayList<String>();
//                        JSONArray resultArray = json.getJSONArray("result");
                        JSONArray resultArray = new JSONArray(new String("[{\"district\":\"上海\",\"province\":\"上海\",\"id\":\"16\",\"city\":\"上海\"},{\"district\":\"闵行\",\"province\":\"上海\",\"id\":\"17\",\"city\":\"上海\"},{\"district\":\"宝山\",\"province\":\"上海\",\"id\":\"18\",\"city\":\"上海\"},{\"district\":\"嘉定\",\"province\":\"上海\",\"id\":\"19\",\"city\":\"上海\"},{\"district\":\"南汇\",\"province\":\"上海\",\"id\":\"20\",\"city\":\"上海\"},{\"district\":\"金山\",\"province\":\"上海\",\"id\":\"21\",\"city\":\"上海\"},{\"district\":\"青浦\",\"province\":\"上海\",\"id\":\"22\",\"city\":\"上海\"},{\"district\":\"松江\",\"province\":\"上海\",\"id\":\"23\",\"city\":\"上海\"},{\"district\":\"奉贤\",\"province\":\"上海\",\"id\":\"24\",\"city\":\"上海\"},{\"district\":\"崇明\",\"province\":\"上海\",\"id\":\"25\",\"city\":\"上海\"},{\"district\":\"徐家汇\",\"province\":\"上海\",\"id\":\"26\",\"city\":\"上海\"},{\"district\":\"浦东\",\"province\":\"上海\",\"id\":\"27\",\"city\":\"上海\"},\n" +
                                "{\"district\":\"大庆\",\"province\":\"黑龙江\",\"id\":\"27\",\"city\":\"黑龙江\"},\n" +
                                "{\"district\":\"哈尔滨\",\"province\":\"黑龙江\",\"id\":\"27\",\"city\":\"黑龙江\"},{\"district\":\"齐齐哈尔\",\"province\":\"黑龙江\",\"id\":\"27\",\"city\":\"黑龙江\"}\n" +
                                "]"));
//                        System.out.println("返回的城市数据"+resultArray.toString());
                        Set<String> citySet = new HashSet<String>();
                        for (int i = 0; i < resultArray.length(); i++) {
                            String city = resultArray.getJSONObject(i).getString("district");
                            citySet.add(city);
                        }
                        list.addAll(citySet);
                        CityListAdatper adatper = new CityListAdatper(CityActivty.this, list);
                        lv_city.setAdapter(adatper);
                        lv_city.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                            @Override
                            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                                // TODO Auto-generated method stub
                                Intent intent = new Intent();
                                intent.putExtra("city", list.get(arg2));
                                setResult(1, intent);
                                finish();
                            }
                        });

                    }
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        });
    }
}
