package activitytest.example.com.coolweather.toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import activitytest.example.com.coolweather.db.CoolWeatherDB;
import activitytest.example.com.coolweather.model.City;
import activitytest.example.com.coolweather.model.County;
import activitytest.example.com.coolweather.model.Index;
import activitytest.example.com.coolweather.model.Province;
import activitytest.example.com.coolweather.model.Results;
import activitytest.example.com.coolweather.model.Status;
import activitytest.example.com.coolweather.service.AutoUpdateService;
import activitytest.example.com.coolweather.R;
import activitytest.example.com.coolweather.activity.ChooseAreaActivity;

import activitytest.example.com.coolweather.service.AutoUpdateService;
import activitytest.example.com.coolweather.util.HttpCallbackListener;
import activitytest.example.com.coolweather.util.HttpUtil;
import activitytest.example.com.coolweather.util.Utility;

/**
 * Created by lyx on 2016/4/4.
 */
public class WeatherFragment extends Fragment implements View.OnClickListener {
    String kk;
     String c;
    String baiduUrl;

    private Button switchCity;
    private Button refreshWeather;
    private LinearLayout weatherInfoLayout;
    private TextView data;
    private TextView wea;
    private TextView temp;
    private TextView wind;
    private TextView pm;
    private TextView cloth;
    private TextView cloth1;
    private TextView sport;
    private TextView sport1;
    private TextView travel;
    private TextView travel1;
    private TextView flu;
    private TextView flu1;
    private TextView city;

    private TextView publishText;

    RequestQueue mQueue;
    StringRequest stringRequest;
    Gson gson;
    String countyName, a;


    private ImageView imageView;
    private ImageView icon;
    private ImageView icon1;
    private ImageView icon2;
    private ImageView icon3;


    TextView fore_date;
    TextView fore_date1;
    TextView fore_date2;
    TextView fore_date3;

    TextView fore_temp;
    TextView fore_temp1;
    TextView fore_temp2;
    TextView fore_temp3;

    TextView  fore_txt ;
    TextView  fore_txt1;
    TextView fore_txt2 ;
    TextView  fore_txt3 ;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewWea = inflater.inflate(R.layout.wea_layout, container, false);

        city = (TextView) viewWea.findViewById(R.id.city_name);
        switchCity = (Button) viewWea.findViewById(R.id.switch_city);
        refreshWeather = (Button) viewWea.findViewById(R.id.refresh_weather);

        publishText = (TextView) viewWea.findViewById(R.id.publish_text);

        data = (TextView) viewWea.findViewById(R.id.data_view);
        wea = (TextView) viewWea.findViewById(R.id.weather_view);
        temp = (TextView) viewWea.findViewById(R.id.temp_view);
        wind = (TextView) viewWea.findViewById(R.id.wind_view);
        pm = (TextView) viewWea.findViewById(R.id.pm_view);

        cloth = (TextView) viewWea.findViewById(R.id.cloth_brief);
        cloth1 = (TextView) viewWea.findViewById(R.id.cloth_txt);
        sport = (TextView) viewWea.findViewById(R.id.sport_brief);
        sport1 = (TextView) viewWea.findViewById(R.id.sport_txt);
        travel = (TextView) viewWea.findViewById(R.id.travel_brief);
        travel1 = (TextView) viewWea.findViewById(R.id.travel_txt);
        flu = (TextView) viewWea.findViewById(R.id.flu_brief);
        flu1 = (TextView) viewWea.findViewById(R.id.flu_txt);
        imageView = (ImageView) viewWea.findViewById(R.id.weather1);

        icon=(ImageView)viewWea.findViewById(R.id.forecast_icon);
         icon1=(ImageView)viewWea.findViewById(R.id.forecast_icon1);
         icon2=(ImageView)viewWea.findViewById(R.id.forecast_icon2);
        icon3=(ImageView)viewWea.findViewById(R.id.forecast_icon3);

         fore_date=(TextView) viewWea.findViewById(R.id.forecast_date);
         fore_date1=(TextView) viewWea.findViewById(R.id.forecast_date1);
        fore_date2=(TextView) viewWea.findViewById(R.id.forecast_date2);
         fore_date3=(TextView) viewWea.findViewById(R.id.forecast_date3);

        fore_temp=(TextView) viewWea.findViewById(R.id.forecast_temp);
         fore_temp1=(TextView) viewWea.findViewById(R.id.forecast_temp1);
        fore_temp2=(TextView) viewWea.findViewById(R.id.forecast_temp2);
         fore_temp3=(TextView) viewWea.findViewById(R.id.forecast_temp3);

       fore_txt =(TextView) viewWea.findViewById(R.id.forecast_txt);
         fore_txt1 =(TextView) viewWea.findViewById(R.id.forecast_txt1);
         fore_txt2  =(TextView) viewWea.findViewById(R.id.forecast_txt2);
         fore_txt3 =(TextView) viewWea.findViewById(R.id.forecast_txt3);




