package mx.edu.itm.link.plataformasge_res.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import mx.edu.itm.link.plataformasge_res.Utils
import mx.edu.itm.link.plataformasge_res.models.Alumno
import mx.edu.itm.link.plataformasge_res.models.Reporte
import java.lang.Exception

class DataBaseSQL(
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

        val profesor = """
            create table profesor
            (
            	idProfesor integer not null
            		constraint profesor_pk
            			primary key autoincrement,
            	linea text not null,
            	nombre text not null,
            	titulo text not null
            );

            create unique index profesor_id_uindex
            	on profesor (idProfesor);

        """.trimIndent()

        val proyecto = """
            create table proyecto
            (
                idProyecto integer not null
                    constraint proyecto_pk
                        primary key autoincrement,
                nombre text not null,
                empresa text not null,
                descripcion text not null,
                lgac text not null
            );
            
            create unique index proyecto_idProyecto_uindex
                on proyecto (idProyecto);
        """.trimIndent()

        val dependencia = """
            create table dependencia
            (
                idDependencia integer
                    constraint dependencia_pk
                        primary key autoincrement
                    references alumno,
                idAlumnoRegistrador integer not null,
                nombreEmpresa text not null,
                nombreProyecto text not null,
                descripcionProyecto text not null,
                aprobado integer default 0 not null,
                lgac text not null
            );
            
            create unique index dependencia_idDependencia_uindex
                on dependencia (idDependencia);
        """.trimIndent()

        //-------------------- Inserts -------------------
        val insertAlumnos = """
            insert into alumno (nombre, apellidos, nc, pass)
            values ('Emmanuel', 'Esquivel', '18120215', '123');
            
            INSERT into alumno (nombre, apellidos, nc, pass)
            values ('Alberto', 'Oseguera', '17121095', '123');
            
            insert into alumno (nombre, apellidos, nc, pass)
            values ('Gabriel', 'Ruiz', '17121102', '123');
        """.trimIndent()

        val insertReporte = """
            insert into reporte (aprobado, titulo, descripcion, alumno)
            values (0, 'Me pusieron a trapear :v',
                    'Hoy no habia nada que hacer asi que me puse a trapear el yogurt que tire',
                    1);
            
            insert into reporte (aprobado, titulo, descripcion, alumno)
            values (0, 'Combati hasta que mori pero sobrevivi',
                    'Hoy en la entrega del proyecto de la segunda unidad casi reprobamos',
                    2);
        """.trimIndent()

        val insertProfesor = """
                insert into profesor (linea, nombre, titulo)
                values ('ciencias del universo','Carol Aidee','Ing. ciencias del universo');

                insert into profesor (linea, nombre, titulo)
                values ('Energías alternativas del espacio','Alejandro Amaro','Ing. de energia obscura');

                insert into profesor (linea, nombre, titulo)
                values ('Redaccion y filosofía profunda','Raymundo','Lic. Redaccion y filosofía profunda');

                insert into profesor (linea, nombre, titulo)
                values ('Energías alternativas del espacio','Abel Pintor','Ing. de energia obscura');
        """.trimIndent()

        val insertProyectos = """
            insert into proyecto (nombre, empresa, descripcion, lgac)
            values ('Reclutar aliens','Area 51','Reclutar cientificos del planeta X12.0 para aplicaciones de intervuelo','ciencias del universo');

            insert into proyecto (nombre, empresa, descripcion, lgac)
            values ('Energia desconocida procedente del Ether','Energy Mars y quantum computing','Liderar una flotilla de ingenieros de energia con el fin de obtener informacion sobre esa fuente desconocida','Energías alternativas del espacio');

            insert into proyecto (nombre, empresa, descripcion, lgac)
            values ('Diccionario para entender a mi novia','IT-Morelia','Redactar una guia eficaz dirigida al Emmanuel para desbloquear el tope del conocimiento humano','Redaccion y filosofía profunda');
        """.trimIndent()

        val insertDependencia = """
            insert into dependencia (idAlumnoRegistrador, nombreEmpresa, nombreProyecto, descripcionProyecto, aprobado, lgac)
            values (1,'Zamoris-B','Montar centro de comunicaciones interestelar','Hay una plaga de reptiles radioactivos que no nos permite hacerlo, hemos delegado la mision a un becario del tec',1,'Energías alternativas del espacio');
        """.trimIndent()

        db?.let {
            it.execSQL(alumnos)
            it.execSQL(reporte)
            it.execSQL(profesor)
            it.execSQL(proyecto)
            it.execSQL(dependencia)
            it.execSQL(insertAlumnos)
            it.execSQL(insertReporte)
            it.execSQL(insertProfesor)
            it.execSQL(insertProyectos)
            it.execSQL(insertDependencia)
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
        } catch (e: Exception) {
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