package com.example.myappa15 // O el nombre de tu paquete

import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myappa15.ui.theme.MyappA15Theme // O el nombre de tu tema
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyappA15Theme {
                AppNavigation()
            }
        }
    }
}

/**
 * Gestor de la navegación principal de la aplicación.
 */
@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "project_selection_screen") {
        composable("project_selection_screen") { ProjectSelectionScreen(navController = navController) }
        composable("verification_screen") { VerificationScreen(navController = navController) }
        composable("about_screen") { AboutScreen(navController = navController) }
        composable("project3_login_screen") { Project3_LoginScreen(navController = navController) }
        composable("project3_welcome_screen") { Project3_WelcomeScreen(navController = navController) }
        composable("project2_main_screen") { Project2_MainScreen(navController = navController) }
        composable("project2_webview_screen/{data}") { backStackEntry ->
            val encodedData = backStackEntry.arguments?.getString("data") ?: ""
            val data = URLDecoder.decode(encodedData, StandardCharsets.UTF_8.toString())
            Project2_WebViewScreen(navController = navController, urlToLoad = data)
        }
    }
}

// =========================================================================
// PANTALLA DE SELECCIÓN DE PROYECTOS (MENÚ PRINCIPAL)
// =========================================================================
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProjectSelectionScreen(navController: NavController) {
    Scaffold(topBar = { TopAppBar(title = { Text("Práctica de Estudiantes") }) }) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "Proyecto Final", fontSize = 32.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 8.dp))
            Text(text = "Grupo de Estudiantes #1", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(bottom = 40.dp))

            Button(onClick = { navController.navigate("verification_screen") }, modifier = Modifier.fillMaxWidth()) { Text("Proyecto 1: Verificación de Usuario") }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("project2_main_screen") }, modifier = Modifier.fillMaxWidth()) { Text("Proyecto 2: Cargar Página Web") }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("project3_login_screen") }, modifier = Modifier.fillMaxWidth()) { Text("Proyecto 3: Login con Clave") }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { navController.navigate("about_screen") }, modifier = Modifier.fillMaxWidth()) { Text("Acerca de los Autores") }
        }
    }
}

// =========================================================================
// PANTALLAS DE CADA PROYECTO
// =========================================================================

/**
 * PANTALLA: Verificación (Proyecto 1).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Proyecto 1: Verificación") }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver") }
        })
    }) { innerPadding ->
        VerificationContent(modifier = Modifier.padding(innerPadding))
    }
}

/**
 * PANTALLA: Acerca de los Autores.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AboutScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Acerca de los Autores") }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver") }
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text(text = "Sustentado por:", style = MaterialTheme.typography.titleLarge, textAlign = TextAlign.Center, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 24.dp))
            AuthorCard(name = "Henry Castro", matricula = "1-21-4112")
            AuthorCard(name = "Lissette Rodríguez", matricula = "1-19-3824")
            AuthorCard(name = "Miguel Berroa", matricula = "2-16-3694")
        }
    }
}

/**
 * PANTALLA: Principal del Proyecto 2 (Enviar URL).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Project2_MainScreen(navController: NavController) {
    var textValue by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(topBar = {
        TopAppBar(title = { Text("Proyecto 2: Cargar Web") }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver") }
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = textValue,
                onValueChange = { textValue = it },
                label = { Text("Escribe una dirección web (ej: google.com)") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    if (textValue.isNotBlank()) {
                        val encodedText = URLEncoder.encode(textValue, StandardCharsets.UTF_8.toString())
                        navController.navigate("project2_webview_screen/$encodedText")
                    } else {
                        Toast.makeText(context, "Por favor, escribe una dirección", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cargar Página Web")
            }
        }
    }
}

/**
 * PANTALLA: Muestra la página web (Proyecto 2).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Project2_WebViewScreen(navController: NavController, urlToLoad: String) {
    val fullUrl = remember(urlToLoad) {
        if (urlToLoad.startsWith("http://") || urlToLoad.startsWith("https://")) {
            urlToLoad
        } else {
            "https://$urlToLoad"
        }
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text(urlToLoad) }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver") }
        })
    }) { innerPadding ->
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    webViewClient = WebViewClient()
                    loadUrl(fullUrl)
                }
            },
            modifier = Modifier.fillMaxSize().padding(innerPadding)
        )
    }
}

/**
 * PANTALLA: Login (Proyecto 3). Pide una clave.
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Project3_LoginScreen(navController: NavController) {
    var password by remember { mutableStateOf("") }
    val context = LocalContext.current

    Scaffold(topBar = {
        TopAppBar(title = { Text("Proyecto 3: Login") }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver") }
        })
    }) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Ingresa la clave para continuar", style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(bottom = 16.dp))
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Clave") },
                singleLine = true,
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    if (password == "abc123") {
                        navController.navigate("project3_welcome_screen")
                    } else {
                        Toast.makeText(context, "Clave incorrecta", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Verificar")
            }
        }
    }
}

/**
 * PANTALLA: Bienvenida (Proyecto 3).
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Project3_WelcomeScreen(navController: NavController) {
    Scaffold(topBar = {
        TopAppBar(title = { Text("Bienvenido") }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) { Icon(Icons.AutoMirrored.Filled.ArrowBack, "Volver") }
        })
    }) { innerPadding ->
        Box(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text("¡Bienvenido! Has ingresado correctamente.", fontSize = 20.sp, textAlign = TextAlign.Center, modifier = Modifier.padding(16.dp))
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

            AuthorCard(name = "Henry Castro", matricula = "1-21-4112")
            AuthorCard(name = "Lissette Rodríguez", matricula = "1-19-3824")
            AuthorCard(name = "Miguel Berroa", matricula = "2-16-3694")
        }
    }
}



/**
 * Componente para mostrar la información de un autor.
 */
@Composable
fun AuthorCard(name: String, matricula: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = name, style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
            Text(text = matricula, style = MaterialTheme.typography.bodyMedium)
        }
    }
}


// =========================================================================
// COMPONENTES REUTILIZABLES Y CONTENIDO
// =========================================================================

/**
 * Contenido específico de la pantalla de verificación.
 */
@Composable
fun VerificationContent(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    var textField1Value by remember { mutableStateOf("") }
    var claveValue by remember { mutableStateOf("") }

    fun verificarClave() {
        if (claveValue.isEmpty()) {
            Toast.makeText(context, "La clave no puede quedar vacía", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, "Verificación exitosa", Toast.LENGTH_SHORT).show()
        }
    }

    Column(
        modifier = modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(value = textField1Value, onValueChange = { textField1Value = it }, label = { Text("Usuario (ejemplo)") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        OutlinedTextField(value = claveValue, onValueChange = { claveValue = it }, label = { Text("Clave") }, modifier = Modifier.fillMaxWidth(), singleLine = true)
        Spacer(modifier = Modifier.height(8.dp))
        Button(onClick = { verificarClave() }, modifier = Modifier.fillMaxWidth()) {
            Text("Verificar")
        }
    }
}

@Preview(showBackground = true, name = "Project Selection Screen")
@Composable
fun ProjectSelectionPreview() {
    MyappA15Theme {
        ProjectSelectionScreen(rememberNavController())
    }
}

@Preview(showBackground = true, name = "Verification Screen")
@Composable
fun VerificationPreview() {
    MyappA15Theme { VerificationScreen(rememberNavController()) }
}

@Preview(showBackground = true, name = "About Screen")
@Composable
fun AboutScreenPreview() {
    MyappA15Theme {
        AboutScreen(rememberNavController())
    }
}