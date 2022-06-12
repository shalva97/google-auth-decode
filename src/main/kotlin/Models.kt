import com.google.protobuf.ByteString
import gAuth.Payload
import gAuth.Payload.Algorithm
import gAuth.Payload.OtpParameters
import org.apache.commons.codec.binary.Base32

data class OTPData(
    val name: String,
    val algorithm: OTPAlgorithm,
    val issuer: String,
    val secret: String,
    val type: OTPType,
) {
    constructor(payload: OtpParameters) : this(
        name = payload.name,
        payload.algorithm.toDomainModel(),
        payload.issuer,
        payload.secret.toDomainModel(),
        payload.type.toDomainModel()
    )
}

enum class OTPAlgorithm() {
    ALGORITHM_UNSPECIFIED, ALGORITHM_SHA1, ALGORITHM_SHA256, ALGORITHM_SHA512, ALGORITHM_MD5, UNRECOGNIZED
}

enum class OTPType(val key: String) {
    HOTP("hotp"), TOTP("totp"), OTP_TYPE_UNSPECIFIED("Unspecified"), UNRECOGNIZED("Unrecognized")
}

private fun Algorithm.toDomainModel() = when (this) {
    Algorithm.ALGORITHM_UNSPECIFIED -> OTPAlgorithm.ALGORITHM_UNSPECIFIED
    Algorithm.ALGORITHM_SHA1 -> OTPAlgorithm.ALGORITHM_SHA1
    Algorithm.ALGORITHM_SHA256 -> OTPAlgorithm.ALGORITHM_SHA256
    Algorithm.ALGORITHM_SHA512 -> OTPAlgorithm.ALGORITHM_SHA512
    Algorithm.ALGORITHM_MD5 -> OTPAlgorithm.ALGORITHM_MD5
    Algorithm.UNRECOGNIZED -> OTPAlgorithm.UNRECOGNIZED
}

private fun Payload.OtpType.toDomainModel() = when (this) {
    Payload.OtpType.OTP_TYPE_UNSPECIFIED -> OTPType.OTP_TYPE_UNSPECIFIED
    Payload.OtpType.OTP_TYPE_HOTP -> OTPType.HOTP
    Payload.OtpType.OTP_TYPE_TOTP -> OTPType.TOTP
    Payload.OtpType.UNRECOGNIZED -> OTPType.UNRECOGNIZED
}

private fun ByteString.toDomainModel(): String {
    return Base32().encodeAsString(this.toByteArray()).trim { it == '=' }
}