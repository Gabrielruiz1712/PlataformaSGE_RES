package mx.edu.itm.link.plataformasge_res

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import mx.edu.itm.link.plataformasge_res.databinding.ActivityReporteBinding
import mx.edu.itm.link.plataformasge_res.models.DataBase
import mx.edu.itm.link.plataformasge_res.models.Reporte

class ReporteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityReporteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReporteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Base de datos
        //val bdString = resources.getString(R.string.baseDatos)
        //val bd = Gson().fromJson(bdString, DataBase::class.java) as DataBase

        binding.fabAddTareaReporte.setOnClickListener {

            val titulo = binding.tituloReporte.text.toString()
            val descripcion = binding.editDescripcion.text.toString()
            val r = Reporte(0, 0, titulo, descripcion, Menu.alumnoLogeado.id)

            if (titulo != "" && descripcion != "") {
                Menu.alumnoLogeado.reportes.add(r)
                Utils.database.altaReporte(r, Menu.alumnoLogeado)

                Toast.makeText(this, "Se agrego con exito", Toast.LENGTH_SHORT).show()
            }

            finish()
        }

    }
}