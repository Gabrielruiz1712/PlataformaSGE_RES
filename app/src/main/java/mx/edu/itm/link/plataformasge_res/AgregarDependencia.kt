package mx.edu.itm.link.plataformasge_res

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import mx.edu.itm.link.plataformasge_res.databinding.ActivityAgregarDependenciaBinding
import mx.edu.itm.link.plataformasge_res.models.Alumno
import mx.edu.itm.link.plataformasge_res.models.DependenciaPorAprobar
import java.lang.Exception

class AgregarDependencia : AppCompatActivity() {
    private lateinit var binding: ActivityAgregarDependenciaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarDependenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alumno = intent.getSerializableExtra("ALUMNO") as Alumno

        binding.numeroControlR.setText(alumno.nc)
        binding.nombreR.setText("${alumno.nombre} ${alumno.apellidos}")

        binding.btnRegistro.setOnClickListener {
            val idAlumno = MainActivity.alumnoLogeado.id
            val nombreEmpresa = binding.LugarR.text.toString()
            val nombreProyecto = binding.nombreProyectoD.text.toString()
            val descripcionProyecto = binding.ProyectoD.text.toString()
            val aprovado = 0
            val lgac = binding.lgacProyectoD.text.toString()

            val dependencia = DependenciaPorAprobar(
                0,
                idAlumno,
                nombreEmpresa,
                nombreProyecto,
                descripcionProyecto,
                aprovado,
                lgac
            )
            try {
                Utils.database.altaDependencia(dependencia)
                Toast.makeText(this, "Se ha dado de alta", Toast.LENGTH_SHORT).show()
                finish()
            }catch (e: Exception){
                Toast.makeText(this, "Se ha fallado al dar de alta", Toast.LENGTH_SHORT).show()
                e.printStackTrace()
            }
        }

    }
}