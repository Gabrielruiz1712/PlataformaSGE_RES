package mx.edu.itm.link.plataformasge_res.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class ActividadReporte(
    @SerializedName("titulo")
    val titulo: String,
    @SerializedName("descripcion")
    val descripcion: String
) : Serializable