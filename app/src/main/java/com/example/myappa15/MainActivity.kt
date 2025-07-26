package com.example.myappa15 // O el nombre de tu paquete

import android.os.Bundle
import android.widget.Toast
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
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myappa15.ui.theme.MyappA15Theme // O el nombre de tu tema

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

        composable("project_selection_screen") {
            ProjectSelectionScreen(navController = navController)
        }
        composable("verification_screen") {
            VerificationScreen(navController = navController)
        }
        // <-- NUEVO: Ruta para la pantalla "Acerca de los Autores".
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
// <-- NUEVO: Esta es la pantalla para la información de los autores.
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
                .padding(24.dp), // Un poco más de padding
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
        ) {
            Text(
                text = "Este proyecto fue desarrollado por:",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            // Autor 1
            AuthorCard(name = "Juan Pérez", role = "Líder de Proyecto / UI-UX")

            // Autor 2
            AuthorCard(name = "María Rodríguez", role = "Desarrolladora Android / Navegación")

            // Autor 3
            AuthorCard(name = "Carlos Gómez", role = "Lógica de Negocio / Pruebas")
        }
    }
}

// Composable de ayuda para mostrar la info de cada autor de forma ordenada
@Composable
fun AuthorCard(name: String, role: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = role,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


// =========================================================================
// Contenido y Previsualizaciones
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
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        OutlinedTextField(
            value = textField1Value,
            onValueChange = { textField1Value = it },
            label = { Text("Usuario (ejemplo)") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        OutlinedTextField(
            value = claveValue,
            onValueChange = { claveValue = it },
            label = { Text("Clave") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
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

// <-- NUEVO: Preview para la pantalla de "Acerca de".
@Preview(showBackground = true, name = "About Screen")
@Composable
fun AboutScreenPreview() {
    MyappA15Theme {
        AboutScreen(rememberNavController())
    }
}