package com.example.janelatcc

import android.app.Fragment
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.janelatcc.databinding.ActivityMainBinding
import com.example.janelatcc.databinding.ActivityTelaInicialBinding

class TelaInicialActivity : AppCompatActivity() {
    private lateinit var ligacaoTela :ActivityTelaInicialBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ligacaoTela = ActivityTelaInicialBinding.inflate(layoutInflater)
        setContentView(ligacaoTela.root)

        trocarFragmento(Inicio())

        ligacaoTela.barraNavegacaoInferior.setOnItemSelectedListener { menu ->

            when(menu.itemId) {
                R.id.item_inicio -> trocarFragmento(Inicio())
                R.id.item_historico -> trocarFragmento(Historico())
                R.id.item_codigo -> trocarFragmento(Codigo())
                else -> {

                }
            }
            true
        }
    }

    private fun trocarFragmento(fragmento: androidx.fragment.app.Fragment) {
        val gerenciadorDeFragmentos = supportFragmentManager
        val transicaoDeFragmento = gerenciadorDeFragmentos.beginTransaction()
        transicaoDeFragmento.replace(R.id.frame_layout, fragmento)
        transicaoDeFragmento.commit()
    }
}