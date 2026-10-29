package fatec.labweb.projetocasa.view

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseAuth
import fatec.labweb.projetocasa.BemVindoActivity
import fatec.labweb.projetocasa.R

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        auth = FirebaseAuth.getInstance()

        val usuario = auth.currentUser
        if (usuario == null) {
            irParaInicio()
            return
        }

        val email = usuario.email ?: "-"
        val nome = usuario.displayName?.takeIf { it.isNotBlank() }
            ?: email.substringBefore("@")
        val primeiroNome = nome.trim().substringBefore(" ")

        findViewById<TextView>(R.id.txtOla).text = "Olá, $primeiroNome!"
        findViewById<TextView>(R.id.txtNome).text = nome
        findViewById<TextView>(R.id.txtEmail).text = email

        // Ao clicar em sair, chama o modal de confirmação
        findViewById<MaterialButton>(R.id.btnSair).setOnClickListener {
            mostrarModalDesconectar()
        }

        findViewById<MaterialButton>(R.id.btnRedefinirSenha).setOnClickListener {
            startActivity(Intent(this, RedefinirSenhaActivity::class.java))
        }

        findViewById<MaterialButton>(R.id.btnExcluirConta).setOnClickListener {
            startActivity(Intent(this, ExcluirContaActivity::class.java))
        }
    }

    private fun mostrarModalDesconectar() {
        AlertDialog.Builder(this)
            .setTitle("Desconectar")
            .setMessage("Tem certeza de que deseja sair da sua conta?")
            .setPositiveButton("Sim") { _, _ ->
                // Se a resposta for SIM, vai para a tela de desconectar conta
                val intent = Intent(this, DesconectarContaActivity::class.java)
                startActivity(intent)
            }
            .setNegativeButton("Não", null) // Fecha o modal se for "Não"
            .show()
    }

    private fun irParaInicio() {
        val intent = Intent(this, BemVindoActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}