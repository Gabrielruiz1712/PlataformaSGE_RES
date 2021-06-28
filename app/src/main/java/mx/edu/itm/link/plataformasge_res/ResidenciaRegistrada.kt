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

        navegar(0)
        var indice = 1
        binding.btnNext.setOnClickListener {
            //Se obtiene el tamaño del listado de las dependencias
            val nDependencias = Utils.database.getDependencias().size
            //Si el indice es menor al numero de dependencias en la lista
            if (indice >= nDependencias) {
                indice = 0
                navegar(indice)
            } else {
                navegar(indice)
                indice++
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