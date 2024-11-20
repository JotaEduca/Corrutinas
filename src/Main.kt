

import kotlinx.coroutines.*


/*
fun main() = runBlocking { // this: CoroutineScope
    launch { // launch a new coroutine and continue
        delay(1000L) // non-blocking delay for 1 second (default time unit is ms)
        println("World!") // print after delay
    }
    println("Hello") // main coroutine continues while a previous one is delayed
}



fun main() {
    GlobalScope.launch {
        delay(1000L) // Simula una tarea que tarda 1 segundo
        println("Hola desde una corrutina!")
    }

    println("Hola desde el hilo principal!")
    // Otras tareas del hilo principal
}

*/

import kotlinx.coroutines.*

fun main() {

    // runBlocking...Ejecuta una nueva rutina y bloquea el hilo actual de forma interrumpible hasta su finalización
    runBlocking {
        println("Pronóstico del tiempo \n")

        // Crea una corrutina y devuelve su resultado futuro como una implementación de Deferred (diferida)
        val pronostico: Deferred<String> = async {
            obtenerPronostico()
        }
        val temperatura: Deferred<String> = async {
            obtenerTemperatura()
        }
        println("${pronostico.await()} ${temperatura.await()}")
        println("¡Que tengas un buen día!")
    }
}

suspend fun obtenerPronostico(): String {

    // delay.... Retrasa la rutina durante al menos el tiempo indicado
    //sin bloquear un hilo y la reanuda después de un tiempo específico.

    delay(1000)
    return "Soleado \n\n"
}

suspend fun obtenerTemperatura(): String {
    delay(1000)
    return "30\u00b0C \n"
}





