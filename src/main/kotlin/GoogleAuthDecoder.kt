import gAuth.Payload
import org.apache.commons.codec.binary.Base32
import java.net.URI
import java.util.*

fun main() {

}

fun String.decodeGoogleAuthMigrationURI(): List<OTPData> {
    return extractQueryData(this)
        .decodeFromBase64()
        .parseToProtoBuff()
        .toDomainModel()
}

internal fun extractQueryData(data: String): String {
    val uri = URI.create(data)
    val secretData = uri.query.split("=")

    require(uri.scheme == "otpauth-migration") { "Wrong scheme" }
    require(uri.host == "offline") { "Wrong host" }
    require(secretData.first() == "data") { "Can not find \"data=\" query parameter" }

    return secretData.component2()
}

internal fun String.decodeFromBase64(): ByteArray {
    return Base64.getDecoder().decode(this)
}

internal fun ByteArray.parseToProtoBuff(): Payload {
    return Payload.parseFrom(this)
}

internal fun Payload.toDomainModel(): List<OTPData> {
    return this.otpParametersList.map { OTPData(it) }
}

internal fun decodeOTPSecret(data: ByteArray): String {
    return Base32().encodeAsString(data).trim { it == '=' }
}