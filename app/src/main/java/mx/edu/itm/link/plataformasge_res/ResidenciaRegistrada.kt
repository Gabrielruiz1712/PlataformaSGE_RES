package mx.edu.itm.link.plataformasge_res

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mx.edu.itm.link.plataformasge_res.databinding.ActivityResidenciaRegistradaBinding
import mx.edu.itm.link.plataformasge_res.models.DependenciaPorAprobar
import java.lang.Exception

class ResidenciaRegistrada : AppCompatActivity() {
    private lateinit var binding: ActivityResidenciaRegistradaBinding

    //Se llena el listado de dependencias no aprobadas
    lateinit var dependenciasProAprobar: ArrayList<DependenciaPorAprobar>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResidenciaRegistradaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        try {
            dependenciasProAprobar = ArrayList()
            if (Utils.database.getDependencias().isNotEmpty()) {
                for (dep in Utils.database.getDependencias()) {
                    if (dep.aprobado == 0) {
                        dependenciasProAprobar.add(dep)
                    }
                }
            } else {
                binding.lblNoHay.visibility = View.VISIBLE
                binding.linearTemplate.visibility = View.INVISIBLE
            }

            if (dependenciasProAprobar.isNotEmpty()) {
                navegar(0)
            }

            var indice = 1
            var dependenciaSeleccionada = dependenciasProAprobar[0]

            binding.btnNext.setOnClickListener {

                if (dependenciasProAprobar.size == 0) {
                    binding.lblNoHay.visibility = View.VISIBLE
                    binding.linearTemplate.visibility = View.INVISIBLE
                } else {
                    //Se obtiene el tamaño del listado de las dependencias
                    val nDependencias = dependenciasProAprobar.size
                    //Si el indice es menor al numero de dependencias en la lista
                    if (indice >= nDependencias) {
                        indice = 0
                        navegar(indice)
                        dependenciaSeleccionada = dependenciasProAprobar[indice]
                    } else {
                        navegar(indice)
                        dependenciaSeleccionada = dependenciasProAprobar[indice]
                        indice++
                    }
                }
            }

            binding.btnAprobar.setOnClickListener {
                dependenciaSeleccionada.aprobado = 1
                try {
                    Utils.database.updateDependencia(dependenciaSeleccionada)
                    dependenciasProAprobar.remove(dependenciaSeleccionada)
                    Toast.makeText(this, "Se aprobo dependencia", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                    e.printStackTrace()
                    Toast.makeText(this, "Error al aprobar", Toast.LENGTH_SHORT).show()
                }
            }
            binding.btnRechazar.setOnClickListener {
                Utils.database.borrarDependencia(dependenciaSeleccionada)
                Toast.makeText(this, "Se Rechazo dependencia", Toast.LENGTH_SHORT).show()
                dependenciasProAprobar.remove(dependenciaSeleccionada)
            }

            binding.btnReportes.setOnClickListener {
                val intent = Intent(this, AprovarReportes::class.java)
                startActivity(intent)
            }
        } catch (e: Exception) {
            binding.lblNoHay.visibility = View.VISIBLE
            binding.linearTemplate.visibility = View.INVISIBLE
        }
    }

    fun navegar(position: Int) {
        val dependencias = dependenciasProAprobar
        //Para imprimir el indice de la navegacion
        binding.lblNavegacion.text = "${position + 1} de ${dependencias.size}"
        binding.nombreEmpresaReg.text = dependencias[position].nombreEmpresa
        binding.nombreProyectoReg.text = dependencias[position].nombreProyecto
        binding.lgacProyectoReg.text = dependencias[position].lgac
        binding.descripcionReg.text = dependencias[position].descripcionDelProyecto
    }
}