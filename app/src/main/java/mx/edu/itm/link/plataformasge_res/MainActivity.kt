package mx.edu.itm.link.plataformasge_res

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.edu.itm.link.plataformasge_res.data.DAOAlumno
import mx.edu.itm.link.plataformasge_res.data.DAOReporte
import mx.edu.itm.link.plataformasge_res.data.DataBase
import mx.edu.itm.link.plataformasge_res.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Utils.daoAlumno = DAOAlumno(this, "sge", null, 1)
        //Utils.daoReporte = DAOReporte(this, "sge", null, 1)
        Utils.database = DataBase(this, "sge", null, 1)

        //Base de datos
        //val bdString = resources.getString(R.string.baseDatos)
        //val bd = Gson().fromJson(bdString, DataBase::class.java) as DataBase


        val alumnos = Utils.database.getAlumnos()

        binding.btnLogin.setOnClickListener {
            val nc = binding.noControlLogin.text.toString()
            val pass = binding.contrasenaLogin.text.toString()

            for (alumno in alumnos) {
                if (alumno.nc.equals(nc) && alumno.pass.equals(pass)) {
                    val intent = Intent(this, Menu::class.java)
                    intent.putExtra("ALUMNO", alumno)
                    startActivity(intent)
                    break
                }
            }
        }
    }
}