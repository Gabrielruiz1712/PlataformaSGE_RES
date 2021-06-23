package mx.edu.itm.link.plataformasge_res.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Reporte(
    @SerializedName("id")
    val id: Int,
    @SerializedName("aprovado")
    val aprovado: Int,
    @SerializedName("titulo")
    val titulo: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("alumno")
    val alumno: Int
) : Serializable