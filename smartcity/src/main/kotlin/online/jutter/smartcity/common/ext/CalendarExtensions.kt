package online.jutter.smartcity.common.ext

import java.lang.IllegalArgumentException
import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.*

/**
 * Парсинг календаря из строки.
 * Полезная функция, везде где идет получение календаря.
 * @param format формат даты ("dd.MM.yyyy")
 */
fun String.parseCalendarByFormat(format: String): Calendar {
    val calendar = Calendar.getInstance()
    val sdf =
            SimpleDateFormat(format, Locale.ENGLISH)
    calendar.time = sdf.parse(this)
    return calendar
}

/**
 * Перевод даты в дрцгой формат через календарь.
 *
 * @param from формат входной даты.
 * @param to формат полученной даты.
 *
 * @return строка даты в формате to.
 */
fun String.dateToFormatFromFormat(from: String, to: String): String {
    val calendar = this.parseCalendarByFormat(from)
    val sdf = SimpleDateFormat(to)
    return sdf.format(calendar.time)
}

/**
 * Перевод календаря в дату.
 *
 * @param to полученная строка даты.
 */
fun Calendar.toDateString(to: String): String {
    val sdf = SimpleDateFormat(to)
    return sdf.format(this.time)
}

/**
 * Устанавливает время в календаре на начало дня.
 */
fun Calendar.setDayStart(): Calendar {
    this.set(Calendar.HOUR_OF_DAY, 0)
    this.set(Calendar.MINUTE, 0)
    this.set(Calendar.SECOND, 0)
    this.set(Calendar.MILLISECOND, 0)
    return this
}

/**
 * Устанавливает время в календаре на конец дня.
 */
fun Calendar.setDayEnd(): Calendar {
    this.set(Calendar.HOUR_OF_DAY, 23)
    this.set(Calendar.MINUTE, 59)
    this.set(Calendar.SECOND, 59)
    this.set(Calendar.MILLISECOND, 999)
    return this
}

/**
 * Определяем входит ли дата в интервал.
 *
 * @param start начало интервала (например начало месяца)
 * @param end конец интервалв (например конец месяца)
 */
fun Calendar.inPeriod(start: Calendar, end: Calendar) = this.after(start) && this.before(end)

fun Long.toCalendar() = Calendar.getInstance().apply { timeInMillis = this@toCalendar }

fun calendarFromDMY(day: Int, month: Int, year: Int? = null) = Calendar.getInstance().apply {
    set(Calendar.DAY_OF_MONTH, day)
    set(Calendar.MONTH, month)
    year?.let { set(Calendar.YEAR, it) }
}!!

/**
 * Получение интервала дат на семестр.
 *
 * @param number номер семестра (1 или 2)
 */
fun getSemRange(number: Int = -1): Pair<Calendar, Calendar> {
    val currentNumber = if (number < 0)
            if (calendarFromDMY(30, 7).after(Calendar.getInstance())) 2 else 1
        else
            number

    val currentYear = if (calendarFromDMY(30, 7).after(Calendar.getInstance())) {
        Calendar.getInstance().get(Calendar.YEAR)
    } else {
        Calendar.getInstance().get(Calendar.YEAR) + 1
    }

    return when(currentNumber) {
        1 -> Pair(
            calendarFromDMY(1, 8, currentYear - 1),
            calendarFromDMY(30, 11, currentYear - 1),
        )
        2 -> Pair(
            calendarFromDMY(1, 1, currentYear),
            calendarFromDMY(30, 4, currentYear),
        )
        else -> throw IllegalArgumentException("sem number only 1 or 2 [current = $currentNumber]")
    }
}

fun Int.toStringMonth() = when(this) {
    0 -> "января"
    1 -> "февраля"
    2 -> "марта"
    3 -> "апреля"
    4 -> "мая"
    5 -> "июня"
    6 -> "июля"
    7 -> "августа"
    8 -> "сентября"
    9 -> "октября"
    10 -> "ноября"
    11 -> "декабря"
    else -> "{error}"
}