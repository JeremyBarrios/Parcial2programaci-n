Documentación del Proyecto de Inventario
Introducción
La gestión de inventarios es una parte crucial de cualquier negocio que maneje productos físicos. Un sistema eficiente de gestión de inventarios permite a las empresas rastrear sus productos, optimizar sus procesos de compra y venta, y garantizar que siempre tengan suficiente stock disponible para satisfacer la demanda del cliente. Este proyecto de inventario busca abordar estas necesidades mediante una aplicación web que permite a los usuarios agregar, visualizar y gestionar productos de manera sencilla y eficiente.

El sistema está diseñado como una aplicación de full stack que consta de dos partes principales: el frontend, que se encarga de la interacción con el usuario, y el backend, que maneja la lógica de negocio y el acceso a la base de datos. El frontend está construido utilizando React, una biblioteca de JavaScript para crear interfaces de usuario interactivas. Por otro lado, el backend está desarrollado con Spring Boot, un framework robusto para crear aplicaciones web en Java, y utiliza H2 como base de datos en memoria para facilitar el desarrollo y las pruebas.

Tecnologías Utilizadas
Frontend
React: Esta biblioteca de JavaScript se utiliza para construir interfaces de usuario dinámicas y responsivas. Su arquitectura basada en componentes permite una fácil reutilización del código y una gestión eficiente del estado de la aplicación.

Axios: Una biblioteca para realizar solicitudes HTTP que simplifica la comunicación entre el frontend y el backend. Axios facilita la configuración de las solicitudes y el manejo de las respuestas, lo que permite un flujo de datos más fluido en la aplicación.

Backend
Spring Boot: Este framework se utiliza para crear aplicaciones Java de manera rápida y sencilla. Spring Boot ofrece una configuración automática y una serie de características listas para usar, lo que acelera el proceso de desarrollo.

H2 Database: H2 es una base de datos en memoria que se utiliza para almacenar datos temporalmente durante el desarrollo y las pruebas. Su facilidad de uso y configuración la hacen ideal para aplicaciones que no requieren un almacenamiento persistente en producción.

Estructura del Proyecto
El proyecto se divide en dos carpetas principales: inventory-backend para el backend y inventory-frontend para el frontend. A continuación, se presenta una descripción general de la estructura de carpetas:

inventory-backend/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── tu_paquete/
│   │   │           ├── InventoryApplication.java
│   │   │           ├── controladores/
│   │   │           │   └── ProductoController.java
│   │   │           └── servicios/
│   │   │               └── ProductoService.java
│   │   └── resources/
│   │       └── application.properties
├── pom.xml

inventory-frontend/
├── src/
│   ├── components/
│   │   └── ProductoList.js
│   ├── App.js
│   └── App.css
├── public/
├── package.json
└── README.md
Funcionalidades de la Aplicación
1. Agregar Productos
La aplicación permite a los usuarios ingresar el nombre y el precio de un nuevo producto a través de un formulario en la interfaz. Esta funcionalidad es crucial para mantener el inventario actualizado. Una vez completados los campos, el usuario puede hacer clic en el botón "Agregar Producto", lo que enviará una solicitud al backend para guardar el nuevo producto.

2. Ver Productos
Los productos existentes se cargan automáticamente desde el backend y se muestran en una lista en la interfaz. Esta funcionalidad permite a los usuarios tener una vista clara de los productos disponibles, facilitando la gestión del inventario.

3. Validación de Entradas
Antes de enviar el formulario para agregar un nuevo producto, la aplicación verifica que los campos de nombre y precio no estén vacíos. Si se intenta enviar el formulario sin completar todos los campos, se mostrará un mensaje de alerta pidiendo al usuario que complete la información necesaria.

Cómo Ejecutar el Proyecto
1. Backend
Para ejecutar la parte del backend, sigue estos pasos:

Clona este repositorio y navega a la carpeta del backend (inventory-backend).

Asegúrate de que tengas Maven instalado en tu máquina.

Ejecuta el siguiente comando para iniciar la aplicación Spring Boot:

a API estará disponible en http://localhost:8080/productos, donde podrás realizar solicitudes para ver y agregar productos.

2. Frontend
Para ejecutar la parte del frontend, sigue estos pasos:

Navega a la carpeta del frontend (inventory-frontend).

Asegúrate de tener Node.js y npm instalados en tu máquina.

Instala las dependencias del proyecto con el siguiente comando:
mvn spring-boot:run

La API estará disponible en http://localhost:8080/productos, donde podrás realizar solicitudes para ver y agregar productos.

2. Frontend
Para ejecutar la parte del frontend, sigue estos pasos:

Navega a la carpeta del frontend (inventory-frontend).

Asegúrate de tener Node.js y npm instalados en tu máquina.

Instala las dependencias del proyecto con el siguiente comando:
npm install
npm start

Accede a la aplicación en http://localhost:3000, donde podrás interactuar con el sistema de gestión de inventario.

Configuración de la Base de Datos
El backend utiliza una base de datos H2 en memoria para almacenar datos de productos. La configuración de la base de datos se encuentra en el archivo application.properties, que incluye la habilitación de la consola H2 para facilitar la visualización de datos.

