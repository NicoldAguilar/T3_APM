package com.example.t3_apm.servicios;

import com.example.t3_apm.entidades.PeliEnt;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PeliculaService {
    //todo esto depende del API( GET SET DELETE )
    @GET("contactos")
    Call<List<PeliEnt>> getListContact();
    @POST("contactos")
    Call<Void> create(@Body PeliEnt pelirg);//guardar datos
    /*@PUT("contacts/{id}")
    Call<Void> update (@Body ContactApi contactApi, @Path("id")int id);*/
    @PUT("contactos/{id}")
    Call<PeliEnt> update(@Body PeliEnt pelirg, @Path("id")int id);
    @DELETE("contactos/{id}")
    Call<PeliEnt> delete (@Path("id")int id);
}
