# MyAppA15 - Aplicación Android con WebView

## 📱 Descripción

Esta es una aplicación Android desarrollada con Jetpack Compose que incluye funcionalidades de verificación de usuario y un WebView integrado. El proyecto fue desarrollado como trabajo final por un grupo de estudiantes.

## 🚀 Características

- **Jetpack Compose UI**: Interfaz moderna y declarativa
- **Navegación**: Sistema de navegación entre pantallas
- **WebView**: Integración de contenido web
- **Verificación de Usuario**: Sistema de autenticación básico
- **Material Design 3**: Diseño moderno y accesible

## 🛠️ Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación principal
- **Jetpack Compose**: Framework de UI moderno
- **Android Navigation**: Navegación entre pantallas
- **Material Design 3**: Sistema de diseño
- **WebView**: Visualización de contenido web

## 📋 Requisitos Previos

- Android Studio Hedgehog | 2023.1.1 o superior
- JDK 11 o superior
- Android SDK 34 (API Level 34) o superior
- Gradle 8.0 o superior

## 🔧 Instalación y Configuración

### 1. Clonar el Repositorio

```bash
git clone <url-del-repositorio>
cd MyappA15
```

### 2. Abrir en Android Studio

1. Abre Android Studio
2. Selecciona "Open an existing project"
3. Navega hasta la carpeta del proyecto y selecciónala
4. Espera a que se sincronice el proyecto

### 3. Configurar Dependencias

El archivo `app/build.gradle.kts` ya incluye todas las dependencias necesarias:

```kotlin
dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation("androidx.navigation:navigation-compose:2.7.7")
    // WebView está incluido por defecto en Android
}
```

### 4. Ejecutar la Aplicación

1. Conecta un dispositivo Android o inicia un emulador
2. Presiona el botón "Run" (▶️) en Android Studio
3. Selecciona tu dispositivo/emulador
4. La aplicación se instalará y ejecutará automáticamente

## 📱 Estructura del Proyecto

```
app/
├── src/main/
│   ├── java/com/example/myappa15/
│   │   ├── MainActivity.kt          # Actividad principal
│   │   └── ui/theme/                # Temas y estilos
│   ├── res/                         # Recursos (imágenes, strings, etc.)
│   └── AndroidManifest.xml          # Configuración de la app
├── build.gradle.kts                 # Dependencias del módulo
└── proguard-rules.pro              # Reglas de ofuscación
```

## 🎯 Funcionalidades

### Pantalla Principal
- Selección de proyectos disponibles
- Navegación a diferentes secciones

### Proyecto 1: Verificación de Usuario
- Campos de entrada para usuario y clave
- Validación de datos
- Mensajes de confirmación

### Acerca de los Autores
- Información de los desarrolladores
- Créditos del proyecto
- WebView con contenido adicional

## 🔒 Permisos Requeridos

La aplicación requiere los siguientes permisos (ya configurados en `AndroidManifest.xml`):

```xml
<uses-permission android:name="android.permission.INTERNET" />
```

## 🧪 Pruebas

### Ejecutar Pruebas Unitarias
```bash
./gradlew test
```

### Ejecutar Pruebas de Instrumentación
```bash
./gradlew connectedAndroidTest
```

## 📦 Construir APK

### APK de Debug
```bash
./gradlew assembleDebug
```

### APK de Release
```bash
./gradlew assembleRelease
```

## 🐛 Solución de Problemas

### Error de Sincronización de Gradle
1. Ve a File → Invalidate Caches / Restart
2. Selecciona "Invalidate and Restart"
3. Espera a que Android Studio se reinicie

### Error de Compilación
1. Verifica que tienes JDK 11 instalado
2. Ejecuta `./gradlew clean`
3. Ejecuta `./gradlew build`

### WebView no carga contenido
1. Verifica la conexión a internet
2. Asegúrate de que el permiso INTERNET esté en el manifest
3. Revisa la URL en el código

## 👥 Autores

Este proyecto fue desarrollado por:

- **Henry Castro** - 1-21-4112
- **Lissette Rodríguez** - 1-19-3824  
- **Miguel Berroa** - 2-16-3694

## 📄 Licencia

Este proyecto es de uso educativo y académico.

## 🤝 Contribuciones

Para contribuir al proyecto:

1. Fork el repositorio
2. Crea una rama para tu feature (`git checkout -b feature/AmazingFeature`)
3. Commit tus cambios (`git commit -m 'Add some AmazingFeature'`)
4. Push a la rama (`git push origin feature/AmazingFeature`)
5. Abre un Pull Request

## 📞 Contacto

Para preguntas o soporte, contacta a los desarrolladores del proyecto.

---

**Nota**: Este README está diseñado para ser una guía completa que permita a cualquier desarrollador configurar y ejecutar el proyecto sin problemas. 