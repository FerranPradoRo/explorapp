from graphviz import Digraph

def create_simplified_database_schema():
    """
    Esquema simplificado de base de datos para la aplicación turística
    Sin: rutas, localizaciones_idiomas, historial_ubicaciones
    """
    dot = Digraph(comment='Simplified Tourist App Database Schema', format='png')
    dot.attr(rankdir='TB', splines='ortho', nodesep='0.8', ranksep='1.2')
    dot.attr('node', shape='plaintext', fontname='Arial')
    
    # TABLA: usuarios
    usuarios_table = '''<
    <TABLE BORDER="1" CELLBORDER="1" CELLSPACING="0" CELLPADDING="8" BGCOLOR="#E8F4F8">
        <TR><TD COLSPAN="3" BGCOLOR="#2C5F77"><FONT COLOR="white"><B>usuarios</B></FONT></TD></TR>
        <TR><TD BGCOLOR="#FFE5B4">PK</TD><TD ALIGN="LEFT"><B>usuario_id</B></TD><TD ALIGN="LEFT">SERIAL</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">nombre</TD><TD ALIGN="LEFT">VARCHAR(100)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">apellido</TD><TD ALIGN="LEFT">VARCHAR(100)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">email</TD><TD ALIGN="LEFT">VARCHAR(255) UNIQUE</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">password_hash</TD><TD ALIGN="LEFT">VARCHAR(255)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">fecha_registro</TD><TD ALIGN="LEFT">TIMESTAMP</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">pais_origen</TD><TD ALIGN="LEFT">VARCHAR(100)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">foto_perfil</TD><TD ALIGN="LEFT">TEXT</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">activo</TD><TD ALIGN="LEFT">BOOLEAN</TD></TR>
    </TABLE>>'''
    
    dot.node('usuarios', usuarios_table)
    
    # TABLA: categorias
    categorias_table = '''<
    <TABLE BORDER="1" CELLBORDER="1" CELLSPACING="0" CELLPADDING="8" BGCOLOR="#E8F4F8">
        <TR><TD COLSPAN="3" BGCOLOR="#2C5F77"><FONT COLOR="white"><B>categorias</B></FONT></TD></TR>
        <TR><TD BGCOLOR="#FFE5B4">PK</TD><TD ALIGN="LEFT"><B>categoria_id</B></TD><TD ALIGN="LEFT">SERIAL</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">nombre</TD><TD ALIGN="LEFT">VARCHAR(50)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">descripcion</TD><TD ALIGN="LEFT">TEXT</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">icono</TD><TD ALIGN="LEFT">VARCHAR(100)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">color</TD><TD ALIGN="LEFT">VARCHAR(20)</TD></TR>
    </TABLE>>'''
    
    dot.node('categorias', categorias_table)
    
    # TABLA: localizaciones
    localizaciones_table = '''<
    <TABLE BORDER="1" CELLBORDER="1" CELLSPACING="0" CELLPADDING="8" BGCOLOR="#E8F4F8">
        <TR><TD COLSPAN="3" BGCOLOR="#2C5F77"><FONT COLOR="white"><B>localizaciones</B></FONT></TD></TR>
        <TR><TD BGCOLOR="#FFE5B4">PK</TD><TD ALIGN="LEFT"><B>localizacion_id</B></TD><TD ALIGN="LEFT">SERIAL</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">nombre</TD><TD ALIGN="LEFT">VARCHAR(200)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">descripcion</TD><TD ALIGN="LEFT">TEXT</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">latitud</TD><TD ALIGN="LEFT">DECIMAL(10,8)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">longitud</TD><TD ALIGN="LEFT">DECIMAL(11,8)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">direccion</TD><TD ALIGN="LEFT">VARCHAR(255)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">ciudad</TD><TD ALIGN="LEFT">VARCHAR(100)</TD></TR>
        <TR><TD BGCOLOR="#D4E6F1">FK</TD><TD ALIGN="LEFT">categoria_id</TD><TD ALIGN="LEFT">INTEGER</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">costo_promedio</TD><TD ALIGN="LEFT">DECIMAL(10,2)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">horario_apertura</TD><TD ALIGN="LEFT">TIME</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">horario_cierre</TD><TD ALIGN="LEFT">TIME</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">telefono</TD><TD ALIGN="LEFT">VARCHAR(20)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">sitio_web</TD><TD ALIGN="LEFT">VARCHAR(255)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">popularidad_score</TD><TD ALIGN="LEFT">FLOAT</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">fecha_creacion</TD><TD ALIGN="LEFT">TIMESTAMP</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">activo</TD><TD ALIGN="LEFT">BOOLEAN</TD></TR>
    </TABLE>>'''
    
    dot.node('localizaciones', localizaciones_table)
    
    # TABLA: imagenes_localizaciones
    imagenes_table = '''<
    <TABLE BORDER="1" CELLBORDER="1" CELLSPACING="0" CELLPADDING="8" BGCOLOR="#E8F4F8">
        <TR><TD COLSPAN="3" BGCOLOR="#2C5F77"><FONT COLOR="white"><B>imagenes_localizaciones</B></FONT></TD></TR>
        <TR><TD BGCOLOR="#FFE5B4">PK</TD><TD ALIGN="LEFT"><B>imagen_id</B></TD><TD ALIGN="LEFT">SERIAL</TD></TR>
        <TR><TD BGCOLOR="#D4E6F1">FK</TD><TD ALIGN="LEFT">localizacion_id</TD><TD ALIGN="LEFT">INTEGER</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">url_imagen</TD><TD ALIGN="LEFT">TEXT</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">es_principal</TD><TD ALIGN="LEFT">BOOLEAN</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">orden</TD><TD ALIGN="LEFT">INTEGER</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">fecha_subida</TD><TD ALIGN="LEFT">TIMESTAMP</TD></TR>
    </TABLE>>'''
    
    dot.node('imagenes_localizaciones', imagenes_table)
    
    # TABLA: reviews
    reviews_table = '''<
    <TABLE BORDER="1" CELLBORDER="1" CELLSPACING="0" CELLPADDING="8" BGCOLOR="#E8F4F8">
        <TR><TD COLSPAN="3" BGCOLOR="#2C5F77"><FONT COLOR="white"><B>reviews</B></FONT></TD></TR>
        <TR><TD BGCOLOR="#FFE5B4">PK</TD><TD ALIGN="LEFT"><B>review_id</B></TD><TD ALIGN="LEFT">SERIAL</TD></TR>
        <TR><TD BGCOLOR="#D4E6F1">FK</TD><TD ALIGN="LEFT">usuario_id</TD><TD ALIGN="LEFT">INTEGER</TD></TR>
        <TR><TD BGCOLOR="#D4E6F1">FK</TD><TD ALIGN="LEFT">localizacion_id</TD><TD ALIGN="LEFT">INTEGER</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">calificacion</TD><TD ALIGN="LEFT">INTEGER (1-5)</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">comentario</TD><TD ALIGN="LEFT">TEXT</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">fecha_visita</TD><TD ALIGN="LEFT">DATE</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">fecha_review</TD><TD ALIGN="LEFT">TIMESTAMP</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">likes</TD><TD ALIGN="LEFT">INTEGER</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">verificado</TD><TD ALIGN="LEFT">BOOLEAN</TD></TR>
    </TABLE>>'''
    
    dot.node('reviews', reviews_table)
    
    # TABLA: favoritos
    favoritos_table = '''<
    <TABLE BORDER="1" CELLBORDER="1" CELLSPACING="0" CELLPADDING="8" BGCOLOR="#E8F4F8">
        <TR><TD COLSPAN="3" BGCOLOR="#2C5F77"><FONT COLOR="white"><B>favoritos</B></FONT></TD></TR>
        <TR><TD BGCOLOR="#FFE5B4">PK</TD><TD ALIGN="LEFT"><B>favorito_id</B></TD><TD ALIGN="LEFT">SERIAL</TD></TR>
        <TR><TD BGCOLOR="#D4E6F1">FK</TD><TD ALIGN="LEFT">usuario_id</TD><TD ALIGN="LEFT">INTEGER</TD></TR>
        <TR><TD BGCOLOR="#D4E6F1">FK</TD><TD ALIGN="LEFT">localizacion_id</TD><TD ALIGN="LEFT">INTEGER</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">fecha_agregado</TD><TD ALIGN="LEFT">TIMESTAMP</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">notas_personales</TD><TD ALIGN="LEFT">TEXT</TD></TR>
    </TABLE>>'''
    
    dot.node('favoritos', favoritos_table)
    
    # TABLA: preferencias_usuario
    preferencias_table = '''<
    <TABLE BORDER="1" CELLBORDER="1" CELLSPACING="0" CELLPADDING="8" BGCOLOR="#E8F4F8">
        <TR><TD COLSPAN="3" BGCOLOR="#2C5F77"><FONT COLOR="white"><B>preferencias_usuario</B></FONT></TD></TR>
        <TR><TD BGCOLOR="#FFE5B4">PK</TD><TD ALIGN="LEFT"><B>preferencia_id</B></TD><TD ALIGN="LEFT">SERIAL</TD></TR>
        <TR><TD BGCOLOR="#D4E6F1">FK</TD><TD ALIGN="LEFT">usuario_id</TD><TD ALIGN="LEFT">INTEGER</TD></TR>
        <TR><TD BGCOLOR="#D4E6F1">FK</TD><TD ALIGN="LEFT">categoria_id</TD><TD ALIGN="LEFT">INTEGER</TD></TR>
        <TR><TD></TD><TD ALIGN="LEFT">nivel_interes</TD><TD ALIGN="LEFT">INTEGER (1-5)</TD></TR>
    </TABLE>>'''
    
    dot.node('preferencias_usuario', preferencias_table)
    
    # Definir las relaciones (edges)
    dot.edge('localizaciones', 'categorias', label='  pertenece a', color='#2C5F77')
    dot.edge('imagenes_localizaciones', 'localizaciones', label='  muestra', color='#2C5F77')
    dot.edge('reviews', 'usuarios', label='  escribe', color='#2C5F77')
    dot.edge('reviews', 'localizaciones', label='  evalúa', color='#2C5F77')
    dot.edge('favoritos', 'usuarios', label='  guarda', color='#2C5F77')
    dot.edge('favoritos', 'localizaciones', label='  marca', color='#2C5F77')
    dot.edge('preferencias_usuario', 'usuarios', label='  configura', color='#2C5F77')
    dot.edge('preferencias_usuario', 'categorias', label='  prefiere', color='#2C5F77')
    
    return dot

if __name__ == "__main__":
    schema = create_simplified_database_schema()
    
    # Guardar el diagrama
    output_path = '/mnt/user-data/outputs/database_schema_simplified'
    schema.render(output_path, cleanup=True)
    print(f"✅ Diagrama simplificado generado: {output_path}.png")
    
    # También guardar el código DOT
    with open('/mnt/user-data/outputs/database_schema_simplified.dot', 'w') as f:
        f.write(schema.source)
    print(f"✅ Código DOT guardado: /mnt/user-data/outputs/database_schema_simplified.dot")
