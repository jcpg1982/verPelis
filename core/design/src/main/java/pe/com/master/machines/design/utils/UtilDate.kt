package pe.com.master.machines.design.utils

import android.annotation.SuppressLint
import android.content.Context
import android.text.format.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

object UtilDate {

    private val possibleFormats
        get() = listOf(
            YYYY_MM_DD_T_HH_MM_SS_SSS_Z,
            YYYY_MM_DD_T_HH_MM_SS_SS_Z,
            YYYY_MM_DD_T_HH_MM_SS_Z,
            YYYY_MM_DD,
        )

    val Context.is24HourFormat get() = DateFormat.is24HourFormat(this)

    val getCurrentCalendar
        get() = Calendar.getInstance(Locale.getDefault()).apply {
            timeZone = TimeZone.getDefault()
        }

    val getCurrentTime get() = getCurrentCalendar.time
    val getCurrentDateMillis get() = getCurrentCalendar.timeInMillis

    val String.simpleDateFormat
        get() = SimpleDateFormat(this, Locale.getDefault()).apply {
            timeZone = TimeZone.getDefault()
        }

    val String.getCurrentDateFormatted: String
        get() = simpleDateFormat.format(getCurrentTime)

    val Context.getCurrentDateTimeByFormatDevice: String
        get() = if (this.is24HourFormat) DD_MM_YYYY_HH_MM.getCurrentDateFormatted
        else DD_MM_YYYY_HH_MM_A.getCurrentDateFormatted

    val Context.getFormatDateTime: String
        get() = if (this.is24HourFormat) DD_MM_YYYY_HH_MM else DD_MM_YYYY_HH_MM_A

    val Context.getFormatDateTimeCustom: String
        get() = if (this.is24HourFormat) DD_d_MMM_d_YYYY_HH_MM else DD_d_MMM_d_YYYY_HH_MM_A

    val Context.getFormatTime: String
        get() = if (this.is24HourFormat) HH_MM else HH_MM_A

    fun Date.formatTo(format: String): String {
        val dateFormat = format.simpleDateFormat
        return dateFormat.format(this)
    }

    fun Date.removeTime(): Date {
        val calendar = Calendar.getInstance()
        calendar.time = this
        calendar.set(Calendar.HOUR_OF_DAY, 0)
        calendar.set(Calendar.MINUTE, 0)
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return calendar.time
    }

