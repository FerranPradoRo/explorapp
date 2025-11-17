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
    private static final int DATABASE_VERSION = 2;

    // ==================== TABLA: users ====================
    public static final String TABLE_USERS = "users";
    public static final String COL_USER_ID = "user_id";
    public static final String COL_USER_FIRST_NAME = "first_name";
    public static final String COL_USER_LAST_NAME = "last_name";
    public static final String COL_USER_EMAIL = "email";
    public static final String COL_USER_PASSWORD_HASH = "password_hash";
    public static final String COL_USER_REGISTRATION_DATE = "registration_date";
    public static final String COL_USER_COUNTRY_OF_ORIGIN = "country_of_origin";
    public static final String COL_USER_PROFILE_PICTURE = "profile_picture";
    public static final String COL_USER_ACTIVE = "active";

    // ==================== TABLA: categories ====================
    public static final String TABLE_CATEGORIES = "categories";
    public static final String COL_CATEGORY_ID = "category_id";
    public static final String COL_CATEGORY_NAME = "name";
    public static final String COL_CATEGORY_DESCRIPTION = "description";
    public static final String COL_CATEGORY_ICON = "icon";
    public static final String COL_CATEGORY_COLOR = "color";

    // ==================== TABLA: locations ====================
    public static final String TABLE_LOCATIONS = "locations";
    public static final String COL_LOCATION_ID = "location_id";
    public static final String COL_LOCATION_NAME = "name";
    public static final String COL_LOCATION_DESCRIPTION = "description";
    public static final String COL_LOCATION_LATITUDE = "latitude";
    public static final String COL_LOCATION_LONGITUDE = "longitude";
    public static final String COL_LOCATION_ADDRESS = "address";
    public static final String COL_LOCATION_CITY = "city";
    public static final String COL_LOCATION_CATEGORY_ID = "category_id";
    public static final String COL_LOCATION_AVERAGE_COST = "average_cost";
    public static final String COL_LOCATION_OPENING_TIME = "opening_time";
    public static final String COL_LOCATION_CLOSING_TIME = "closing_time";
    public static final String COL_LOCATION_PHONE = "phone";
    public static final String COL_LOCATION_WEBSITE = "website";
    public static final String COL_LOCATION_POPULARITY_SCORE = "popularity_score";
    public static final String COL_LOCATION_CREATION_DATE = "creation_date";
    public static final String COL_LOCATION_ACTIVE = "active";

    // ==================== TABLA: location_images ====================
    public static final String TABLE_LOCATION_IMAGES = "location_images";
    public static final String COL_IMAGE_ID = "image_id";
    public static final String COL_IMAGE_LOCATION_ID = "location_id";
    public static final String COL_IMAGE_URL = "image_url";
    public static final String COL_IMAGE_IS_PRIMARY = "is_primary";
    public static final String COL_IMAGE_ORDER = "display_order";
    public static final String COL_IMAGE_UPLOAD_DATE = "upload_date";

    // ==================== TABLA: reviews ====================
    public static final String TABLE_REVIEWS = "reviews";
    public static final String COL_REVIEW_ID = "review_id";
    public static final String COL_REVIEW_USER_ID = "user_id";
    public static final String COL_REVIEW_LOCATION_ID = "location_id";
    public static final String COL_REVIEW_RATING = "rating";
    public static final String COL_REVIEW_COMMENT = "comment";
    public static final String COL_REVIEW_VISIT_DATE = "visit_date";
    public static final String COL_REVIEW_DATE = "review_date";
    public static final String COL_REVIEW_LIKES = "likes";
    public static final String COL_REVIEW_VERIFIED = "verified";

    // ==================== TABLA: favorites ====================
    public static final String TABLE_FAVORITES = "favorites";
    public static final String COL_FAVORITE_ID = "favorite_id";
    public static final String COL_FAVORITE_USER_ID = "user_id";
    public static final String COL_FAVORITE_LOCATION_ID = "location_id";
    public static final String COL_FAVORITE_DATE_ADDED = "date_added";
    public static final String COL_FAVORITE_PERSONAL_NOTES = "personal_notes";

    // ==================== TABLA: user_preferences ====================
    public static final String TABLE_USER_PREFERENCES = "user_preferences";
    public static final String COL_PREFERENCE_ID = "preference_id";
    public static final String COL_PREFERENCE_USER_ID = "user_id";
    public static final String COL_PREFERENCE_CATEGORY_ID = "category_id";
    public static final String COL_PREFERENCE_INTEREST_LEVEL = "interest_level";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("PRAGMA foreign_keys=ON;");

        String createUsers = "CREATE TABLE " + TABLE_USERS + " ("
                + COL_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_USER_FIRST_NAME + " TEXT NOT NULL, "
                + COL_USER_LAST_NAME + " TEXT NOT NULL, "
                + COL_USER_EMAIL + " TEXT UNIQUE NOT NULL, "
                + COL_USER_PASSWORD_HASH + " TEXT NOT NULL, "
                + COL_USER_REGISTRATION_DATE + " INTEGER DEFAULT (strftime('%s', 'now')), "
                + COL_USER_COUNTRY_OF_ORIGIN + " TEXT, "
                + COL_USER_PROFILE_PICTURE + " TEXT, "
                + COL_USER_ACTIVE + " INTEGER DEFAULT 1"
                + ")";
        db.execSQL(createUsers);

        String createCategories = "CREATE TABLE " + TABLE_CATEGORIES + " ("
                + COL_CATEGORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_CATEGORY_NAME + " TEXT NOT NULL, "
                + COL_CATEGORY_DESCRIPTION + " TEXT, "
                + COL_CATEGORY_ICON + " TEXT, "
                + COL_CATEGORY_COLOR + " TEXT"
                + ")";
        db.execSQL(createCategories);

        String createLocations = "CREATE TABLE " + TABLE_LOCATIONS + " ("
                + COL_LOCATION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_LOCATION_NAME + " TEXT NOT NULL, "
                + COL_LOCATION_DESCRIPTION + " TEXT, "
                + COL_LOCATION_LATITUDE + " REAL NOT NULL, "
                + COL_LOCATION_LONGITUDE + " REAL NOT NULL, "
                + COL_LOCATION_ADDRESS + " TEXT, "
                + COL_LOCATION_CITY + " TEXT NOT NULL, "
                + COL_LOCATION_CATEGORY_ID + " INTEGER, "
                + COL_LOCATION_AVERAGE_COST + " REAL DEFAULT 0, "
                + COL_LOCATION_OPENING_TIME + " TEXT, "
                + COL_LOCATION_CLOSING_TIME + " TEXT, "
                + COL_LOCATION_PHONE + " TEXT, "
                + COL_LOCATION_WEBSITE + " TEXT, "
                + COL_LOCATION_POPULARITY_SCORE + " REAL DEFAULT 0, "
                + COL_LOCATION_CREATION_DATE + " INTEGER DEFAULT (strftime('%s', 'now')), "
                + COL_LOCATION_ACTIVE + " INTEGER DEFAULT 1, "
                + "FOREIGN KEY (" + COL_LOCATION_CATEGORY_ID + ") REFERENCES "
                + TABLE_CATEGORIES + "(" + COL_CATEGORY_ID + ") ON DELETE SET NULL"
                + ")";
        db.execSQL(createLocations);

        String createImages = "CREATE TABLE " + TABLE_LOCATION_IMAGES + " ("
                + COL_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_IMAGE_LOCATION_ID + " INTEGER NOT NULL, "
                + COL_IMAGE_URL + " TEXT NOT NULL, "
                + COL_IMAGE_IS_PRIMARY + " INTEGER DEFAULT 0, "
                + COL_IMAGE_ORDER + " INTEGER DEFAULT 0, "
                + COL_IMAGE_UPLOAD_DATE + " INTEGER DEFAULT (strftime('%s', 'now')), "
                + "FOREIGN KEY (" + COL_IMAGE_LOCATION_ID + ") REFERENCES "
                + TABLE_LOCATIONS + "(" + COL_LOCATION_ID + ") ON DELETE CASCADE"
                + ")";
        db.execSQL(createImages);

        String createReviews = "CREATE TABLE " + TABLE_REVIEWS + " ("
                + COL_REVIEW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_REVIEW_USER_ID + " INTEGER NOT NULL, "
                + COL_REVIEW_LOCATION_ID + " INTEGER NOT NULL, "
                + COL_REVIEW_RATING + " INTEGER NOT NULL CHECK(rating >= 1 AND rating <= 5), "
                + COL_REVIEW_COMMENT + " TEXT, "
                + COL_REVIEW_VISIT_DATE + " INTEGER, "
                + COL_REVIEW_DATE + " INTEGER DEFAULT (strftime('%s', 'now')), "
                + COL_REVIEW_LIKES + " INTEGER DEFAULT 0, "
                + COL_REVIEW_VERIFIED + " INTEGER DEFAULT 0, "
                + "FOREIGN KEY (" + COL_REVIEW_USER_ID + ") REFERENCES "
                + TABLE_USERS + "(" + COL_USER_ID + ") ON DELETE CASCADE, "
                + "FOREIGN KEY (" + COL_REVIEW_LOCATION_ID + ") REFERENCES "
                + TABLE_LOCATIONS + "(" + COL_LOCATION_ID + ") ON DELETE CASCADE"
                + ")";
        db.execSQL(createReviews);

        String createFavorites = "CREATE TABLE " + TABLE_FAVORITES + " ("
                + COL_FAVORITE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_FAVORITE_USER_ID + " INTEGER NOT NULL, "
                + COL_FAVORITE_LOCATION_ID + " INTEGER NOT NULL, "
                + COL_FAVORITE_DATE_ADDED + " INTEGER DEFAULT (strftime('%s', 'now')), "
                + COL_FAVORITE_PERSONAL_NOTES + " TEXT, "
                + "UNIQUE(" + COL_FAVORITE_USER_ID + ", " + COL_FAVORITE_LOCATION_ID + "), "
                + "FOREIGN KEY (" + COL_FAVORITE_USER_ID + ") REFERENCES "
                + TABLE_USERS + "(" + COL_USER_ID + ") ON DELETE CASCADE, "
                + "FOREIGN KEY (" + COL_FAVORITE_LOCATION_ID + ") REFERENCES "
                + TABLE_LOCATIONS + "(" + COL_LOCATION_ID + ") ON DELETE CASCADE"
                + ")";
        db.execSQL(createFavorites);

        String createPreferences = "CREATE TABLE " + TABLE_USER_PREFERENCES + " ("
                + COL_PREFERENCE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COL_PREFERENCE_USER_ID + " INTEGER NOT NULL, "
                + COL_PREFERENCE_CATEGORY_ID + " INTEGER NOT NULL, "
                + COL_PREFERENCE_INTEREST_LEVEL + " INTEGER CHECK(interest_level >= 1 AND interest_level <= 5), "
                + "UNIQUE(" + COL_PREFERENCE_USER_ID + ", " + COL_PREFERENCE_CATEGORY_ID + "), "
                + "FOREIGN KEY (" + COL_PREFERENCE_USER_ID + ") REFERENCES "
                + TABLE_USERS + "(" + COL_USER_ID + ") ON DELETE CASCADE, "
                + "FOREIGN KEY (" + COL_PREFERENCE_CATEGORY_ID + ") REFERENCES "
                + TABLE_CATEGORIES + "(" + COL_CATEGORY_ID + ") ON DELETE CASCADE"
                + ")";
        db.execSQL(createPreferences);

        db.execSQL("CREATE INDEX idx_locations_category ON " + TABLE_LOCATIONS
                + "(" + COL_LOCATION_CATEGORY_ID + ")");
        db.execSQL("CREATE INDEX idx_locations_city ON " + TABLE_LOCATIONS
                + "(" + COL_LOCATION_CITY + ")");
        db.execSQL("CREATE INDEX idx_reviews_location ON " + TABLE_REVIEWS
                + "(" + COL_REVIEW_LOCATION_ID + ")");
        db.execSQL("CREATE INDEX idx_reviews_user ON " + TABLE_REVIEWS
                + "(" + COL_REVIEW_USER_ID + ")");
        db.execSQL("CREATE INDEX idx_favorites_user ON " + TABLE_FAVORITES
                + "(" + COL_FAVORITE_USER_ID + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // En caso de actualización, borrar todas las tablas y recrear
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_PREFERENCES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_REVIEWS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATION_IMAGES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LOCATIONS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CATEGORIES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys=ON;");
    }

    // MÉTODOS CRUD PARA USUARIOS

    // Insertar un nuevo usuario
    public long insertUser(String firstName, String lastName, String email, String passwordHash,
                          String countryOfOrigin, String profilePicture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_FIRST_NAME, firstName);
        values.put(COL_USER_LAST_NAME, lastName);
        values.put(COL_USER_EMAIL, email);
        values.put(COL_USER_PASSWORD_HASH, passwordHash);
        values.put(COL_USER_COUNTRY_OF_ORIGIN, countryOfOrigin);
        values.put(COL_USER_PROFILE_PICTURE, profilePicture);

        long id = db.insert(TABLE_USERS, null, values);
        db.close();
        return id;
    }

    // Buscar usuario por email
    public Cursor findUserByEmail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COL_USER_EMAIL + " = ?";
        return db.rawQuery(query, new String[]{email});
    }

    // Buscar usuario por ID
    public Cursor findUserById(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE "
                + COL_USER_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(userId)});
    }

    // Actualizar datos de usuario
    public int updateUser(long userId, String firstName, String lastName,
                         String countryOfOrigin, String profilePicture) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_FIRST_NAME, firstName);
        values.put(COL_USER_LAST_NAME, lastName);
        values.put(COL_USER_COUNTRY_OF_ORIGIN, countryOfOrigin);
        values.put(COL_USER_PROFILE_PICTURE, profilePicture);

        int rowsAffected = db.update(TABLE_USERS, values,
                COL_USER_ID + " = ?", new String[]{String.valueOf(userId)});
        db.close();
        return rowsAffected;
    }

    // Eliminar usuario (soft delete - marcar como inactivo)
    public int deactivateUser(long userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_USER_ACTIVE, 0);

        int rowsAffected = db.update(TABLE_USERS, values,
                COL_USER_ID + " = ?", new String[]{String.valueOf(userId)});
        db.close();
        return rowsAffected;
    }

    // MÉTODOS CRUD PARA CATEGORIAS

    // Insertar una nueva categoría
    public long insertCategory(String name, String description, String icon, String color) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_CATEGORY_NAME, name);
        values.put(COL_CATEGORY_DESCRIPTION, description);
        values.put(COL_CATEGORY_ICON, icon);
        values.put(COL_CATEGORY_COLOR, color);

        long id = db.insert(TABLE_CATEGORIES, null, values);
        db.close();
        return id;
    }

    // Obtener todas las categorías
    public Cursor getAllCategories() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CATEGORIES + " ORDER BY " + COL_CATEGORY_NAME;
        return db.rawQuery(query, null);
    }

    // Buscar categoría por ID
    public Cursor findCategoryById(long categoryId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_CATEGORIES + " WHERE "
                + COL_CATEGORY_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(categoryId)});
    }

    // MÉTODOS CRUD PARA LOCALIZACIONES

    // Insertar una nueva localización
    public long insertLocation(String name, String description, double latitude, double longitude,
                              String address, String city, Long categoryId, double averageCost,
                              String openingTime, String closingTime, String phone,
                              String website, float popularityScore) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LOCATION_NAME, name);
        values.put(COL_LOCATION_DESCRIPTION, description);
        values.put(COL_LOCATION_LATITUDE, latitude);
        values.put(COL_LOCATION_LONGITUDE, longitude);
        values.put(COL_LOCATION_ADDRESS, address);
        values.put(COL_LOCATION_CITY, city);
        if (categoryId != null) {
            values.put(COL_LOCATION_CATEGORY_ID, categoryId);
        }
        values.put(COL_LOCATION_AVERAGE_COST, averageCost);
        values.put(COL_LOCATION_OPENING_TIME, openingTime);
        values.put(COL_LOCATION_CLOSING_TIME, closingTime);
        values.put(COL_LOCATION_PHONE, phone);
        values.put(COL_LOCATION_WEBSITE, website);
        values.put(COL_LOCATION_POPULARITY_SCORE, popularityScore);

        long id = db.insert(TABLE_LOCATIONS, null, values);
        db.close();
        return id;
    }

    // Obtener todas las localizaciones activas de una ciudad
    public Cursor getLocationsByCity(String city) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT l.*, c." + COL_CATEGORY_NAME + " as category_name FROM "
                + TABLE_LOCATIONS + " l LEFT JOIN " + TABLE_CATEGORIES + " c ON l."
                + COL_LOCATION_CATEGORY_ID + " = c." + COL_CATEGORY_ID + " WHERE l."
                + COL_LOCATION_CITY + " = ? AND l." + COL_LOCATION_ACTIVE + " = 1 "
                + "ORDER BY l." + COL_LOCATION_POPULARITY_SCORE + " DESC";
        return db.rawQuery(query, new String[]{city});
    }

    // Obtener localizaciones por categoría
    public Cursor getLocationsByCategory(long categoryId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_LOCATIONS + " WHERE "
                + COL_LOCATION_CATEGORY_ID + " = ? AND " + COL_LOCATION_ACTIVE + " = 1 "
                + "ORDER BY " + COL_LOCATION_POPULARITY_SCORE + " DESC";
        return db.rawQuery(query, new String[]{String.valueOf(categoryId)});
    }

    // Buscar localización por ID
    public Cursor findLocationById(long locationId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT l.*, c." + COL_CATEGORY_NAME + " as category_name FROM "
                + TABLE_LOCATIONS + " l LEFT JOIN " + TABLE_CATEGORIES + " c ON l."
                + COL_LOCATION_CATEGORY_ID + " = c." + COL_CATEGORY_ID + " WHERE l."
                + COL_LOCATION_ID + " = ?";
        return db.rawQuery(query, new String[]{String.valueOf(locationId)});
    }

    // Actualizar localización
    public int updateLocation(long locationId, String name, String description,
                             double latitude, double longitude, String address,
                             Long categoryId, double averageCost, String phone,
                             String website) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LOCATION_NAME, name);
        values.put(COL_LOCATION_DESCRIPTION, description);
        values.put(COL_LOCATION_LATITUDE, latitude);
        values.put(COL_LOCATION_LONGITUDE, longitude);
        values.put(COL_LOCATION_ADDRESS, address);
        if (categoryId != null) {
            values.put(COL_LOCATION_CATEGORY_ID, categoryId);
        }
        values.put(COL_LOCATION_AVERAGE_COST, averageCost);
        values.put(COL_LOCATION_PHONE, phone);
        values.put(COL_LOCATION_WEBSITE, website);

        int rowsAffected = db.update(TABLE_LOCATIONS, values,
                COL_LOCATION_ID + " = ?", new String[]{String.valueOf(locationId)});
        db.close();
        return rowsAffected;
    }

    // Desactivar localización (soft delete)
    public int deactivateLocation(long locationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_LOCATION_ACTIVE, 0);

        int rowsAffected = db.update(TABLE_LOCATIONS, values,
                COL_LOCATION_ID + " = ?", new String[]{String.valueOf(locationId)});
        db.close();
        return rowsAffected;
    }

    // MÉTODOS CRUD PARA IMAGENES_LOCALIZACIONES

    // Insertar imagen de localización
    public long insertLocationImage(long locationId, String imageUrl,
                                   boolean isPrimary, int order) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_IMAGE_LOCATION_ID, locationId);
        values.put(COL_IMAGE_URL, imageUrl);
        values.put(COL_IMAGE_IS_PRIMARY, isPrimary ? 1 : 0);
        values.put(COL_IMAGE_ORDER, order);

        long id = db.insert(TABLE_LOCATION_IMAGES, null, values);
        db.close();
        return id;
    }

    // Obtener todas las imágenes de una localización
    public Cursor getImagesByLocation(long locationId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_LOCATION_IMAGES + " WHERE "
                + COL_IMAGE_LOCATION_ID + " = ? ORDER BY " + COL_IMAGE_ORDER;
        return db.rawQuery(query, new String[]{String.valueOf(locationId)});
    }

    // Obtener imagen principal de una localización
    public Cursor getPrimaryImage(long locationId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_LOCATION_IMAGES + " WHERE "
                + COL_IMAGE_LOCATION_ID + " = ? AND " + COL_IMAGE_IS_PRIMARY + " = 1 LIMIT 1";
        return db.rawQuery(query, new String[]{String.valueOf(locationId)});
    }

    // MÉTODOS CRUD PARA REVIEWS

    // Insertar un review
    public long insertReview(long userId, long locationId, int rating,
                            String comment, long visitDate) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_REVIEW_USER_ID, userId);
        values.put(COL_REVIEW_LOCATION_ID, locationId);
        values.put(COL_REVIEW_RATING, rating);
        values.put(COL_REVIEW_COMMENT, comment);
        values.put(COL_REVIEW_VISIT_DATE, visitDate);

        long id = db.insert(TABLE_REVIEWS, null, values);
        db.close();
        return id;
    }

    // Obtener reviews de una localización
    public Cursor getReviewsByLocation(long locationId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT r.*, u." + COL_USER_FIRST_NAME + ", u." + COL_USER_LAST_NAME
                + ", u." + COL_USER_PROFILE_PICTURE + " FROM " + TABLE_REVIEWS + " r "
                + "INNER JOIN " + TABLE_USERS + " u ON r." + COL_REVIEW_USER_ID
                + " = u." + COL_USER_ID + " WHERE r." + COL_REVIEW_LOCATION_ID + " = ? "
                + "ORDER BY r." + COL_REVIEW_DATE + " DESC";
        return db.rawQuery(query, new String[]{String.valueOf(locationId)});
    }

    // Obtener calificación promedio de una localización
    public float getAverageRating(long locationId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT AVG(" + COL_REVIEW_RATING + ") as average FROM "
                + TABLE_REVIEWS + " WHERE " + COL_REVIEW_LOCATION_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(locationId)});

        float average = 0;
        if (cursor.moveToFirst()) {
            average = cursor.getFloat(0);
        }
        cursor.close();
        db.close();
        return average;
    }

    // Actualizar likes de un review
    public int updateReviewLikes(long reviewId, int likes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_REVIEW_LIKES, likes);

        int rowsAffected = db.update(TABLE_REVIEWS, values,
                COL_REVIEW_ID + " = ?", new String[]{String.valueOf(reviewId)});
        db.close();
        return rowsAffected;
    }

    // Eliminar review
    public int deleteReview(long reviewId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_REVIEWS,
                COL_REVIEW_ID + " = ?", new String[]{String.valueOf(reviewId)});
        db.close();
        return rowsAffected;
    }

    // MÉTODOS CRUD PARA FAVORITOS

    // Agregar localización a favoritos
    public long addFavorite(long userId, long locationId, String personalNotes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_FAVORITE_USER_ID, userId);
        values.put(COL_FAVORITE_LOCATION_ID, locationId);
        values.put(COL_FAVORITE_PERSONAL_NOTES, personalNotes);

        long id = -1;
        try {
            id = db.insertOrThrow(TABLE_FAVORITES, null, values);
        } catch (SQLException e) {
            // Ya existe en favoritos
        }
        db.close();
        return id;
    }

    // Obtener favoritos de un usuario
    public Cursor getFavoritesByUser(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT f.*, l.*, c." + COL_CATEGORY_NAME + " as category_name FROM "
                + TABLE_FAVORITES + " f "
                + "INNER JOIN " + TABLE_LOCATIONS + " l ON f." + COL_FAVORITE_LOCATION_ID
                + " = l." + COL_LOCATION_ID + " "
                + "LEFT JOIN " + TABLE_CATEGORIES + " c ON l." + COL_LOCATION_CATEGORY_ID
                + " = c." + COL_CATEGORY_ID + " "
                + "WHERE f." + COL_FAVORITE_USER_ID + " = ? "
                + "ORDER BY f." + COL_FAVORITE_DATE_ADDED + " DESC";
        return db.rawQuery(query, new String[]{String.valueOf(userId)});
    }

    // Verificar si una localización está en favoritos
    public boolean isFavorite(long userId, long locationId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_FAVORITES + " WHERE "
                + COL_FAVORITE_USER_ID + " = ? AND " + COL_FAVORITE_LOCATION_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userId),
                String.valueOf(locationId)});

        boolean exists = cursor.getCount() > 0;
        cursor.close();
        db.close();
        return exists;
    }

    // Eliminar de favoritos
    public int removeFavorite(long userId, long locationId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_FAVORITES,
                COL_FAVORITE_USER_ID + " = ? AND " + COL_FAVORITE_LOCATION_ID + " = ?",
                new String[]{String.valueOf(userId), String.valueOf(locationId)});
        db.close();
        return rowsAffected;
    }

    // MÉTODOS CRUD PARA PREFERENCIAS_USUARIO

    /**
     * Establecer preferencia de usuario por categoría
     */
    public long setPreference(long userId, long categoryId, int interestLevel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PREFERENCE_USER_ID, userId);
        values.put(COL_PREFERENCE_CATEGORY_ID, categoryId);
        values.put(COL_PREFERENCE_INTEREST_LEVEL, interestLevel);

        long id = -1;
        try {
            id = db.insertOrThrow(TABLE_USER_PREFERENCES, null, values);
        } catch (SQLException e) {
            // Ya existe, actualizar
            id = db.update(TABLE_USER_PREFERENCES, values,
                    COL_PREFERENCE_USER_ID + " = ? AND " + COL_PREFERENCE_CATEGORY_ID + " = ?",
                    new String[]{String.valueOf(userId), String.valueOf(categoryId)});
        }
        db.close();
        return id;
    }

    // Obtener preferencias de un usuario
    public Cursor getPreferencesByUser(long userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT p.*, c." + COL_CATEGORY_NAME + ", c." + COL_CATEGORY_ICON
                + ", c." + COL_CATEGORY_COLOR + " FROM " + TABLE_USER_PREFERENCES + " p "
                + "INNER JOIN " + TABLE_CATEGORIES + " c ON p." + COL_PREFERENCE_CATEGORY_ID
                + " = c." + COL_CATEGORY_ID + " WHERE p." + COL_PREFERENCE_USER_ID + " = ? "
                + "ORDER BY p." + COL_PREFERENCE_INTEREST_LEVEL + " DESC";
        return db.rawQuery(query, new String[]{String.valueOf(userId)});
    }

    // Eliminar preferencia
    public int removePreference(long userId, long categoryId) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsAffected = db.delete(TABLE_USER_PREFERENCES,
                COL_PREFERENCE_USER_ID + " = ? AND " + COL_PREFERENCE_CATEGORY_ID + " = ?",
                new String[]{String.valueOf(userId), String.valueOf(categoryId)});
        db.close();
        return rowsAffected;
    }

    // ==================== MÉTODOS AUXILIARES ====================

    // Obtener número total de reviews de una localización
    public int getReviewCount(long locationId) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT COUNT(*) FROM " + TABLE_REVIEWS + " WHERE "
                + COL_REVIEW_LOCATION_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(locationId)});

        int count = 0;
        if (cursor.moveToFirst()) {
            count = cursor.getInt(0);
        }
        cursor.close();
        db.close();
        return count;
    }

    // Buscar localizaciones por nombre
    public Cursor searchLocationsByName(String searchTerm) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_LOCATIONS + " WHERE "
                + COL_LOCATION_NAME + " LIKE ? AND " + COL_LOCATION_ACTIVE + " = 1";
        return db.rawQuery(query, new String[]{"%" + searchTerm + "%"});
    }
}
