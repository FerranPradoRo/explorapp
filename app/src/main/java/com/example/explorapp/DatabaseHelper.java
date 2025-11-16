package com.example.explorapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Nombre y versión de la base de datos
    private static final String DATABASE_NAME = "explorapp.db";
    private static final int DATABASE_VERSION = 1;

    // ==================== TABLA: usuarios ====================
    public static final String TABLE_USUARIOS = "usuarios";
    public static final String COL_USUARIO_ID = "usuario_id";
    public static final String COL_USUARIO_NOMBRE = "nombre";
    public static final String COL_USUARIO_APELLIDO = "apellido";
    public static final String COL_USUARIO_EMAIL = "email";
    public static final String COL_USUARIO_PASSWORD_HASH = "password_hash";
    public static final String COL_USUARIO_FECHA_REGISTRO = "fecha_registro";
    public static final String COL_USUARIO_PAIS_ORIGEN = "pais_origen";
    public static final String COL_USUARIO_FOTO_PERFIL = "foto_perfil";
    public static final String COL_USUARIO_ACTIVO = "activo";

    // ==================== TABLA: categorias ====================
    public static final String TABLE_CATEGORIAS = "categorias";
    public static final String COL_CATEGORIA_ID = "categoria_id";
    public static final String COL_CATEGORIA_NOMBRE = "nombre";
    public static final String COL_CATEGORIA_DESCRIPCION = "descripcion";
    public static final String COL_CATEGORIA_ICONO = "icono";
    public static final String COL_CATEGORIA_COLOR = "color";

    // ==================== TABLA: localizaciones ====================
    public static final String TABLE_LOCALIZACIONES = "localizaciones";
    public static final String COL_LOCALIZACION_ID = "localizacion_id";
    public static final String COL_LOCALIZACION_NOMBRE = "nombre";
    public static final String COL_LOCALIZACION_DESCRIPCION = "descripcion";
    public static final String COL_LOCALIZACION_LATITUD = "latitud";
    public static final String COL_LOCALIZACION_LONGITUD = "longitud";
    public static final String COL_LOCALIZACION_DIRECCION = "direccion";
    public static final String COL_LOCALIZACION_CIUDAD = "ciudad";
    public static final String COL_LOCALIZACION_CATEGORIA_ID = "categoria_id";
    public static final String COL_LOCALIZACION_COSTO_PROMEDIO = "costo_promedio";
    public static final String COL_LOCALIZACION_HORARIO_APERTURA = "horario_apertura";
    public static final String COL_LOCALIZACION_HORARIO_CIERRE = "horario_cierre";
    public static final String COL_LOCALIZACION_TELEFONO = "telefono";
    public static final String COL_LOCALIZACION_SITIO_WEB = "sitio_web";
    public static final String COL_LOCALIZACION_POPULARIDAD_SCORE = "popularidad_score";
    public static final String COL_LOCALIZACION_FECHA_CREACION = "fecha_creacion";
    public static final String COL_LOCALIZACION_ACTIVO = "activo";

    // ==================== TABLA: imagenes_localizaciones ====================
    public static final String TABLE_IMAGENES_LOCALIZACIONES = "imagenes_localizaciones";
    public static final String COL_IMAGEN_ID = "imagen_id";
    public static final String COL_IMAGEN_LOCALIZACION_ID = "localizacion_id";
    public static final String COL_IMAGEN_URL = "url_imagen";
    public static final String COL_IMAGEN_ES_PRINCIPAL = "es_principal";
    public static final String COL_IMAGEN_ORDEN = "orden";
    public static final String COL_IMAGEN_FECHA_SUBIDA = "fecha_subida";

    // ==================== TABLA: reviews ====================
    public static final String TABLE_REVIEWS = "reviews";
    public static final String COL_REVIEW_ID = "review_id";
    public static final String COL_REVIEW_USUARIO_ID = "usuario_id";
    public static final String COL_REVIEW_LOCALIZACION_ID = "localizacion_id";
    public static final String COL_REVIEW_CALIFICACION = "calificacion";
    public static final String COL_REVIEW_COMENTARIO = "comentario";
    public static final String COL_REVIEW_FECHA_VISITA = "fecha_visita";
    public static final String COL_REVIEW_FECHA_REVIEW = "fecha_review";
    public static final String COL_REVIEW_LIKES = "likes";
    public static final String COL_REVIEW_VERIFICADO = "verificado";

    // ==================== TABLA: favoritos ====================
    public static final String TABLE_FAVORITOS = "favoritos";
    public static final String COL_FAVORITO_ID = "favorito_id";
    public static final String COL_FAVORITO_USUARIO_ID = "usuario_id";
    public static final String COL_FAVORITO_LOCALIZACION_ID = "localizacion_id";
    public static final String COL_FAVORITO_FECHA_AGREGADO = "fecha_agregado";
    public static final String COL_FAVORITO_NOTAS_PERSONALES = "notas_personales";

    // ==================== TABLA: preferencias_usuario ====================
    public static final String TABLE_PREFERENCIAS_USUARIO = "preferencias_usuario";
    public static final String COL_PREFERENCIA_ID = "preferencia_id";
    public static final String COL_PREFERENCIA_USUARIO_ID = "usuario_id";
    public static final String COL_PREFERENCIA_CATEGORIA_ID = "categoria_id";
    public static final String COL_PREFERENCIA_NIVEL_INTERES = "nivel_interes";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");

        String createUsuarios = "CREATE TABLE " + TABLE_USUARIOS + " ("
                + COL_USUARIO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_USUARIO_NOMBRE + " TEXT NOT NULL, "
                + COL_USUARIO_APELLIDO + " TEXT NOT NULL, "
                + COL_USUARIO_EMAIL + " TEXT UNIQUE NOT NULL, "
                + COL_USUARIO_PASSWORD_HASH + " TEXT NOT NULL, "
                + COL_USUARIO_FECHA_REGISTRO + " INTEGER DEFAULT (strftime('%s', 'now')), "
                + COL_USUARIO_PAIS_ORIGEN + " TEXT, "
                + COL_USUARIO_FOTO_PERFIL + " TEXT, "
                + COL_USUARIO_ACTIVO + " INTEGER DEFAULT 1"
                + ")";
        db.execSQL(createUsuarios);

        String createCategorias = "CREATE TABLE " + TABLE_CATEGORIAS + " ("
                + COL_CATEGORIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_CATEGORIA_NOMBRE + " TEXT NOT NULL, "
                + COL_CATEGORIA_DESCRIPCION + " TEXT, "
                + COL_CATEGORIA_ICONO + " TEXT, "
                + COL_CATEGORIA_COLOR + " TEXT"
                + ")";
        db.execSQL(createCategorias);

        String createLocalizaciones = "CREATE TABLE " + TABLE_LOCALIZACIONES + " ("
                + COL_LOCALIZACION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_LOCALIZACION_NOMBRE + " TEXT NOT NULL, "
                + COL_LOCALIZACION_DESCRIPCION + " TEXT, "
                + COL_LOCALIZACION_LATITUD + " REAL NOT NULL, "
                + COL_LOCALIZACION_LONGITUD + " REAL NOT NULL, "
                + COL_LOCALIZACION_DIRECCION + " TEXT, "
                + COL_LOCALIZACION_CIUDAD + " TEXT NOT NULL, "
                + COL_LOCALIZACION_CATEGORIA_ID + " INTEGER, "
                + COL_LOCALIZACION_COSTO_PROMEDIO + " REAL DEFAULT 0, "
                + COL_LOCALIZACION_HORARIO_APERTURA + " TEXT, "
                + COL_LOCALIZACION_HORARIO_CIERRE + " TEXT, "
                + COL_LOCALIZACION_TELEFONO + " TEXT, "
                + COL_LOCALIZACION_SITIO_WEB + " TEXT, "
                + COL_LOCALIZACION_POPULARIDAD_SCORE + " REAL DEFAULT 0, "
                + COL_LOCALIZACION_FECHA_CREACION + " INTEGER DEFAULT (strftime('%s', 'now')), "
                + COL_LOCALIZACION_ACTIVO + " INTEGER DEFAULT 1, "
                + "FOREIGN KEY (" + COL_LOCALIZACION_CATEGORIA_ID + ") REFERENCES "
                + TABLE_CATEGORIAS + "(" + COL_CATEGORIA_ID + ") ON DELETE SET NULL"
                + ")";
        db.execSQL(createLocalizaciones);

        String createImagenes = "CREATE TABLE " + TABLE_IMAGENES_LOCALIZACIONES + " ("
                + COL_IMAGEN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_IMAGEN_LOCALIZACION_ID + " INTEGER NOT NULL, "
                + COL_IMAGEN_URL + " TEXT NOT NULL, "
                + COL_IMAGEN_ES_PRINCIPAL + " INTEGER DEFAULT 0, "
                + COL_IMAGEN_ORDEN + " INTEGER DEFAULT 0, "
                + COL_IMAGEN_FECHA_SUBIDA + " INTEGER DEFAULT (strftime('%s', 'now')), "
                + "FOREIGN KEY (" + COL_IMAGEN_LOCALIZACION_ID + ") REFERENCES "
                + TABLE_LOCALIZACIONES + "(" + COL_LOCALIZACION_ID + ") ON DELETE CASCADE"
                + ")";
        db.execSQL(createImagenes);

        String createReviews = "CREATE TABLE " + TABLE_REVIEWS + " ("
                + COL_REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_REVIEW_USUARIO_ID + " INTEGER NOT NULL, "
                + COL_REVIEW_LOCALIZACION_ID + " INTEGER NOT NULL, "
                + COL_REVIEW_CALIFICACION + " INTEGER NOT NULL CHECK(calificacion >= 1 AND calificacion <= 5), "
                + COL_REVIEW_COMENTARIO + " TEXT, "
                + COL_REVIEW_FECHA_VISITA + " INTEGER, "
                + COL_REVIEW_FECHA_REVIEW + " INTEGER DEFAULT (strftime('%s', 'now')), "
                + COL_REVIEW_LIKES + " INTEGER DEFAULT 0, "
                + COL_REVIEW_VERIFICADO + " INTEGER DEFAULT 0, "
                + "FOREIGN KEY (" + COL_REVIEW_USUARIO_ID + ") REFERENCES "
                + TABLE_USUARIOS + "(" + COL_USUARIO_ID + ") ON DELETE CASCADE, "
                + "FOREIGN KEY (" + COL_REVIEW_LOCALIZACION_ID + ") REFERENCES "
                + TABLE_LOCALIZACIONES + "(" + COL_LOCALIZACION_ID + ") ON DELETE CASCADE"
                + ")";
        db.execSQL(createReviews);

        String createFavoritos = "CREATE TABLE " + TABLE_FAVORITOS + " ("
                + COL_FAVORITO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_FAVORITO_USUARIO_ID + " INTEGER NOT NULL, "
                + COL_FAVORITO_LOCALIZACION_ID + " INTEGER NOT NULL, "
                + COL_FAVORITO_FECHA_AGREGADO + " INTEGER DEFAULT (strftime('%s', 'now')), "
                + COL_FAVORITO_NOTAS_PERSONALES + " TEXT, "
                + "UNIQUE(" + COL_FAVORITO_USUARIO_ID + ", " + COL_FAVORITO_LOCALIZACION_ID + "), "
                + "FOREIGN KEY (" + COL_FAVORITO_USUARIO_ID + ") REFERENCES "
                + TABLE_USUARIOS + "(" + COL_USUARIO_ID + ") ON DELETE CASCADE, "
                + "FOREIGN KEY (" + COL_FAVORITO_LOCALIZACION_ID + ") REFERENCES "
                + TABLE_LOCALIZACIONES + "(" + COL_LOCALIZACION_ID + ") ON DELETE CASCADE"
                + ")";
        db.execSQL(createFavoritos);

        String createPreferencias = "CREATE TABLE " + TABLE_PREFERENCIAS_USUARIO + " ("
                + COL_PREFERENCIA_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_PREFERENCIA_USUARIO_ID + " INTEGER NOT NULL, "
                + COL_PREFERENCIA_CATEGORIA_ID + " INTEGER NOT NULL, "
                + COL_PREFERENCIA_NIVEL_INTERES + " INTEGER CHECK(nivel_interes >= 1 AND nivel_interes <= 5), "
                + "UNIQUE(" + COL_PREFERENCIA_USUARIO_ID + ", " + COL_PREFERENCIA_CATEGORIA_ID + "), "
                + "FOREIGN KEY (" + COL_PREFERENCIA_USUARIO_ID + ") REFERENCES "
                + TABLE_USUARIOS + "(" + COL_USUARIO_ID + ") ON DELETE CASCADE, "
                + "FOREIGN KEY (" + COL_PREFERENCIA_CATEGORIA_ID + ") REFERENCES "
                + TABLE_CATEGORIAS + "(" + COL_CATEGORIA_ID + ") ON DELETE CASCADE"
                + ")";
        db.execSQL(createPreferencias);

        db.execSQL("CREATE INDEX idx_localizaciones_categoria ON " + TABLE_LOCALIZACIONES
                + "(" + COL_LOCALIZACION_CATEGORIA_ID + ")");
        db.execSQL("CREATE INDEX idx_localizaciones_ciudad ON " + TABLE_LOCALIZACIONES
                + "(" + COL_LOCALIZACION_CIUDAD + ")");
        db.execSQL("CREATE INDEX idx_reviews_localizacion ON " + TABLE_REVIEWS
                + "(" + COL_REVIEW_LOCALIZACION_ID + ")");
        db.execSQL("CREATE INDEX idx_reviews_usuario ON " + TABLE_REVIEWS
                + "(" + COL_REVIEW_USUARIO_ID + ")");
        db.execSQL("CREATE INDEX idx_favoritos_usuario ON " + TABLE_FAVORITOS
                + "(" + COL_FAVORITO_USUARIO_ID + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de actualización, borrar todas las tablas y recrear
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PREFERENCIAS_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEWS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGENES_LOCALIZACIONES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCALIZACIONES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USUARIOS);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    // MÉTODOS CRUD PARA USUARIOS

    // Insertar un nuevo usuario
    public long insertarUsuario(String nombre, String apellido, String email, String passwordHash,
                                String paisOrigen, String fotoPerfil) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USUARIO_NOMBRE, nombre);
        values.put(COL_USUARIO_APELLIDO, apellido);
        values.put(COL_USUARIO_EMAIL, email);
        values.put(COL_USUARIO_PASSWORD_HASH, passwordHash);
        values.put(COL_USUARIO_PAIS_ORIGEN, paisOrigen);
        values.put(COL_USUARIO_FOTO_PERFIL, fotoPerfil);

        long id = db.insert(TABLE_USUARIOS, null, values);
        db.close();
        return id;
    }

    // Buscar usuario por email
    public Cursor buscarUsuarioPorEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USUARIOS + " WHERE "
                + COL_USUARIO_EMAIL + " = ?";
        return db.rawQuery(query, new String[]{email});
    }

    // Buscar usuario por ID
    public Cursor buscarUsuarioPorId(long usuarioId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USUARIOS + " WHERE "
                + COL_USUARIO_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(usuarioId)});
    }

    // Actualizar datos de usuario
    public int actualizarUsuario(long usuarioId, String nombre, String apellido,
                                  String paisOrigen, String fotoPerfil) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USUARIO_NOMBRE, nombre);
        values.put(COL_USUARIO_APELLIDO, apellido);
        values.put(COL_USUARIO_PAIS_ORIGEN, paisOrigen);
        values.put(COL_USUARIO_FOTO_PERFIL, fotoPerfil);

        int rowsAffected = db.update(TABLE_USUARIOS, values,
                COL_USUARIO_ID + " = ?", new String[]{String.valueOf(usuarioId)});
        db.close();
        return rowsAffected;
    }

    // Eliminar usuario (soft delete - marcar como inactivo)
    public int desactivarUsuario(long usuarioId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USUARIO_ACTIVO, 0);

        int rowsAffected = db.update(TABLE_USUARIOS, values,
                COL_USUARIO_ID + " = ?", new String[]{String.valueOf(usuarioId)});
        db.close();
        return rowsAffected;
    }

    // MÉTODOS CRUD PARA CATEGORIAS

    // Insertar una nueva categoría
    public long insertarCategoria(String nombre, String descripcion, String icono, String color) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORIA_NOMBRE, nombre);
        values.put(COL_CATEGORIA_DESCRIPCION, descripcion);
        values.put(COL_CATEGORIA_ICONO, icono);
        values.put(COL_CATEGORIA_COLOR, color);

        long id = db.insert(TABLE_CATEGORIAS, null, values);
        db.close();
        return id;
    }

    // Obtener todas las categorías
    public Cursor obtenerTodasCategorias() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CATEGORIAS + " ORDER BY " + COL_CATEGORIA_NOMBRE;
        return db.rawQuery(query, null);
    }

    // Buscar categoría por ID
    public Cursor buscarCategoriaPorId(long categoriaId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CATEGORIAS + " WHERE "
                + COL_CATEGORIA_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(categoriaId)});
    }

    // MÉTODOS CRUD PARA LOCALIZACIONES

    // Insertar una nueva localización
    public long insertarLocalizacion(String nombre, String descripcion, double latitud, double longitud,
                                      String direccion, String ciudad, Long categoriaId, double costoPromedio,
                                      String horarioApertura, String horarioCierre, String telefono,
                                      String sitioWeb, float popularidadScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LOCALIZACION_NOMBRE, nombre);
        values.put(COL_LOCALIZACION_DESCRIPCION, descripcion);
        values.put(COL_LOCALIZACION_LATITUD, latitud);
        values.put(COL_LOCALIZACION_LONGITUD, longitud);
        values.put(COL_LOCALIZACION_DIRECCION, direccion);
        values.put(COL_LOCALIZACION_CIUDAD, ciudad);
        if (categoriaId != null) {
            values.put(COL_LOCALIZACION_CATEGORIA_ID, categoriaId);
        }
        values.put(COL_LOCALIZACION_COSTO_PROMEDIO, costoPromedio);
        values.put(COL_LOCALIZACION_HORARIO_APERTURA, horarioApertura);
        values.put(COL_LOCALIZACION_HORARIO_CIERRE, horarioCierre);
        values.put(COL_LOCALIZACION_TELEFONO, telefono);
        values.put(COL_LOCALIZACION_SITIO_WEB, sitioWeb);
        values.put(COL_LOCALIZACION_POPULARIDAD_SCORE, popularidadScore);

        long id = db.insert(TABLE_LOCALIZACIONES, null, values);
        db.close();
        return id;
    }

    // Obtener todas las localizaciones activas de una ciudad
    public Cursor obtenerLocalizacionesPorCiudad(String ciudad) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT l.*, c." + COL_CATEGORIA_NOMBRE + " as categoria_nombre FROM "
                + TABLE_LOCALIZACIONES + " l LEFT JOIN " + TABLE_CATEGORIAS + " c ON l."
                + COL_LOCALIZACION_CATEGORIA_ID + " = c." + COL_CATEGORIA_ID + " WHERE l."
                + COL_LOCALIZACION_CIUDAD + " = ? AND l." + COL_LOCALIZACION_ACTIVO + " = 1 "
                + "ORDER BY l." + COL_LOCALIZACION_POPULARIDAD_SCORE + " DESC";
        return db.rawQuery(query, new String[]{ciudad});
    }

    // Obtener localizaciones por categoría
    public Cursor obtenerLocalizacionesPorCategoria(long categoriaId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_LOCALIZACIONES + " WHERE "
                + COL_LOCALIZACION_CATEGORIA_ID + " = ? AND " + COL_LOCALIZACION_ACTIVO + " = 1 "
                + "ORDER BY " + COL_LOCALIZACION_POPULARIDAD_SCORE + " DESC";
        return db.rawQuery(query, new String[]{String.valueOf(categoriaId)});
    }

    // Buscar localización por ID
    public Cursor buscarLocalizacionPorId(long localizacionId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT l.*, c." + COL_CATEGORIA_NOMBRE + " as categoria_nombre FROM "
                + TABLE_LOCALIZACIONES + " l LEFT JOIN " + TABLE_CATEGORIAS + " c ON l."
                + COL_LOCALIZACION_CATEGORIA_ID + " = c." + COL_CATEGORIA_ID + " WHERE l."
                + COL_LOCALIZACION_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(localizacionId)});
    }

    // Actualizar localización
    public int actualizarLocalizacion(long localizacionId, String nombre, String descripcion,
                                       double latitud, double longitud, String direccion,
                                       Long categoriaId, double costoPromedio, String telefono,
                                       String sitioWeb) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LOCALIZACION_NOMBRE, nombre);
        values.put(COL_LOCALIZACION_DESCRIPCION, descripcion);
        values.put(COL_LOCALIZACION_LATITUD, latitud);
        values.put(COL_LOCALIZACION_LONGITUD, longitud);
        values.put(COL_LOCALIZACION_DIRECCION, direccion);
        if (categoriaId != null) {
            values.put(COL_LOCALIZACION_CATEGORIA_ID, categoriaId);
        }
        values.put(COL_LOCALIZACION_COSTO_PROMEDIO, costoPromedio);
        values.put(COL_LOCALIZACION_TELEFONO, telefono);
        values.put(COL_LOCALIZACION_SITIO_WEB, sitioWeb);

        int rowsAffected = db.update(TABLE_LOCALIZACIONES, values,
                COL_LOCALIZACION_ID + " = ?", new String[]{String.valueOf(localizacionId)});
        db.close();
        return rowsAffected;
    }

    // Desactivar localización (soft delete)
    public int desactivarLocalizacion(long localizacionId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LOCALIZACION_ACTIVO, 0);

        int rowsAffected = db.update(TABLE_LOCALIZACIONES, values,
                COL_LOCALIZACION_ID + " = ?", new String[]{String.valueOf(localizacionId)});
        db.close();
        return rowsAffected;
    }

    // MÉTODOS CRUD PARA IMAGENES_LOCALIZACIONES

    // Insertar imagen de localización
    public long insertarImagenLocalizacion(long localizacionId, String urlImagen,
                                            boolean esPrincipal, int orden) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_IMAGEN_LOCALIZACION_ID, localizacionId);
        values.put(COL_IMAGEN_URL, urlImagen);
        values.put(COL_IMAGEN_ES_PRINCIPAL, esPrincipal ? 1 : 0);
        values.put(COL_IMAGEN_ORDEN, orden);

        long id = db.insert(TABLE_IMAGENES_LOCALIZACIONES, null, values);
        db.close();
        return id;
    }

    // Obtener todas las imágenes de una localización
    public Cursor obtenerImagenesPorLocalizacion(long localizacionId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_IMAGENES_LOCALIZACIONES + " WHERE "
                + COL_IMAGEN_LOCALIZACION_ID + " = ? ORDER BY " + COL_IMAGEN_ORDEN;
        return db.rawQuery(query, new String[]{String.valueOf(localizacionId)});
    }

    // Obtener imagen principal de una localización
    public Cursor obtenerImagenPrincipal(long localizacionId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_IMAGENES_LOCALIZACIONES + " WHERE "
                + COL_IMAGEN_LOCALIZACION_ID + " = ? AND " + COL_IMAGEN_ES_PRINCIPAL + " = 1 LIMIT 1";
        return db.rawQuery(query, new String[]{String.valueOf(localizacionId)});
    }

    // MÉTODOS CRUD PARA REVIEWS

    // Insertar un review
    public long insertarReview(long usuarioId, long localizacionId, int calificacion,
                               String comentario, long fechaVisita) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_REVIEW_USUARIO_ID, usuarioId);
        values.put(COL_REVIEW_LOCALIZACION_ID, localizacionId);
        values.put(COL_REVIEW_CALIFICACION, calificacion);
        values.put(COL_REVIEW_COMENTARIO, comentario);
        values.put(COL_REVIEW_FECHA_VISITA, fechaVisita);

        long id = db.insert(TABLE_REVIEWS, null, values);
        db.close();
        return id;
    }

    // Obtener reviews de una localización
    public Cursor obtenerReviewsPorLocalizacion(long localizacionId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT r.*, u." + COL_USUARIO_NOMBRE + ", u." + COL_USUARIO_APELLIDO
                + ", u." + COL_USUARIO_FOTO_PERFIL + " FROM " + TABLE_REVIEWS + " r "
                + "INNER JOIN " + TABLE_USUARIOS + " u ON r." + COL_REVIEW_USUARIO_ID
                + " = u." + COL_USUARIO_ID + " WHERE r." + COL_REVIEW_LOCALIZACION_ID + " = ? "
                + "ORDER BY r." + COL_REVIEW_FECHA_REVIEW + " DESC";
        return db.rawQuery(query, new String[]{String.valueOf(localizacionId)});
    }

    // Obtener calificación promedio de una localización
    public float obtenerCalificacionPromedio(long localizacionId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT AVG(" + COL_REVIEW_CALIFICACION + ") as promedio FROM "
                + TABLE_REVIEWS + " WHERE " + COL_REVIEW_LOCALIZACION_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(localizacionId)});

        float promedio = 0;
        if (cursor.moveToFirst()) {
            promedio = cursor.getFloat(0);
        }
        cursor.close();
        db.close();
        return promedio;
    }

    // Actualizar likes de un review
    public int actualizarLikesReview(long reviewId, int likes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_REVIEW_LIKES, likes);

        int rowsAffected = db.update(TABLE_REVIEWS, values,
                COL_REVIEW_ID + " = ?", new String[]{String.valueOf(reviewId)});
        db.close();
        return rowsAffected;
    }

    // Eliminar review
    public int eliminarReview(long reviewId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_REVIEWS,
                COL_REVIEW_ID + " = ?", new String[]{String.valueOf(reviewId)});
        db.close();
        return rowsAffected;
    }

    // MÉTODOS CRUD PARA FAVORITOS

    // Agregar localización a favoritos
    public long agregarFavorito(long usuarioId, long localizacionId, String notasPersonales) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FAVORITO_USUARIO_ID, usuarioId);
        values.put(COL_FAVORITO_LOCALIZACION_ID, localizacionId);
        values.put(COL_FAVORITO_NOTAS_PERSONALES, notasPersonales);

        long id = -1;
        try {
            id = db.insertOrThrow(TABLE_FAVORITOS, null, values);
        } catch (SQLException e) {
            // Ya existe en favoritos
        }
        db.close();
        return id;
    }

    // Obtener favoritos de un usuario
    public Cursor obtenerFavoritosPorUsuario(long usuarioId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT f.*, l.* FROM " + TABLE_FAVORITOS + " f "
                + "INNER JOIN " + TABLE_LOCALIZACIONES + " l ON f." + COL_FAVORITO_LOCALIZACION_ID
                + " = l." + COL_LOCALIZACION_ID + " WHERE f." + COL_FAVORITO_USUARIO_ID + " = ? "
                + "ORDER BY f." + COL_FAVORITO_FECHA_AGREGADO + " DESC";
        return db.rawQuery(query, new String[]{String.valueOf(usuarioId)});
    }

    // Verificar si una localización está en favoritos
    public boolean esFavorito(long usuarioId, long localizacionId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_FAVORITOS + " WHERE "
                + COL_FAVORITO_USUARIO_ID + " = ? AND " + COL_FAVORITO_LOCALIZACION_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(usuarioId),
                String.valueOf(localizacionId)});

        boolean existe = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return existe;
    }

    // Eliminar de favoritos
    public int eliminarFavorito(long usuarioId, long localizacionId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_FAVORITOS,
                COL_FAVORITO_USUARIO_ID + " = ? AND " + COL_FAVORITO_LOCALIZACION_ID + " = ?",
                new String[]{String.valueOf(usuarioId), String.valueOf(localizacionId)});
        db.close();
        return rowsAffected;
    }

    // MÉTODOS CRUD PARA PREFERENCIAS_USUARIO

    /**
     * Establecer preferencia de usuario por categoría
     */
    public long establecerPreferencia(long usuarioId, long categoriaId, int nivelInteres) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PREFERENCIA_USUARIO_ID, usuarioId);
        values.put(COL_PREFERENCIA_CATEGORIA_ID, categoriaId);
        values.put(COL_PREFERENCIA_NIVEL_INTERES, nivelInteres);

        long id = -1;
        try {
            id = db.insertOrThrow(TABLE_PREFERENCIAS_USUARIO, null, values);
        } catch (SQLException e) {
            // Ya existe, actualizar
            id = db.update(TABLE_PREFERENCIAS_USUARIO, values,
                    COL_PREFERENCIA_USUARIO_ID + " = ? AND " + COL_PREFERENCIA_CATEGORIA_ID + " = ?",
                    new String[]{String.valueOf(usuarioId), String.valueOf(categoriaId)});
        }
        db.close();
        return id;
    }

    // Obtener preferencias de un usuario
    public Cursor obtenerPreferenciasPorUsuario(long usuarioId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT p.*, c." + COL_CATEGORIA_NOMBRE + ", c." + COL_CATEGORIA_ICONO
                + ", c." + COL_CATEGORIA_COLOR + " FROM " + TABLE_PREFERENCIAS_USUARIO + " p "
                + "INNER JOIN " + TABLE_CATEGORIAS + " c ON p." + COL_PREFERENCIA_CATEGORIA_ID
                + " = c." + COL_CATEGORIA_ID + " WHERE p." + COL_PREFERENCIA_USUARIO_ID + " = ? "
                + "ORDER BY p." + COL_PREFERENCIA_NIVEL_INTERES + " DESC";
        return db.rawQuery(query, new String[]{String.valueOf(usuarioId)});
    }

    // Eliminar preferencia
    public int eliminarPreferencia(long usuarioId, long categoriaId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_PREFERENCIAS_USUARIO,
                COL_PREFERENCIA_USUARIO_ID + " = ? AND " + COL_PREFERENCIA_CATEGORIA_ID + " = ?",
                new String[]{String.valueOf(usuarioId), String.valueOf(categoriaId)});
        db.close();
        return rowsAffected;
    }

    // ==================== MÉTODOS AUXILIARES ====================

    // Obtener número total de reviews de una localización
    public int obtenerNumeroReviews(long localizacionId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_REVIEWS + " WHERE "
                + COL_REVIEW_LOCALIZACION_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(localizacionId)});

        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return count;
    }

    // Buscar localizaciones por nombre
    public Cursor buscarLocalizacionesPorNombre(String termino) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_LOCALIZACIONES + " WHERE "
                + COL_LOCALIZACION_NOMBRE + " LIKE ? AND " + COL_LOCALIZACION_ACTIVO + " = 1";
        return db.rawQuery(query, new String[]{"%" + termino + "%"});
    }
}
