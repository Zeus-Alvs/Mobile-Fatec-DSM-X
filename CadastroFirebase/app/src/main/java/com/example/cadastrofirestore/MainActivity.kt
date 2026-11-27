package com.example.cadastrofirestore

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore

class MainActivity : AppCompatActivity() {

    private lateinit var campoNome: TextInputEditText
    private lateinit var campoCpf: TextInputEditText
    private lateinit var botaoCadastrar: MaterialButton

    private val firestore by lazy { Firebase.firestore }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        configurarInsets()
        inicializarComponentes()

        botaoCadastrar.setOnClickListener {
            val nomeInformado = campoNome.text?.toString()?.trim().orEmpty()
            val cpfInformado = campoCpf.text?.toString()?.trim().orEmpty()

            if (nomeInformado.isEmpty() || cpfInformado.isEmpty()) {
                exibirMensagem(getString(R.string.msg_campos_vazios))
                return@setOnClickListener
            }

            registrarCliente(nomeInformado, cpfInformado)
        }
    }

    private fun configurarInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.rootContainer)) { view, insets ->
            val barras = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            view.setPadding(barras.left, barras.top, barras.right, barras.bottom)
            insets
        }
    }

    private fun inicializarComponentes() {
        campoNome = findViewById(R.id.inputNome)
        campoCpf = findViewById(R.id.inputCpf)
        botaoCadastrar = findViewById(R.id.btnCadastrar)
    }

    private fun registrarCliente(nome: String, cpf: String) {
        val registro = mapOf(
            "nome" to nome,
            "cpf" to cpf,
            "data_compra" to FieldValue.serverTimestamp()
        )

        firestore.collection("colecao_cliente")
            .add(registro)
            .addOnSuccessListener {
                Log.d("FIRESTORE", "Registro salvo com sucesso")
                exibirMensagem(getString(R.string.msg_sucesso))
                limparFormulario()
            }
            .addOnFailureListener { erro ->
                Log.d("FIRESTORE", "Erro ao salvar: $erro")
                Toast.makeText(
                    this,
                    getString(R.string.msg_erro, erro.message),
                    Toast.LENGTH_LONG
                ).show()
            }
    }

    private fun limparFormulario() {
        campoNome.text?.clear()
        campoCpf.text?.clear()
    }

    private fun exibirMensagem(texto: String) {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show()
    }
}
