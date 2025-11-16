# Guía de Uso de la Base de Datos - EXPLORAPP

## Estructura de la Base de Datos

La base de datos `explorapp.db` implementa el esquema completo definido en `db_schema.py` con 7 tablas:

### Tablas

1. **usuarios** - Gestión de usuarios de la aplicación
2. **categorias** - Categorías de lugares turísticos (Iglesia, Teatro, Museo, etc.)
3. **localizaciones** - Lugares turísticos con coordenadas GPS
4. **imagenes_localizaciones** - Imágenes asociadas a cada localización
5. **reviews** - Reviews y calificaciones de usuarios
6. **favoritos** - Lugares marcados como favoritos por usuarios
7. **preferencias_usuario** - Preferencias de categorías por usuario

## Clases Implementadas

### 1. DatabaseHelper.java
Clase principal que extiende `SQLiteOpenHelper`. Contiene:
- Definición de todas las tablas con foreign keys
- Métodos CRUD completos para todas las tablas
- Índices para optimización de consultas
- Gestión de integridad referencial

### 2. Modelos (package models/)
POJOs para representar cada tabla:
- `Usuario.java`
- `Categoria.java`
- `Localizacion.java`
- `ImagenLocalizacion.java`
- `Review.java`
- `Favorito.java`
- `PreferenciaUsuario.java`

### 3. DataSeeder.java
Clase para poblar la base de datos con datos iniciales:
- 7 categorías básicas
- 8 localizaciones de Guadalajara (las mismas que están hardcodeadas en MapaActivity)

## Inicialización de la Base de Datos

### Opción 1: Inicialización Automática en Application

Crea una clase Application personalizada:

```java
public class ExplorappApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // Poblar la base de datos con datos iniciales
        DataSeeder seeder = new DataSeeder(this);
        seeder.seedDatabase(); // Solo se ejecuta si la BD está vacía
    }
}
```

No olvides registrarla en `AndroidManifest.xml`:

```xml
<application
    android:name=".ExplorappApplication"
    ...>
</application>
```

### Opción 2: Inicialización Manual en MainActivity

```java
public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar base de datos
        DataSeeder seeder = new DataSeeder(this);
        seeder.seedDatabase();
    }
}
```

## Ejemplos de Uso

### 1. Obtener Localizaciones de una Ciudad

```java
DatabaseHelper dbHelper = new DatabaseHelper(this);
Cursor cursor = dbHelper.obtenerLocalizacionesPorCiudad("Guadalajara");

List<Localizacion> localizaciones = new ArrayList<>();
if (cursor.moveToFirst()) {
    do {
        Localizacion loc = new Localizacion();
        loc.setLocalizacionId(cursor.getLong(cursor.getColumnIndexOrThrow("localizacion_id")));
        loc.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
        loc.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow("descripcion")));
        loc.setLatitud(cursor.getDouble(cursor.getColumnIndexOrThrow("latitud")));
        loc.setLongitud(cursor.getDouble(cursor.getColumnIndexOrThrow("longitud")));
        loc.setPopularidadScore(cursor.getFloat(cursor.getColumnIndexOrThrow("popularidad_score")));
        localizaciones.add(loc);
    } while (cursor.moveToNext());
}
cursor.close();
```

### 2. Insertar un Usuario

```java
DatabaseHelper dbHelper = new DatabaseHelper(this);
long usuarioId = dbHelper.insertarUsuario(
    "Juan",
    "Pérez",
    "juan@example.com",
    "hashed_password_here",
    "México",
    null // foto_perfil
);

if (usuarioId != -1) {
    // Usuario insertado correctamente
}
```

### 3. Agregar un Lugar a Favoritos

```java
DatabaseHelper dbHelper = new DatabaseHelper(this);
long resultado = dbHelper.agregarFavorito(
    usuarioId,
    localizacionId,
    "Me encanta este lugar!"
);

if (resultado != -1) {
    // Agregado a favoritos correctamente
}
```

### 4. Verificar si un Lugar está en Favoritos

```java
DatabaseHelper dbHelper = new DatabaseHelper(this);
boolean esFavorito = dbHelper.esFavorito(usuarioId, localizacionId);

if (esFavorito) {
    // Mostrar icono de corazón lleno
} else {
    // Mostrar icono de corazón vacío
}
```

### 5. Obtener Favoritos de un Usuario

```java
DatabaseHelper dbHelper = new DatabaseHelper(this);
Cursor cursor = dbHelper.obtenerFavoritosPorUsuario(usuarioId);

List<Localizacion> favoritos = new ArrayList<>();
if (cursor.moveToFirst()) {
    do {
        Localizacion loc = new Localizacion();
        // Mapear datos del cursor
        favoritos.add(loc);
    } while (cursor.moveToNext());
}
cursor.close();
```

### 6. Insertar un Review

```java
DatabaseHelper dbHelper = new DatabaseHelper(this);
long reviewId = dbHelper.insertarReview(
    usuarioId,
    localizacionId,
    5, // calificación (1-5)
    "Excelente lugar, muy recomendado!",
    System.currentTimeMillis() / 1000 // fecha de visita en unix timestamp
);
```

### 7. Obtener Reviews de una Localización

