package hnu.multimedia.sololifetalk.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import hnu.multimedia.sololifetalk.MainActivity
import hnu.multimedia.sololifetalk.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private val binding by lazy { ActivityLoginBinding.inflate(layoutInflater) }
    private lateinit var auth : FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        auth = Firebase.auth

        binding.buttonLogin.setOnClickListener {

            val email = binding.editTextID.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (email.isEmpty()) {
                Snackbar.make(binding.root, "이메일을 입력해 주세요", Snackbar.LENGTH_LONG).show()
            } else if (password.isEmpty()) {
                Snackbar.make(binding.root, "비밀번호를 입력해주세요", Snackbar.LENGTH_LONG).show()
            } else if (password.length < 6) {
                Snackbar.make(binding.root, "비밀번호를 6자리 이상 입력해 주세요", Snackbar.LENGTH_LONG).show()
            } else {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this) {
                        if (it.isSuccessful) {
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Snackbar.make(binding.root, "로그인 실패", Snackbar.LENGTH_LONG).show()
                        }
                    }
            }
        }
    }
}