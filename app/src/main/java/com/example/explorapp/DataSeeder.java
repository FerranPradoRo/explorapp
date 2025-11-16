package com.example.explorapp;

import android.content.Context;
import android.database.Cursor;

/**
 * DataSeeder - Clase para poblar la base de datos con datos iniciales
 * Incluye categorías y las localizaciones de Guadalajara
 */
public class DataSeeder {

    private DatabaseHelper dbHelper;
    private Context context;

    public DataSeeder(Context context) {
        this.context = context;
        this.dbHelper = new DatabaseHelper(context);
    }

    /**
     * Poblar la base de datos con datos iniciales
     * Solo se ejecuta si la base de datos está vacía
     */
    public void seedDatabase() {
        // Verificar si ya hay datos
        if (isDatabaseSeeded()) {
            return;
        }

        // Poblar categorías
        long[] categoriaIds = seedCategorias();

        // Poblar localizaciones de Guadalajara
        seedLocalizacionesGuadalajara(categoriaIds);
    }

    /**
     * Verificar si la base de datos ya tiene datos
     */
    private boolean isDatabaseSeeded() {
        Cursor cursor = dbHelper.obtenerTodasCategorias();
        boolean hasData = cursor.getCount() > 0;
        cursor.close();
        return hasData;
    }

    /**
     * Poblar categorías iniciales
     * @return Array con los IDs de las categorías creadas
     */
    private long[] seedCategorias() {
        long[] ids = new long[7];

        // 0: Iglesia
        ids[0] = dbHelper.insertarCategoria(
                "Iglesia",
                "Templos y lugares de culto religioso",
                "church",
                "#8B4513"
        );

        // 1: Teatro
        ids[1] = dbHelper.insertarCategoria(
                "Teatro",
                "Teatros y espacios culturales para artes escénicas",
                "theater",
                "#9C27B0"
        );

        // 2: Museo
        ids[2] = dbHelper.insertarCategoria(
                "Museo",
                "Museos y galerías de arte",
                "museum",
                "#3F51B5"
        );

        // 3: Plaza
        ids[3] = dbHelper.insertarCategoria(
                "Plaza",
                "Plazas públicas y espacios de reunión",
                "plaza",
                "#4CAF50"
        );

        // 4: Mercado
        ids[4] = dbHelper.insertarCategoria(
                "Mercado",
                "Mercados tradicionales y centros comerciales",
                "market",
                "#FF9800"
        );

        // 5: Parque
        ids[5] = dbHelper.insertarCategoria(
                "Parque",
                "Parques y áreas verdes recreativas",
                "park",
                "#4CAF50"
        );

        // 6: Monumento
        ids[6] = dbHelper.insertarCategoria(
                "Monumento",
                "Monumentos históricos y conmemorativos",
                "monument",
                "#795548"
        );

        return ids;
    }

