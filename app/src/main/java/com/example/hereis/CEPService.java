package com.example.hereis;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CEPService {

    @GET("{estado}/{municipio}/{localidade}/json/")
    Call<List<CEP>> recuperarCEP(@Path("estado") String estado, @Path("municipio")String municipio, @Path("localidade")String localidade);
}
