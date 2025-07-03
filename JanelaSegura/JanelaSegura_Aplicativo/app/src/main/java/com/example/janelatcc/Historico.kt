package com.example.janelatcc

import android.graphics.Typeface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Historico.newInstance] factory method to
 * create an instance of this fragment.
 */
class Historico : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var banco : FirebaseFirestore
    lateinit var historicos : RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_historico, container, false)

        historicos = view.findViewById(R.id.listagem)

        banco = FirebaseFirestore.getInstance()

        banco.collection("janelas").get().addOnCompleteListener { status ->
            if (status.isSuccessful){
                val listaDeDados = mutableListOf< String>()

                for( documento in status.result){
                    listaDeDados
                        .add("${documento.get("status")} - ${documento.get("data-hora")}")
                }
                popularListaDeDados(listaDeDados)
            }else{
                AlertDialog.Builder(requireContext()).setTitle("LISTAGEM DE DADOS").setMessage("ERRO").show()
            }
        }
        return view
    }
    private fun popularListaDeDados(lista: List<String>){
        historicos.layoutManager = LinearLayoutManager(requireContext())
        historicos.adapter = object : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
            override fun onCreateViewHolder(
                parent: ViewGroup,
                viewType: Int): RecyclerView.ViewHolder {
                val view = LayoutInflater.from(parent.context)
                    .inflate(android.R.layout.simple_list_item_1, parent, false)
                return object : RecyclerView.ViewHolder(view){}
            }

            override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
                val texto = holder.itemView.findViewById<TextView>(android.R.id.text1)
                texto.text = lista[position]
                texto.textSize = 18F

                texto.typeface = Typeface.DEFAULT_BOLD
            }

            override fun getItemCount(): Int = lista.size

            }
        }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Historico.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Historico().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}