package activitytest.example.com.coolweather.toolbar;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Created by lyx on 2016/4/15.
 */
public class ScrollViewnew  extends ScrollView{
    private float xDistance, yDistance, xLast, yLast;


    public ScrollViewnew(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public ScrollViewnew(Context context) {
        super(context);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xDistance = yDistance = 0f;
                xLast = ev.getX();
                yLast = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                final float curX = ev.getX();
                final float curY = ev.getY();

//			int off= view.getMeasuredHeight()-extend.getHeight();
//			Log.i("OFF", off+"");

                xDistance += Math.abs(curX - xLast);
                yDistance += Math.abs(curY - yLast);
                xLast = curX;
                yLast = curY;


                if (xDistance <yDistance) {

                    return true;
                }
        }

        return super.onInterceptTouchEvent(ev);
    }}

