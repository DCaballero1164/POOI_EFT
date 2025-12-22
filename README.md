# 🧠 Evaluacion Final Transversal - Semana 9 - Desarrollo Orientado a Objetos I


👤 Autor del proyecto

Nombre completo: Daniel Francisco Caballero Salas

Sección: Programación Orientada a Objetos I

Carrera: Analista Programador Computacional

Sede: Campus Virtual

📘 Descripción general del sistema El sistema desarrollado para Salmontt es una plataforma de gestión empresarial que aplica los pilares de la Programación Orientada a Objetos (POO) para administrar unidades operativas, recursos humanos y procesos de compra.

Capacidades principales:

Estructura Jerárquica: Implementación de herencia desde la clase abstracta UnidadOperativa y la clase base Persona.

Contratos de Interfaz: Uso de la interfaz Registrable para estandarizar el comportamiento de las entidades.

Gestión de Datos y Persistencia: Uso de GestorEntidades y RepositorioArchivos para el manejo de colecciones y persistencia de datos.

Herramientas de Soporte: Inclusión de manejo de Excepciones personalizadas, lectura de archivos y validaciones lógicas para la integridad de los datos.

Interfaz Gráfica: Visualización dinámica de datos mediante TablaConFiltros y manejo de diálogos de usuario mediante JOptionPane.

🧱 Estructura del Proyecto (Estructura de Paquetes) El proyecto utiliza una organización de dominio invertido bajo el paquete raíz cl.salmontt:


```
├── 📂 cl.salmontt.app/           # Punto de entrada
│   └── Main.java                 # Clase principal que inicia la aplicación.
│
├── 📂 cl.salmontt.data/          # Capa de Acceso a Datos
│   ├── GestorEntidades.java      # Lógica de gestión de listas y objetos.
│   └── RepositorioArchivos.java  # Manejo de persistencia y carga de archivos.
│
├── 📂 cl.salmontt.model/         # Capa de Negocio (Entidades)
│   ├── 📂 entities/              # Clases del Dominio
│   │   ├── CentroCultivo.java    
│   │   ├── Empleado.java         
│   │   ├── Persona.java          
│   │   ├── PlantaProceso.java    
│   │   ├── Producto.java         
│   │   ├── Proveedor.java        
│   │   ├── Registrable.java      (Interfaz)
│   │   └── UnidadOperativa.java  (Abstracta)
│   └── 📂 order/                 # Gestión de Transacciones
│       └── OrdenDeCompra.java    
│
└── 📂 cl.salmontt.utils/         # Utilidades y Componentes UI
    ├── Excepciones.java          # Manejo de errores personalizados.
    ├── FileReader.java           # Utilidad de lectura de sistema de archivos.
    ├── TablaConFiltros.java      # Componente de interfaz gráfica (JTable).
    └── Validaciones.java         # Lógica de validación general.
```


⚙️ Instrucciones para clonar y ejecutar el proyecto
Clonar el repositorio desde GitHub:

Opcion 1:
 git clone https://github.com/DCaballero1164/POO1_EFT.git

Opcion2:
 Archivo .jar en carpeta en main de GitHub -> Ruta: out/artifacts/SalmonttApp_jar/SalmonttApp.jar

Abrir el proyecto en IntelliJ IDEA (utilizar JDK 17 o superior).

Ejecutar el archivo Main.java desde el package ui.

Visualizar los resultados en la consola.

📌 Repositorio GitHub: https://github.com/DCaballero1164/POO1_EFT.git 📅 Fecha de entrega: [21/12/2025]