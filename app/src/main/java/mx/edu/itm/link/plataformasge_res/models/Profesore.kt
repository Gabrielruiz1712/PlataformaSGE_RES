package mx.edu.itm.link.plataformasge_res.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Profesore(
    @SerializedName("id")
    val id: Int,
    @SerializedName("linea")
    val linea: String,
    @SerializedName("nombre")
    val nombre: String,
    @SerializedName("titulo")
    val titulo: String
): Serializable