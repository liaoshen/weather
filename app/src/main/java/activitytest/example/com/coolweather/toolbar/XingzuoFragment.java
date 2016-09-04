package activitytest.example.com.coolweather.toolbar;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import activitytest.example.com.coolweather.R;
import activitytest.example.com.coolweather.loader.Article;
import activitytest.example.com.coolweather.loader.ImageLoader;
import activitytest.example.com.coolweather.loader.Statu;
import activitytest.example.com.coolweather.loader.Status;
import activitytest.example.com.coolweather.util.MyUtils;
import activitytest.example.com.coolweather.util.SimpleActivity;

/**
 * Created by lyx on 2016/4/4.
 */
public class XingzuoFragment extends Fragment implements AbsListView.OnScrollListener {

    private SwipeRefreshLayout swiperview;
    public Article article;


    static String content;

    List<String> newsList;
    private List<String> imageUrl=new ArrayList<String>();
    private Statu statu;

    private  String news1;


    private static final String TAG = "MainActivity";
    StringRequest stringRequest1;
    RequestQueue mQueue;
    StringRequest stringRequest;
    Gson gson;

    private static List<Article> mUrList = new ArrayList<Article>();
    ImageLoader mImageLoader;
    private GridView mImageGridView;
    private BaseAdapter mImageAdapter;

    private boolean mIsGridViewIdle = true;
    private int mImageWidth = 0;
    private boolean mIsWifi = false;
    private boolean mCanGetBitmapFromNetWork = false;
        private View viewWea;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
         viewWea = inflater.inflate(R.layout.activity_main1, container, false);

        initData();

        mImageLoader = ImageLoader.build(getActivity());
        loadUrl();
        return viewWea;
    }

    private void loadUrl() {

        gson=new Gson();

        mQueue = Volley.newRequestQueue(getActivity());
        try {

            String newsUrl = "http://op.juhe.cn/onebox/news/words?key=5f415aedf19d5b2b5f8b51e1eed26062";
            stringRequest = new

                    StringRequest(newsUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(final  String response) {
                    Log.d("TAG", response);

                    System.out.println("response=" + response);

                    statu = gson.fromJson(response, Statu.class);

                    newsList = statu.getResult();
                    System.out.println("response=" + newsList.get(0));
                    for (int i = 0; i < newsList.size(); i++) {
                        String news = newsList.get(i);
                        System.out.println("response=" + news);
                        try {
                            news1 = "http://op.juhe.cn/onebox/news/query?key=5f415aedf19d5b2b5f8b51e1eed26062&q=" + URLEncoder.encode(news, "utf-8");
                        } catch (UnsupportedEncodingException e1) {
                            e1.printStackTrace();
                        }
                        loading(news1,i);
                    }



                }

            }

                    ,
                    new Response.ErrorListener()

                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.e("TAG", error.getMessage(), error);
                        }

                    }

            );


            mQueue.add(stringRequest);
        }catch (NullPointerException e){e.printStackTrace();}
    }


    public void  loading(String news1,final int i){

        try {
            stringRequest1 = new StringRequest(news1, new Response.Listener<String>() {
                @Override
                public void onResponse(final String response1) {



                    Log.d("TAG", response1);
                    System.out.println("response=" + response1);


                    Status status = gson.fromJson(response1, Status.class);
                    if(  status.getError_code().equals("0") &&!status.getResult().isEmpty()&& !status.getResult().get(0).getImg().equals("")) {
                        System.out.print("url" + status.getResult().get(0).getImg());


                        String myurl = status.getResult().get(0).getImg();
                        String mytitle = status.getResult().get(0).getTitle();
                        String mydate = status.getResult().get(0).getPdate();
                        String mysrc = status.getResult().get(0).getSrc();
                        String myarturl = status.getResult().get(0).getUrl();
                        article=new Article( mytitle,mydate,mysrc,myurl,myarturl);

                        mUrList.add(article);






                    }

                    initView();
                }

            },


                    new Response.ErrorListener()

                    {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                            Log.e("TAG", error.getMessage(), error);
                        }

                    }

            );


            mQueue.add(stringRequest1);


        }catch(NullPointerException e){e.printStackTrace();}

    }

    private void initData() {
        gson=new Gson();


        System.out.print("zhuzhuzhuzqqqqqqqqqq"+content);






        int screenWidth = MyUtils.getScreenMetrics(getActivity()).widthPixels;
        int space = (int)MyUtils.dp2px(getActivity(), 20f);
        mImageWidth = (screenWidth - space) / 3;
        mIsWifi = MyUtils.isWifi(getActivity());
        if (mIsWifi) {
            mCanGetBitmapFromNetWork = true;
        }}

    private void initView() {


        mImageGridView = (GridView) viewWea.findViewById(R.id.gridView1);
        mImageAdapter = new ImageAdapter(getActivity());
        mImageGridView.setAdapter(mImageAdapter);
        mImageGridView.setOnScrollListener(this);


    }
    private class ImageAdapter extends BaseAdapter {
        private LayoutInflater mInflater;
        private Drawable mDefaultBitmapDrawable;

        private ImageAdapter(Context context) {
            mInflater = LayoutInflater.from(context);
            mDefaultBitmapDrawable = context.getResources().getDrawable(R.drawable.image_default);
        }

        @Override
        public int getCount() {
            return mUrList.size();
        }

        @Override
        public Article getItem(int position) {
            return mUrList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.conten_item,parent, false);

                holder = new ViewHolder();
                holder.imageView = (ImageView) convertView.findViewById(R.id.image);
                holder.text=(TextView)convertView.findViewById(R.id.text_news);

                holder.text1=(TextView)convertView.findViewById(R.id.text_news1);
                holder.text2=(TextView)convertView.findViewById(R.id.text_news2);




                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            TextView text=holder.text;
            TextView text1=holder.text1;
            TextView text2=holder.text2;
            ImageView imageView = holder.imageView;
            text.setText(getItem(position).getTitle());
            text1.setText(getItem(position).getSrc());
            text2.setText(getItem(position).getPdate());


            final String tag = (String)imageView.getTag();
            final String uri = getItem(position).getImg();
            if (!uri.equals(tag)) {
                imageView.setImageDrawable(mDefaultBitmapDrawable);
            }
            if (mIsGridViewIdle && mCanGetBitmapFromNetWork) {
                imageView.setTag(uri);
                mImageLoader.bindBitmap(uri, imageView, mImageWidth, mImageWidth);

            }


            mImageGridView .setOnItemClickListener(new GridView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                    Intent intent = new Intent(getActivity(), SimpleActivity.class);
                    intent.putExtra("cont",getItem(position).getUrl());
                    System.out.print(   "廖云翔"+ getItem(position).getUrl() );
                    startActivity(intent);
                }
            });
            return convertView;
        }

    }

    private static class ViewHolder {
        public ImageView imageView;
        public TextView text;
        public TextView text1;
        public TextView text2;
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            mIsGridViewIdle = true;
            mImageAdapter.notifyDataSetChanged();
        } else {
            mIsGridViewIdle = false;
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem,
                         int visibleItemCount, int totalItemCount) {
        // ignored

    }
}



