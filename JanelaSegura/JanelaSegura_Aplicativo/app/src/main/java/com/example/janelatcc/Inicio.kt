package com.example.janelatcc

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.hivemq.client.mqtt.MqttGlobalPublishFilter
import com.hivemq.client.mqtt.datatypes.MqttQos
import com.hivemq.client.mqtt.mqtt5.Mqtt5AsyncClient
import com.hivemq.client.mqtt.mqtt5.Mqtt5Client
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

import java.util.UUID

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Inicio.newInstance] factory method to
 * create an instance of this fragment.
 */
class Inicio : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var cliente: Mqtt5AsyncClient
    lateinit var statusJanela: ImageView
    lateinit var banco: FirebaseFirestore

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
        val view = inflater.inflate(R.layout.fragment_inicio, container, false)


        view.findViewById<Button>(R.id.botao_abrir).setOnClickListener {
            enviarComando("abrir")
        }

        view.findViewById<Button>(R.id.botao_fechar).setOnClickListener {
            enviarComando("fechar")
        }

        banco = FirebaseFirestore.getInstance()

        statusJanela = view.findViewById<ImageView>(R.id.janela_aberta)

        cliente = Mqtt5Client
            .builder()
            .identifier(UUID.randomUUID().toString())
            .serverHost("test.mosquitto.org")
            .serverPort(1883)
            .buildAsync()

        subscrevendo()

        return view
    }


    private fun enviarComando(mensagem: String) {
        cliente.publishWith()
            .topic("janela/comando")
            .payload(mensagem.toByteArray())
            .qos(MqttQos.AT_LEAST_ONCE)
            .send()
            .thenRun {
                exibirMensagem("Comando enviado: $mensagem")
            }
            .exceptionally { erro ->
                exibirMensagem("Falha ao enviar: ${erro.message}")
                null
            }
    }

    fun exibirMensagem(mensagem: String) {
        requireActivity().runOnUiThread {
            Toast.makeText(requireActivity(), mensagem, Toast.LENGTH_SHORT).show()
        }
    }

    private fun dataHoraFormatada(dataHora: LocalDateTime): String {
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")
        return dataHora.format(formatter)
    }

    private fun subscrevendo() {
        cliente?.publishes(MqttGlobalPublishFilter.SUBSCRIBED) { publicacao ->
            val pacote = String(publicacao.payloadAsBytes, Charsets.UTF_8)
            var status = ""

            requireActivity().runOnUiThread {
                exibirMensagem(pacote)
                when (pacote) {
                    "ABERTA" -> {
                        status = "ABERTA"
                        statusJanela.setImageResource(R.drawable.janela_aberta)
                    }

                    "FECHADA" -> {
                        status = "FECHADA"
                        statusJanela.setImageResource(R.drawable.janela_fechada)
                    }

                    else ->{
                        status = "ERRO"
                        statusJanela.setImageResource(R.drawable.janela_fechada)
                    }

                }

                val dataHora = dataHoraFormatada(LocalDateTime.now())
                var registro = mutableMapOf<String, String>()

                registro.put("status", status)
                registro.put("data-hora", dataHora)

                banco.collection("janelas")
                    .document(UUID.randomUUID().toString())
                    .set(registro)
                    .addOnSuccessListener {
                        Toast.makeText(requireContext(),"", Toast.LENGTH_SHORT).show()
                    }

                    .addOnFailureListener { erro ->
                        Toast.makeText(requireContext(),
                            "FALHA\n ${erro.message}"
                            , Toast.LENGTH_SHORT
                        ).show()

                    }
            }
        }
        cliente
            .connect()
            .thenCompose {
                cliente.subscribeWith()
                    .topicFilter("janela/status")
                    .send()
            }

    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment Inicio.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Inicio().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}