package pe.com.master.machines.common.response

sealed class ErrorType {

    sealed class Api : ErrorType() {

        data class Network(val message: String = "Error de conexión") : Api()

        data class Unauthorized(val message: String = "Usuario no autorizado") : Api()

        data class ServiceUnavailable(val message: String = "Servicio no disponible") : Api()

        data class NotFound(val message: String = "No encontrado") : Api()

        data class Server(val message: String = "Server Error") : Api()

        data class Timeout(val message: String = "Tiempo de espera agotado") : Api()

    }

    data class Unknown(val message: String = "Error desconocido") : ErrorType()
    data class ErrorAuth(val message: String = "ERROR") : ErrorType()
}