    /**
     * Poblar localizaciones de Guadalajara
     */
    private void seedLocalizacionesGuadalajara(long[] categoriaIds) {
        // 1. Catedral de Guadalajara
        long catedral = dbHelper.insertarLocalizacion(
                "Catedral de Guadalajara",
                "Hermosa catedral en el centro histórico de Guadalajara. Construida entre 1558 y 1618, "
                        + "es uno de los edificios más emblemáticos de la ciudad con sus icónicas torres gemelas.",
                20.6767,
                -103.3475,
                "Av. Fray Antonio Alcalde 10, Zona Centro",
                "Guadalajara",
                categoriaIds[0], // Iglesia
                0.0,
                "06:00",
                "20:00",
                "+52 33 3614 5504",
                null,
                4.8f
        );

        // 2. Teatro Degollado
        long teatro = dbHelper.insertarLocalizacion(
                "Teatro Degollado",
                "Teatro neoclásico emblemático de la ciudad. Inaugurado en 1866, es sede de la Orquesta "
                        + "Filarmónica de Jalisco y escenario de importantes eventos culturales.",
                20.6763,
                -103.3434,
                "Av. Degollado s/n, Zona Centro",
                "Guadalajara",
                categoriaIds[1], // Teatro
                100.0,
                "10:00",
                "18:00",
                "+52 33 3030 9771",
                "https://teatrodegollado.gob.mx",
                4.7f
        );

        // 3. Hospicio Cabañas
        long hospicio = dbHelper.insertarLocalizacion(
                "Hospicio Cabañas",
                "Patrimonio de la Humanidad con murales de Orozco. Fundado en 1810, alberga 57 murales "
                        + "del maestro José Clemente Orozco, siendo 'El Hombre de Fuego' el más famoso.",
                20.6755,
                -103.3397,
                "Calle Cabañas 8, Las Fresas",
                "Guadalajara",
                categoriaIds[2], // Museo
                80.0,
                "10:00",
                "18:00",
                "+52 33 3668 1642",
                "https://hospiciocabanas.jalisco.gob.mx",
                4.9f
        );

        // 4. Plaza de Armas
        long plaza = dbHelper.insertarLocalizacion(
                "Plaza de Armas",
                "Plaza principal de Guadalajara con kiosco francés. Espacio público histórico rodeado "
                        + "de importantes edificios como Palacio de Gobierno y la Catedral.",
                20.6770,
                -103.3467,
                "Av. Fray Antonio Alcalde, Zona Centro",
                "Guadalajara",
                categoriaIds[3], // Plaza
                0.0,
                "00:00",
                "23:59",
                null,
                null,
                4.6f
        );

        // 5. Mercado San Juan de Dios
        long mercado = dbHelper.insertarLocalizacion(
                "Mercado San Juan de Dios",
                "Uno de los mercados cubiertos más grandes de Latinoamérica. Conocido como 'Mercado Libertad', "
                        + "cuenta con más de 3,000 locales comerciales en tres pisos.",
                20.6748,
                -103.3454,
                "Calzada Independencia Sur s/n, Zona Centro",
                "Guadalajara",
                categoriaIds[4], // Mercado
                50.0,
                "06:00",
                "20:00",
                null,
                null,
                4.5f
        );

        // 6. Parque Agua Azul
        long parque = dbHelper.insertarLocalizacion(
                "Parque Agua Azul",
                "Parque urbano con áreas verdes y juegos. Espacio recreativo de 16 hectáreas con jardines, "
                        + "fuentes, áreas infantiles, pista de atletismo y un pequeño zoológico.",
                20.6678,
                -103.3386,
                "Calz Independencia Sur 973, Agua Azul",
                "Guadalajara",
                categoriaIds[5], // Parque
                20.0,
                "06:00",
                "18:00",
                "+52 33 3619 0328",
                null,
                4.4f
        );

        // 7. Rotonda de los Jaliscienses Ilustres
        long rotonda = dbHelper.insertarLocalizacion(
                "Rotonda de los Jaliscienses Ilustres",
                "Monumento en honor a personajes destacados de Jalisco. Inaugurado en 1952, alberga "
                        + "los restos de ilustres jaliscienses como José Clemente Orozco y Enrique Díaz de León.",
                20.6730,
                -103.3615,
                "Av. Fray Antonio Alcalde, Zona Centro",
                "Guadalajara",
                categoriaIds[6], // Monumento
                0.0,
                "00:00",
                "23:59",
                null,
                null,
                4.5f
        );

        // 8. Templo Expiatorio
        long templo = dbHelper.insertarLocalizacion(
                "Templo Expiatorio",
                "Templo de estilo neogótico con hermosos vitrales. Construcción iniciada en 1897 y "
                        + "finalizada en 1972, destaca por sus vitrales alemanes y arquitectura inspirada en "
                        + "la catedral de Colonia.",
                20.6797,
                -103.3562,
                "C. López Cotilla 935, Americana",
                "Guadalajara",
                categoriaIds[0], // Iglesia
                0.0,
                "07:00",
                "20:00",
                "+52 33 3825 4920",
                null,
                4.8f
        );
    }

    /**
     * Limpiar todos los datos de la base de datos
     * USAR CON PRECAUCIÓN - Borra todos los datos
     */
    public void clearDatabase() {
        dbHelper.onUpgrade(dbHelper.getWritableDatabase(), 1, 1);
    }

    /**
     * Re-poblar la base de datos (limpia y vuelve a insertar datos)
     */
    public void reseedDatabase() {
        clearDatabase();
        seedDatabase();
    }
}
