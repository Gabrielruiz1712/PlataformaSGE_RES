package mx.edu.itm.link.plataformasge_res

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import mx.edu.itm.link.plataformasge_res.databinding.ActivityMenuBinding
import mx.edu.itm.link.plataformasge_res.databinding.ActivityMenuResidencias2Binding



class Menu : AppCompatActivity() {
    private lateinit var binding: ActivityMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val alumno = intent.getStringExtra("ALUMNO")

        binding.btnResi.setOnClickListener {
            val intent = Intent(this, MenuResidencias::class.java)
            startActivity(intent)
        }
    }
}