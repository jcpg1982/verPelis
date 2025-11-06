package pe.com.master.machines.common.response

object ErrorCodes {

    object Http {
        const val INTERNAL_SERVER = 500
        const val SERVICE_UNAVAILABLE = 503
        const val RESOURCE_NOT_FOUND = 404
        const val UNAUTHORIZED = 401
    }
}