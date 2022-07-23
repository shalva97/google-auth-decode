data class Payload(
    val otpParameters: List<OtpParameters>,
    val version: Int? = null,
    val batch_size: Int? = null,
    val batch_index: Int? = null,
    val batch_id: Int? = null,
) {
    constructor(payloadDTO: PayloadDTO) : this(
        otpParameters = payloadDTO.otp_parameters.map { OtpParameters(it) },
        version = payloadDTO.version,
        batch_size = payloadDTO.batch_size,
        batch_index = payloadDTO.batch_index,
        batch_id = payloadDTO.batch_id
    )
}

data class OtpParameters(
    val name: String,
    val algorithm: Algorithm,
    val issuer: String,
    val secret: String,
    val type: OtpType,
) {
    constructor(payload: OtpParametersDTO) : this(
        name = payload.name,
        algorithm = payload.algorithm,
        issuer = payload.issuer,
        secret = payload.secret.toDomainModel(),
        type = payload.type,
    )
}

private fun ByteArray.toDomainModel(): String {
    return decodeOTPSecret(this)
}

enum class Algorithm {
    ALGORITHM_UNSPECIFIED,
    ALGORITHM_SHA1,
    ALGORITHM_SHA256,
    ALGORITHM_SHA512,
    ALGORITHM_MD5,
}

enum class DigitCount {
    DIGIT_COUNT_UNSPECIFIED,
    DIGIT_COUNT_SIX,
    DIGIT_COUNT_EIGHT,
}

enum class OtpType {
    OTP_TYPE_UNSPECIFIED,
    OTP_TYPE_HOTP,
    OTP_TYPE_TOTP,
}
