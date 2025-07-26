package com.example.myappa15

/**
 * =========================================================================
 * PROYECTO FINAL - DESARROLLO ANDROID CON JETPACK COMPOSE
 * =========================================================================
 * 
 * DESCRIPCIÓN DEL PROYECTO:
 * Esta aplicación Android fue desarrollada como proyecto final por un grupo
 * de estudiantes. Implementa funcionalidades de verificación de usuario y
 * muestra información de los desarrolladores mediante un WebView integrado.
 * 
 * CARACTERÍSTICAS PRINCIPALES:
 * - Interfaz moderna usando Jetpack Compose
 * - Sistema de navegación entre pantallas
 * - Verificación de credenciales de usuario
 * - WebView con información del proyecto
 * - Diseño Material Design 3
 * 
 * ESTRUCTURA DE LA APLICACIÓN:
 * 1. Pantalla Principal: Selección de proyectos
 * 2. Pantalla de Verificación: Formulario de login
 * 3. Pantalla de Autores: Información de desarrolladores
 * 
 * TECNOLOGÍAS UTILIZADAS:
 * - Kotlin (Lenguaje de programación)
 * - Jetpack Compose (Framework de UI)
 * - Android Navigation (Navegación)
 * - Material Design 3 (Sistema de diseño)
 * - WebView (Contenido web)
 * 
 * AUTORES:
 * - Henry Castro (1-21-4112)
 * - Lissette Rodríguez (1-19-3824)
 * - Miguel Berroa (2-16-3694)
 * 
 * FECHA DE DESARROLLO: 2024
 * VERSIÓN: 1.0
 * 
 * =========================================================================
 */

// =========================================================================
// IMPORTS NECESARIOS PARA LA APLICACIÓN
// =========================================================================

// Imports básicos de Android
import android.os.Bundle
import android.widget.Toast

// Imports de Jetpack Compose (UI moderna de Android)
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// Imports para navegación entre pantallas
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

// Imports para WebView (mostrar contenido web)
import androidx.compose.ui.viewinterop.AndroidView
import android.webkit.WebView
import android.webkit.WebViewClient

// Import del tema personalizado de la aplicación
import com.example.myappa15.ui.theme.MyappA15Theme

/**
 * ACTIVIDAD PRINCIPAL DE LA APLICACIÓN
 * 
 * Esta es la actividad principal que se ejecuta cuando el usuario abre la app.
 * Aquí configuramos el tema y la navegación principal.
 * 
 * @author Henry Castro, Lissette Rodríguez, Miguel Berroa
 * @version 1.0
 */
class MainActivity : ComponentActivity() {
    
