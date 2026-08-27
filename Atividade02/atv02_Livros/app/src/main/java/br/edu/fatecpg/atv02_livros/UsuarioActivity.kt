package br.edu.fatecpg.atv02_livros

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import br.edu.fatecpg.atv02_livros.R
import com.google.android.material.floatingactionbutton.FloatingActionButton

class UsuarioActivity : AppCompatActivity(R.layout.activity_usuario) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val txvLivros = findViewById<TextView>(R.id.txv_livros)
        val fabVolta = findViewById<FloatingActionButton>(R.id.fab_volta)

        val nomeLivro = intent.getStringExtra("nome_livro")
        val nomeAutor = intent.getStringExtra("nome_autor")

        txvLivros.text = "Livro: $nomeLivro \nAutor: $nomeAutor"

        fabVolta.setOnClickListener{
            finish()
        }
    }
}