# Habilitar la consola H2
spring.h2.console.enabled=true

# Configuración de la base de datos H2 en memoria
spring.datasource.url=jdbc:h2:mem:inventorydb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Configuración de JPA
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

# Habilitar la consola H2
spring.h2.console.enabled=true

# Configuración de la base de datos H2 en memoria
spring.datasource.url=jdbc:h2:mem:inventorydb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# Configuración de JPA
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update
Puedes acceder a la consola H2 en http://localhost:8080/h2-console. Usa los siguientes detalles para conectarte:

JDBC URL: jdbc:h2:mem:inventorydb
User: sa
Password: (dejar en blanco)
Código Clave del Proyecto
ProductoList.js (Frontend)
Este componente maneja la visualización y adición de productos.

import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ProductoList = () => {
    const [productos, setProductos] = useState([]);
    const [nuevoProducto, setNuevoProducto] = useState({ nombre: '', precio: '' });

    useEffect(() => {
        const obtenerProductos = async () => {
            try {
                const response = await axios.get('http://localhost:8080/productos');
                setProductos(response.data);
            } catch (error) {
                console.error('Error al obtener los productos:', error);
            }
        };
        obtenerProductos();
    }, []);

    const agregarProducto = async () => {
        if (!nuevoProducto.nombre || !nuevoProducto.precio) {
            alert('Por favor, completa todos los campos.');
            return;
        }

        try {
            await axios.post('http://localhost:8080/productos', nuevoProducto);
            setNuevoProducto({ nombre: '', precio: '' });
            const response = await axios.get('http://localhost:8080/productos');
            setProductos(response.data);
        } catch (error) {
            console.error('Error al agregar el producto:', error);
        }
    };

    return (
        <div>
            <h2>Lista de Productos</h2>
            <ul>
                {productos.map((producto, index) => (
                    <li key={index}>{producto.nombre} - ${producto.precio}</li>
                ))}
            </ul>
            <h3>Agregar Producto</h3>
            <input
                type="text"
                placeholder="Nombre"
                value={nuevoProducto.nombre}
                onChange={(e) => setNuevoProducto({ ...nuevoProducto, nombre: e.target.value })}
            />
            <input
                type="number"
                placeholder="Precio"
                value={nuevoProducto.precio}
                onChange={(e) => setNuevoProducto({ ...nuevoProducto, precio: e.target.value })}
            />
            <button onClick={agregarProducto}>Agregar Producto</button>
        </div>
    );
};

export default ProductoList;

import React, { useState, useEffect } from 'react';
import axios from 'axios';

const ProductoList = () => {
    const [productos, setProductos] = useState([]);
    const [nuevoProducto, setNuevoProducto] = useState({ nombre: '', precio: '' });

    useEffect(() => {
        const obtenerProductos = async () => {
            try {
                const response = await axios.get('http://localhost:8080/productos');
                setProductos(response.data);
            } catch (error) {
                console.error('Error al obtener los productos:', error);
            }
        };
        obtenerProductos();
    }, []);

    const agregarProducto = async () => {
        if (!nuevoProducto.nombre || !nuevoProducto.precio) {
            alert('Por favor, completa todos los campos.');
            return;
        }

        try {
            await axios.post('http://localhost:8080/productos', nuevoProducto);
            setNuevoProducto({ nombre: '', precio: '' });
            const response = await axios.get('http://localhost:8080/productos');
            setProductos(response.data);
        } catch (error) {
            console.error('Error al agregar el producto:', error);
        }
    };

    return (
        <div>
            <h2>Lista de Productos</h2>
            <ul>
                {productos.map((producto, index) => (
                    <li key={index}>{producto.nombre} - ${producto.precio}</li>
                ))}
            </ul>
            <h3>Agregar Producto</h3>
            <input
                type="text"
                placeholder="Nombre"
                value={nuevoProducto.nombre}
                onChange={(e) => setNuevoProducto({ ...nuevoProducto, nombre: e.target.value })}
            />
            <input
                type="number"
                placeholder="Precio"
                value={nuevoProducto.precio}
                onChange={(e) => setNuevoProducto({ ...nuevoProducto, precio: e.target.value })}
            />
            <button onClick={agregarProducto}>Agregar Producto</button>
        </div>
    );
};

export default ProductoList;
ProductoController.java (Backend)
Este controlador maneja las solicitudes HTTP para agregar y listar productos

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerProductos() {
        return productoService.obtenerTodosLosProductos();
    }

    @PostMapping
    public Producto agregarProducto(@RequestBody Producto producto) {
        return productoService.agregarProducto(producto);
    }
}
ProductoService.java (Backend)
Este servicio gestiona la lógica de negocio para manejar los productos.

@Service
public class ProductoService {

    private List<Producto> productos = new ArrayList<>();

    public List<Producto> obtenerTodosLosProductos() {
        return productos;
    }

    public Producto agregarProducto(Producto producto) {
        productos.add(producto);
        return producto;
    }
}
``

