package mx.edu.itm.link.plataformasge_res.data

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import mx.edu.itm.link.plataformasge_res.models.*
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
                pass text not null,
                tieneProyecto integer default 0 not null
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
                titulo text not null,
                nc text not null,
                pass text not null,
                proyecto integer,
                constraint profesor_proyecto_idProyecto_idProyecto_fk
                    foreign key (idProfesor, idProfesor) references proyecto (idProyecto, idProyecto)
            );
            
            create unique index profesor_id_uindex
                on profesor (idProfesor);
        """.trimIndent()

        val proyecto = """
            create table proyecto
            (
                idProyecto integer not null
                    constraint proyecto_pk
                        primary key autoincrement
                    references alumno,
                nombre text not null,
                empresa text not null,
                descripcion text not null,
                lgac text not null,
                seleccionado integer default 0 not null
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

        //-------------------- Inserts Alumnos -------------------
        val insertAlumnos1 = """
            insert into alumno (nombre, apellidos, nc, pass, tieneProyecto) values ('Emmanuel', 'Esquivel', '18120215', '123', 0);
        """.trimIndent()

        val insertAlumnos2 = """
            insert into alumno (nombre, apellidos, nc, pass, tieneProyecto) values ('Gabriel', 'Ruiz', '17121102', '123', 1);
        """.trimIndent()

        val insertAlumnos3 = """
            insert into alumno (nombre, apellidos, nc, pass, tieneProyecto) values ('Alberto', 'Oseguera', '17121095', '123', 0);
        """.trimIndent()

        //--------------------------REPORTE ----------------------------------------
        val insertReporte1 = """
            insert into reporte (aprobado, titulo, descripcion, alumno) values (1, 'Combati hasta que mori pero sobrevivi', 'Hoy en la entrega del proyecto de la segunda unidad casi reprobamos', 2);
        """.trimIndent()

        val insertReporte2 = """
            insert into reporte (aprobado, titulo, descripcion, alumno) values (0, 'Combati hasta que mori pero sobrevivi', 'Hoy en la entrega del proyecto de la segunda unidad casi reprobamos', 2);
        """.trimIndent()

        //-----------------------------INSERTS Profesor ------------------------------
        val insertProfesor1 = """
                insert into profesor (linea, nombre, titulo, nc, pass) values ('ciencias del universo', 'Carol Aidee', 'Ing. ciencias del universo', '1', '123');
        """.trimIndent()

        val insertProfesor2 = """
                insert into profesor (linea, nombre, titulo, nc, pass) values ('Energ??as alternativas del espacio', 'Alejandro Amaro', 'Ing. de energia obscura', '2', '123');
        """.trimIndent()

        val insertProfesor3 = """
                insert into profesor (linea, nombre, titulo, nc, pass) values ('Redaccion y filosof??a profunda', 'Raymundo', 'Lic. Redaccion y filosof??a profunda', '3', '123');
        """.trimIndent()

        val insertProfesor4 = """
                insert into profesor (linea, nombre, titulo, nc, pass) values ('Energ??as alternativas del espacio', 'Abel Pintor', 'Ing. de energia obscura', '4', '123');
        """.trimIndent()
        //-----------------------------INSERTS Proyectos ------------------------------
        val insertProyectos1 = """
            insert into proyecto (nombre, empresa, descripcion, lgac, seleccionado) values ('Reclutar aliens','Area 51','Reclutar cientificos del planeta X12.0 para aplicaciones de intervuelo','ciencias del universo', 0);
        """.trimIndent()
        val insertProyectos2 = """
            insert into proyecto (nombre, empresa, descripcion, lgac, seleccionado) values ('Energia desconocida procedente del Ether','Energy Mars y quantum computing','Liderar una flotilla de ingenieros de energia con el fin de obtener informacion sobre esa fuente desconocida','Energ??as alternativas del espacio', 0);
        """.trimIndent()
        val insertProyectos3 = """
            insert into proyecto (nombre, empresa, descripcion, lgac, seleccionado) values ('Diccionario para entender a mi novia','IT-Morelia','Redactar una guia eficaz dirigida al Emmanuel para desbloquear el tope del conocimiento humano','Redaccion y filosof??a profunda', 0);
        """.trimIndent()
//        // ------------------------INSERTS Dependencia -------------------------------------
//        val insertDependencia1 = """
//            insert into dependencia (idAlumnoRegistrador, nombreEmpresa, nombreProyecto, descripcionProyecto, aprobado, lgac) values (1,'Zamoris-B','Montar centro de comunicaciones interestelar','Hay una plaga de reptiles radioactivos que no nos permite hacerlo, hemos delegado la mision a un becario del tec',0,'Energ??as alternativas del espacio');
//        """.trimIndent()
//
//        val insertDependencia2 = """
//            insert into dependencia (idAlumnoRegistrador, nombreEmpresa, nombreProyecto, descripcionProyecto, aprobado, lgac) values (1,'ITM','Desarrollo de formula anticovid','Desarrollo de una formula para desinfectar superficies incluyendo covid',0,'Salud publica');
//        """.trimIndent()

        try {


            db?.let {
                it.execSQL(alumnos)
                it.execSQL(reporte)
                it.execSQL(profesor)
                it.execSQL(proyecto)
                it.execSQL(dependencia)

                it.execSQL(insertAlumnos1)
                it.execSQL(insertAlumnos2)
                it.execSQL(insertAlumnos3)

                it.execSQL(insertReporte1)
                it.execSQL(insertReporte2)

                it.execSQL(insertProfesor1)
                it.execSQL(insertProfesor2)
                it.execSQL(insertProfesor3)
                it.execSQL(insertProfesor4)

                it.execSQL(insertProyectos1)
                it.execSQL(insertProyectos2)
                it.execSQL(insertProyectos3)

//                it.execSQL(insertDependencia1)
//                it.execSQL(insertDependencia2)
            }
        } catch (e: Exception) {
            println("ERROR: Al dar de alta la BD")
            e.printStackTrace()
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
                cursor.getInt(5),
                ArrayList()
            )
            alumno.reportes = getReportes(alumno)

            resultados.add(alumno)
        }
        db.close()

        return resultados
    }

    @Throws
    fun updateAlumno(a: Alumno) {
        val db = writableDatabase

        val data = ContentValues()
        data.put("tieneProyecto", a.tieneProyecto)

        db.update("alumno", data, "idAlumno = ?", arrayOf(a.id.toString()))

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
    fun getTodosReportes(): ArrayList<Reporte> {
        val db = readableDatabase

        val sql =
            "select idReporte, aprobado, titulo, descripcion, alumno from reporte"

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
    fun updateReporte(nuevo: Reporte) {
        val db = writableDatabase

        val sql =
            "UPDATE reporte SET aprobado= ${nuevo.aprovado} WHERE idReporte = ${nuevo.id}"

        db.execSQL(sql)

        db.close()
    }

    @Throws
    fun borrarReporte(reporte: Reporte) {
        val db = writableDatabase

        val sql = "DELETE FROM reporte WHERE idReporte=${reporte.id}"

        try {
            db.execSQL(sql)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        db.close()
    }

    //--------------------Profesor------------------------

    @Throws
    fun getProfesores(): ArrayList<Profesore> {
        val db = readableDatabase

        val sql = "select idProfesor, linea, nombre, titulo, nc, pass from profesor"

        val cursor = db.rawQuery(sql, null)

        val resultados = ArrayList<Profesore>()
        while (cursor.moveToNext()) {
            val clase = Profesore(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getString(5)
            )

            resultados.add(clase)
        }
        db.close()

        return resultados
    }

    //--------------------Proyecto------------------------
    @Throws
    fun altaProyecto(p: Proyecto) {
        val db = writableDatabase

        val sql =
            "insert into proyecto (nombre, empresa, descripcion, lgac) values ('${p.nombreProyecto}', '${p.nombreEmpresa}', '${p.descripcion}', '${p.lgac}',)"

        db.execSQL(sql)

        db.close()
    }

    @Throws
    fun proeyectoDeAlumnoByID(idAlumno: Int): ArrayList<Proyecto> {
        val db = readableDatabase

        val sql =
            "select p.idProyecto, p.nombre, p.empresa, p.descripcion, p.lgac from alumno a inner join proyecto p on a.idAlumno = p.idProyecto where a.idAlumno like ${idAlumno}; "

        val cursor = db.rawQuery(sql, null)

        val resultados = ArrayList<Proyecto>()
        while (cursor.moveToNext()) {
            val proyecto = Proyecto(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4)
            )
            resultados.add(proyecto)
        }
        db.close()

        return resultados
    }

    @Throws
    fun getProyectos(): ArrayList<Proyecto> {
        val db = readableDatabase

        val sql = "select idProyecto, nombre, empresa, descripcion, lgac from proyecto"

        val cursor = db.rawQuery(sql, null)

        val resultados = ArrayList<Proyecto>()
        while (cursor.moveToNext()) {
            val clase = Proyecto(
                cursor.getInt(0),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
            )

            resultados.add(clase)
        }
        db.close()

        return resultados
    }

    @Throws
    fun updateProyecto(p: Proyecto) {
        val db = writableDatabase
        val data = ContentValues()
        data.put("nombre", p.nombreProyecto)
        data.put("empresa", p.nombreEmpresa)
        data.put("descripcion", p.descripcion)
        data.put("lgac", p.lgac)

        db.update("proyecto", data, "idProyecto = ?", arrayOf(p.id.toString()))

        db.close()
    }

    //--------------------Dependencia------------------------
    @Throws
    fun altaDependencia(d: DependenciaPorAprobar) {
        val db = writableDatabase

        val sql =
            """
                insert into dependencia (idAlumnoRegistrador, nombreEmpresa, nombreProyecto, descripcionProyecto, aprobado, lgac)
                values ('${d.idAlumnoRegistrador}','${d.nombreEmpresa}','${d.nombreProyecto}', '${d.descripcionDelProyecto}', ${d.aprobado},'${d.lgac}');
            """.trimIndent()

        try {
            db.execSQL(sql)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        db.close()
    }

    @Throws
    fun getDependencias(): ArrayList<DependenciaPorAprobar> {
        val db = readableDatabase

        val sql =
            "select idDependencia, idAlumnoRegistrador, nombreEmpresa, nombreProyecto, descripcionProyecto, aprobado, lgac from dependencia"

        //TODO: No jala las dependencias de la BD
        val cursor = db.rawQuery(sql, null)
        val resultados = ArrayList<DependenciaPorAprobar>()
        while (cursor.moveToNext()) {
            val clase = DependenciaPorAprobar(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5),
                cursor.getString(6),
            )

            resultados.add(clase)
        }
        db.close()

        return resultados
    }

    @Throws
    fun getDependenciasDeAlumno(alumno: Alumno): ArrayList<DependenciaPorAprobar> {
        val db = readableDatabase

        val sql =
            "select idDependencia, idAlumnoRegistrador, nombreEmpresa, nombreProyecto, descripcionProyecto, aprobado, lgac from dependencia inner join alumno a on a.idAlumno = dependencia.idDependencia where a.idAlumno like ${alumno.id};"

        //TODO: No jala las dependencias de la BD
        val cursor = db.rawQuery(sql, null)
        val resultados = ArrayList<DependenciaPorAprobar>()
        while (cursor.moveToNext()) {
            val clase = DependenciaPorAprobar(
                cursor.getInt(0),
                cursor.getInt(1),
                cursor.getString(2),
                cursor.getString(3),
                cursor.getString(4),
                cursor.getInt(5),
                cursor.getString(6),
            )

            resultados.add(clase)
        }
        db.close()

        return resultados
    }

    @Throws
    fun updateDependencia(d: DependenciaPorAprobar) {
        val db = writableDatabase

        val data = ContentValues()
        data.put("aprobado", d.aprobado)

        db.update("dependencia", data, "idDependencia = ?", arrayOf(d.id.toString()))

        db.close()
    }

    @Throws
    fun borrarDependencia(dependenciaPorAprobar: DependenciaPorAprobar) {
        val db = writableDatabase

        val sql = "DELETE FROM dependencia WHERE idDependencia=${dependenciaPorAprobar.id}"

        try {
            db.execSQL(sql)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        db.close()
    }

}