package edu.curso.cp5_mobile.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CadastroViewModel : ViewModel() {

    private val _listaCadastros = MutableLiveData<List<Cadastro>>()
    val listaCadastros: LiveData<List<Cadastro>> get() = _listaCadastros

    private val cadastros = mutableListOf<Cadastro>()

    fun adicionarCadastro(nome: String, email: String, telefone: String, endereco: String, cidade: String) {
        val novoCadastro = Cadastro(nome, email, telefone, endereco, cidade)
        cadastros.add(novoCadastro)
        _listaCadastros.value = cadastros
    }
}

data class Cadastro(
    val nome: String,
    val email: String,
    val telefone: String,
    val endereco: String,
    val cidade: String
)

