package luiscorrea.prueba.arequipa.Config;

import android.content.Context;
import android.widget.Toast;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Config {
    //private static final String BASEURL = "http://192.168.0.54/arequipa/public/api/";
    private static final String BASEURL = "http://192.168.0.6/arequipa/public/api/";

    private static Retrofit retrofit;

    public static Retrofit getRetrofit(){
        if(retrofit==null){
            retrofit = new Retrofit.Builder()
                .baseUrl(BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        }
        return retrofit;
    }
    public static void Mensaje(Context context, String mensaje){
        Toast.makeText(context, mensaje, Toast.LENGTH_LONG).show();
    }
}
