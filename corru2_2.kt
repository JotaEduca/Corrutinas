import kotlinx.coroutines.*

fun ejemploSinScope() {
    GlobalScope.launch { // NO RECOMENDADO
        delay(1000)
        println("Tarea completada")
    }

    // Imagina que la función termina aquí. La corrutina sigue ejecutándose en segundo plano.
    println("Función terminada")
}

fun ejemploConScope() {
    runBlocking { // Crea un scope que espera a que terminen las corrutinas
        launch {
            delay(1000)
            println("Tarea completada")
        }
    }

    println("Función terminada") // Se imprime DESPUÉS de que la corrutina termine
}

fun main() {
    ejemploSinScope() // Imprime "Función terminada" primero, luego "Tarea completada" (quizás)
    ejemploConScope() // Imprime "Tarea completada" primero, luego "Función terminada"
}