package br.com.mgr.buscacepmvvm.address

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mgr.buscacepmvvm.address.AddressViewState.Error
import br.com.mgr.buscacepmvvm.address.AddressViewState.Success
import br.com.mgr.buscacepmvvm.service.BrasilApiService
import com.google.gson.Gson
import com.google.gson.JsonSyntaxException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException


class AddressViewModel(
    private val service: BrasilApiService
): ViewModel() {

    //Variavel que sera observada pela Activity
    private val _livedata = MutableLiveData<AddressViewState>()
    val liveData: LiveData<AddressViewState> = _livedata


    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        _livedata.value = Error(throwable)
    }

    fun getAddress(cep: String){
        _livedata.value = AddressViewState.Loading

        viewModelScope.launch(exceptionHandler) {
            withContext(Dispatchers.IO) {
                val result: Address = service.getAddressByCep(cep)
                _livedata.postValue( Success(result))
             }
        }
    }
}

sealed class AddressViewState {
    object Loading : AddressViewState()
    data class Error(val throwable: Throwable) : AddressViewState()
    data class Success(val address: Address) : AddressViewState()
}