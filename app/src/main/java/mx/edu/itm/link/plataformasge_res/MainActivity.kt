package mx.edu.itm.link.plataformasge_res

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import mx.edu.itm.link.plataformasge_res.databinding.ActivityMainBinding
import mx.edu.itm.link.plataformasge_res.models.DataBase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Base de datos
        val bdString =  resources.getString(R.string.baseDatos)
        val bd = Gson().fromJson(bdString, DataBase::class.java) as DataBase

        binding.btnLogin.setOnClickListener {
            val nc = binding.noControlLogin.text.toString()
            val pass = binding.contrasenaLogin.text.toString()

            println(bd)

            for (alumno in bd.alumnos) {
                if (alumno.nc == nc && alumno.pass == pass) {
                    val intent = Intent(this, Menu::class.java)
                    intent.putExtra("ALUMNO", alumno)

                    startActivity(intent)
                    break
                }else{
                    Toast.makeText(this, "Credenciales incorrectas", Toast.LENGTH_SHORT).show()
                }
            }


        }

    }
}