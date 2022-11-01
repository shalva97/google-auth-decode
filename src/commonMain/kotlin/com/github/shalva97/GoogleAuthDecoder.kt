package com.github.shalva97

import com.eygraber.uri.Uri
import io.matthewnelson.component.base64.decodeBase64ToArray
import io.matthewnelson.component.encoding.base32.encodeBase32
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.protobuf.ProtoBuf

fun String.decodeGoogleAuthMigrationURI(): Payload {
    return extractQueryData(this)
        .decodeFromBase64()
        .parseToProtoBuff()
        .toDomainModel()
}

private fun PayloadDTO.toDomainModel(): Payload {
    return Payload(this)
}

internal fun extractQueryData(data: String): String {
    val uri = Uri.parse(data)
    val secretData = uri.query!!.split("=")

    require(uri.scheme == "otpauth-migration") { "Wrong scheme" }
    require(uri.host == "offline") { "Wrong host" }
    require(secretData.first() == "data") { "Can not find \"data=\" query parameter" }

    return secretData.component2()
}

internal fun String.decodeFromBase64(): ByteArray {
    return decodeBase64ToArray()!!
}

@OptIn(ExperimentalSerializationApi::class)
internal fun ByteArray.parseToProtoBuff(): PayloadDTO {
    return ProtoBuf.decodeFromByteArray(this)
}

internal fun decodeOTPSecret(data: ByteArray): String {
    return data.encodeBase32().trim { it == '=' }
}