```java
DatabaseHelper dbHelper = new DatabaseHelper(this);
Cursor cursor = dbHelper.obtenerReviewsPorLocalizacion(localizacionId);

List<Review> reviews = new ArrayList<>();
if (cursor.moveToFirst()) {
    do {
        Review review = new Review();
        review.setReviewId(cursor.getLong(cursor.getColumnIndexOrThrow("review_id")));
        review.setCalificacion(cursor.getInt(cursor.getColumnIndexOrThrow("calificacion")));
        review.setComentario(cursor.getString(cursor.getColumnIndexOrThrow("comentario")));
        review.setUsuarioNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
        review.setUsuarioApellido(cursor.getString(cursor.getColumnIndexOrThrow("apellido")));
        reviews.add(review);
    } while (cursor.moveToNext());
}
cursor.close();
```

### 8. Obtener Calificación Promedio

```java
DatabaseHelper dbHelper = new DatabaseHelper(this);
float calificacionPromedio = dbHelper.obtenerCalificacionPromedio(localizacionId);
// Ej: 4.5
```

### 9. Obtener Todas las Categorías

```java
DatabaseHelper dbHelper = new DatabaseHelper(this);
Cursor cursor = dbHelper.obtenerTodasCategorias();

List<Categoria> categorias = new ArrayList<>();
if (cursor.moveToFirst()) {
    do {
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(cursor.getLong(cursor.getColumnIndexOrThrow("categoria_id")));
        categoria.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
        categoria.setIcono(cursor.getString(cursor.getColumnIndexOrThrow("icono")));
        categoria.setColor(cursor.getString(cursor.getColumnIndexOrThrow("color")));
        categorias.add(categoria);
    } while (cursor.moveToNext());
}
cursor.close();
```

### 10. Buscar Localizaciones por Nombre

```java
DatabaseHelper dbHelper = new DatabaseHelper(this);
Cursor cursor = dbHelper.buscarLocalizacionesPorNombre("catedral");

// Procesar resultados...
cursor.close();
```

## Migración desde Datos Mock

Para migrar de los datos mock en MapaActivity a la base de datos:

### Antes (MapaActivity con datos mock):

```java
private List<PlaceMock> createMockPlaces() {
    List<PlaceMock> lugares = new ArrayList<>();
    lugares.add(new PlaceMock(
        "Catedral de Guadalajara",
        "Iglesia",
        new GeoPoint(20.6767, -103.3475),
        "Hermosa catedral...",
        4.8f
    ));
    return lugares;
}
```

### Después (MapaActivity con base de datos):

```java
private List<Localizacion> loadPlacesFromDatabase() {
    DatabaseHelper dbHelper = new DatabaseHelper(this);
    Cursor cursor = dbHelper.obtenerLocalizacionesPorCiudad("Guadalajara");

    List<Localizacion> lugares = new ArrayList<>();
    if (cursor.moveToFirst()) {
        do {
            Localizacion loc = new Localizacion();
            loc.setLocalizacionId(cursor.getLong(cursor.getColumnIndexOrThrow("localizacion_id")));
            loc.setNombre(cursor.getString(cursor.getColumnIndexOrThrow("nombre")));
            loc.setLatitud(cursor.getDouble(cursor.getColumnIndexOrThrow("latitud")));
            loc.setLongitud(cursor.getDouble(cursor.getColumnIndexOrThrow("longitud")));
            loc.setDescripcion(cursor.getString(cursor.getColumnIndexOrThrow("descripcion")));
            loc.setPopularidadScore(cursor.getFloat(cursor.getColumnIndexOrThrow("popularidad_score")));
            lugares.add(loc);
        } while (cursor.moveToNext());
    }
    cursor.close();

    return lugares;
}
```

## Notas Importantes

1. **Foreign Keys**: La base de datos tiene habilitadas las foreign keys con `PRAGMA foreign_keys=ON`

2. **Soft Deletes**: Los usuarios y localizaciones usan "soft delete" (campo `activo`) en lugar de borrado físico

3. **Timestamps**: Las fechas se almacenan como Unix timestamps (segundos desde 1970)
   - Para obtener timestamp actual: `System.currentTimeMillis() / 1000`
   - Para convertir a Date: `new Date(timestamp * 1000)`

4. **Cerrar Cursors**: Siempre cerrar los cursors después de usarlos para evitar memory leaks

5. **Transacciones**: Para operaciones múltiples, usar transacciones:
   ```java
   SQLiteDatabase db = dbHelper.getWritableDatabase();
   db.beginTransaction();
   try {
       // Múltiples operaciones...
       db.setTransactionSuccessful();
   } finally {
       db.endTransaction();
   }
   ```

6. **Constantes**: Usar las constantes definidas en `DatabaseHelper` para nombres de columnas:
   ```java
   cursor.getString(cursor.getColumnIndexOrThrow(DatabaseHelper.COL_LOCALIZACION_NOMBRE));
   ```

## DataSeeder - Métodos Disponibles

```java
DataSeeder seeder = new DataSeeder(context);

// Poblar solo si está vacía
seeder.seedDatabase();

// Limpiar toda la BD (¡PRECAUCIÓN!)
seeder.clearDatabase();

// Limpiar y volver a poblar
seeder.reseedDatabase();
```

## Próximos Pasos

1. Integrar DatabaseHelper en MapaActivity para cargar localizaciones desde BD
2. Implementar sistema de login/registro con tabla usuarios
3. Conectar FavoritesActivity con la tabla favoritos
4. Implementar sistema de reviews en PlaceDetailActivity
5. Agregar imágenes a la tabla imagenes_localizaciones
6. Implementar preferencias de usuario para recomendaciones personalizadas
