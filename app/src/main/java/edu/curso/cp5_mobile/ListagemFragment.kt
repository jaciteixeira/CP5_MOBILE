package edu.curso.cp5_mobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.curso.cp5_mobile.model.CadastroViewModel

class ListagemFragment : Fragment() {

    private lateinit var viewModel: CadastroViewModel
    private lateinit var txtNome: TextView
    private lateinit var txtEmail: TextView
    private lateinit var txtTelefone: TextView
    private lateinit var txtEndereco: TextView
    private lateinit var txtCidade: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_listagem, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(CadastroViewModel::class.java)

        val spinner: Spinner = view.findViewById(R.id.spinner)
        txtNome = view.findViewById(R.id.txtNome)
        txtEmail = view.findViewById(R.id.txtEmail)
        txtTelefone = view.findViewById(R.id.txtTelefone)
        txtEndereco = view.findViewById(R.id.txtEndereco)
        txtCidade = view.findViewById(R.id.txtCidade)

        viewModel.listaCadastros.observe(viewLifecycleOwner) { cadastros ->
            val cadastroNomes = cadastros.map { it.nome } // Mostrar apenas os nomes no Spinner

            // Configurar Spinner
            val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cadastroNomes)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = spinnerAdapter

            // Adicionar o listener para o Spinner
            spinner.setOnItemSelectedListener(object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    val selectedCadastro = cadastros[position]
                    // Exibir os detalhes do cadastro selecionado
                    txtNome.text = selectedCadastro.nome
                    txtEmail.text = selectedCadastro.email
                    txtTelefone.text = selectedCadastro.telefone
                    txtEndereco.text = selectedCadastro.endereco
                    txtCidade.text = selectedCadastro.cidade
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    // Não faça nada se nenhum item for selecionado
                }
            })
        }

        return view
    }
}
