package br.com.mgr.buscacepmvvm

import android.app.Application
import br.com.mgr.buscacepmvvm.address.addressModule
import br.com.mgr.buscacepmvvm.service.networkBrasilApiModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BuscaCepApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@BuscaCepApplication)
            modules(networkBrasilApiModule, addressModule)
        }
    }
}