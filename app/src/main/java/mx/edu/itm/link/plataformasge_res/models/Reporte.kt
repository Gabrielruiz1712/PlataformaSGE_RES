package mx.edu.itm.link.plataformasge_res.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Reporte(
    @SerializedName("parte")
    val parte: Int,
    @SerializedName("version")
    val version: Int,
    @SerializedName("aprovado")
    val aprovado: Boolean,
    @SerializedName("actividadReporte")
    val actividadReporte: ArrayList<ActividadReporte>
) : Serializable