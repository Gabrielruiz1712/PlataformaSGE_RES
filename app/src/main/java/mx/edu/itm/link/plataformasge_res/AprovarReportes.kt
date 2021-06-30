package mx.edu.itm.link.plataformasge_res

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.edu.itm.link.plataformasge_res.adapters.AprobarReporteAdapter
import mx.edu.itm.link.plataformasge_res.databinding.ActivityAprovarReportesBinding
import mx.edu.itm.link.plataformasge_res.models.Reporte

class AprovarReportes : AppCompatActivity() {
    private lateinit var binding: ActivityAprovarReportesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAprovarReportesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val reportes = ArrayList<Reporte>()

        for (r in Utils.database.getTodosReportes()){
            if (r.aprovado != 1){
                reportes.add(r)
            }
        }

        binding.lvReportesPorAprobar.adapter = object :
            AprobarReporteAdapter(
                this,
                R.layout.aprobar_reporte,
                reportes
            ) {
            override fun aprobarReporte(reporte: Reporte) {
                reporte.aprovado = 1
                Utils.database.updateReporte(reporte)
            }
        }
    }
}