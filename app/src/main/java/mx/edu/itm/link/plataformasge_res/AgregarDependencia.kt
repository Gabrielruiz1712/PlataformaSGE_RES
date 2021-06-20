package mx.edu.itm.link.plataformasge_res

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.edu.itm.link.plataformasge_res.databinding.ActivityAgregarDependenciaBinding
import mx.edu.itm.link.plataformasge_res.models.Alumno

class AgregarDependencia : AppCompatActivity() {
    private lateinit var binding: ActivityAgregarDependenciaBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgregarDependenciaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alumno = intent.getSerializableExtra("ALUMNO") as Alumno

        binding.numeroControlR.setText(alumno.nc)
        binding.nombreR.setText("${alumno.nombre} ${alumno.apellidos}")

        //TODO: Hacer todo lo relacionado con la alta de dependencia en la BD
    }
}