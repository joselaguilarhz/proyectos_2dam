# Sistema de Gestión de Empresas

Este proyecto es un sistema de gestión de empresas que permite insertar, leer, actualizar y eliminar registros de empresas, departamentos y empleados. Además, incluye funcionalidades para consultar todos los empleados de un departamento y mostrar los departamentos de una empresa junto con el número de empleados en cada uno.

## Características

- Insertar nuevos registros de empresas, departamentos y empleados.
- Leer registros por su identificador.
- Actualizar registros existentes.
- Eliminar registros existentes.
- Consultar todos los empleados de un departamento.
- Mostrar los departamentos de una empresa y el número de empleados en cada uno.
- Insertar valores de ejemplo.
- Limpiar todas las tablas y reiniciar los contadores de AUTO_INCREMENT.

## Requisitos

- Java 8 o superior
- MySQL
- Maven

## Configuración

### Configuración de la Base de Datos

1. Crea una base de datos en MySQL:

    ```sql
    CREATE DATABASE gestion_empresas;
    ```

2. Configura las credenciales de la base de datos en el archivo `hibernate.cfg.xml`:

    ```xml
    <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/gestion_empresas</property>
    <property name="hibernate.connection.username">tu_usuario</property>
    <property name="hibernate.connection.password">tu_contraseña</property>
    ```

### Construcción del Proyecto

1. Clona este repositorio:

    ```sh
    git clone https://github.com/tu_usuario/sistema-gestion-empresas.git
    cd sistema-gestion-empresas
    ```

2. Construye el proyecto usando Maven:

    ```sh
    mvn clean install
    ```

## Uso

1. Ejecuta la aplicación:

    ```sh
    mvn exec:java -Dexec.mainClass="org.example.Main"
    ```

2. Selecciona una opción del menú en la consola:

    ```plaintext
    Seleccione una opción:
    1. Insertar
    2. Leer un registro por su identificador
    3. Actualizar un registro existente
    4. Eliminar un registro existente
    5. Consultar todos los empleados de un departamento
    6. Mostrar los departamentos de una empresa y el número de empleados de cada uno
    7. Insertar valores de ejemplo
    8. Limpiar todas las tablas
    0. Salir
    ```

### Opciones del Menú

- **1. Insertar**: Permite insertar un nuevo registro de empresa, departamento o empleado.
- **2. Leer un registro por su identificador**: Permite leer un registro existente por su ID.
- **3. Actualizar un registro existente**: Permite actualizar los datos de un registro existente.
- **4. Eliminar un registro existente**: Permite eliminar un registro existente por su ID.
- **5. Consultar todos los empleados de un departamento**: Muestra todos los empleados de un departamento específico.
- **6. Mostrar los departamentos de una empresa y el número de empleados de cada uno**: Muestra los departamentos de una empresa y el número de empleados en cada uno.
- **7. Insertar valores de ejemplo**: Inserta valores de ejemplo en las tablas.
- **8. Limpiar todas las tablas**: Elimina todos los registros de las tablas y reinicia los contadores de AUTO_INCREMENT.
- **0. Salir**: Termina la ejecución de la aplicación.

## Ejemplo de Uso

### Insertar Valores de Ejemplo

Para insertar valores de ejemplo, selecciona la opción 7 del menú. Esto creará registros de ejemplo en las tablas de empresas, departamentos y empleados.

### Limpiar Todas las Tablas

Para limpiar todas las tablas y reiniciar los contadores de AUTO_INCREMENT, selecciona la opción 8 del menú.

## Contribuciones

Las contribuciones son bienvenidas. Para contribuir, por favor sigue los siguientes pasos:

1. Haz un fork del repositorio.
2. Crea una nueva rama (`git checkout -b feature/nueva-funcionalidad`).
3. Realiza los cambios necesarios y haz commit (`git commit -am 'Añadir nueva funcionalidad'`).
4. Haz push a la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está licenciado bajo la Licencia MIT. Consulta el archivo [LICENSE](LICENSE) para obtener más detalles.