        return viewWea;


    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);



        countyName=getActivity() .getIntent() .getStringExtra("county_name");


      String b = countyName;

        a=b;


        gson=new

                Gson();

        mQueue=Volley.newRequestQueue(

                getActivity()

        );




        baiduUrl = "http://api.map.baidu.com/telematics/v3/weather?location=" + a + "&output=json&ak=6tYzTvGZSOpYB5Oc2YGGOKt8";



        quest(baiduUrl);


        switchCity.setOnClickListener(this);
        refreshWeather.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.switch_city:
                Intent intent = new Intent(getActivity(), ChooseAreaActivity.class);
                intent.putExtra("from_weather_activity", true);
                startActivity(intent);
                getActivity().finish();
                break;
            case R.id.refresh_weather:
                publishText.setText("同步中");


                SharedPreferences editor1 =MainActivity.mAppContext .getSharedPreferences("setting", Context.MODE_PRIVATE);



              String k=  editor1.getString("city", "");
                c="http://api.map.baidu.com/telematics/v3/weather?location=" + k + "&output=json&ak=6tYzTvGZSOpYB5Oc2YGGOKt8";


                quest(c);



                System.out.print("lalalalalalalala"+c);


                break;
            default:
                break;
        }
    }



    public  void quest(String p) {



    stringRequest=new

    StringRequest(p, new Response.Listener<String>() {
        @Override
        public void onResponse ( final String response){
            Log.d("TAG", response);
            System.out.println("response=" + response);
            saved(response);

            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {


                    publishText.setText("成功");
                    showWeather(response);
                    publishText.setText("");


                }
            });
        }

    }

    ,
            new Response.ErrorListener()

    {
        @Override
        public void onErrorResponse (VolleyError error){
        publishText.setText("同步失败");
        Log.e("TAG", error.getMessage(), error);
    }

    }

    );


    mQueue.add(stringRequest);
}

    public void saved( String response){


        SharedPreferences editor1 =MainActivity.mAppContext .getSharedPreferences("setting", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=editor1.edit();



try {
    Status status = gson.fromJson(response, Status.class);
    String city = status.getResults().get(0).getCurrentCity();
    System.out.print(      "bbbb"+  city );
      editor.putString("city",city);

    String Tem = status.getResults().get(0).getWeather_data().get(0).getTemperature();
    String Wea = status.getResults().get(0).getWeather_data().get(0).getWeather();
    String Wind = status.getResults().get(0).getWeather_data().get(0).getWind();
    String Pm = status.getResults().get(0).getPm25();
    String Date = status.getResults().get(0).getWeather_data().get(0).getDate();
    String Url = status.getResults().get(0).getWeather_data().get(0).getDayPictureUrl();


    String Tem1 = status.getResults().get(0).getWeather_data().get(1).getTemperature();
    String Wea1 = status.getResults().get(0).getWeather_data().get(1).getWeather();
    String Wind1 = status.getResults().get(0).getWeather_data().get(1).getWind();
    String Date1 = status.getResults().get(0).getWeather_data().get(1).getDate();
    String Url1 = status.getResults().get(0).getWeather_data().get(1).getDayPictureUrl();

    String Tem2 = status.getResults().get(0).getWeather_data().get(2).getTemperature();
    String Wea2 = status.getResults().get(0).getWeather_data().get(2).getWeather();
    String Wind2 = status.getResults().get(0).getWeather_data().get(2).getWind();
    String Date2 = status.getResults().get(0).getWeather_data().get(2).getDate();
    String Url2 = status.getResults().get(0).getWeather_data().get(2).getDayPictureUrl();


    String Tem3 = status.getResults().get(0).getWeather_data().get(3).getTemperature();
    String Wea3 = status.getResults().get(0).getWeather_data().get(3).getWeather();
    String Wind3 = status.getResults().get(0).getWeather_data().get(3).getWind();
    String Date3 = status.getResults().get(0).getWeather_data().get(3).getDate();
    String Url3 = status.getResults().get(0).getWeather_data().get(3).getDayPictureUrl();





    try{ if(status.getResults().get(0).getIndex().isEmpty()) {

         editor.putString("Cloth ", ">_<");
         editor.putString(" Cloth1","及时加减衣服，注意着凉!" );
         editor.putString("Sport ",">_< ");
         editor.putString(" Sport1","生命在于运动！");
         editor.putString("Travel ", ">_<");
         editor.putString(" Travel1","旅游洗涤心灵!" );
         editor.putString(" Flu", ">_<");
         editor.putString("Flu1 ", "今天没吃药，感觉萌萌哒!");




      }else{




         String Cloth = status.getResults().get(0).getIndex().get(0).getZs();
         String Cloth1 = status.getResults().get(0).getIndex().get(0).getDes();
         String Sport = status.getResults().get(0).getIndex().get(4).getZs();
         String Sport1 = status.getResults().get(0).getIndex().get(4).getDes();
         String Travel = status.getResults().get(0).getIndex().get(2).getZs();
         String Travel1 = status.getResults().get(0).getIndex().get(2).getDes();
         String Flu = status.getResults().get(0).getIndex().get(3).getZs();
         String Flu1 = status.getResults().get(0).getIndex().get(3).getDes();


         editor.putString("Cloth ", Cloth);
         editor.putString(" Cloth1", Cloth1);
         editor.putString("Sport ", Sport);
         editor.putString(" Sport1", Sport1);
         editor.putString("Travel ", Travel);
         editor.putString(" Travel1", Travel1);
         editor.putString(" Flu", Flu);
         editor.putString("Flu1 ", Flu1);


     }}catch (NullPointerException e){
         e.printStackTrace();


     }




    editor.putString("Tem", Tem);
    editor.putString("Wea", Wea);
    editor.putString("Wind", Wind);
    editor.putString("Pm", Pm);
    editor.putString("Date", Date);


    editor.putString("Tem1", Tem1);
    editor.putString("Wea1", Wea1);
    editor.putString("Wind1", Wind1);
    editor.putString("Date1", Date1);


    editor.putString("Tem2", Tem2);
    editor.putString("Wea2", Wea2);
    editor.putString("Wind2", Wind2);
    editor.putString("Date2", Date2);


    editor.putString("Tem3", Tem3);
    editor.putString("Wea3", Wea3);
    editor.putString("Wind3", Wind3);
    editor.putString("Date3", Date3);

    editor.commit();

    loadPictures(Url,imageView);
    loadPictures(Url,icon);
    loadPictures(Url1,icon1);
    loadPictures(Url2,icon2);
    loadPictures(Url3,icon3);



}catch (NullPointerException e){e.printStackTrace();}catch ( IndexOutOfBoundsException e){e.printStackTrace();}

    }
    public void loadPictures(String Url,final ImageView imageView) {


            ImageRequest imageRequest = new ImageRequest(
                    Url,
                    new Response.Listener<Bitmap>() {
                        @Override
                        public void onResponse(Bitmap response) {
                            imageView.setImageBitmap(response);

                        }
                    }, 60, 60, Bitmap.Config.RGB_565, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    imageView.setImageResource(R.drawable.bi);
                }
            });
            mQueue.add(imageRequest);


    }





    private void showWeather(String response){


        Status status = gson.fromJson(response, Status.class);



            /**
             * 将服务器返回的所有天气信息存储到SharedPreferences文件中。
             */
            try {

                SharedPreferences editor1 =MainActivity.mAppContext .getSharedPreferences("setting", Context.MODE_PRIVATE);

                city.setText(editor1.getString("city", "") );
                    System.out.print(      "bbbb"+  editor1.getString("city", "")  );

        temp.setText( editor1.getString("Tem", ""));
                 wea.setText(editor1.getString("Wea", ""));
                wind.setText(editor1.getString("Wind", ""));
               pm.setText( editor1.getString("Pm","" ));
               data.setText( editor1.getString("Date", ""));

                cloth.setText( "穿衣指数---"+editor1.getString("Cloth ", ""));
               cloth1.setText( editor1.getString(" Cloth1", ""));
               sport. setText( "运动指数---"+ editor1.getString("Sport ", ""));
               sport1. setText( editor1.getString(" Sport1", ""));
               travel. setText( "旅游指数---"+editor1.getString("Travel ", ""));
               travel1.setText(editor1.getString(" Travel1", ""));
              flu.  setText("感冒指数---"+ editor1.getString(" Flu",""));
                flu1.setText(editor1.getString("Flu1 ", ""));


                fore_date.setText(  editor1.getString("Date", ""));
                fore_date1.setText(  editor1.getString("Date1", ""));
                fore_date2.setText(  editor1.getString("Date2", ""));
                fore_date3.setText(  editor1.getString("Date3", ""));

                fore_temp.setText(editor1.getString("Wea", ""));
                fore_temp1.setText(editor1.getString("Wea1", ""));
                fore_temp2.setText(editor1.getString("Wea2", ""));
                fore_temp3.setText(editor1.getString("Wea3", ""));

                fore_txt.setText("温度："+editor1.getString("Tem", "" )+"风向："+editor1.getString("Wind", ""));
                fore_txt1.setText("温度："+editor1.getString("Tem1", "" )+"风向："+editor1.getString("Wind1", ""));
                fore_txt2.setText("温度："+editor1.getString("Tem2", "" )+"风向："+editor1.getString("Wind2", ""));
                fore_txt3.setText("温度："+editor1.getString("Tem3", "" )+"风向："+editor1.getString("Wind3", ""));






            Intent intent = new Intent(getActivity(), AutoUpdateService.class);
            getActivity().startService(intent);

    }catch (NullPointerException e){e.printStackTrace();}
    }

}