package mx.edu.itm.link.plataformasge_res.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import mx.edu.itm.link.plataformasge_res.Utils
import mx.edu.itm.link.plataformasge_res.models.Alumno
import mx.edu.itm.link.plataformasge_res.models.Reporte
import java.lang.Exception

class DataBase(
    context: Context?,
    name: String?,
    factory: SQLiteDatabase.CursorFactory?,
    version: Int
) : SQLiteOpenHelper(context, name, factory, version) {
    override fun onCreate(db: SQLiteDatabase?) {
        val alumnos = """
           create table alumno
            (
                idAlumno integer not null
                    constraint alumno_pk
                        primary key autoincrement,
                nombre text not null,
                apellidos text not null,
                nc text not null,
                pass text not null
            );
            
            create unique index alumno_idAlumno_uindex
                on alumno (idAlumno);
        """.trimIndent()

        val reporte = """
        create table reporte
            (
            	idReporte integer not null
            		constraint reportes_pk
            			primary key autoincrement
            		references alumno,
            	aprobado integer default 0 not null,
            	titulo text not null,
            	descripcion text not null,
            	alumno integer not null
            );
        """.trimIndent()

        db?.let {
            it.execSQL(alumnos)
            it.execSQL(reporte)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    //--------------------Alumnos------------------------
    @Throws
    fun altaAlumno(alumno: Alumno) {
        val db = writableDatabase

        val sql =
            "insert into alumno (nombre, apellidos, nc, pass) values ('${alumno.nombre}','${alumno.apellidos}', '${alumno.nc}' ,'${alumno.pass}')"

        db.execSQL(sql)

        db.close()
    }

    @Throws
    fun getAlumnos(): ArrayList<Alumno> {
        val db = readableDatabase

        val sql = "select * from alumno"

        val cursor = db.rawQuery(sql, null)

        val resultados = ArrayList<Alumno>()
        while (cursor.moveToNext()) {
            val alumno = Alumno(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                ArrayList()
            )
            alumno.reportes = getReportes(alumno)

            resultados.add(alumno)
        }
        db.close()

        return resultados
    }

    @Throws
    fun updateAlumno(viejo: Alumno, nuevo: Alumno) {
        val db = writableDatabase

        val sql =
            "UPDATE alumno SET nombre = '${nuevo.nombre}', apellidos = '${nuevo.apellidos}', nc = '${nuevo.nc}', pass = '${nuevo.pass}' WHERE idAlumno = ${viejo.id}"

        db.execSQL(sql)

        db.close()
    }

    @Throws
    fun borrarAlumno(alumno: Alumno) {
        val db = writableDatabase

        val sql = "DELETE FROM alumno WHERE id=${alumno.id}"

        db.execSQL(sql)

        db.close()
    }

    //------------------------ Reporte --------------------------
    @Throws
    fun altaReporte(reporte: Reporte, alumno: Alumno) {
        val db = writableDatabase

        val sql =
            "insert into reporte (aprobado, titulo, descripcion, alumno) values (0,'${reporte.titulo}','${reporte.descripcion}', ${alumno.id});"

        db.execSQL(sql)

        db.close()
    }

    @Throws
    fun getReportes(alumno: Alumno): ArrayList<Reporte> {
        val db = readableDatabase

        val sql =
            "select idReporte, aprobado, titulo, descripcion, alumno from reporte where alumno like ${alumno.id}"

        try {
            val cursor = db.rawQuery(sql, null)

            val resultados = ArrayList<Reporte>()
            while (cursor.moveToNext()) {
                val contact = Reporte(
                    cursor.getInt(0),
                    cursor.getInt(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getInt(4)
                )

                resultados.add(contact)
            }
            db.close()
            return resultados
        } catch (e: Exception){
            e.printStackTrace()
        }
        return ArrayList()
    }

    @Throws
    fun updateReporte(viejo: Reporte, nuevo: Reporte) {
        val db = writableDatabase

        val sql =
            "UPDATE reporte SET aprobado= ${nuevo.aprovado}, titulo= '${nuevo.titulo}', descripcion = '${nuevo.descripcion}' WHERE idReporte = ${viejo.id}"

        db.execSQL(sql)

        db.close()
    }

    @Throws
    fun borrarReporte(reporte: Reporte) {
        val db = writableDatabase

        val sql = "DELETE FROM reporte WHERE id=${reporte.id}"

        db.execSQL(sql)

        db.close()
    }
}