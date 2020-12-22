package br.com.mgr.buscacepmvvm.service

import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

var networkBrasilApiModule = module {

    single { createRetrofit() }
    single { createService(get()) }

}

fun createRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl("https://brasilapi.com.br/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}

fun createService(retrofit: Retrofit): BrasilApiService {
    return retrofit.create(BrasilApiService::class.java)
}
