# ExploreApp - Documentación Completa del Proyecto

## Tabla de Contenidos
1. [Introducción](#introducción)
2. [Problema Identificado](#problema-identificado)
3. [Justificación](#justificación)
4. [Alcances y Límites](#alcances-y-límites)
5. [Objetivos](#objetivos)
6. [Preguntas de Investigación](#preguntas-de-investigación)
7. [Hipótesis](#hipótesis)
8. [Cronograma](#cronograma)
9. [Maquetado UI/UX](#maquetado-uiux)
10. [Requerimientos Técnicos](#requerimientos-técnicos)
11. [Arquitectura del Sistema](#arquitectura-del-sistema)
12. [Atributos de Calidad](#atributos-de-calidad)

---

## Introducción

**ExploreApp** es una aplicación móvil para el sector turístico que permite a los usuarios descubrir y explorar puntos de interés en una ciudad (específicamente enfocada en la Zona Metropolitana de Guadalajara).

### Concepto Principal
- Aplicación de mapas para turistas
- Visualización de puntos de interés organizados por categorías
- Guía visual y fácil de usar para visitantes
- Categorías: restaurantes, lugares para visitar, sitios históricos, entretenimiento, etc.

---

## Problema Identificado

### Limitaciones Actuales
Los turistas actualmente dependen de herramientas como **Google Maps** y fuentes dispersas de información (blogs, videos), lo que presenta varios problemas:

1. **Información Limitada**: Los resultados dependen de palabras clave específicas
2. **Resultados Inconsistentes**: Diferentes búsquedas generan diferentes resultados
3. **Falta de Categorización**: No se pueden categorizar lugares por tipo de experiencia
4. **Información Incompleta**: No muestra todas las posibilidades de lugares para visitar
5. **Proceso Lento**: Requiere mucho tiempo de investigación por parte del turista

---

## Justificación

### Ventajas de ExploreApp

1. **Solución Especializada**: Aplicación específica para turismo que ahorra tiempo
2. **Centralización**: Todos los puntos de interés en un solo lugar
3. **Descubrimiento**: Da visibilidad a lugares menos conocidos
4. **Beneficio Mutuo**: 
   - Usuarios encuentran nuevos lugares
   - Negocios locales obtienen más visibilidad
5. **Competitividad**: Puede competir con métodos actuales de búsqueda turística

---

## Alcances y Límites

### El Programa PODRÁ:

✅ **Geolocalización**
- Conectarse a la ubicación actual del dispositivo
- La ubicación del usuario será privada y no se compartirá con terceros

✅ **Actualización en Tiempo Real**
- Actualizar constantemente las ubicaciones de sitios importantes
- Sistema de calificaciones y comentarios de usuarios

✅ **Autenticación**
- Requerir registro de usuario para acceder a funciones

✅ **Contenido Multiidioma**
- Información de lugares turísticos en varios idiomas (español, inglés, francés, alemán)

✅ **Sistema de Rutas**
- Mostrar rutas a pie o en vehículo
- Considerar calles cerradas o en reparación

✅ **Funcionalidad Offline**
- Caché de datos con Firebase Firestore
- Mapas offline con Google Maps

### Radio de Operación
- Radio máximo de búsqueda: 50km desde el centro de Guadalajara
- Opciones de filtro: 1km, 5km, 10km o toda la ciudad

---

## Objetivos

### Objetivo General
Diseñar, desarrollar e implementar una aplicación móvil que funcione como herramienta de apoyo y guía para turistas, permitiéndoles explorar sitios de interés de forma autónoma sin necesidad de un guía turístico tradicional.

### Objetivos Específicos

#### 1. Diseño Atractivo
- Interfaz llamativa con esquemas de color apropiados por sección
- Elementos visuales simples y fáciles de identificar
- Diseño de botones intuitivo para navegación

#### 2. Desarrollo Intuitivo
- Aplicación que se explica por sí misma
- Uso de la intuición del usuario para comprender funcionalidades
- Minimal necesidad de tutoriales o explicaciones

#### 3. Implementación y Testing
- Pruebas en entorno controlado
- Testing en zonas turísticas específicas
- Evaluación con usuarios de diferentes edades y orígenes
- Análisis de reacciones y comportamiento de usuarios

---

## Preguntas de Investigación

### Pregunta Principal
**¿Cómo puede una aplicación móvil con tours virtuales y recomendaciones personalizadas mejorar la experiencia turística en la Zona Metropolitana de Guadalajara?**

### Preguntas Secundarias

1. **Necesidades de Información**
   - ¿Cuáles son las principales necesidades y preferencias de información de los turistas que visitan Guadalajara?

2. **Factores de Decisión**
   - ¿Qué factores consideran más importantes los turistas al elegir lugares para visitar?
   - Factores: proximidad, popularidad, tipo de experiencia, costo

3. **Formato de Información**
   - ¿Cómo prefieren los turistas recibir información sobre destinos?
   - Opciones: texto, video, audio, realidad aumentada

---

## Hipótesis

**Una aplicación móvil que combine tours virtuales, recomendaciones personalizadas basadas en preferencias del usuario y geolocalización, aumentará significativamente la satisfacción y eficiencia en la experiencia turística de los visitantes a la Zona Metropolitana de Guadalajara.**

---

## Supuestos del Proyecto

1. **Crecimiento de Negocios**: Los negocios locales crecerán gracias a las recomendaciones
2. **Contenido Generado**: Los usuarios generarán más contenido para aumentar la base de datos
3. **Expansión**: El éxito demandará implementación en más ciudades
4. **Competencia**: Otros competidores implementarán funcionalidades exitosas de la app

---

## Cronograma

### Fase 1: Propuesta del Proyecto ✅ Completado
- **Identificación del área de oportunidad**: 25-26 agosto 2025 (100%)
- **Identificación del problema**: 25-26 agosto 2025 (100%)
- **Generación de propuesta**: 25-26 agosto 2025 (100%)

### Fase 2: Protocolo ✅ Completado
- **Creación de cronograma de actividades**: 17-18 septiembre 2025 (100%)
- **Creación de protocolo**: 17-18 septiembre 2025 (100%)

### Fase 3: Planeación 🔄 Pendiente
- **Revisión y redacción de requerimientos**: 22-25 septiembre 2025
- **Listado de funcionalidades principales y secundarias**: 24-28 septiembre 2025
- **Diseño de arquitectura de software**: 26-27 septiembre 2025
- **Creación de diagramas**: 27-29 septiembre 2025

### Fase 4: Diseño y Codificación 🔄 Pendiente
- **Diseño de interfaz de usuario**: 30 septiembre - 5 octubre 2025
- **Diseño de base de datos**: 30 septiembre - 5 octubre 2025
- **Codificación**: 5-26 octubre 2025

### Fase 5: Pruebas 🔄 Pendiente
- **Reporte de pruebas**: 27-30 octubre 2025
- **Corrección de errores**: 30 octubre - 9 noviembre 2025
- **Reporte final de pruebas**: 9-12 noviembre 2025

### Fase 6: Entrega del Proyecto 🔄 Pendiente
- **Documentación final**: 13-15 noviembre 2025
- **Entrega del proyecto**: 13-15 noviembre 2025
- **Presentación del proyecto**: 16 noviembre 2025

---

## Maquetado UI/UX

### Flujo de Navegación Principal

```
Splash Screen (Logo Wayfinder)
    ↓
Login/Registro
    ├── Login con email/contraseña
    ├── Opción "Crear cuenta"
    ├── Login con Google
    └── Login con Facebook
    ↓
Pantalla Principal (Mapa)
    ├── Vista de mapa con marcadores
    ├── Búsqueda de lugares
    ├── Filtros por categoría
    └── Menú de navegación inferior
    ↓
Menú Lateral
    ├── Ubicación
    ├── Ciudad
    ├── Mapas
    ├── Calificaciones
    ├── Idioma
    └── Configuración
```

### Elementos de Diseño
- **Logo**: Brújula estilizada (Wayfinder)
- **Colores**: Esquema que varía por sección/categoría
- **Navegación**: Barra inferior con iconos principales
- **Mapas**: Vista principal con marcadores coloridos por categoría

---

## Requerimientos Técnicos

### Plataforma y Dispositivos

**Plataforma Objetivo**: Android Nativo
- **Dispositivos**: Smartphones y tablets Android
- **Versión Mínima**: Android 7.0 (API level 24) o superior

**Hardware Requerido**:
- GPS integrado para geolocalización
- Conectividad a internet (WiFi/datos móviles)
- Mínimo 2GB de RAM
- 500MB de almacenamiento disponible

### Entorno de Desarrollo

**IDE**: Android Studio (versión más reciente estable)
- **Lenguaje**: Java
- **SDK**: Android SDK con APIs necesarias
- **Build System**: Gradle
- **Control de Versiones**: Git integrado

### Base de Datos y Almacenamiento

**Base de Datos Local**: SQLite (nativa de Android)
- Almacenamiento de datos offline
- Consultas rápidas para búsquedas locales

**Base de Datos en la Nube**: Firebase Firestore
- Sincronización en tiempo real
- Persistencia offline automática
- Almacenamiento de imágenes: Firebase Storage

**Almacenamiento de Imágenes**: 
- Firebase Storage (respaldo en la nube)
- Almacenamiento interno del dispositivo (caché)

### APIs y Servicios Integrados

**Mapas y Geolocalización**:
- Google Maps Android API
- LocationManager de Android
- Google Play Services Location API

**Autenticación**:
- Firebase Authentication (email/password, Google, Facebook)

**Rutas**:
- Google Directions API (cálculo de rutas)

### Librerías y Dependencias Java

```gradle
// Networking
implementation 'com.squareup.retrofit2:retrofit:2.9.0'
implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

// Image Loading
implementation 'com.github.bumptech.glide:glide:4.15.0'
// o alternativamente
implementation 'com.squareup.picasso:picasso:2.8.0'

// Material Design
implementation 'com.google.android.material:material:1.9.0'

// Google Services
implementation 'com.google.android.gms:play-services-maps:18.1.0'
implementation 'com.google.android.gms:play-services-location:21.0.1'

// Firebase
implementation platform('com.google.firebase:firebase-bom:32.2.0')
implementation 'com.google.firebase:firebase-auth'
implementation 'com.google.firebase:firebase-firestore'
implementation 'com.google.firebase:firebase-storage'
```

### Funcionalidades Técnicas Implementadas

1. **Sistema de Recomendaciones**: Algoritmos locales basados en consultas SQLite
2. **Soporte Multiidioma**: Resources strings en múltiples idiomas (res/values)
3. **Modo Offline**: 
   - Datos almacenados en SQLite local
   - Caché de mapas con Google Maps offline
4. **Sistema de Calificaciones**: Tablas relacionales en SQLite
5. **Cálculo de Rutas**: Google Directions API

### Conocimientos Previos Requeridos

- ✅ Programación en Java (sintaxis, POO, manejo de excepciones)
- ✅ Desarrollo Android nativo (Activities, Intents, Services)
- ✅ Bases de datos (SQLite, consultas SQL, diseño de esquemas)
- ✅ Android Studio (navegación del IDE, debugging, emuladores)
- ✅ APIs REST (consumo de servicios web, manejo de JSON)
- ✅ Google Maps (integración de mapas, marcadores, eventos)
- ✅ Material Design (principios de diseño UI/UX para Android)

---

## Arquitectura del Sistema

### Arquitectura en Capas

```
┌─────────────────────────────────────────────┐
│        CAPA DE PRESENTACIÓN (UI)            │
│     Android Activities/Fragments (Java)     │
└─────────────────────────────────────────────┘
                    ↕
┌─────────────────────────────────────────────┐
│      CAPA DE NEGOCIO (Business Logic)       │
│         Gestores y Reglas de Negocio        │
└─────────────────────────────────────────────┘
                    ↕
┌─────────────────────────────────────────────┐
│     CAPA DE PERSISTENCIA (Data Access)      │
│         Firebase SDK / SQLite Helper        │
└─────────────────────────────────────────────┘
                    ↕
┌─────────────────────────────────────────────┐
│       CAPA DE DATOS (Data Storage)          │
│   Firebase Firestore / SQLite Database      │
└─────────────────────────────────────────────┘
```

---

## Capa de Presentación (UI Layer)

### Tecnología
- **Implementación**: Java + Android Studio
- **Framework UI**: Android SDK + Material Design Components

### Componentes Principales

#### 1. Activities/Fragments
- **SplashActivity**: Pantalla de inicio con logo
- **LoginActivity**: Autenticación de usuario
- **RegisterActivity**: Registro de nuevo usuario
- **MainActivity**: Pantalla principal con mapa
- **PlaceDetailActivity**: Detalle de un lugar específico
- **ProfileActivity**: Perfil del usuario
- **SettingsActivity**: Configuración de la app

#### 2. Elementos de Navegación (XML + Java)
- **Bottom Navigation Bar**: Navegación principal
- **Navigation Drawer**: Menú lateral deslizante
- **Toolbar**: Barra superior con título y acciones
- **Tab Layout**: Pestañas para categorías

#### 3. Mapas Interactivos (Google Maps API + Java)
- **MapView/MapFragment**: Vista del mapa
- **Marcadores personalizados**: Por categoría de lugar
- **InfoWindows**: Información rápida al tocar marcador
- **Rutas dibujadas**: Visualización de rutas a pie/vehículo
- **Geolocalización**: Marcador de ubicación del usuario

#### 4. Formularios (XML + Java)
- **Registro de usuario**: Email, contraseña, nombre
- **Login**: Autenticación
- **Comentarios y reseñas**: TextViews expandibles
- **Calificaciones**: RatingBar (sistema de estrellas)

#### 5. Listas de Contenido (RecyclerView/ListView)
- **Lista de lugares**: Con fotos, nombre, calificación
- **Lista de reseñas**: Comentarios de usuarios
- **Lista de categorías**: Grid o lista de categorías
- **Recomendaciones**: Lugares sugeridos al usuario

#### 6. Soporte Multiidioma (resources/strings.xml)
- Archivos `strings.xml` por idioma:
  - `values/strings.xml` (español)
  - `values-en/strings.xml` (inglés)
  - `values-fr/strings.xml` (francés)
  - `values-de/strings.xml` (alemán)

#### 7. Retroalimentación Visual (Material Design)
- **SnackBars**: Mensajes temporales
- **Progress Indicators**: Carga de datos
- **Dialogs**: Confirmaciones y alertas
- **Toasts**: Notificaciones breves
- **Pull-to-refresh**: Actualización de contenido

---

## Capa de Negocio (Business Logic Layer)

### Componentes Principales (Gestores)

#### 1. Gestor de Recomendaciones
**Responsabilidad**: Generar listas personalizadas de lugares

**Algoritmo de Recomendación**:
```java
// Fórmula de puntuación
score = (popularidad * 0.3) + (proximidad * 0.25) + 
        (coincidenciaConPerfil * 0.35) + (horarioAbierto * 0.1)

donde:
- popularidad = (calificación promedio) × (número de reviews)
- proximidad = 1 / distancia_en_km
- coincidenciaConPerfil = similitud con preferencias del usuario
- horarioAbierto = 1 si está abierto, 0.5 si no
```

**Factores Considerados**:
- Popularidad (calificación × número de reviews)
- Proximidad geográfica
- Coincidencia con perfil del usuario
- Horarios de apertura

#### 2. Gestor de Perfiles de Usuario
**Responsabilidad**: Clasificar y entender preferencias del usuario

**Tipos de Turistas**:
- 🏃 Aventurero (actividades al aire libre, deportes)
- 🎨 Cultural (museos, galerías, sitios históricos)
- 🍽️ Gastronómico (restaurantes, mercados, tours culinarios)
- 👨‍👩‍👧 Familiar (parques, zonas infantiles, actividades para niños)
- 🌙 Nocturno (bares, clubs, vida nocturna)

**Método de Clasificación**:
1. Cuestionario inicial al registrarse
2. Aprendizaje basado en comportamiento:
   - Lugares visitados
   - Lugares guardados
   - Lugares calificados positivamente

#### 3. Gestor de Geolocalización
**Responsabilidad**: Manejo de ubicación y cálculo de distancias

**Funciones**:
- Obtener ubicación actual del usuario (GPS + Network)
- Calcular distancia entre dos puntos (fórmula de Haversine)
- Filtrar resultados por radio de búsqueda:
  - Cerca (1km)
  - Moderado (5km)
  - Lejos (10km)
  - Toda la ciudad

**Verificación de Visita**:
- Radio de verificación: 50 metros
- Tiempo mínimo en ubicación: 2 minutos
- Usado para permitir calificaciones

#### 4. Gestor de Tours Virtuales
**Responsabilidad**: Organizar rutas temáticas

**Tours Predefinidos**:
- 🏛️ Centro Histórico
- 🎨 Arte y Cultura
- 🌮 Sabores de Jalisco
- 🌳 Parques y Naturaleza
- 🛍️ Shopping Tour
- 🌆 Arquitectura Moderna

**Funcionalidad**:
- Ordenar lugares por cercanía (algoritmo del vecino más cercano)
- Calcular tiempos estimados de recorrido
- Sugerir medio de transporte óptimo

#### 5. Gestor de Calificaciones
**Responsabilidad**: Sistema de reviews y ratings

**Reglas**:
- ✅ Usuario debe haber visitado el lugar (verificación GPS)
- ⭐ Calificación: 1-5 estrellas
- 💬 Comentario opcional (máximo 500 caracteres)
- 📊 Promedio ponderado: calificaciones recientes tienen más peso

**Fórmula de Promedio Ponderado**:
```java
pesoReciente = 1.0
pesoMedio = 0.7
pesoAntiguo = 0.4

promedioFinal = suma(calificación × peso) / suma(pesos)
```

#### 6. Gestor de Rutas
**Responsabilidad**: Calcular rutas óptimas

**Medios de Transporte**:
- 🚶 A pie
- 🚌 Transporte público
- 🚗 Auto

**Consideraciones**:
- Cierres viales temporales
- Eventos que afectan tráfico
- Horarios de transporte público
- Accesibilidad (rampas, elevadores)

**API Utilizada**: Google Directions API

#### 7. Gestor de Categorización
**Responsabilidad**: Organizar lugares por tipo

**Categorías Principales**:
1. 🍽️ Restaurantes y Gastronomía
2. 🏛️ Museos y Galerías
3. 🎭 Entretenimiento
4. 🌳 Parques y Naturaleza
5. 🏛️ Sitios Históricos
6. 🛍️ Compras
7. 🌙 Vida Nocturna
8. 👨‍👩‍👧 Actividades Familiares

#### 8. Gestor de Contenido Multiidioma
**Responsabilidad**: Proporcionar información en múltiples idiomas

**Idiomas Soportados**:
- 🇪🇸 Español
- 🇬🇧 Inglés
- 🇫🇷 Francés
- 🇩🇪 Alemán

**Implementación**:
- Detección automática del idioma del dispositivo
- Opción de cambio manual en configuración
- Información básica disponible en todos los idiomas
- Reseñas en idioma original con opción de traducción

---

### Reglas de Negocio Principales

#### Privacidad
- ❌ La ubicación del usuario NO se comparte con otros usuarios
- ❌ La ubicación NO se comparte con negocios
- ✅ La ubicación solo se usa para funcionalidades de la app

#### Verificación de Visitas
- ✅ Solo usuarios que visitaron un lugar pueden calificarlo
- 📍 Verificación mediante GPS (radio de 50m)
- ⏱️ Tiempo mínimo en ubicación: 2 minutos

#### Actualización de Información
- 🔄 Información de lugares se actualiza cada 24 horas
- 🔄 Calificaciones se recalculan al recibir nuevas reviews
- 🔄 Tours virtuales se actualizan semanalmente

#### Límites de Operación
- 🗺️ Radio máximo de búsqueda: 50km desde centro de Guadalajara
- ⭐ Calificación mínima para lugares destacados: 4.0 estrellas
- 💬 Mínimo de reviews para destacados: 10 reseñas

#### Contenido Generado por Usuario
- 📝 Máximo 3 reseñas por usuario por lugar
- 📸 Máximo 5 fotos por usuario por lugar
- 🚫 Sistema de reportes para contenido inapropiado

---

### Flujo Principal de Uso

```
1. REGISTRO
   Usuario se registra → Completa cuestionario de preferencias
   
2. CREACIÓN DE PERFIL
   Sistema crea perfil personalizado → Genera recomendaciones iniciales
   
3. BÚSQUEDA
   Usuario busca lugares → Aplica filtros (categoría, distancia, calificación)
   
4. VISUALIZACIÓN
   Sistema ordena resultados por relevancia → Muestra en mapa
   
5. SELECCIÓN
   Usuario selecciona un lugar → Ve detalles, fotos, reviews
   
6. NAVEGACIÓN
   Usuario inicia navegación → Sistema calcula ruta óptima
   
7. VISITA
   Usuario llega al lugar → Sistema verifica ubicación GPS
   
8. CALIFICACIÓN
   Usuario califica el lugar → Sistema actualiza promedios
   
9. APRENDIZAJE
   Sistema analiza comportamiento → Mejora recomendaciones futuras
```

---

## Capa de Persistencia (Data Access Layer)

### Tecnología
**Firebase SDK**: Puente entre lógica de negocio y base de datos en la nube

### Responsabilidades del SDK

#### 1. Abstracción de la Base de Datos
Simplifica operaciones CRUD sin escribir consultas SQL manualmente:
```java
// Ejemplo de escritura
FirebaseFirestore db = FirebaseFirestore.getInstance();
db.collection("puntosDeInteres")
  .document(placeId)
  .set(placeData)
  .addOnSuccessListener(...)
  .addOnFailureListener(...);

// Ejemplo de lectura
db.collection("puntosDeInteres")
  .whereEqualTo("categoria", "Restaurantes")
  .get()
  .addOnSuccessListener(...);
```

#### 2. Manejo de Persistencia Offline
- Caché local automático
- Sincronización automática al recuperar conexión
- Sin código adicional necesario para modo offline

```java
// Habilitar persistencia offline
FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
    .setPersistenceEnabled(true)
    .build();
db.setFirestoreSettings(settings);
```

#### 3. Sincronización en Tiempo Real
Escuchar cambios en la base de datos:
```java
db.collection("puntosDeInteres")
  .document(placeId)
  .addSnapshotListener((snapshot, e) -> {
      if (e != null) {
          // Manejar error
          return;
      }
      if (snapshot != null && snapshot.exists()) {
          // Actualizar UI con nuevos datos
          Place place = snapshot.toObject(Place.class);
          updateUI(place);
      }
  });
```

### SQLite Helper (Datos Locales)

**Clase Helper para SQLite**:
```java
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "explorapp.db";
    private static final int DATABASE_VERSION = 1;
    
    // Tablas
    private static final String TABLE_PLACES = "places";
    private static final String TABLE_FAVORITES = "favorites";
    private static final String TABLE_REVIEWS = "reviews";
    
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tablas
        db.execSQL(CREATE_TABLE_PLACES);
        db.execSQL(CREATE_TABLE_FAVORITES);
        db.execSQL(CREATE_TABLE_REVIEWS);
    }
    
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Actualizar esquema
    }
}
```

---

## Capa de Datos (Data Storage Layer)

### Firebase Firestore (Base de Datos en la Nube)

#### Estructura de Colecciones

```
explorapp (raíz)
│
├── puntosDeInteres/
│   ├── {placeId}/
│   │   ├── nombre: "Hospicio Cabañas"
│   │   ├── descripcion: "Patrimonio de la Humanidad UNESCO..."
│   │   ├── categoria: "Museos"
│   │   ├── subcategoria: "Arte y Cultura"
│   │   ├── coordenadas: GeoPoint(20.676, -103.347)
│   │   ├── direccion: "Calle Cabañas 8, Centro..."
│   │   ├── horarios: {
│   │   │   lunes: "10:00-18:00",
│   │   │   martes: "10:00-18:00",
│   │   │   ...
│   │   │   domingo: "Cerrado"
│   │   ├── }
│   │   ├── precioPromedio: 70
│   │   ├── telefono: "+52 33 3668 1642"
│   │   ├── sitioWeb: "https://..."
│   │   ├── calificacionPromedio: 4.5
│   │   ├── numeroReviews: 234
│   │   ├── imagenes: [
│   │   │   "gs://explorapp.../image1.jpg",
│   │   │   "gs://explorapp.../image2.jpg"
│   │   ├── ]
│   │   ├── etiquetas: ["histórico", "arte", "arquitectura"]
│   │   ├── idiomas: {
│   │   │   es: {...},
│   │   │   en: {...},
│   │   │   fr: {...}
│   │   ├── }
│   │   ├── fechaCreacion: Timestamp
│   │   ├── fechaActualizacion: Timestamp
│   │   │
│   │   └── reseñas/ (sub-colección)
│   │       ├── {reviewId}/
│   │       │   ├── usuarioId: "ref:usuarios/{uid}"
│   │       │   ├── nombreUsuario: "Juan Pérez"
│   │       │   ├── calificacion: 5
│   │       │   ├── comentario: "Excelente lugar..."
│   │       │   ├── fechaVisita: Timestamp
│   │       │   ├── fechaReview: Timestamp
│   │       │   ├── verificadoGPS: true
│   │       │   ├── imagenes: [...]
│   │       │   ├── meGusta: 12
│   │       │   └── reportes: 0
│   │       └── ...
│   └── ...
│
├── usuarios/
│   ├── {userId}/
│   │   ├── nombre: "María González"
│   │   ├── email: "maria@example.com"
│   │   ├── fotoPerfil: "gs://..."
│   │   ├── fechaRegistro: Timestamp
│   │   ├── tipoTurista: "Cultural"
│   │   ├── preferencias: {
│   │   │   categoriasFavoritas: ["Museos", "Restaurantes"],
│   │   │   presupuesto: "Moderado",
│   │   │   movilidad: "A pie"
│   │   ├── }
│   │   ├── estadisticas: {
│   │   │   lugaresVisitados: 15,
│   │   │   reviewsEscritas: 8,
│   │   │   fotosSubidas: 23
│   │   ├── }
│   │   ├── favoritos: [
│   │   │   "ref:puntosDeInteres/{placeId}",
│   │   │   ...
│   │   ├── ]
│   │   ├── historialVisitas: [
│   │   │   {placeId: "...", fecha: Timestamp},
│   │   │   ...
│   │   ├── ]
│   │   └── configuracion: {
│   │       idioma: "es",
│   │       notificaciones: true,
│   │       privacidad: "publica"
│   │   }
│   └── ...
│
├── categorias/
│   ├── {categoriaId}/
│   │   ├── nombreDeCategoria: "Restaurantes"
│   │   ├── icono: "restaurant"
│   │   ├── color: "#FF5722"
│   │   ├── descripcion: {
│   │   │   es: "Lugares para comer",
│   │   │   en: "Places to eat"
│   │   ├── }
│   │   ├── orden: 1
│   │   └── activa: true
│   └── ...
│
├── tours/
│   ├── {tourId}/
│   │   ├── nombre: "Centro Histórico"
│   │   ├── descripcion: "Recorrido por el corazón..."
│   │   ├── duracionEstimada: 180 (minutos)
│   │   ├── distanciaTotal: 3.5 (km)
│   │   ├── dificultad: "Fácil"
│   │   ├── lugares: [
│   │   │   {orden: 1, placeId: "...", tiempoSugerido: 30},
│   │   │   {orden: 2, placeId: "...", tiempoSugerido: 45},
│   │   │   ...
│   │   ├── ]
│   │   ├── calificacion: 4.7
│   │   ├── vecesCompletado: 456
│   │   └── activo: true
│   └── ...
│
└── configuracion/
    └── app/
        ├── versionMinima: "1.0.0"
        ├── radioMaximoBusqueda: 50 (km)
        ├── idiomasSoportados: ["es", "en", "fr", "de"]
        ├── categoriasActivas: [...]
        └── mensajesMantenimiento: {...}
```

#### Índices de Firestore
Para optimizar consultas:
```
Colección: puntosDeInteres
- Índice compuesto: categoria + calificacionPromedio (desc)
- Índice compuesto: coordenadas (geohash) + categoria
- Índice: fechaActualizacion (desc)

Colección: reseñas (sub-colección)
- Índice: fechaReview (desc)
- Índice: calificacion (desc)
```

#### Reglas de Seguridad de Firestore
```javascript
rules_version = '2';
service cloud.firestore {
  match /databases/{database}/documents {
    
    // Puntos de interés: lectura pública, escritura solo administradores
    match /puntosDeInteres/{placeId} {
      allow read: if true;
      allow write: if request.auth != null && 
                     get(/databases/$(database)/documents/usuarios/$(request.auth.uid)).data.rol == 'admin';
      
      // Reseñas: usuarios autenticados pueden escribir
      match /reseñas/{reviewId} {
        allow read: if true;
        allow create: if request.auth != null && 
                        request.resource.data.usuarioId == request.auth.uid &&
                        hasVisitedPlace(placeId);
        allow update, delete: if request.auth != null && 
                                 resource.data.usuarioId == request.auth.uid;
      }
    }
    
    // Usuarios: solo el propietario puede leer/escribir
    match /usuarios/{userId} {
      allow read, write: if request.auth != null && 
                           request.auth.uid == userId;
    }
    
    // Categorías: lectura pública
    match /categorias/{categoriaId} {
      allow read: if true;
      allow write: if false; // Solo via admin SDK
    }
    
    // Tours: lectura pública
    match /tours/{tourId} {
      allow read: if true;
      allow write: if false; // Solo via admin SDK
    }
  }
  
  // Función helper para verificar visita
  function hasVisitedPlace(placeId) {
    return exists(/databases/$(database)/documents/usuarios/$(request.auth.uid)/historialVisitas/$(placeId));
  }
}
```

### SQLite (Base de Datos Local)

#### Esquema de Tablas

```sql
-- Tabla de lugares (caché)
CREATE TABLE places (
    id TEXT PRIMARY KEY,
    nombre TEXT NOT NULL,
    descripcion TEXT,
    categoria TEXT,
    latitud REAL NOT NULL,
    longitud REAL NOT NULL,
    direccion TEXT,
    calificacion REAL,
    numero_reviews INTEGER,
    precio_promedio INTEGER,
    imagen_principal TEXT,
    fecha_sincronizacion INTEGER,
    UNIQUE(id)
);

-- Tabla de favoritos
CREATE TABLE favorites (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id TEXT NOT NULL,
    place_id TEXT NOT NULL,
    fecha_agregado INTEGER,
    FOREIGN KEY (place_id) REFERENCES places(id),
    UNIQUE(user_id, place_id)
);

-- Tabla de visitas
CREATE TABLE visits (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id TEXT NOT NULL,
    place_id TEXT NOT NULL,
    fecha_visita INTEGER,
    duracion INTEGER,
    verificado_gps BOOLEAN,
    FOREIGN KEY (place_id) REFERENCES places(id)
);

-- Tabla de búsquedas recientes
CREATE TABLE recent_searches (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    user_id TEXT NOT NULL,
    query TEXT NOT NULL,
    fecha INTEGER,
    UNIQUE(user_id, query)
);

-- Tabla de categorías (caché)
CREATE TABLE categories (
    id TEXT PRIMARY KEY,
    nombre TEXT NOT NULL,
    icono TEXT,
    color TEXT,
    orden INTEGER
);

-- Índices para optimización
CREATE INDEX idx_places_categoria ON places(categoria);
CREATE INDEX idx_places_calificacion ON places(calificacion DESC);
CREATE INDEX idx_places_location ON places(latitud, longitud);
CREATE INDEX idx_favorites_user ON favorites(user_id);
CREATE INDEX idx_visits_user_date ON visits(user_id, fecha_visita DESC);
```

### Firebase Storage (Almacenamiento de Imágenes)

#### Estructura de Carpetas
```
gs://explorapp.appspot.com/
│
├── lugares/
│   ├── {placeId}/
│   │   ├── principal.jpg
│   │   ├── galeria/
│   │   │   ├── 1.jpg
│   │   │   ├── 2.jpg
│   │   │   └── ...
│   │   └── thumbnails/
│   │       ├── principal_thumb.jpg
│   │       └── ...
│   └── ...
│
├── usuarios/
│   ├── {userId}/
│   │   ├── perfil.jpg
│   │   └── reviews/
│   │       ├── {reviewId}/
│   │       │   ├── 1.jpg
│   │       │   └── 2.jpg
│   │       └── ...
│   └── ...
│
└── tours/
    ├── {tourId}/
    │   └── portada.jpg
    └── ...
```

#### Reglas de Seguridad de Storage
```javascript
rules_version = '2';
service firebase.storage {
  match /b/{bucket}/o {
    
    // Imágenes de lugares
    match /lugares/{placeId}/{allPaths=**} {
      allow read: if true;
      allow write: if request.auth != null;
    }
    
    // Imágenes de usuarios
    match /usuarios/{userId}/{allPaths=**} {
      allow read: if true;
      allow write: if request.auth != null && 
                     request.auth.uid == userId;
    }
    
    // Imágenes de tours
    match /tours/{tourId}/{allPaths=**} {
      allow read: if true;
      allow write: if false; // Solo administradores
    }
  }
}
```

---

## Atributos de Calidad

### 1. Usabilidad ⭐⭐⭐⭐⭐
**Prioridad**: CRÍTICA

**Requisitos**:
- Interfaz intuitiva para turistas de todas las edades
- No requiere tutorial para funcionalidades básicas
- Iconos reconocibles universalmente
- Categorías claramente diferenciadas
- Accesibilidad para personas con discapacidades

**Métricas**:
- Tiempo para completar tarea básica: < 30 segundos
- Tasa de éxito en primera interacción: > 90%
- Puntuación SUS (System Usability Scale): > 80

**Implementación**:
- Seguir Material Design Guidelines
- Pruebas de usabilidad con usuarios reales
- Feedback visual inmediato en todas las interacciones

---

### 2. Rendimiento ⚡⚡⚡⚡⚡
**Prioridad**: ALTA

**Requisitos**:
- Carga rápida de mapas incluso con conexión limitada
- Tiempo de respuesta al buscar lugares: < 3 segundos
- Tiempo de cálculo de rutas: < 5 segundos
- Fluidez en navegación: 60 FPS constantes
- Consumo eficiente de batería

**Métricas**:
- Tiempo de inicio de app: < 2 segundos
- Tiempo de carga de mapa: < 3 segundos
- Tiempo de búsqueda: < 3 segundos
- Consumo de batería: < 5% por hora de uso activo

**Optimizaciones**:
- Caché de imágenes con Glide
- Paginación de resultados (lazy loading)
- Compresión de imágenes antes de subir
- Uso de WebP para imágenes
- Recycling de vistas en RecyclerView

---

### 3. Disponibilidad 🌐
**Prioridad**: ALTA

**Requisitos**:
- Sistema operativo 24/7
- Uptime mínimo: 99% (aprox. 7.2 horas de downtime/año)
- Funcionalidad offline para características críticas
- Sincronización automática al recuperar conexión

**Estrategias**:
- Infraestructura en la nube (Firebase)
- Modo offline robusto con SQLite
- Caché de mapas offline (Google Maps)
- Manejo gracioso de errores de red

**Funcionalidades Offline**:
- ✅ Visualización de lugares guardados
- ✅ Consulta de favoritos
- ✅ Visualización de rutas previamente consultadas
- ✅ Lectura de reseñas descargadas
- ❌ Búsqueda de nuevos lugares (requiere conexión)
- ❌ Actualización de calificaciones (requiere conexión)

---

### 4. Precisión de Geolocalización 📍
**Prioridad**: CRÍTICA

**Requisitos**:
- Margen de error: < 10 metros
- Actualización de ubicación: cada 5 segundos en movimiento
- Funcionar en interiores (cuando sea posible)
- Detección de movimiento para optimizar batería

**Implementación**:
```java
// Configuración de LocationRequest
LocationRequest locationRequest = LocationRequest.create()
    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
    .setInterval(5000) // 5 segundos
    .setFastestInterval(2000) // 2 segundos
    .setSmallestDisplacement(10); // 10 metros
```

**Verificación de Visitas**:
- Radio de verificación: 50 metros del punto de interés
- Tiempo mínimo en ubicación: 2 minutos
- Usado para habilitar sistema de calificaciones

---

### 5. Seguridad y Privacidad 🔒
**Prioridad**: CRÍTICA

**Requisitos**:
- Ubicación del usuario encriptada end-to-end
- No compartir ubicación con terceros
- No compartir ubicación con otros usuarios
- Información personal protegida con autenticación
- Cumplimiento con GDPR y leyes de protección de datos

**Medidas de Seguridad**:
- Autenticación con Firebase Authentication
- Reglas de seguridad en Firestore y Storage
- Encriptación de datos sensibles
- No almacenar contraseñas (delegado a Firebase)
- Tokens de sesión con expiración

**Privacidad**:
- Política de privacidad clara y accesible
- Consentimiento explícito para uso de ubicación
- Opción de eliminar cuenta y datos
- Anonimización de datos para análisis

---

### 6. Escalabilidad 📈
**Prioridad**: MEDIA

**Requisitos**:
- Soportar crecimiento de usuarios sin degradación
- Soportar aumento de contenido generado (reseñas, fotos)
- Facilitar expansión a nuevas ciudades
- Arquitectura modular para añadir características

**Estrategias**:
- Uso de Firebase (auto-escalable)
- Diseño de base de datos NoSQL optimizado
- Arquitectura en capas bien definida
- Código modular y reutilizable

**Plan de Expansión**:
1. Fase 1: Guadalajara (MVP)
2. Fase 2: Otras ciudades de Jalisco
3. Fase 3: Principales ciudades turísticas de México
4. Fase 4: Expansión internacional

---

### 7. Confiabilidad de Contenido ✔️
**Prioridad**: ALTA

**Requisitos**:
- Información de lugares actualizada constantemente
- Verificación de horarios y direcciones
- Sistema de reportes para información incorrecta
- Validación de reseñas auténticas

**Estrategias**:
- Actualización automática cada 24 horas
- Verificación GPS para reseñas
- Sistema de reporte y moderación
- Datos iniciales de fuentes confiables (Google Places, gobierno)
- Verificación periódica con negocios

**Sistema de Reportes**:
- Usuarios pueden reportar:
  - Información incorrecta
  - Lugar cerrado permanentemente
  - Horarios incorrectos
  - Contenido inapropiado
- Umbral de reportes para revisión manual: 3

---

### 8. Mantenibilidad 🔧
**Prioridad**: MEDIA

**Requisitos**:
- Código limpio y bien documentado
- Arquitectura clara y separada en capas
- Facilidad para añadir nuevas características
- Pruebas unitarias y de integración

**Buenas Prácticas**:
- Seguir principios SOLID
- Comentarios en código complejo
- Nomenclatura consistente
- Versionamiento semántico
- Documentación de APIs

---

### 9. Internacionalización (i18n) 🌍
**Prioridad**: ALTA

**Requisitos**:
- Soporte para múltiples idiomas
- Adaptación a formatos locales (fechas, números, moneda)
- Contenido localizado apropiadamente

**Idiomas Soportados**:
1. Español (idioma principal)
2. Inglés
3. Francés
4. Alemán

**Implementación**:
```xml
<!-- res/values/strings.xml (español) -->
<string name="app_name">ExploreApp</string>
<string name="welcome_message">Bienvenido a ExploreApp</string>

<!-- res/values-en/strings.xml (inglés) -->
<string name="app_name">ExploreApp</string>
<string name="welcome_message">Welcome to ExploreApp</string>
```

---

## Testing y Calidad

### Plan de Pruebas

#### 1. Pruebas Unitarias
- Clases de negocio (gestores)
- Helpers y utilidades
- Validaciones

#### 2. Pruebas de Integración
- Conexión con Firebase
- APIs de Google Maps
- Sistema de autenticación

#### 3. Pruebas de UI
- Espresso para Android
- Flujos completos de usuario

#### 4. Pruebas de Usuario (UAT)
- **Ubicación**: Zonas turísticas de Guadalajara
- **Participantes**: Usuarios de diferentes edades y orígenes
- **Métricas**:
  - Tiempo para completar tareas
  - Tasa de éxito
  - Satisfacción del usuario (escala 1-10)
  - Problemas encontrados

#### 5. Pruebas de Rendimiento
- Tiempo de carga con diferentes velocidades de red
- Consumo de batería
- Uso de memoria

#### 6. Pruebas de Seguridad
- Penetration testing
- Validación de reglas de Firestore
- Encriptación de datos

---

## Glosario Técnico

### Términos de Desarrollo
- **Activity**: Pantalla única en Android
- **Fragment**: Componente reutilizable de UI
- **Intent**: Mecanismo para navegar entre Activities
- **RecyclerView**: Lista eficiente y reciclable de elementos
- **Adapter**: Conecta datos con RecyclerView

### Términos de Firebase
- **Firestore**: Base de datos NoSQL en tiempo real
- **Firebase Auth**: Servicio de autenticación
- **Firebase Storage**: Almacenamiento de archivos
- **Snapshot**: Estado de datos en un momento específico
- **Listener**: Callback que escucha cambios

### Términos de Mapas
- **Marker**: Marcador en el mapa
- **LatLng**: Coordenadas de latitud y longitud
- **Polyline**: Línea dibujada en el mapa (para rutas)
- **InfoWindow**: Ventana de información sobre un marcador
- **Geocoding**: Convertir dirección a coordenadas

### Términos de Negocio
- **Punto de Interés (POI)**: Lugar turístico
- **Review**: Reseña o calificación de usuario
- **Tour Virtual**: Ruta predefinida de lugares
- **Perfil de Turista**: Clasificación de preferencias
- **Verificación GPS**: Confirmación de visita a un lugar

---

## Recursos y Referencias

### Documentación Oficial
- [Android Developer Guides](https://developer.android.com/guide)
- [Firebase Documentation](https://firebase.google.com/docs)
- [Google Maps SDK for Android](https://developers.google.com/maps/documentation/android-sdk)
- [Material Design Guidelines](https://material.io/design)

### APIs Utilizadas
- Google Maps Android API
- Google Directions API
- Firebase Authentication
- Firebase Firestore
- Firebase Storage

### Librerías Principales
- Retrofit (networking)
- Gson (JSON parsing)
- Glide (image loading)
- Material Components (UI)

---

## Contacto y Equipo

**Nombre del Proyecto**: ExploreApp - Sistema de Recomendación de Lugares Turísticos

**Ubicación**: Zona Metropolitana de Guadalajara, Jalisco, México

**Fecha de Inicio**: 25 de agosto de 2025

**Fecha de Entrega**: 16 de noviembre de 2025

---

## Notas Adicionales para Claude Code

### Convenciones de Código
- **Nomenclatura**: camelCase para variables y métodos, PascalCase para clases
- **Paquetes**: com.explorapp.{feature}
- **Idioma**: Comentarios en español, código en inglés

### Estructura de Directorios Recomendada
```
app/
├── src/
│   ├── main/
│   │   ├── java/com/explorapp/
│   │   │   ├── activities/
│   │   │   ├── fragments/
│   │   │   ├── adapters/
│   │   │   ├── models/
│   │   │   ├── managers/ (gestores)
│   │   │   ├── utils/
│   │   │   └── services/
│   │   ├── res/
│   │   │   ├── layout/
│   │   │   ├── drawable/
│   │   │   ├── values/
│   │   │   ├── values-en/
│   │   │   ├── values-fr/
│   │   │   └── values-de/
│   │   └── AndroidManifest.xml
│   └── test/
└── build.gradle
```

### Prioridades de Desarrollo
1. **Fase 1**: Autenticación y perfiles de usuario
2. **Fase 2**: Visualización de mapa y lugares
3. **Fase 3**: Sistema de búsqueda y filtros
4. **Fase 4**: Navegación y rutas
5. **Fase 5**: Sistema de calificaciones y reseñas
6. **Fase 6**: Tours virtuales y recomendaciones

### Consideraciones Especiales
- Manejar permisos de ubicación según Android 10+
- Implementar modo oscuro (Dark Mode)
- Optimizar para diferentes tamaños de pantalla
- Considerar accesibilidad (TalkBack, tamaños de texto)

---

## Changelog del Documento

**Versión 1.0** - Noviembre 14, 2025
- Documento inicial completo basado en el protocolo del proyecto
- Incluye todas las secciones: arquitectura, requerimientos, cronograma
- Listo para ser utilizado por Claude Code como contexto completo

---

**Fin del Documento**
