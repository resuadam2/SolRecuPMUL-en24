# Enunciado

Crear una aplicación Android usando el IDE Android Studio y el lenguaje de programación Java.

La aplicación tendrá como finalidad almacenar una lista de nuestros videojuegos favoritos. 
Para ello, la aplicación debe contar con una base de datos local elaborada con SQLite que almacene
los siguientes datos de los videojuegos:
- Un identificador.
- El nombre del videojuego.
- El año de publicación.
- La compañía desarrolladora.
- La plataforma para la que está disponible (para simplificar la solución, asumiremos que un juego 
que esté disponible para varias plataformas, simplemente aparecerá en filas distintas duplicado 
- pero asignado a diferentes plataformas)

Las plataformas disponibles son: PLaystation 1, PLaystation 2, Playstation 3, Playstation 4, 
Playstation 5, PSP, GabeBoy, GabeBoy Advance, Nintendo DS, Nintendo 3DS, Nintendo Game Cube, 
Nintendo 64, Nintendo Wii, Nintendo Switch, XBOX, XBOX 360, XBOX One, XBOX Series, PC, 
Android e IOs. Debemos controlar que no se introduzca ninguna plataforma que no esté en la lista.
Sin embargo, no es necesario controlar esto a nivel base de datos, tan solo en la vista.

La aplicación debe además, contar con una serie de juegos que se creen al instalarla para poder 
probar toda su funcionalidad.
Los juegos que debe contener son los siguientes:
- Crash Bandicoot (1996), por Naughty Dog para Playstation 1.
- Pokémon Esmeralda (2004), por Game Freak para GameBoy Advance.
- Super Mario Run (2016), por Nintendo para Android.
- Super Mario Run (2016), por Nintendo para IOs.
- Dragon Quest VIII (2004), por Level-5 para Playstation 2.
- Rachet & Clank (2002), por Insomniac Games para PLaystation 2.
- Pokémon Go (2016), por Niantic para Android.
- Pokémon Go (2016), por Niantic para IOs.
- Final Fantasy VII (1997), por Square Enix para Playstation 1.

**Cada alumno o alumna debe añadir un juego más a la lista que se cree al instalar la aplicación.**

La aplicación debe de constar de dos actividades. En la actividad principal nos encontraremos con 
una barra de búsqueda, en la cuál podremos filtrar la lista de juegos, tanto por nombre de juego 
como de desarrolladora. También nos encontraremos con una lista desplegable de todas las posibles
plataformas, al seleccionar una plataforma de este desplegable también filtraremos los resultados. 
Ambas opciones deben ser acumulables, es decir, debemos poder estar filtrando al mismo tiempo los 
resultados de la barra de búsqueda junto con la plataforma seleccionada. Aunque debe caber siempre 
la posiblidad de dejar cualquiera de los dos filtros en blanco.

El o los filtros que estemos utilizando deben almacenarse en preferencias para que, ante un cierre
inesperado de la aplicación o que giremos la pantalla, no se pierdan los datos por los que el
usuario había decidido filtrar sus juegos. 

En esa misma actividad principal contaremos también con un botón para añadir nuevos juegos (el cuál
nos llevará a la subactividad que usaremos tanto para añadir juegos nuevos como para editar los 
existentes). Y, contaremos también con una lista que nos mostrará los juegos en base a los filtros
utilizados (o no utilizados). 

Al hacer una pulsación larga sobre un elemento de la lista, debe saltarnos un diálogo que nos 
pregunte si queremos borrar dicho elemento. Si hacemos una pulsación corta, esto nos debe llevar a 
la subactividad con los datos del videojuego seleccionado y darnos la opción de modificarlos. 

La vista de esta actividad principal se proporciona adjunta a este enunciado, así como la vista de
los elementos de la lista de videojuegos para su adaptador personalizado. 

La subactividad debe reconocer si estamos añadiendo o editando un elemento y, en el primer caso, 
mostrar el formulario solicitando los datos necesarios vacíos, pero, si es el segundo, precargar 
los datos del elemento que estemos editando.

