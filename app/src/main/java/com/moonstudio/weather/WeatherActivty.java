package com.moonstudio.weather;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.moonstudio.weather.bean.Future;
import com.moonstudio.weather.bean.HoursWeatherBean;
import com.moonstudio.weather.bean.Sk;
import com.moonstudio.weather.bean.Today;
import com.moonstudio.weather.service.WeatherService;
import com.moonstudio.weather.swiperefresh.PullToRefreshBase;
import com.moonstudio.weather.swiperefresh.PullToRefreshScrollView;
import com.thinkland.juheapi.common.JsonCallBack;
import com.thinkland.juheapi.data.air.AirData;
import com.thinkland.juheapi.data.weather.WeatherData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

//import android.widget.RelativeLayout;


public class WeatherActivty extends Activity {
    private PullToRefreshScrollView mPullToRefreshScrollView;
    private ScrollView mScrollView;
    private TextView tv_city,// 城市
            tv_release,// 发布时间
            tv_now_weather,// 今日天气
            tv_now_today,// 今日温度范围
            tv_now_temp,// 目前实时温度
            tv_aqi,// PM2.5指数
            tv_quality,// 污染程度
            tv_next_three,// 下三小时温度
			tv_next_six,// 下六小时温度
			tv_next_nine,// 下九小时温度
			tv_next_twelve,// 下十二小时温度
			tv_next_fifteen,// 下十五小时温度
            tv_next_three_temp,// 下三小时温度
            tv_next_six_temp,// 下六小时温度
            tv_next_nine_temp,// 下九小时温度
            tv_next_twelve_temp,// 下十二小时温度
            tv_next_fifteen_temp,// 下十五小时温度
			today_high,// 今天最高温度
			today_low,// 今天最低温度
			tomorrow_low,// 明天最低温度
			tomorrow_high,// 明天最高温度
    tv_felt_air_temp,// 体感温度
            humidity,// 湿度
            tv_wind,// 风力
            tv_uv_index,// 紫外线强度
            tv_dressing_index,// 穿衣指数
            tv_dressing_advice;//穿衣建议
    private ImageView iv_now_weather,// 现在
            iv_next_three,// 3小时
			iv_next_six,// 6小时
			iv_next_nine,// 9小时
			iv_next_twelve,// 12小时
			iv_next_fifteen,// 15小时
			iv_today_weather,// 今天
			iv_tommorrow_weather,// 明天
			iv_thirdday_weather,// 第三天
			iv_fourthday_weather;// 第四天
	private RelativeLayout rl_city;
    private Context mContext;
    private int count = 0;
    private boolean isRunning = false;
    private String city = "上海";
    private List<HoursWeatherBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activty_weather);
        mContext = this;
        getCityWeather(city);
        init();

        initService();
    }

    private void initService() {
        Intent intent = new Intent(mContext, WeatherService.class);
        startService(intent);
        bindService(intent, conn, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName arg0, IBinder arg1) {
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == 1 && resultCode == 1) {
            city = data.getStringExtra("city").toString();
            tv_city.setText(city);
            getCityWeather(city);
        }

    }


    /**
     * 初始化获取城市天气a
     */
    public void getCityWeather(String city) {
        if (isRunning == true) {
            return;
        }
        isRunning = false;
        count = 0;
        WeatherData date = WeatherData.getInstance();
        AirData air = AirData.getInstance();
        /**
         *  未来间隔三小时天气
         */
//        date.getForecast3h("哈尔滨", new JsonCallBack() {
//            @Override
//            public void jsonLoaded(JSONObject json) {
//                list = parserForecast3h(json);
//                if (list != null && list.size() >= 5) {
//                    setHourViews(list);
//                }
//                count++;
//                if (count >= 3) {
//                    mPullToRefreshScrollView.onRefreshComplete();
//                    isRunning = false;
//                }
//                System.out.println("未来三小时" + json.toString());
//            }
//        });

        air.cityAir(city, new JsonCallBack() {

            @Override
            public void jsonLoaded(JSONObject jsonObject) {
                count++;
                try {
                    JSONArray resultArray = jsonObject.getJSONArray("result");

                    JSONObject resObj = (JSONObject) resultArray.get(0);
                    JSONObject cityNow = resObj.getJSONObject("citynow");
                    String pm = cityNow.getString("AQI");
                    String quality = cityNow.getString("quality");

                    if (pm != null && quality != null) {
                        tv_aqi.setText(pm);
                        tv_quality.setText(quality);
                        if (count >= 2) {
                            mPullToRefreshScrollView.onRefreshComplete();
                            isRunning = false;
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "网络有点小忙", Toast.LENGTH_SHORT).show();
                        if (count >= 2) {
                            mPullToRefreshScrollView.onRefreshComplete();
                            isRunning = false;
                        }
                    }

                } catch (Exception e) {
                    mPullToRefreshScrollView.onRefreshComplete();
                    isRunning = false;
                    e.printStackTrace();
                }
            }
        });

        date.getByCitys(city, 2, new JsonCallBack() {

            @Override
            public void jsonLoaded(JSONObject arg0) {
                count++;
                try {
                    int errorCode = arg0.getInt("error_code");
                    if (errorCode != 200) {
                        Today today = parserWeather(arg0);
                        setView(today);
                        if (count >= 2) {
                            mPullToRefreshScrollView.onRefreshComplete();
                            isRunning = true;
                        }
                    } else {
                        Today today = new Today();
                        setView(today);
                        mPullToRefreshScrollView.onRefreshComplete();
                        Toast.makeText(getApplicationContext(), "网络有点小忙，稍后再试试", Toast.LENGTH_SHORT).show();
                        if (count >= 2) {
                            mPullToRefreshScrollView.onRefreshComplete();
                            isRunning = false;
                        }
                    }
                    count++;
                } catch (Exception e) {
                    mPullToRefreshScrollView.onRefreshComplete();
                    isRunning = false;
                    e.printStackTrace();
                }
            }

        });
    }

    private void setHourViews(List<HoursWeatherBean> list) {
        setHourData(tv_next_three, iv_next_three, tv_next_three_temp, list.get(0));
        setHourData(tv_next_six, iv_next_six, tv_next_six_temp, list.get(1));
        setHourData(tv_next_nine, iv_next_nine, tv_next_nine_temp, list.get(2));
        setHourData(tv_next_twelve, iv_next_twelve, tv_next_twelve_temp, list.get(3));
        setHourData(tv_next_fifteen, iv_next_fifteen, tv_next_fifteen_temp, list.get(4));
    }

    private void setHourData(TextView tv_hour, ImageView iv_weather, TextView tv_temp, HoursWeatherBean bean) {

        String prefixStr = null;
        int time = Integer.valueOf(bean.getTime());
        if (time >= 6 && time < 18) {
            prefixStr = "d";
        } else {
            prefixStr = "n";
        }

        tv_hour.setText(bean.getTime() + "时");
        iv_weather.setImageResource(getResources().getIdentifier(prefixStr + bean.getWeather_id(), "drawable", "com.juhe.weather"));
        tv_temp.setText(bean.getTemp() + "°");
    }
    /**
     * 解析Json到实体类
     *
     * @param json
     */
    private Today parserWeather(JSONObject json) {
        Today today = new Today();
        try {
            int errorcode = json.getInt("error_code");
            int resultcode = json.getInt("resultcode");
            if (errorcode == 0 && resultcode == 200) {
                JSONObject resultJson = json.getJSONObject("result");
                JSONObject todayJson = resultJson.getJSONObject("today");
                JSONObject skJson = resultJson.getJSONObject("sk");
                JSONObject weatherJson = todayJson.getJSONObject("weather_id");
                JSONArray fuJson = resultJson.getJSONArray("future");

                today.setWind(todayJson.getString("wind"));
                today.setUvIndex(todayJson.getString("uv_index"));
                today.setTravelIndex(todayJson.getString("travel_index"));
                today.setCity(todayJson.getString("city"));
                today.setTemperature(todayJson.getString("temperature"));
                today.setComfortIndex(todayJson.getString("comfort_index"));
                today.setDateY(todayJson.getString("date_y"));
                today.setWashIndex(todayJson.getString("wash_index"));
                today.setExerciseIndex(todayJson.getString("exercise_index"));
                today.setWeather(todayJson.getString("weather"));
                today.setDryingIndex(todayJson.getString("drying_index"));
                today.setWeatherIdFa(weatherJson.getString("fa"));
                today.setWeatherIdFb(weatherJson.getString("fb"));
                today.setDressingAdvice(todayJson.getString("dressing_advice"));
                today.setDressingIndex(todayJson.getString("dressing_index"));
                today.setWeek(todayJson.getString("week"));
                Sk sk = new Sk();
                sk.setWindStrength(skJson.getString("wind_strength"));
                sk.setTemp(skJson.getString("temp"));
                sk.setTime(skJson.getString("time"));
                sk.setHumidity(skJson.getString("humidity"));
                sk.setWindDirection(skJson.getString("wind_direction"));
                today.setSk(sk);
                List<Future> fuList = new ArrayList<Future>();
                for (int i = 0; i < fuJson.length(); i++) {
                    Future future = new Future();
                    JSONObject oj = fuJson.getJSONObject(i);
                    JSONObject wJson = oj.getJSONObject("weather_id");
                    future.setWind(oj.getString("wind"));
                    future.setWeather(oj.getString("weather"));
                    future.setDate(oj.getString("date"));
                    future.setWeek(oj.getString("week"));
                    future.setTemperature(oj.getString("temperature"));
                    future.setWeatherIdFa(wJson.getString("fa"));
                    future.setWeatherIdFb(wJson.getString("fb"));
                    fuList.add(future);
                }
                today.setFutureList(fuList);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return today;
    }

    // 解析3小时预报
    private List<HoursWeatherBean> parserForecast3h(JSONObject json) {
        List<HoursWeatherBean> list = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
        Date date = new Date(System.currentTimeMillis());
        try {
            int code = json.getInt("resultcode");
            int error_code = json.getInt("error_code");
            if (error_code == 0 && code == 200) {
                list = new ArrayList<HoursWeatherBean>();
                JSONArray resultArray = json.getJSONArray("result");
                for (int i = 0; i < resultArray.length(); i++) {
                    JSONObject hourJson = resultArray.getJSONObject(i);
                    Date hDate = sdf.parse(hourJson.getString("sfdate"));
                    if (!hDate.after(date)) {
                        continue;
                    }
                    HoursWeatherBean bean = new HoursWeatherBean();
                    bean.setWeather_id(hourJson.getString("weatherid"));
                    bean.setTemp(hourJson.getString("temp1"));
                    Calendar c = Calendar.getInstance();
                    c.setTime(hDate);
                    bean.setTime(c.get(Calendar.HOUR_OF_DAY) + "");
                    list.add(bean);
                    if (list.size() == 5) {
                        break;
                    }
                }

            } else {
                Toast.makeText(getApplicationContext(), "HOURS_ERROR", Toast.LENGTH_SHORT).show();
            }
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return list;

    }

    /**
     * 初始化界面
     *
     * @param today 今日数据json
     */
    public void setView(Today today) {
        if (today == null) {
            Toast.makeText(getApplicationContext(), "网络有点小忙，稍后再试试吧", Toast.LENGTH_SHORT).show();
            return;
        }
        tv_release.setText(today.getSk().getTime());
        tv_now_weather.setText(today.getWeather());
        iv_now_weather.setImageResource(getResources().getIdentifier("d" + today.getWeatherIdFa(), "drawable", "com.moonstudio.weather"));

        tv_now_today.setText(today.getTemperature());
        tv_now_temp.setText(today.getSk().getTemp() + "°");
        tv_felt_air_temp.setText(today.getSk().getTemp() + "°");
        humidity.setText(today.getSk().getHumidity());
        tv_dressing_index.setText(today.getDressingIndex());
        tv_uv_index.setText(today.getUvIndex());
        tv_dressing_advice.setText(today.getDressingAdvice());
        tv_wind.setText(today.getWind());
    }


    private void init() {
        mPullToRefreshScrollView = (PullToRefreshScrollView) findViewById(R.id.pull_refresh_scollview);
        mPullToRefreshScrollView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener<ScrollView>() {
            @Override
            public void onRefresh(PullToRefreshBase<ScrollView> refreshView) {
                getCityWeather(city);
            }
        });
        mScrollView = mPullToRefreshScrollView.getRefreshableView();
        tv_city = (TextView) findViewById(R.id.tv_city);
        tv_release = (TextView) findViewById(R.id.tv_release);
        tv_now_weather = (TextView) findViewById(R.id.tv_now_weather);
        iv_now_weather = (ImageView) findViewById(R.id.iv_now_weather);
        tv_now_today = (TextView) findViewById(R.id.tv_now_today);
        tv_now_temp = (TextView) findViewById(R.id.tv_now_temp);
        tv_felt_air_temp = (TextView) findViewById(R.id.tv_felt_air_temp);
        humidity = (TextView) findViewById(R.id.humidity);
        tv_dressing_index = (TextView) findViewById(R.id.tv_dressing_index);
        tv_uv_index = (TextView) findViewById(R.id.tv_uv_index);
        tv_wind = (TextView) findViewById(R.id.tv_wind);
        tv_aqi = (TextView) findViewById(R.id.tv_aqi);
        tv_quality = (TextView) findViewById(R.id.tv_quality);
        tv_dressing_advice = (TextView) findViewById(R.id.tv_dressing_advice);
//        tv_next_three = (TextView) findViewById(R.id.tv_next_three);
//        tv_next_six = (TextView) findViewById(R.id.tv_next_six);
//        tv_next_nine = (TextView) findViewById(R.id.tv_next_nine);
//        tv_next_twelve = (TextView) findViewById(R.id.tv_next_twelve);
//        tv_next_fifteen = (TextView) findViewById(R.id.tv_next_fifteen);
//        tv_next_three_temp = (TextView) findViewById(R.id.tv_next_three_temp);
//        tv_next_six_temp = (TextView) findViewById(R.id.tv_next_six_temp);
//        tv_next_nine_temp = (TextView) findViewById(R.id.tv_next_nine_temp);
//        tv_next_twelve_temp = (TextView) findViewById(R.id.tv_next_twelve_temp);
//        tv_next_fifteen_temp = (TextView) findViewById(R.id.tv_next_fifteen_temp);
        tv_city.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent i = new Intent(WeatherActivty.this, CityActivty.class);
                startActivityForResult(new Intent(mContext, CityActivty.class), 1);
                //startActivity(i);
            }
        });

    }

    @Override
    protected void onDestroy() {
        unbindService(conn);
        super.onDestroy();
    }
}
