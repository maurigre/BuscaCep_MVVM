package br.com.mgr.buscacepmvvm.address

data class Address(
    val cep: String? = null,
    val state: String? = null,
    val city: String? = null,
    val neighborhood: String? = null,
    val street: String? = null
)