    /**
     * Método que se ejecuta cuando la actividad se crea por primera vez.
     * Aquí configuramos la interfaz de usuario usando Jetpack Compose.
     * 
     * @param savedInstanceState Bundle que contiene el estado anterior de la actividad
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        // Habilitamos el modo edge-to-edge para una experiencia más inmersiva
        enableEdgeToEdge()
        
        // Configuramos el contenido de la actividad usando Jetpack Compose
        setContent {
            // Aplicamos el tema personalizado de la aplicación
            MyappA15Theme {
                // Iniciamos la navegación principal de la app
                AppNavigation()
            }
        }
    }
}

/**
 * GESTOR DE NAVEGACIÓN PRINCIPAL
 * 
 * Esta función maneja toda la navegación entre las diferentes pantallas de la aplicación.
 * Usamos Jetpack Navigation Compose para una navegación moderna y declarativa.
 * 
 * Funcionalidades:
 * - Navegación entre pantallas
 * - Gestión del estado de navegación
 * - Configuración de rutas
 * 
 * @author Henry Castro, Lissette Rodríguez, Miguel Berroa
 */
@Composable
fun AppNavigation() {
    // Creamos el controlador de navegación que maneja los cambios entre pantallas
    val navController = rememberNavController()
    
    // Configuramos el host de navegación con todas las rutas disponibles
    NavHost(
        navController = navController, 
        startDestination = "project_selection_screen" // Pantalla que se muestra al abrir la app
    ) {
        
        // RUTA 1: Pantalla de selección de proyectos (pantalla principal)
        composable("project_selection_screen") {
            ProjectSelectionScreen(navController = navController)
        }
        
        // RUTA 2: Pantalla de verificación de usuario (Proyecto 1)
        composable("verification_screen") {
            VerificationScreen(navController = navController)
        }
        
        // RUTA 3: Pantalla de información de los autores
        composable("about_screen") {
            AboutScreen(navController = navController)
        }
    }
}

// =========================================================================
// AQUÍ ESTÁN TODAS LAS PANTALLAS DE LA APP
// =========================================================================

/**
 * PANTALLA 1: Pantalla de bienvenida y selección de proyectos.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectSelectionScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Práctica de Estudiantes") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Proyecto Final",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Grupo de Estudiantes #1",
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 40.dp)
            )

            Text(
                text = "Seleccione una opción:",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            Button(
                onClick = { navController.navigate("verification_screen") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Proyecto 1: Verificación de Usuario")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // <-- CAMBIO: El botón de "Proyecto 2" ahora es el de "Acerca de".
            Button(
                onClick = { navController.navigate("about_screen") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Acerca de los Autores")
            }
        }
    }
}


/**
 * PANTALLA 2: Verificación (Proyecto 1).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Proyecto 1: Verificación") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        VerificationContent(modifier = Modifier.padding(innerPadding))
    }
}

/**
 * PANTALLA 3: Acerca de los Autores.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Acerca de los Autores") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Volver"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text(
                text = "Sustentado por:",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 24.dp)
            )

            // Autor 1 - Henry Castro
            AuthorCard(name = "Henry Castro", matricula = "1-21-4112")

            // Autor 2 - Lissette Rodríguez
            AuthorCard(name = "Lissette Rodríguez", matricula = "1-19-3824")

            // Autor 3 - Miguel Berroa
            AuthorCard(name = "Miguel Berroa", matricula = "2-16-3694")

            Spacer(modifier = Modifier.height(32.dp))

            // WebView con contenido adicional
            Text(
                text = "Información Adicional:",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            WebViewContent()
        }
    }
}

/**
 * Componente WebView para mostrar contenido web
 */
@Composable
fun WebViewContent() {
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                webViewClient = WebViewClient()
                settings.apply {
                    javaScriptEnabled = true
                    domStorageEnabled = true
                    loadWithOverviewMode = true
                    useWideViewPort = true
                }
            }
        },
        update = { webView ->
            // Cargar una página HTML simple con información del proyecto
            val htmlContent = """
                <!DOCTYPE html>
                <html>
                <head>
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <style>
                        body {
                            font-family: Arial, sans-serif;
                            margin: 20px;
                            background-color: #f5f5f5;
                            color: #333;
                        }
                        .container {
                            background-color: white;
                            padding: 20px;
                            border-radius: 10px;
                            box-shadow: 0 2px 4px rgba(0,0,0,0.1);
                        }
                        h2 {
                            color: #2196F3;
                            text-align: center;
                        }
                        .info-item {
                            margin: 10px 0;
                            padding: 10px;
                            background-color: #f8f9fa;
                            border-radius: 5px;
                        }
                        .highlight {
                            background-color: #e3f2fd;
                            padding: 15px;
                            border-radius: 8px;
                            margin: 15px 0;
                        }
                    </style>
                </head>
                <body>
                    <div class="container">
                        <h2>Proyecto Final - Desarrollo Android</h2>
                        
                        <div class="highlight">
                            <strong>Descripción del Proyecto:</strong><br>
                            Aplicación Android desarrollada con Jetpack Compose que incluye 
                            funcionalidades de verificación de usuario y WebView integrado.
                        </div>
                        
                        <div class="info-item">
                            <strong>Tecnologías Utilizadas:</strong><br>
                            • Kotlin<br>
                            • Jetpack Compose<br>
                            • Android Navigation<br>
                            • Material Design 3<br>
                            • WebView
                        </div>
                        
                        <div class="info-item">
                            <strong>Funcionalidades:</strong><br>
                            • Sistema de navegación entre pantallas<br>
                            • Verificación de usuario<br>
                            • Visualización de contenido web<br>
                            • Interfaz moderna y responsiva
                        </div>
                        
                        <div class="highlight">
                            <strong>Fecha de Desarrollo:</strong> 2024<br>
                            <strong>Versión:</strong> 1.0
                        </div>
                    </div>
                </body>
                </html>
            """.trimIndent()
            
            webView.loadDataWithBaseURL(
                null,
                htmlContent,
                "text/html",
                "UTF-8",
                null
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
    )
}

// Composable de ayuda para mostrar la info de cada autor de forma ordenada
@Composable
fun AuthorCard(name: String, matricula: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = matricula,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }
    }
}


// =========================================================================
// CONTENIDO Y PREVISUALIZACIONES
// =========================================================================

/**
 * CONTENIDO ESPECÍFICO DE LA PANTALLA DE VERIFICACIÓN
 * 
 * Esta función contiene toda la lógica y UI de la pantalla de verificación de usuario.
 * Implementa un formulario simple con validación básica.
 * 
 * Características:
 * - Campos de entrada para usuario y clave
 * - Validación de datos
 * - Mensajes de confirmación
 * - Diseño limpio y centrado
 * 
 * @param modifier Modificador opcional para personalizar el layout
 * @author Henry Castro, Lissette Rodríguez, Miguel Berroa
 */
