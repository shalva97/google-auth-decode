import gAuth.Payload
import java.net.URI
import java.util.*

fun main() {
    val data =
        "CkYKFDCMaZ5zJewkULs87ggCGlaiiP7sEh1CaXR3YXJkZW46c2hhbHZhMjU4QGdtYWlsLmNvbRoJQml0d2FyZGVuIAEoATACCloKCvg55ykVGq72WrwSM3Nzby5zb2hvaG91c2UuY29tOmdpb3JnaS5zaGFsdmFzaHZpbGlAc29ob2hvdXNlLmNvbRoRc3NvLnNvaG9ob3VzZS5jb20gASgBMAIQARgBIAAojZu1Zg=="


}

fun decodeGoogleAuth(data: String): String {
    return Base64.getDecoder().decode(data)
        .decodeToString()
}

fun String.decodeGoogleAuthMigrationData() {
    val payload = extractQueryData(this)
        .decodeFromBase64()
        .parseProtoBuff()

}

internal fun extractQueryData(data: String): String {
    val uri = URI.create(data)
    val secretData = uri.query.split("=")
    if (secretData.first() == "data") {
        return secretData.component2()
    }
    throw java.lang.IllegalArgumentException("Can not find \"data=\" query parameter")
}

internal fun String.decodeFromBase64(): ByteArray {
    return Base64.getDecoder().decode(this)
}

internal fun ByteArray.parseProtoBuff(): Payload {
    return Payload.parseFrom(this)
}

@JvmInline
value class Main(val blah: String)