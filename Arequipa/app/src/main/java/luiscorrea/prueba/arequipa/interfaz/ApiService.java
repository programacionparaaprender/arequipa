package luiscorrea.prueba.arequipa.interfaz;
import java.util.List;

import luiscorrea.prueba.arequipa.model.Publicaciones;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
@GET("publicaciones")
Call<List<Publicaciones>> getPublicaciones();
@POST("publicaciones")
@FormUrlEncoded
Call<Publicaciones> savePublicacion(@Field("nombre") String nombre,
                                    @Field("descripcion") String descripcion);
}
