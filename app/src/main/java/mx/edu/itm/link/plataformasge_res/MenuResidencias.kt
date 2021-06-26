package mx.edu.itm.link.plataformasge_res

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import mx.edu.itm.link.plataformasge_res.adapters.ProyectoAdapter
import mx.edu.itm.link.plataformasge_res.databinding.ActivityMenuResidencias2Binding
import mx.edu.itm.link.plataformasge_res.models.Alumno
import mx.edu.itm.link.plataformasge_res.models.DataBase
import mx.edu.itm.link.plataformasge_res.models.Proyecto

class MenuResidencias : AppCompatActivity() {

    private lateinit var binding: ActivityMenuResidencias2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuResidencias2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val alumno = intent.getSerializableExtra("ALUMNO") as Alumno

        //Base de datos
        //val bdString = resources.getString(R.string.baseDatos)
        //val bd = Gson().fromJson(bdString, DataBase::class.java) as DataBase

        val bd = Utils.database

        //Hice el listView con herencia ya que nececitaba pasar un extra adicional que
        //   no podria traerme desde el adapter
        binding.lvProyecto.adapter =
            object : ProyectoAdapter(this, R.layout.proyecto, bd.getProyectos()) {
                override fun clickItemProyecto(proyecto: Proyecto) {
                    val intent = Intent(context, DetalleProyecto::class.java)
                    intent.putExtra("PROYECTO", proyecto)
                    intent.putExtra("ALUMNO", alumno)
                    context.startActivity(intent)
                }

            }

        binding.fabAgregarProeyecto.setOnClickListener {
            if (alumno.tieneProyecto == 1) {
                Toast.makeText(this, "Ya tienes residencias seleccionadas", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val intent = Intent(this, AgregarDependencia::class.java)
                intent.putExtra("ALUMNO", alumno)
                startActivity(intent)
            }
        }

        //Si el alumno tiene proyecto, el boton de agregar reporte se habilita
        if (MainActivity.alumnoLogeado.tieneProyecto == 1) {
            binding.fabAddReporteResidencias.isEnabled = true
            binding.fabAgregarProeyecto.isEnabled = false
        }else{
            binding.fabAddReporteResidencias.isEnabled = false
            binding.fabAgregarProeyecto.isEnabled = true
        }


        binding.fabAddReporteResidencias.setOnClickListener {
            Utils.database.proeyectoDeAlumnoByID(alumno.id)

            val intent = Intent(this, SeleccionProyecto::class.java)
            intent.putExtra("ALUMNO", MainActivity.alumnoLogeado)
            intent.putExtra("PROYECTO", Utils.database.proeyectoDeAlumnoByID(alumno.id)[0])
            startActivity(intent)
        }

    }
}