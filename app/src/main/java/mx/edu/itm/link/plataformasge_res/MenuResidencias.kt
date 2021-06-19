package mx.edu.itm.link.plataformasge_res

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import mx.edu.itm.link.plataformasge_res.adapters.ProyectoAdapter
import mx.edu.itm.link.plataformasge_res.databinding.ActivityMenuResidencias2Binding
import mx.edu.itm.link.plataformasge_res.models.DataBase

class MenuResidencias : AppCompatActivity() {

    private lateinit var binding: ActivityMenuResidencias2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuResidencias2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        //Base de datos
        val bdString = resources.getString(R.string.baseDatos)
        val bd = Gson().fromJson(bdString, DataBase::class.java) as DataBase

        binding.lvProyecto.adapter = ProyectoAdapter(this, R.layout.proyecto, bd.proyectos)


    }
}