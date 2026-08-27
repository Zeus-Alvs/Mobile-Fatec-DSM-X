package br.edu.fatecpg.atv02_alunos

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.txv_titulo)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val edtNomeUsuario = findViewById<EditText>(R.id.edt_nome_usuario)
        val btnSalvar = findViewById<Button>(R.id.btn_salvar)
        val fabAvanca = findViewById<FloatingActionButton>(R.id.fab_avanca)
        var nomeUsuario = ""
        btnSalvar.setOnClickListener {
            nomeUsuario = edtNomeUsuario.text.toString()
            edtNomeUsuario.text.clear()
            Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()
        }
        fabAvanca.setOnClickListener {
            val intent = Intent(this, UsuarioActivity::class.java)
            intent.putExtra("nome_usuario",nomeUsuario)
            startActivity(intent)
        }

    }
}