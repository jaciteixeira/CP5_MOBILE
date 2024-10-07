package edu.curso.cp5_mobile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import edu.curso.cp5_mobile.model.CadastroViewModel

class ListagemFragment : Fragment() {

    private lateinit var viewModel: CadastroViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_listagem, container, false)

        viewModel = ViewModelProvider(requireActivity()).get(CadastroViewModel::class.java)

        val listView: ListView = view.findViewById(R.id.listView)
        val spinner: Spinner = view.findViewById(R.id.spinner)

        viewModel.listaCadastros.observe(viewLifecycleOwner) { cadastros ->
            val cadastroNomes = cadastros.map { it.nome } // Mostrar apenas os nomes

            // Configurar ListView
            val listViewAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, cadastroNomes)
            listView.adapter = listViewAdapter

            // Configurar Spinner
            val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cadastroNomes)
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = spinnerAdapter
        }

        return view
    }
}
