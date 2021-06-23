package mx.edu.itm.link.plataformasge_res.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import mx.edu.itm.link.plataformasge_res.Utils
import mx.edu.itm.link.plataformasge_res.models.Alumno

class DAOAlumno(
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

        db?.let {
            it.execSQL(alumnos)
        }
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

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
            alumno.reportes = Utils.daoReporte.getReportes(alumno)

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

}