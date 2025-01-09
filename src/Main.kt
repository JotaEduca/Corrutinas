

import kotlinx.coroutines.*

/*

fun main() = runBlocking { // esta... CoroutineScope
    launch { //  iniciar una nueva corrutina Y  continua
        delay(1000L) // Retraso sin bloqueo durante 1 segundo (la unidad de tiempo predeterminada es milisegundos)
        println("2º DAM!") // imprime después del retraso
    }
    println("Buenas tardes......") // La corrutina principal continúa mientras que la anterior se retrasa.
}

/**************************************************************************/
/**************************************************************************/


*/

/*

fun main() {
    GlobalScope.launch {
        delay(1000L) // Simula una tarea que tarda 1 segundo
        println("Hola desde una corrutina!")
    }

    println("Hola desde el hilo principal!")
    // Otras tareas del hilo principal
}

*/
/**************************************************************************/
/**************************************************************************/
/*
suspend fun obtenerPronostico(): String {

    // delay.... Retrasa la rutina durante al menos el tiempo indicado
    //sin bloquear un hilo y la reanuda después de un tiempo específico.

    delay(5000)
    println("\nEspero 5 seg. el Pronóstico del tiempo \n")
    return "Soleado \n\n"
}

suspend fun obtenerTemperatura(): String {
    delay(5000)
    println("\nEspero 5 seg. la temperatura \n")
    //probar también que el delay sea delay(1000)
    return "30\u00b0C \n"
}
/**************************************************************************/
/**************************************************************************/

fun main() {

    // runBlocking...Ejecuta una nueva rutina y bloquea el hilo actual de forma ininterrumpible hasta su finalización
    runBlocking {
        println("\nPronóstico del tiempo \n")

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


*/

/**************************************************************************/
/**************************************************************************/

/*
//Descomposición paralela

//La descomposición paralela implica tomar un problema y
// dividirlo en subtareas más pequeñas que se puedan resolver en paralelo

suspend fun obtenerInformeMeteorologico() = coroutineScope {
    val pronostico = async { obtenerPronostico() }
    val temperatura = async { obtenerTemperatura() }
    "${pronostico.await()} ${temperatura.await()}"
}

suspend fun obtenerPronostico(): String {
    delay(6000)
    return "Soleado"
}

suspend fun obtenerTemperatura(): String {
    delay(1000)
    return "30\u00b0C"
}

fun main() {
    runBlocking {
        println("\nPronóstico del tiempo\n")
        println(obtenerInformeMeteorologico())
        println("\nQue tengas un buen día!")
    }
}
*/
/*

CoroutineScope en Kotlin se puede definir como
un objeto que se hace cargo de los procesos de seguimiento de cualquier corrutina
que sea creada en el sistema, a través del uso de recursos como launch y async


hay algunas conclusiones importantes aquí....

coroutineScope() solo se mostrará una vez que se haya completado toda la tarea,
incluidas las corrutinas que haya iniciado


la idea clave aquí para la simultaneidad estructurada es que puedes tomar varias operaciones simultáneas y
colocarlas en una sola operación síncrona, en la que la simultaneidad es un detalle de implementación. El único
requisito sobre el código de llamada es estar en una función de suspensión o una corrutina. Aparte de eso, la
estructura del código de llamada no necesita tener en cuenta los detalles de simultaneidad.

 */


fun ejemploSinScope() {
    GlobalScope.launch { // NO RECOMENDADO
        delay(1000)
        println("Tarea completada Sin Scope")
    }

    // Imagina que la función termina aquí. La corrutina sigue ejecutándose en segundo plano.
    println("Función terminada Sin Scope")
}

fun ejemploConScope() {
    runBlocking { // Crea un scope que espera a que terminen las corrutinas
        launch {
            delay(1000)
            println("Tarea completada con Scope")
        }
    }

    println("Función terminada con Scope") // Se imprime DESPUÉS de que la corrutina termine
}

fun main() {


    ejemploSinScope() // Imprime "Función terminada" primero, luego "Tarea completada" (quizás)

    println("\n\n") //Ojo al doble salto de línea en la consola

    ejemploConScope() // Imprime "Tarea completada" primero, luego "Función terminada"
}

