package mx.edu.itm.link.plataformasge_res

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import mx.edu.itm.link.plataformasge_res.databinding.ActivityDetalleProyectoBinding
import mx.edu.itm.link.plataformasge_res.databinding.ActivitySeleccionProyectoBinding
import mx.edu.itm.link.plataformasge_res.models.DataBase
import mx.edu.itm.link.plataformasge_res.models.Profesore
import mx.edu.itm.link.plataformasge_res.models.Proyecto
import java.util.logging.Logger

class SeleccionProyecto : AppCompatActivity() {
    private lateinit var binding: ActivitySeleccionProyectoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionProyectoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Base de datos
        val bdString = resources.getString(R.string.baseDatos)
        val bd = Gson().fromJson(bdString, DataBase::class.java) as DataBase

        val alumno = intent.getSerializableExtra("ALUMNO")
        val proyecto = intent.getSerializableExtra("PROYECTO") as Proyecto

        binding.nombreProyectoSeleccion.text = proyecto.nombreProyecto
        binding.nombreEmpresaSeleccion.text = proyecto.nombreEmpresa
        binding.lgacSeleccion.text = proyecto.lgac

        //TODO: esto se va a hacer con un join cuando sea BD
        val profesoresDeLinea = ArrayList<Profesore>()
        for (p in bd.profesores) {
            if (p.linea == proyecto.lgac) {
                profesoresDeLinea.add(p)
            }
        }
        //Se asigna profesor random

        val profesorSeleccionado = profesoresDeLinea.random()
        binding.asesorSeleccion.text =
            "${profesorSeleccionado.nombre}\n${profesorSeleccionado.titulo}"

    }
}