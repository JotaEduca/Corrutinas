
import kotlinx.coroutines.*


fun main() {
    GlobalScope.launch {
        delay(1000L) // Simula una tarea que tarda 1 segundo
        println("Hola desde una corrutina!")
    }

    println("Hola desde el hilo principal!")
    // Otras tareas del hilo principal
}


