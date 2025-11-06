package pe.com.master.machines.common.utils

import pe.com.master.machines.common.response.ErrorType
import pe.com.master.machines.common.response.Resource

val <T>Resource.Error<T>.messageError: String
    get() = when (val error = this.error) {
        is ErrorType.Api.Network -> error.message
        is ErrorType.Api.Unauthorized -> error.message
        is ErrorType.Api.ServiceUnavailable -> error.message
        is ErrorType.Api.NotFound -> error.message
        is ErrorType.Api.Server -> error.message
        is ErrorType.Api.Timeout -> error.message
        is ErrorType.Unknown -> error.message
        is ErrorType.ErrorAuth -> error.message
    }