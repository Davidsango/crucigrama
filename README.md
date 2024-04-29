**Practica Aplicada**

**Proyecto:**

- Desarrollo de una aplicación de juego de crucigramas utilizando la metodología  ́ágil Scrum.

**Integrantes:**

- Daniel Esteban Méndez Díaz
- Andres Enrique Cárdenas Florez
- David Andres Gómez Martinez

**Docente:**

- Wilson Soto.

**CrucigramaX**

Es una aplicación de crucigramas desarrollada en Java con JavaFX. Que permite a los usuarios jugar este famoso rompecabezas formado por filas de casillas que se entrecruzan y en las que hay que completar palabras de acuerdo con el enunciado. Para completar este proyecto se manejo el patrón de diseño Modelo, Vista y Controlador (MVC) patrón de arquitectura de software, que separa los datos y principalmente lo que es la lógica de negocio de una aplicación de su representación y el módulo encargado de gestionar los eventos y las comunicaciones. Para el diseño de la Base de Datos se realizó en PostgreSQL con su gestor PgAdmin4 y el JDBC correspondiente para realizar la conexión con Java, para lo cual se usó otro patrón conocido como Data Acces Object (DAO) el cual permite separar la lógica de acceso a datos de los Objetos de negocios de tal forma que el DAO encapsula toda la lógica de acceso de datos al resto de la aplicación.

**Requisitos previos**
  - Contar con una versión de Java estable, como JavaSE 8, JavaSE 11 y JavaSE 17 (Recomendable)
  - JavaFX 21 (https://openjfx.io/)
  - PostgreSQL JDBC Driver (Driver para Java 8 y versiones superiores)
  - Esta pensado para correr en Windows 10 y Windows 11.
  - JDK 21
  - IDE de preferencia (Apache Netbeans 17, recomendable)

**Instalación**

Una vez cumplidos los requisitos previos para ejecutar el proyecto desde su máquina local es necesario seguir el siguiente paso:
  - Clonar el repositorio (git clone https://github.com/Davidsango/crucigrama.git)

**Configuración de la Base de Datos**

La Base de Datos del proyecto se realizó con el motor de bases de datos PostreSQL y el gestor PgAdmin4. Las credenciales de usuario son "postgres" y contraseña "DmD020827*".
  - Con el archivo sql que se encuentra dentro de la carpeta "DataBase" podrá correr la Base de Datos de manera local en su gestor de bases de preferencia.

**Compilar y ejecutar**

Para evitar inconvenientes podemos dirigirnos al proyecto y dando click derecho y seleccionando "Propierties", podemos configurar el JDK con el cual ejecutarlo, las librerias de JavaFX y el JDBC de postgreSQL.

**Uso**

Una vez que la aplicación esté en funcionamiento, puedes ejecutarla para poder jugar.
  - Ingresa un "Nickname" distintivo que debe estar entre los 3 a 8 carácteres.
  -  Podrás elegir entre hacer "nuevo juego" o visualizar los "puntajes máximos".
  -  Selecciona un nivel de dificultad entre los tres disponibles, le abrira una ventana con un tablero de 10*10 y al lado los enunciados correspondientes asociados a cada cuadricula del cruicgrama.
    - Para los dos primeros niveles podrá contar con "ayudas" las cuales le mostrarán algunas letras de la cuadricula. En el último se elimina este tipo de ayudas y quiere que se logre completar el cruicgrama de manera autonoma.
    - Después de completar un crucigrama, en el botón "validar" podrá ver su resultado y obtener un puntaje asociado.
  - En la sección "puntajes máximos" podrá ver el registro historico de los "Score" guardados.

**Contribución**

Puede colaborar en la mejora de este proyecto.
  - Reporte de Problemas: Si encuentras algún problema o error en la aplicación, por favor háznoslo saber abriendo un nuevo "Issue" en el repositorio. Asegúrate de proporcionar detalles completos sobre el problema, incluyendo pasos para reproducirlo si es posible.
  - Solicitud de Funcionalidades: Abre un nuevo "Issue" para discutir tu idea y cómo podría beneficiar al proyecto.
