package mx.edu.itm.link.plataformasge_res.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import mx.edu.itm.link.plataformasge_res.R
import mx.edu.itm.link.plataformasge_res.models.Reporte

abstract class ReporteAdapter(
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
        val miView = inflater.inflate(layout, null)

        val titulo = miView.findViewById<TextView>(R.id.lblTituloActividad)
        val descipcion = miView.findViewById<TextView>(R.id.lblDescripcionActividad)
        val esAprobado = miView.findViewById<TextView>(R.id.lblEsAprovado)

        val btnDeleteActividad = miView.findViewById<Button>(R.id.btnDeleteActividad)

        //Si el reporte esta aprovado
        if (lista[position].aprovado == 1){
            //Se cambia el texto a aprobado
            esAprobado.text = "Aprovado"
            //Se cambia el color de texto a verde
            esAprobado.setTextColor(R.color.greenAprovado)

            //se deshabilita el boton de eliminar
            btnDeleteActividad.isEnabled = false
        }

        titulo.text = lista[position].titulo
        descipcion.text = lista[position].descripcion

        btnDeleteActividad.setOnClickListener {
            deleteActividad(lista[position])
        }

        return miView
    }

    abstract fun deleteActividad(actividadReporte: Reporte)
}