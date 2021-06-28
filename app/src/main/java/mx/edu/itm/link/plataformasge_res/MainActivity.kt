package mx.edu.itm.link.plataformasge_res

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.edu.itm.link.plataformasge_res.data.DataBaseSQL
import mx.edu.itm.link.plataformasge_res.databinding.ActivityMainBinding
import mx.edu.itm.link.plataformasge_res.models.Alumno
import mx.edu.itm.link.plataformasge_res.models.Profesore

class MainActivity : AppCompatActivity() {

    companion object {
        lateinit var alumnoLogeado: Alumno
        lateinit var profesorLogeado: Profesore
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Utils.daoAlumno = DAOAlumno(this, "sge", null, 1)
        //Utils.daoReporte = DAOReporte(this, "sge", null, 1)
        Utils.database = DataBaseSQL(this, "sg4", null, 1)

        //Base de datos
        //val bdString = resources.getString(R.string.baseDatos)
        //val bd = Gson().fromJson(bdString, DataBase::class.java) as DataBase


        val alumnos = Utils.database.getAlumnos()
        val profesores = Utils.database.getProfesores()

        binding.btnLogin.setOnClickListener {
            val nc = binding.noControlLogin.text.toString()
            val pass = binding.contrasenaLogin.text.toString()

            for (alumno in alumnos) {
                if (alumno.nc.equals(nc) && alumno.pass.equals(pass)) {
                    alumnoLogeado = alumno
                    val intent = Intent(this, Menu::class.java)
                    intent.putExtra("ALUMNO", alumno)
                    startActivity(intent)
                    break
                }
            }

            for (profesor in profesores) {
                if (profesor.nc == nc && profesor.pass == pass) {
                    profesorLogeado = profesor
                    val intent = Intent(this, ResidenciaRegistrada::class.java)
                    startActivity(intent)
                    break
                }
            }
        }
    }
}