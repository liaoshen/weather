package activitytest.example.com.coolweather.loader;

/**
 * Created by lyx on 2016/4/22.
 */

import java.util.List;



        import java.util.List;

/**
 * Created by lyx on 2016/4/20.
 */
public class Status {
    private String reason;
    private String error_code;
    private List<Article> result;

    public void setReason (String reason ){
        this.reason=reason;

    }
    public String getReason(){
        return  reason;
    }

    public void setError_code (String  error_code){
        this.error_code=error_code;
    }
    public String getError_code(){
        return error_code ;
    }

    public void setResult (List<Article> result ){
        this.result=result;

    }
    public List<Article> getResult(){
        return  result;
    }


}

