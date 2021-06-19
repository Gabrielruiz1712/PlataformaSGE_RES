package mx.edu.itm.link.plataformasge_res

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Menu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val alumno = intent.getStringExtra("ALUMNO")
    }
}