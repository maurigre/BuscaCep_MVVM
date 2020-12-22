package br.com.mgr.buscacepmvvm.address

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.mgr.buscacepmvvm.service.BrasilApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddressViewModel(
    private val service: BrasilApiService
): ViewModel() {

    //Variavel que sera observada pela Activity
    private val _livedata = MutableLiveData<Address>()
    val liveData: LiveData<Address> = _livedata

    fun getAddress(cep: String){
        viewModelScope.launch(Dispatchers.IO){
            val result: Address = service.getAddressByCep(cep)
            _livedata.postValue(result)
        }
    }

}