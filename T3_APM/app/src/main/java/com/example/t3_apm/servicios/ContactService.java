package com.example.t3_apm.servicios;

import com.example.t3_apm.entidades.ContactEnt;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContactService {
    //todo esto depende del API( GET SET DELETE )
    @GET("contactos")
    Call<List<ContactEnt>> getListContact();
    @POST("contactos")
    Call<Void> create(@Body ContactEnt contactosrg);//guardar datos
    /*@PUT("contacts/{id}")
    Call<Void> update (@Body ContactApi contactApi, @Path("id")int id);*/
    @PUT("contactos/{id}")
    Call<ContactEnt> update(@Body ContactEnt contactosrg, @Path("id")int id);
    @DELETE("contactos/{id}")
    Call<ContactEnt> delete (@Path("id")int id);
}
