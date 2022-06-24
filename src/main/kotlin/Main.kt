import java.util.Scanner

fun main() {
    val phoneNumber = Scanner(System.`in`).nextLine()
    println(PhoneNumberFormatter().format(phoneNumber))
}
