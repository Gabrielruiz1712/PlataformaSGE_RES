package mx.edu.itm.link.plataformasge_res

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import mx.edu.itm.link.plataformasge_res.adapters.ActividadAdapter
import mx.edu.itm.link.plataformasge_res.databinding.ActivityReporteBinding
import mx.edu.itm.link.plataformasge_res.models.ActividadReporte
import mx.edu.itm.link.plataformasge_res.models.DataBase

class Reporte : AppCompatActivity() {
    private lateinit var binding: ActivityReporteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReporteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Base de datos
        val bdString = resources.getString(R.string.baseDatos)
        val bd = Gson().fromJson(bdString, DataBase::class.java) as DataBase

        //TODO: Traer  reportes de la BD

        val list = Menu.alumnoLogeado.reportes[0].actividadReporte

        binding.lvTareasReporte.adapter = object : ActividadAdapter(this, R.layout.actividad_reporte, list){
            override fun deleteActividad(actividadReporte: ActividadReporte) {
                TODO("Not yet implemented")
            }

            override fun editActividad(actividadReporte: ActividadReporte) {
                TODO("Not yet implemented")
            }

        }
    }
}