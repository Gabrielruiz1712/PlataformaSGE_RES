package mx.edu.itm.link.plataformasge_res.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Proyecto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nombreProyecto")
    val nombreProyecto: String,
    @SerializedName("nombreEmpresa")
    val nombreEmpresa: String,
    @SerializedName("descripcion")
    val descripcion: String,
    @SerializedName("lgac")
    val lgac: String
): Serializable