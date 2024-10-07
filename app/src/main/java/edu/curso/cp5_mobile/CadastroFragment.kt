package edu.curso.cp5_mobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.curso.cp5_mobile.model.CadastroViewModel

class CadastroFragment : Fragment() {
    private lateinit var viewModel: CadastroViewModel
    private lateinit var btnEnviar: Button
    private lateinit var txtResultado: TextView
    private lateinit var editNome: EditText
    private lateinit var editEmail: EditText
    private lateinit var editTelefone: EditText
    private lateinit var editEndereco: EditText
    private lateinit var editCidade: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_cad, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(CadastroViewModel::class.java)

        // Inicializar os elementos de UI
        btnEnviar = view.findViewById(R.id.btnEnviar)
        txtResultado = view.findViewById(R.id.txtResultado)
        editNome = view.findViewById(R.id.editNome)
        editEmail = view.findViewById(R.id.editEmail)
        editTelefone = view.findViewById(R.id.editTelefone)
        editEndereco = view.findViewById(R.id.editEndereco)
        editCidade = view.findViewById(R.id.editCidade)

        btnEnviar.setOnClickListener {
            // Obtenha os valores inseridos pelo usuário
            val nome = editNome.text.toString()
            val email = editEmail.text.toString()
            val telefone = editTelefone.text.toString()
            val endereco = editEndereco.text.toString()
            val cidade = editCidade.text.toString()

            // Verificar se todos os campos foram preenchidos (opcional)
            if (nome.isNotEmpty() && email.isNotEmpty() && telefone.isNotEmpty() &&
                endereco.isNotEmpty() && cidade.isNotEmpty()) {

                // Adicionar o cadastro ao ViewModel
                viewModel.adicionarCadastro(nome, email, telefone, endereco, cidade)

                // Exibir uma mensagem de sucesso
                txtResultado.text = "Cadastro de $nome realizado com sucesso!"
            } else {
                txtResultado.text = "Por favor, preencha todos os campos!"
            }
        }

        return view
    }
}
