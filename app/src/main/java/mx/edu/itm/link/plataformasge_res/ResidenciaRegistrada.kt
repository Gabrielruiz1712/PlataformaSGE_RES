package mx.edu.itm.link.plataformasge_res

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mx.edu.itm.link.plataformasge_res.databinding.ActivityResidenciaRegistradaBinding

class ResidenciaRegistrada : AppCompatActivity() {
    private lateinit var binding: ActivityResidenciaRegistradaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResidenciaRegistradaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var indice = 0
        binding.btnNext.setOnClickListener {
            val nDependencias = Utils.database.getDependencias().size
            if (nDependencias != 0) {
                navegar(indice)
                if (indice < nDependencias) {
                    indice++
                } else {
                    indice = 0
                }
            }else{
                Toast.makeText(this, "No hay dependencias pro aprobar", Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun navegar(position: Int) {
        val dependencias = Utils.database.getDependencias()
        //Para imprimir el indice de la navegacion
        binding.lblNavegacion.text = "${position + 1} de ${dependencias.size}"
        binding.nombreEmpresaReg.text = dependencias[position].nombreEmpresa
        binding.nombreProyectoReg.text = dependencias[position].nombreProyecto
        binding.lgacProyectoReg.text = dependencias[position].lgac
        binding.descripcionReg.text = dependencias[position].descripcionDelProyecto
    }
}