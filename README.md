# VerPelis - Prueba Técnica Senior Android Developer

Este proyecto es una implementación del desafío práctico "App de Películas Populares" como parte de una prueba técnica para una posición de Senior Android Developer.

## Enlace al Repositorio

Puedes clonar o descargar el proyecto desde el siguiente enlace de GitHub:
[https://github.com/jcpg1982/verPelis.git](https://github.com/jcpg1982/verPelis.git)

---

## Características Principales

-   **Arquitectura Modular y Limpia**: El código está organizado en capas y módulos, siguiendo los principios de Clean Architecture para facilitar la escalabilidad, el mantenimiento y las pruebas.
-   **Funcionamiento Offline-First**: La aplicación garantiza una experiencia de usuario fluida incluso sin conexión a internet, utilizando una base de datos local como fuente de verdad.
-   **UI Moderna y Reactiva**: Interfaz de usuario construida completamente con **Jetpack Compose** y **Material Design 3**.
-   **Inyección de Dependencias**: Uso de **Hilt** para un código desacoplado, robusto y fácil de testear.

---

## Funcionamiento Offline y Online

La aplicación sigue una estrategia **offline-first**, asegurando que el contenido principal siempre esté disponible:

1.  **Carga Inicial desde Base de Datos**: Al iniciar, la app intenta mostrar la lista de películas directamente desde la base de datos local (**Realm**). Esto proporciona un arranque casi instantáneo y una experiencia fluida.
2.  **Sincronización en Segundo Plano**:
    -   Si la base de datos está vacía (la primera vez que se usa la app) o se requiere una actualización, la aplicación consulta los datos más recientes de la API remota usando **Retrofit**.
    -   Los resultados obtenidos de la red se guardan inmediatamente en la base de datos Realm.
3.  **Fuente de Verdad Única (Single Source of Truth)**: La UI observa los datos directamente de la base de datos. Cualquier cambio (ya sea por una sincronización de red o una acción del usuario) se refleja automáticamente en la pantalla, garantizando consistencia.

---

## Arquitectura Modular y Clean Architecture

El proyecto está estructurado en **módulos de Gradle** que separan las responsabilidades de manera clara. La comunicación entre las capas es unidireccional (Presentación -> Dominio -> Datos).

```mermaid
graph TD;
    A[<b>Capa de Presentación</b><br><i>:app, :feature:*</i><br>(Compose, ViewModel)] --> B(<b>Capa de Dominio</b><br><i>:core:domain</i><br>(Casos de Uso, Modelos));
    B --> C(<b>Capa de Datos</b><br><i>:core:data</i><br>(Repositorios Impl));
    C --> D[<b>Fuentes de Datos</b><br><i>:core:network</i> (Retrofit)<br><i>:core:database</i> (Realm)];
```

### Descripción de las Capas

-   **Capa de Presentación (`:feature:*`, `:app`)**:
    -   Contiene la UI (hecha con **Jetpack Compose**) y los ViewModels. Es responsable de mostrar los datos al usuario y manejar su interacción.
    -   Está dividida en módulos por funcionalidad (`:feature:home`, `:feature:detail-character`).
    -   Depende de la capa de Dominio para ejecutar la lógica de negocio a través de Casos de Uso.

-   **Capa de Dominio (`:core:domain`)**:
    -   Es el corazón de la aplicación. Contiene la lógica de negocio (Casos de Uso) y las definiciones de los modelos y repositorios (interfaces).
    -   Es un módulo de Kotlin puro, sin dependencias de frameworks de UI o de Android, lo que la hace totalmente reutilizable y testeable.

-   **Capa de Datos (`:core:data`, `:core:network`, `:core:database`)**:
    -   Implementa las interfaces de repositorio definidas en la capa de Dominio.
    -   Es la responsable de decidir de dónde obtener los datos: desde la API remota (**Retrofit**) o desde la base de datos local (**Realm**), abstrayendo este detalle a las capas superiores.

---

## Stack Tecnológico

El proyecto está construido utilizando un stack 100% moderno y recomendado para el desarrollo en Android:

- **Lenguaje**: 100% [Kotlin](https://kotlinlang.org/)
- **UI**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Asincronía**: [Coroutines de Kotlin](https://kotlinlang.org/docs/coroutines-overview.html)
- **Arquitectura**: [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html) y Arquitectura Modular
- **Inyección de Dependencias**: [Hilt](https://developer.android.com/training/dependency-injection/hilt-android)
- **Red**: [Retrofit](https://square.github.io/retrofit/)
- **Base de Datos**: [Realm](https://realm.io/)
- **Analytics**: [Firebase Analytics](https://firebase.google.com/docs/analytics)
- **Diseño**: [Material Design 3](https://m3.material.io/)

---

## Cómo Compilar y Ejecutar

1.  Clona el repositorio:
    ```bash
    git clone https://github.com/jcpg1982/verPelis.git
    ```
2.  Abre el proyecto en Android Studio.
3.  **Importante**: Es posible que necesites agregar tu propia API Key de The Movie Database (TMDb) en el archivo `local.properties` para que las llamadas a la API funcionen.
4.  Permite que Gradle sincronice las dependencias del proyecto.
5.  Ejecuta el módulo `:app` en un emulador o dispositivo físico.

Gracias por la oportunidad de presentar esta prueba.
