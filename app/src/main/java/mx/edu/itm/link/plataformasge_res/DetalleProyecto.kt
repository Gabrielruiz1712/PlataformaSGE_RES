package mx.edu.itm.link.plataformasge_res

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.edu.itm.link.plataformasge_res.databinding.ActivityDetalleProyectoBinding
import mx.edu.itm.link.plataformasge_res.models.Alumno
import mx.edu.itm.link.plataformasge_res.models.Proyecto

class DetalleProyecto : AppCompatActivity() {
    private lateinit var binding: ActivityDetalleProyectoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetalleProyectoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Este proyecto viene desde el adapter proyecto
        val proyecto = intent.getSerializableExtra("PROYECTO") as Proyecto

        binding.nombreProyectoDetalle.text = proyecto.nombreProyecto
        binding.empresaProyectoDetalle.text = proyecto.nombreEmpresa
        binding.lgacProyectoDetalle.text = proyecto.lgac
        binding.descripcionDetalle.text = proyecto.descripcion

        binding.btnQuieroEsteProyecto.setOnClickListener {

        }

    }
}