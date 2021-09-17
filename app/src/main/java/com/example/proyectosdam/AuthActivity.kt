package com.example.proyectosdam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.proyectosdam.databinding.ActivityAuthBinding
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        val view = binding.root
       // setContentView(R.layout.activity_auth)
        setContentView(view)

        setup()
    }
    private fun setup() {
        binding.registerButton.setOnClickListener {
          /////
            Toast.makeText(
                this,
                "pulsado",
                Toast.LENGTH_SHORT
            ).show();
            /////

            var myEmail = binding.emailEditText.text.toString()
            var myPassword = binding.passWordEditText.text.toString()
            var recursos = Recursos()
            if (recursos.isValidEmail(myEmail) && recursos.isValidPassword(myPassword)) {
                //registrar
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(myEmail, myPassword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            // Registro satisfactorio continuar app
                            Toast.makeText(
                                this,
                                "Enhorabuena, registro satisfactorio",
                                Toast.LENGTH_SHORT
                            ).show();

                            continuarApp()
                        } else {
                            // no se ha registrado
                            recursos.mostrarAviso1(
                                this,
                                "Error de Autentificacion",
                                "No has podido registrarte en la aplicacion, puede que ya estes registrado"
                            )
                        }
                    }
            } else {
                // Correo o Contraseña no valido mostrar ventana
                recursos.mostrarAviso1(this, "Aviso", "El correo o la contraseña son incorrentos")
            }
        }
        binding.loginButton.setOnClickListener {
            /////
            Toast.makeText(
                this,
                "pulsado",
                Toast.LENGTH_SHORT
            ).show();
            /////
            continuarApp()
        }
        /*
         loginButton.setOnClickListener {

               var myEmail = emailEditText.text.toString()
                    var myPassword = passWordEditText.text.toString()
                    var recursos = Recursos()
                    if (recursos.isValidEmail(myEmail) && recursos.isValidPassword(myPassword)) {
                        //registrar
                        FirebaseAuth.getInstance().signInWithEmailAndPassword(myEmail, myPassword)
                            .addOnCompleteListener {
                                if (it.isSuccessful) {
                                    // login satisfactorio continuar app
                                    continuarApp()
                                } else {
                                    // no se ha registrado
                                    recursos.mostrarAviso1(
                                        this,
                                        "Error de Login",
                                        "No has podido ingresar en la aplicacion, Comprueba tu email y contraseña"
                                    )
                                }
                            }
                    } else {
                        // Correo o Contraseña no valido mostrar ventana
                        recursos.mostrarAviso1(this, "Aviso", "El correo o la contraseña son incorrentos")
                    }
                }

             */
    }

    public fun continuarApp() {
     //   val intent = Intent(this, MainActivity::class.java)
    //    startActivity(intent)
    }

}