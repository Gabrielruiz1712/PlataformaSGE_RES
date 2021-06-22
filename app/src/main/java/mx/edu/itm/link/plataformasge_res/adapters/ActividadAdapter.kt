package mx.edu.itm.link.plataformasge_res.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import mx.edu.itm.link.plataformasge_res.R
import mx.edu.itm.link.plataformasge_res.models.ActividadReporte

abstract class ActividadAdapter(
    val context: Context,
    val layout: Int,
    val lista: ArrayList<ActividadReporte>
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

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val miView = inflater.inflate(layout, null)

        val titulo = miView.findViewById<TextView>(R.id.lblTituloActividad)
        val descipcion = miView.findViewById<TextView>(R.id.lblDescripcionActividad)

        val btnDeleteActividad = miView.findViewById<Button>(R.id.btnDeleteActividad)
        val btnEditActividad = miView.findViewById<Button>(R.id.btnEditActividadReporte)

        titulo.text = lista[position].titulo
        descipcion.text = lista[position].descripcion

        btnDeleteActividad.setOnClickListener {
            deleteActividad(lista[position])
        }
        btnEditActividad.setOnClickListener {
            editActividad(lista[position])
        }

        return miView
    }

    abstract fun deleteActividad(actividadReporte: ActividadReporte)
    abstract fun editActividad(actividadReporte: ActividadReporte)
}