package mx.edu.itm.link.plataformasge_res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import mx.edu.itm.link.plataformasge_res.databinding.ActivityMenuBinding
import mx.edu.itm.link.plataformasge_res.models.Alumno
import mx.edu.itm.link.plataformasge_res.models.DataBase

class Menu : AppCompatActivity() {

    companion object {
        lateinit var alumnoLogeado: Alumno
        //Base de datos
        lateinit var bd : DataBase
    }


    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Base de datos
        val bdString = resources.getString(R.string.baseDatos)
        bd = Gson().fromJson(bdString, DataBase::class.java) as DataBase

        //No encuentro el error, no me deja abrir el Menu de residencias
        val alumno = intent.getSerializableExtra("ALUMNO")

        alumnoLogeado = alumno as Alumno
        binding.btnResi.setOnClickListener {
            val intent = Intent(this, MenuResidencias::class.java)
            intent.putExtra("ALUMNO", alumno)
            startActivity(intent)
        }
    }
}