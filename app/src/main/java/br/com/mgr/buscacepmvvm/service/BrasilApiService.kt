package br.com.mgr.buscacepmvvm.service

import br.com.mgr.buscacepmvvm.address.Address
import retrofit2.http.GET
import retrofit2.http.Path

interface BrasilApiService {

    @GET( "api/cep/v1/{cep}")
    suspend fun getAddressByCep(@Path("cep") cep: String): Address

}