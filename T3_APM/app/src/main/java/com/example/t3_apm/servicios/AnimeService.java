package com.example.t3_apm.servicios;

import com.example.t3_apm.entidades.AnimeEnt;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AnimeService {
    //todo esto depende del API( GET SET DELETE )
    @GET("contactos")
    Call<List<AnimeEnt>> getListContact();
    @POST("contactos")
    Call<Void> create(@Body AnimeEnt animerg);//guardar datos
    /*@PUT("contacts/{id}")
    Call<Void> update (@Body ContactApi contactApi, @Path("id")int id);*/
    @PUT("contactos/{id}")
    Call<AnimeEnt> update(@Body AnimeEnt animerg, @Path("id")int id);
    @DELETE("contactos/{id}")
    Call<AnimeEnt> delete (@Path("id")int id);
}
