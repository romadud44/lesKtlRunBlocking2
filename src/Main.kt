import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.*

/**
 * Написать программу, выводящую в консоль следующее сообщение:
 * Начало программы
 * 1
 * 2
 * Произошел ленивый запуск
 * 3
 * 4
 * Программа завершена
 * Реализацию вывода переменной счетчика необходимо исполнить с помощью корутины с задержкой внутри 1 сек. Вывод
 * строки "Произошел ленивый запуск" выполнить, передав в параметр своей корутины CoroutineStart.LAZY, задержку
 * выдержать в соответствии заявленным выводом (после вывода 2). Сообщение "Программа завершена" также выполняется
 * в своей корутине с отложенным запуском с таким расчетом, чтобы она сработала после полного вывода переменной счетчика.
 */
suspend fun main() = coroutineScope {
    val jobTwo = launch(start = CoroutineStart.LAZY) {
        println("Произошел ленивый запуск")
    }
    val jobOne = launch {
        for (i in 1..4) {
            println(i)
            if (i==2) jobTwo.start()
            delay(1000L)
        }
    }
    jobOne.join()
    println("Программа завершена")

}