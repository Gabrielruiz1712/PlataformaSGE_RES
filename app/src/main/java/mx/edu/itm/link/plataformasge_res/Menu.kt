package mx.edu.itm.link.plataformasge_res

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import mx.edu.itm.link.plataformasge_res.databinding.ActivityMenuBinding


class Menu : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alumno = intent.getSerializableExtra("ALUMNO")

        binding.btnResi.setOnClickListener {
            val intent = Intent(this, MenuResidencias::class.java)
            intent.putExtra("ALUMNO", alumno)
            startActivity(intent)
        }
    }
}