package activitytest.example.com.coolweather.loader;

/**
 * Created by lyx on 2016/4/22.
 */

import java.util.List;



        import java.util.List;

/**
 * Created by lyx on 2016/4/20.
 */
public class Statu {
    private String reason;
    private String error_code;
    private List<String> result;

    public void setReason (String reason ){
        this.reason=reason;

    }
    public String getReason(){
        return  reason ;
    }


    public void setResult (List<String> result ){
        this.result=result;

    }
    public List<String> getResult(){
        return  result;
    }

}

