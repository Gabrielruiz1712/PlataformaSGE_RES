package mx.edu.itm.link.plataformasge_res.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import mx.edu.itm.link.plataformasge_res.R
import mx.edu.itm.link.plataformasge_res.models.Reporte

abstract class AprobarReporteAdapter(
    val context: Context,
    val layout: Int,
    val lista: ArrayList<Reporte>
) : BaseAdapter() {
    override fun getCount(): Int {
        return lista.size
    }

    override fun getItem(position: Int): Any {
        return lista[position]
    }

    override fun getItemId(position: Int): Long {
        return -1
    }

    @SuppressLint("ResourceAsColor")
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val myView = inflater.inflate(layout, null)

        val titulo = myView.findViewById<TextView>(R.id.tituloReporteAprobar)
        val descripcion = myView.findViewById<TextView>(R.id.descripcionReporteAprobar)
        val btnAprobar = myView.findViewById<TextView>(R.id.btnAprobar)

        titulo.text = lista[position].titulo
        descripcion.text = lista[position].descripcion

        btnAprobar.setOnClickListener {
            aprobarReporte(lista[position])
            btnAprobar.setBackgroundColor(R.color.greenAprovado)
            btnAprobar.isEnabled = false
        }
        return myView
    }

    abstract fun aprobarReporte(reporte: Reporte)
}