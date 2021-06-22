package mx.edu.itm.link.plataformasge_res.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Alumno(
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("apellidos")
    val apellidos: String,
    @SerializedName("nc")
    val nc: String,
    @SerializedName("pass")
    val pass: String,
    @SerializedName("reportes")
    val reportes: ArrayList<Reporte>
) :Serializable