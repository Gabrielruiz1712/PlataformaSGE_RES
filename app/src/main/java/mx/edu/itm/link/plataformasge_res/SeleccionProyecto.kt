package mx.edu.itm.link.plataformasge_res

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import mx.edu.itm.link.plataformasge_res.adapters.ReporteAdapter
import mx.edu.itm.link.plataformasge_res.databinding.ActivitySeleccionProyectoBinding
import mx.edu.itm.link.plataformasge_res.models.DataBase
import mx.edu.itm.link.plataformasge_res.models.Profesore
import mx.edu.itm.link.plataformasge_res.models.Proyecto
import mx.edu.itm.link.plataformasge_res.models.Reporte

class SeleccionProyecto : AppCompatActivity() {
    private lateinit var binding: ActivitySeleccionProyectoBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionProyectoBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val alumno = Menu.alumnoLogeado
        val proyecto = intent.getSerializableExtra("PROYECTO") as Proyecto

        binding.nombreProyectoSeleccion.text = proyecto.nombreProyecto
        binding.nombreEmpresaSeleccion.text = proyecto.nombreEmpresa
        binding.lgacSeleccion.text = proyecto.lgac
        binding.descripcionSeleccionado.text = proyecto.descripcion

        //TODO: esto se va a hacer con un join cuando sea BD
        val profesoresDeLinea = ArrayList<Profesore>()

        for (p in Utils.database.getProfesores()) {
            //Si la linea del profesor es igual a la linea del proyecto
            if (p.linea == proyecto.lgac) {
                profesoresDeLinea.add(p)
            }
        }
        //Se asigna profesor random

        val profesorSeleccionado = profesoresDeLinea.random()
        binding.asesorSeleccion.text =
            "${profesorSeleccionado.nombre}\n${profesorSeleccionado.titulo}"


        val list = Utils.database.getReportes(alumno)

        binding.lvReportes.adapter =
            object : ReporteAdapter(this, R.layout.actividad_reporte, list) {
                override fun deleteActividad(actividadReporte: Reporte) {
                    Utils.database.borrarReporte(actividadReporte)
                    Toast.makeText(context, "Se ha borrado el reporte", Toast.LENGTH_SHORT).show()
                }

            }


        var numReportes = 0
        for (r in Menu.alumnoLogeado.reportes) {
            if (r.aprovado < 3) {
                numReportes++
            }
        }

        var aprovados = 0
        for (reporte in Utils.database.getReportes(alumno)) {
            //Se cuentan los reportes aprovados
            if (reporte.aprovado == 1) {
                aprovados++
            }
            //Si hay 3 reportes aprobados se deshabilita el boton de agregar reporte
            if (aprovados == 3) {
                binding.btnAgregarReporteSeleccion.isEnabled = false
            }
        }
        binding.btnAgregarReporteSeleccion.setOnClickListener {
            val intent = Intent(this, ReporteActivity::class.java)
            startActivity(intent)
        }
    }
}