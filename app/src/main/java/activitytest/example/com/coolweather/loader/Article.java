package activitytest.example.com.coolweather.loader;

/**
 * Created by lyx on 2016/4/22.
 */



/**
 * Created by lyx on 2016/4/20.
 */
public class Article {
    private String title;
    private String pdate;
    private String src;
    private String img;
    private String url;

    public  Article(String title,String pdate,String src,String img,String url){

        this.title=title;
        this.pdate=pdate;
        this.src=src;
        this.img=img;
        this.url=url;
    }

    public void setTitle(String title){
        this.title=title;
    }
    public String getTitle(){
        return title;
    }

    public void setPdate (String pdate ){
        this.pdate=pdate;

    }
    public String getPdate(){
        return pdate;
    }

    public void setSrc (String src ){
        this.src=src;
    }
    public String getSrc(){
        return  src;
    }

    public void setImg (String img ){
        this.img=img;
    }
    public String getImg(){
        return img;
    }

    public void setUrl (String  url){
        this.url=url;
    }
    public String getUrl(){
        return url;
    }




}

