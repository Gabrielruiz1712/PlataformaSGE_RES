package mx.edu.itm.link.plataformasge_res

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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

        val alumno = intent.getSerializableExtra("ALUMNO") as Alumno
        val proyecto = intent.getSerializableExtra("PROYECTO") as Proyecto

        binding.nombreProyectoDetalle.text = proyecto.nombreProyecto
        binding.empresaProyectoDetalle.text = proyecto.nombreEmpresa
        binding.lgacProyectoDetalle.text = proyecto.lgac
        binding.descripcionDetalle.text = proyecto.descripcion

        binding.btnQuieroEsteProyecto.setOnClickListener {

            val builder = AlertDialog.Builder(this)
            builder.setTitle("Estas seguro?")
            builder.setMessage("No podrás seleccionar otro diferente mas tarde")

            builder.setPositiveButton(android.R.string.yes) { dialog, which ->
                val intent = Intent(this, SeleccionProyecto::class.java)
                intent.putExtra("ALUMNO", alumno)
                intent.putExtra("PROYECTO", proyecto)

                //TODO: Poner el la BD que este alumno ya selecciono proyecto

                Utils.database.updateAlumno(alumno, )

                startActivity(intent)
            }

            builder.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext,"Selecciona otro proyecto", Toast.LENGTH_SHORT).show()
            }
            builder.show()

        }

    }
}