    fun String.toDate(inputFormat: String, outputFormat: String): Date? {
        return try {
            val dateFormat = convertDateFormat(inputFormat, outputFormat)
            val sdf = outputFormat.simpleDateFormat
            if (dateFormat.isNullOrBlank()) return null else sdf.parse(dateFormat)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun String.toDate(format: String): Date? {
        return try {
            val dateFormat = format.simpleDateFormat
            dateFormat.parse(this)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    fun String.convertDateFormat(inputFormat: String, outputFormat: String): String? {
        return if (isBlank()) null else this.toDate(inputFormat)?.formatTo(outputFormat)
    }

    fun Context.changeCustomFormatDate(date: String, formatOutput: String): String? {
        if (date.isBlank()) return null
        val formatInput = this.getFormatDateTime
        return date.convertDateFormat(formatInput, formatOutput)
    }

    fun Context.changeFormatDate(date: String): String? {
        if (date.isBlank()) return null
        val formatInput = this.getFormatDateTime
        val formatOutput = this.getFormatTime
        return date.convertDateFormat(formatInput, formatOutput)
    }

    @SuppressLint("NewApi")
    fun Context.convertIsoDateToCustomFormat(isoDate: String): String? {
        if (isoDate.isBlank()) return null
        return try {
            val date = possibleFormats.firstNotNullOfOrNull { format ->
                val inputFormat = SimpleDateFormat(format, Locale.getDefault()).apply {
                    if (format.contains("Z")) {
                        timeZone = TimeZone.getTimeZone("UTC")
                    }
                }
                runCatching { inputFormat.parse(isoDate) }.getOrNull()
            } ?: return null

            val outputFormat = this.getFormatDateTime.simpleDateFormat
            val result = outputFormat.format(date)
            result
        } catch (e: Exception) {
            null
        }
    }

    @SuppressLint("NewApi")
    fun Context.getElapsedTime(isoDate: String): String {
        val formattedDate = convertIsoDateToCustomFormat(isoDate)
        if (formattedDate.isNullOrBlank()) return ""
        val formatter = DateTimeFormatter.ofPattern(getFormatDateTime)
        val inputDate = LocalDate.parse(formattedDate, formatter)
        val currentDate = LocalDate.now()
        val daysDifference = ChronoUnit.DAYS.between(inputDate, currentDate)
        return when {
            inputDate.isEqual(currentDate) -> "Hoy"
            daysDifference < 7 -> {
                val dayText = if (daysDifference == 1L) "día" else "días"
                "Hace $daysDifference $dayText"
            }

            daysDifference < 30 -> {
                val weeks = daysDifference / 7
                val weekText = if (weeks == 1L) "semana" else "semanas"
                "Hace $weeks $weekText"
            }

            daysDifference < 365 -> {
                val months = daysDifference / 30
                val monthText = if (months == 1L) "mes" else "meses"
                "Hace $months $monthText"
            }

            else -> {
                val years = daysDifference / 365
                val yearText = if (years == 1L) "año" else "años"
                "Hace $years $yearText"
            }
        }
    }

    @SuppressLint("NewApi")
    fun Context.getFormatDateTimeCustom(isoDate: String): String {
        val formattedDate = convertIsoDateToCustomFormat(isoDate)
        if (formattedDate.isNullOrBlank()) return ""
        return formattedDate.convertDateFormat(
            getFormatDateTime, getFormatDateTimeCustom
        ).orEmpty()
    }

    fun Long?.calendarLongToString(format: String): String =
        if (this != null) SimpleDateFormat(format, Locale.getDefault()).apply {
            timeZone = TimeZone.getTimeZone("UTC")
        }.format(Date(this))
        else ""

    fun Long?.convertLongToDate(format: String): Date? =
        if (this != null) {
            val newDate = Date(this)
            val dateFormat = newDate.formatTo(format)
            dateFormat.toDate(format)
        } else null

    const val DD_d_MMMM_dl_YYYY = "dd 'de' MMMM 'del' yyyy"
    const val DD_d_MMMM_d_YYYY = "dd 'de' MMMM 'de' yyyy"
    const val DD_d_MMM_dl_YYYY = "dd 'de' MMM 'del' yyyy"
    const val DD_d_MMM_d_YYYY = "dd 'de' MMM 'de' yyyy"
    const val EEE_DD_MMM_YYYY = "EEE., dd MMM  yyyy"
    const val HH_MM = "HH:mm"
    const val HH_MM_SS = "$HH_MM:ss"
    const val HH_MM_SS_SSS = "$HH_MM_SS.SSS"
    const val HH_MM_SS_SS = "$HH_MM_SS.SS"
    const val HH_MM_SS_SSS_Z = "$HH_MM_SS_SSS'Z'"
    const val HH_MM_SS_SS_Z = "$HH_MM_SS_SS'Z'"
    const val HH_MM_SS_Z = "$HH_MM_SS'Z'"
    const val HH_MM_A = "h:mm a"
    const val DD_MM_YYYY = "dd/MM/yyyy"
    const val DD_MM = "dd/MM"
    const val YYYY_MM_DD = "yyyy-MM-dd"
    const val DD_MM_YYYY_HH_MM_SS = "$DD_MM_YYYY $HH_MM_SS"
    const val DD_MM_YYYY_HH_MM = "$DD_MM_YYYY $HH_MM"
    const val DD_MM_YYYY_HH_MM_SS_SSS = "$DD_MM_YYYY $HH_MM_SS_SSS"
    const val DD_MM_YYYY_HH_MM_SS_SSS_Z = "$DD_MM_YYYY_HH_MM_SS_SSS Z"
    const val DD_MM_YYYY_HH_MM_SS_Z = "$DD_MM_YYYY $HH_MM_SS Z"
    const val DD_MM_YYYY_HH_MM_Z = "$DD_MM_YYYY $HH_MM Z"
    const val DD_MM_YYYY_HH_Z = "$DD_MM_YYYY HH Z"
    const val DD_MM_YYYY_Z = "$DD_MM_YYYY Z"
    const val DD_MM_Z = "$DD_MM Z"
    const val DD_MM_HH_MM_SS = "$DD_MM $HH_MM_SS"
    const val DD_MM_HH_MM = "$DD_MM $HH_MM"
    const val DD_MM_HH = "$DD_MM HH"
    const val DD_MM_HH_Z = "$DD_MM HH Z"
    const val DD_MM_HH_MM_Z = "$DD_MM $HH_MM Z"
    const val DD_MM_HH_MM_SS_Z = "$DD_MM $HH_MM_SS Z"
    const val DD_MM_HH_MM_SS_SSS_Z = "$DD_MM $HH_MM_SS.SSS Z"
    const val DD_MM_HH_MM_SS_SSS = "$DD_MM $HH_MM_SS.SSS"
    const val DD_MM_YYYY_HH_MM_SS_A = "$DD_MM_YYYY h:mm:ss a"
    const val DD_MM_YYYY_HH_MM_A = "$DD_MM_YYYY $HH_MM_A"
    const val DD_d_MMMM_d_YYYY_HH_MM_A = "$DD_d_MMMM_d_YYYY $HH_MM_A"
    const val DD_d_MMMM_d_YYYY_HH_MM = "$DD_d_MMMM_d_YYYY $HH_MM"
    const val DD_d_MMM_d_YYYY_HH_MM_A = "$DD_d_MMM_d_YYYY $HH_MM_A"
    const val DD_d_MMM_d_YYYY_HH_MM = "$DD_d_MMM_d_YYYY $HH_MM"
    const val YYYY_MM_DD_T_HH_MM_SS_SSS_Z = "$YYYY_MM_DD'T'$HH_MM_SS_SSS_Z"
    const val YYYY_MM_DD_T_HH_MM_SS_SS_Z = "$YYYY_MM_DD'T'$HH_MM_SS_SS_Z"
    const val YYYY_MM_DD_T_HH_MM_SS_Z = "$YYYY_MM_DD'T'$HH_MM_SS_Z"
    const val YYYY_MM_DD_T_HH_MM_SS = "$YYYY_MM_DD'T'$HH_MM_SS"
}