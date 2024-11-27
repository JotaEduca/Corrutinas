

import kotlinx.coroutines.*


/*
fun main() = runBlocking { // this: CoroutineScope
    launch { //  iniciar una nueva corrutina Y  continua
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




/*
fun main() {

    // runBlocking...Ejecuta una nueva rutina y bloquea el hilo actual de forma interrumpible hasta su finalización
    runBlocking {
        println("Pronóstico del tiempo \n")

        // Crea una corrutina y devuelve su resultado futuro como una implementación de Deferred (diferida)
        // async, se utiliza si te interesa cuándo finaliza la corrutina y necesitas que esta muestre un valor.
        //
        //puedes acceder al resultado de esas corrutinas mediante una llamada a await() en los objetos Deferred.
        //
        val pronostico: Deferred<String> = async {
            obtenerPronostico()
        }
        val temperatura: Deferred<String> = async {
            obtenerTemperatura()
        }
        println("${pronostico.await()} ${temperatura.await()}")
        println("¡Que tengas un buen día!...")
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

*/

//Descomposición paralela
//
//La descomposición paralela implica tomar un problema y dividirlo en subtareas más pequeñas que se puedan resolver en paralelo
//
//
//


fun main() {
    runBlocking {
        println("Pronóstico del tiempo")
        println(obtenerInformeMeteorologico())
        println("Que tengas un buen día!")
    }
}

// CoroutineScope en Kotlin se puede definir como
//un objeto que se hace cargo de los procesos de seguimiento de cualquier corrutina
//que sea creada en el sistema, a través del uso de recursos como launch y async
//

//hay algunas conclusiones importantes aquí
//coroutineScope() solo se mostrará una vez que se haya completado toda la tarea, incluidas las corrutinas que haya iniciado
//

suspend fun obtenerInformeMeteorologico() = coroutineScope {
    val pronostico = async { obtenerPronostico() }
    val temperatura = async { obtenerTemperatura() }
    "${pronostico.await()} ${temperatura.await()}"
}

suspend fun obtenerPronostico(): String {
    delay(1000)
    return "Soleado"
}

suspend fun obtenerTemperatura(): String {
    delay(1000)
    return "30\u00b0C"
}

//la idea clave aquí para la simultaneidad estructurada es que puedes tomar varias operaciones simultáneas y
//colocarlas en una sola operación síncrona, en la que la simultaneidad es un detalle de implementación. El único
//requisito sobre el código de llamada es estar en una función de suspensión o una corrutina. Aparte de eso, la
//estructura del código de llamada no necesita tener en cuenta los detalles de simultaneidad.
//



