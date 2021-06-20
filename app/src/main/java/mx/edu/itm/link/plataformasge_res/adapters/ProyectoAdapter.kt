package mx.edu.itm.link.plataformasge_res.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
import mx.edu.itm.link.plataformasge_res.DetalleProyecto
import mx.edu.itm.link.plataformasge_res.Menu
import mx.edu.itm.link.plataformasge_res.R
import mx.edu.itm.link.plataformasge_res.models.Proyecto

class ProyectoAdapter(val context: Context, val layout: Int, val lista: ArrayList<Proyecto>) :
    BaseAdapter() {
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

        val nombreProyecto = miView.findViewById<TextView>(R.id.nombreProyecto)
        val nombreEmpresa = miView.findViewById<TextView>(R.id.nombreEmpresaProyeto)
        val descripcionProyecto = miView.findViewById<TextView>(R.id.descripcionProyecto)
        val lgacProyecto = miView.findViewById<TextView>(R.id.lgacProyecto)
        val cardProyecto = miView.findViewById<CardView>(R.id.cardProyecto)
        val btnIr = miView.findViewById<Button>(R.id.btnIrProyecto)

        nombreProyecto.text = lista[position].nombreProyecto
        nombreEmpresa.text = lista[position].nombreEmpresa
        descripcionProyecto.text = lista[position].descripcion
        lgacProyecto.text = lista[position].lgac

        cardProyecto.setOnClickListener {
            val intent = Intent(context, DetalleProyecto::class.java)
            intent.putExtra("PROYECTO", lista[position])
            context.startActivity(intent)
        }
        btnIr.setOnClickListener {
            val intent = Intent(context, DetalleProyecto::class.java)
            intent.putExtra("PROYECTO", lista[position])
            context.startActivity(intent)
        }

        return miView
    }
}