@Composable
fun VerificationContent(modifier: Modifier = Modifier) {
    // Obtenemos el contexto actual para mostrar mensajes Toast
    val context = LocalContext.current
    
    // Estados para almacenar los valores de los campos de texto
    // remember { mutableStateOf("") } crea un estado que persiste entre recomposiciones
    var textField1Value by remember { mutableStateOf("") } // Estado para el campo de usuario
    var claveValue by remember { mutableStateOf("") } // Estado para el campo de clave

    /**
     * FUNCIÓN DE VALIDACIÓN
     * 
     * Esta función verifica si los datos ingresados son válidos.
     * Por ahora solo verifica que la clave no esté vacía.
     * 
     * Lógica de validación:
     * - Si la clave está vacía: muestra mensaje de error
     * - Si la clave tiene contenido: muestra mensaje de éxito
     */
    fun verificarClave() {
        if (claveValue.isEmpty()) {
            // Mostramos un mensaje de error si la clave está vacía
            Toast.makeText(context, "La clave no puede quedar vacía", Toast.LENGTH_LONG).show()
        } else {
            // Mostramos un mensaje de éxito si la clave tiene contenido
            Toast.makeText(context, "Verificación exitosa", Toast.LENGTH_SHORT).show()
        }
    }

    // LAYOUT PRINCIPAL DE LA PANTALLA
    Column(
        modifier = modifier
            .fillMaxSize() // Ocupa toda la pantalla disponible
            .padding(16.dp), // Padding exterior de 16dp
        horizontalAlignment = Alignment.CenterHorizontally, // Centra el contenido horizontalmente
        verticalArrangement = Arrangement.spacedBy(12.dp) // Espaciado vertical de 12dp entre elementos
    ) {
        // CAMPO DE ENTRADA PARA USUARIO
        OutlinedTextField(
            value = textField1Value, // Valor actual del campo
            onValueChange = { textField1Value = it }, // Función que se ejecuta cuando cambia el valor
            label = { Text("Usuario (ejemplo)") }, // Etiqueta del campo
            modifier = Modifier.fillMaxWidth(), // El campo ocupa todo el ancho disponible
            singleLine = true // Restringe la entrada a una sola línea
        )
        
        // CAMPO DE ENTRADA PARA CLAVE
        OutlinedTextField(
            value = claveValue, // Valor actual del campo
            onValueChange = { claveValue = it }, // Función que se ejecuta cuando cambia el valor
            label = { Text("Clave") }, // Etiqueta del campo
            modifier = Modifier.fillMaxWidth(), // El campo ocupa todo el ancho disponible
            singleLine = true // Restringe la entrada a una sola línea
        )
        
        // ESPACIADOR para separar los campos del botón
        Spacer(modifier = Modifier.height(8.dp))
        
        // BOTÓN DE VERIFICACIÓN
        Button(
            onClick = { verificarClave() }, // Función que se ejecuta al presionar el botón
            modifier = Modifier.fillMaxWidth() // El botón ocupa todo el ancho disponible
        ) {
            Text("Verificar") // Texto del botón
        }
    }
}

// =========================================================================
// PREVISUALIZACIONES PARA DESARROLLO
// =========================================================================

/**
 * PREVISUALIZACIÓN DE LA PANTALLA DE SELECCIÓN DE PROYECTOS
 * 
 * Esta función permite ver cómo se ve la pantalla principal en Android Studio
 * sin necesidad de ejecutar la aplicación completa.
 * 
 * Uso: Se muestra en el panel de Preview de Android Studio
 * 
 * @author Henry Castro, Lissette Rodríguez, Miguel Berroa
 */
@Preview(showBackground = true, name = "Project Selection Screen")
@Composable
fun ProjectSelectionPreview() {
    // Aplicamos el tema personalizado para la previsualización
    MyappA15Theme {
        // Creamos un controlador de navegación temporal para la preview
        ProjectSelectionScreen(rememberNavController())
    }
}

/**
 * PREVISUALIZACIÓN DE LA PANTALLA DE VERIFICACIÓN
 * 
 * Esta función permite ver cómo se ve la pantalla de verificación en Android Studio
 * sin necesidad de ejecutar la aplicación completa.
 * 
 * Uso: Se muestra en el panel de Preview de Android Studio
 * 
 * @author Henry Castro, Lissette Rodríguez, Miguel Berroa
 */
@Preview(showBackground = true, name = "Verification Screen")
@Composable
fun VerificationPreview() {
    // Aplicamos el tema personalizado para la previsualización
    MyappA15Theme { 
        // Creamos un controlador de navegación temporal para la preview
        VerificationScreen(rememberNavController()) 
    }
}

/**
 * PREVISUALIZACIÓN DE LA PANTALLA DE INFORMACIÓN DE AUTORES
 * 
 * Esta función permite ver cómo se ve la pantalla de autores en Android Studio
 * sin necesidad de ejecutar la aplicación completa.
 * 
 * Uso: Se muestra en el panel de Preview de Android Studio
 * 
 * @author Henry Castro, Lissette Rodríguez, Miguel Berroa
 */
@Preview(showBackground = true, name = "About Screen")
@Composable
fun AboutScreenPreview() {
    // Aplicamos el tema personalizado para la previsualización
    MyappA15Theme {
        // Creamos un controlador de navegación temporal para la preview
        AboutScreen(rememberNavController())
    }
}