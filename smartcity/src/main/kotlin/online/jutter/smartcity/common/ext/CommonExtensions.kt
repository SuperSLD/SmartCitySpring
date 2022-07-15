package online.jutter.smartcity.common.ext

import java.math.BigInteger
import java.security.MessageDigest
import java.util.*

/**
 * Получение дайджеста
 * строки в MD5.
 *
 * @author Solyanoy Leonid (solyanoy.leonid@gmail.com)
 */
fun md5(input:String): String {
    val md = MessageDigest.getInstance("MD5")
    return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
}

/**
 * Получение дайджеста
 * строки в SHA-256.
 *
 * @author Solyanoy Leonid (solyanoy.leonid@gmail.com)
 */
fun String.digestSHA256(): String {
    val messageDigest = MessageDigest.getInstance("SHA-256")
    messageDigest.update(this.toByteArray())
    return String(messageDigest.digest())
}

/**
 * Получение UUID строки.
 */
fun getUUID(): String {
    return UUID.randomUUID().toString()
}

fun <T> MutableList<T>.addNotNull(item: T?) {
    if (item != null) this.add(item)
}