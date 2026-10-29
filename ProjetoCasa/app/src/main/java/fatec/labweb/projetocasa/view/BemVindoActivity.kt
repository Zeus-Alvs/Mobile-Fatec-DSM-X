package fatec.labweb.projetocasa

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import fatec.labweb.projetocasa.view.CadastroActivity
import fatec.labweb.projetocasa.view.HomeActivity
import fatec.labweb.projetocasa.view.LoginActivity

class BemVindoActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 1. O layout deve ser inflado primeiro para que os componentes existam na tela
        // (Confirme se o nome do seu XML é activity_main ou activity_bem_vindo)
        setContentView(R.layout.activity_bemvindo)

        auth = FirebaseAuth.getInstance()

        // 2. Se já estiver logado, vai direto para a Home
        if (auth.currentUser != null) {
            startActivity(Intent(this, HomeActivity::class.java))
            finish()
            return
        }

        // 3. Agora os botões encontram os IDs com segurança pois o layout já foi carregado
        findViewById<Button>(R.id.btnLogin).setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        findViewById<Button>(R.id.btnCadastrar).setOnClickListener {
            startActivity(Intent(this, CadastroActivity::class.java))
        }
    }
}