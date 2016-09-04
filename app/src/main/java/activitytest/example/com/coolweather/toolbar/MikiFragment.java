package activitytest.example.com.coolweather.toolbar;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import activitytest.example.com.coolweather.R;

/**
 * Created by lyx on 2016/4/4.
 */
public class MikiFragment extends Fragment {




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View viewWea=inflater.inflate(R.layout.miki_layout,container,false);

        return viewWea;
    }



    }

