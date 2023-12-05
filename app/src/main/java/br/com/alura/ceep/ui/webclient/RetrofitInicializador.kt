package br.com.alura.ceep.ui.webclient

import br.com.alura.ceep.ui.webclient.services.NotaService
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


class RetrofitInicializador {


    private val retrofit: Retrofit = Retrofit.Builder()
        // inserir seu endereço de ip
        .baseUrl("")
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    val notaService = retrofit.create(NotaService::class.java)
}