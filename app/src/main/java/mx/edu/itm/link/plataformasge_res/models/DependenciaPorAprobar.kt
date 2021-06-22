package mx.edu.itm.link.plataformasge_res.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DependenciaPorAprobar(
    @SerializedName("id")
    val id: Int,
    @SerializedName("idAlumnoRegistrador")
    val idAlumnoRegistrador: Int,
    @SerializedName("nombreEmpresa")
    val nombreEmpresa: String,
    @SerializedName("nombreProyecto")
    val nombreProyecto: String,
    @SerializedName("descripcionDelProyecto")
    val descripcionDelProyecto: String,
    @SerializedName("aprobado")
    val aprobado: Boolean,
    @SerializedName("lgac")
    val lgac: String
) : Serializable