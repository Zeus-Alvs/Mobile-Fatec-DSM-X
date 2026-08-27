package br.edu.fatecpg.atv02_alunos

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UsuarioActivity : AppCompatActivity(R.layout.activity_usuario) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val txvSaudacao = findViewById<TextView>(R.id.txv_saudacao)
        val txvCodigo = findViewById<TextView>(R.id.txv_codigo)
        val btnGerar = findViewById<Button>(R.id.btn_gerar)
        val fabVolta = findViewById<FloatingActionButton>(R.id.fab_volta)

        val nomeUsuario = intent.getStringExtra("nome_usuario")
        txvSaudacao.setText("Bem-Vindo $nomeUsuario")

        btnGerar.setOnClickListener{
            val numAleatorio = (1..999999).random()
            txvCodigo.text = "Código: " + numAleatorio.toString()

            btnGerar.isEnabled = false
        }
        fabVolta.setOnClickListener{
            finish()
        }

    }
}