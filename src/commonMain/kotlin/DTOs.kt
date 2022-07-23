import kotlinx.serialization.Serializable

@Serializable
data class PayloadDTO(
    val otp_parameters: List<OtpParametersDTO>,
    val version: Int? = null,
    val batch_size: Int? = null,
    val batch_index: Int? = null,
    val batch_id: Int? = null,
)

@Serializable
data class OtpParametersDTO(
    val secret: ByteArray,
    val name: String,
    val issuer: String,
    val algorithm: Algorithm = Algorithm.ALGORITHM_UNSPECIFIED,
    val digits: DigitCount = DigitCount.DIGIT_COUNT_UNSPECIFIED,
    val type: OtpType = OtpType.OTP_TYPE_UNSPECIFIED,
    val counter: ULong? = null,
)