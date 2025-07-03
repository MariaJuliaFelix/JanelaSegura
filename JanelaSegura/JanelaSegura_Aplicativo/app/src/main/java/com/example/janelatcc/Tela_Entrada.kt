package com.example.janelatcc

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth

class Tela_Entrada : AppCompatActivity() {

    lateinit var  autenticacao : FirebaseAuth
    lateinit var email : EditText
    lateinit var senha : EditText



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tela_entrada)


        autenticacao = FirebaseAuth.getInstance()


        val prefs = getSharedPreferences("meu_app_prefs", MODE_PRIVATE)

        email = findViewById(R.id.digite_email)
        senha = findViewById(R.id.digite_senha)

        email.setText(prefs.getString("email", ""))
        senha.setText(prefs.getString("senha", ""))



        findViewById<Button>(R.id.botao_cadastrar).setOnClickListener {
            val editor = prefs.edit()
            editor.putString("email", email.text.toString())
            editor.putString("senha", senha.text.toString())
            editor.commit()

            val tarefa = autenticacao.createUserWithEmailAndPassword(
                email.text.toString(),
                senha.text.toString()
            )

            tarefa.addOnSuccessListener { sucesso ->
                Toast.makeText(
                    this, "USUÁRIO CADASTRADO", Toast.LENGTH_SHORT
                ).show()
            }
            tarefa.addOnFailureListener { erro ->
                Toast.makeText(
                    this, "FALHA AO CADASTRAR USUÁRIO\n ${erro.message}", Toast.LENGTH_SHORT
                ).show()
            }
        }

        findViewById<Button>(R.id.botao_login).setOnClickListener {
            val editor = prefs.edit()
            editor.putString("email", email.text.toString())
            editor.putString("senha", senha.text.toString())
            editor.commit()

            val tarefa = autenticacao.signInWithEmailAndPassword(
                email.text.toString(),
                senha.text.toString()
            )
            tarefa.addOnSuccessListener { sucesso ->
                Toast.makeText(
                    this,"Usuário logado", Toast.LENGTH_SHORT
                ).show()
                startActivity(Intent(this, TelaInicialActivity::class.java))
            }
            tarefa.addOnFailureListener { erro ->
                Toast.makeText(
                    this, "Falha ao Logar\n ${erro.message}", Toast.LENGTH_SHORT
                ).show()
            }
        }


    }




}
