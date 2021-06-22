package mx.edu.itm.link.plataformasge_res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import mx.edu.itm.link.plataformasge_res.databinding.ActivityMainBinding
import mx.edu.itm.link.plataformasge_res.databinding.ActivityMenuBinding
import mx.edu.itm.link.plataformasge_res.models.Alumno
import mx.edu.itm.link.plataformasge_res.models.DataBase

class Menu : AppCompatActivity() {

    companion object {
        lateinit var alumnoLogeado: Alumno
    }


    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //No encuentro el error, no me deja abrir el Menu de residencias
        val alumnoLogeado = intent.getSerializableExtra("ALUMNO")


        binding.btnResi.setOnClickListener {
            val intent = Intent(this, MenuResidencias::class.java)
            intent.putExtra("ALUMNO", alumnoLogeado)
            startActivity(intent)
        }
    }
}