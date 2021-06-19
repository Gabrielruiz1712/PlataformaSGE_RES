package mx.edu.itm.link.plataformasge_res.models


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class DataBase(
    @SerializedName("proyectos")
    val proyectos: ArrayList<Proyecto>,
    @SerializedName("profesores")
    val profesores: ArrayList<Profesore>,
    @SerializedName("alumnos")
    val alumnos: ArrayList<Alumno>
): Serializable