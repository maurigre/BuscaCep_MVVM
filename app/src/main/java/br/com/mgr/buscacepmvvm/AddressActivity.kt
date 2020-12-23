package br.com.mgr.buscacepmvvm

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.mgr.buscacepmvvm.address.Address
import br.com.mgr.buscacepmvvm.address.AddressViewModel
import br.com.mgr.buscacepmvvm.address.AddressViewState
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddressActivity : AppCompatActivity() {

    lateinit var cepTextView: TextView
    lateinit var enderecoTextView: TextView
    lateinit var cidadeTextView: TextView
    lateinit var ufTextView: TextView

    private val addressViewModel: AddressViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cepTextView = findViewById(R.id.cepTextView)
        enderecoTextView = findViewById(R.id.enderecoTextView)
        cidadeTextView = findViewById(R.id.cidadeTextView)
        ufTextView = findViewById(R.id.ufTextView)

        cepTextView.setOnFocusChangeListener { view, hasFocus ->
            if(!hasFocus && cepTextView.text.isNotEmpty()){
                addressViewModel.getAddress(cepTextView.text.toString())
            }
        }

        //observar LiveData da AddressViewModel
        addressViewModel.liveData.observe(this, Observer { state ->
            when(state){
                is AddressViewState.Loading -> Toast.makeText(this,"Iniciando consulta", LENGTH_SHORT).show()
                is AddressViewState.Success -> preencheCampos(state.address)
                is AddressViewState.Error -> showError(state.throwable)
            }
        })
    }

    fun preencheCampos(address: Address){
        enderecoTextView.text = address.street
        cidadeTextView.text = address.city
        ufTextView.text =address.state
    }

    fun showError(throwable: Throwable){
        preencheCampos(Address())
        Toast.makeText(this,"CEP informado não encontrado.",LENGTH_LONG).show()
    }
}