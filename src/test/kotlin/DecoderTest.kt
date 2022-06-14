import kotlin.test.Test

class DecoderTest {

    private val exportedFromGoogleAuth =
        "otpauth-migration://offline?data=ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1%2F3%2F%2F%2F%2F%2FAQ%3D%3D"

    @Test
    fun extractQueryData_returnsCorrectValues() {

        val exportedFromGoogleAuth =
            "otpauth-migration://offline?data=ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1%2F3%2F%2F%2F%2F%2FAQ%3D%3D"

        val expectedValue = "ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1/3/////AQ"
        val data = extractQueryData(exportedFromGoogleAuth)

        assert(data == expectedValue)
    }

    @Test
    fun decodeOTPSecret_returnsCorrectValues() {
        // from https://github.com/digitalduke/otpauth-migration-decoder/blob/master/tests/test_decode_secret.py#L11
        //        (b'Hello!\xde\xad\xbe\xef', 'JBSWY3DPEHPK3PXP', ),
        //        (b'Hello!', 'JBSWY3DPEE',),
        //        (b'\xde\xad\xbe\xef', '32W353Y',),

        val rawSecret = "Hello!"
        val secret = "JBSWY3DPEE"

        assert(decodeOTPSecret(rawSecret.toByteArray()) == secret)

        val rawSecret2 = "deadbeef".decodeHex()
        val secret2 = "32W353Y"

        assert(decodeOTPSecret(rawSecret2) == secret2)
    }

    @Test
    fun decodeGoogleAuthMigrationData_decodesDataCorrectly() {
        val data = exportedFromGoogleAuth.decodeGoogleAuthMigrationURI()

        assert(data.first().secret == "SHIQ")
    }
}

private fun String.decodeHex(): ByteArray {
    check(length % 2 == 0) { "Must have an even length" }

    return ByteArray(length / 2) {
        Integer.parseInt(this, it * 2, (it + 1) * 2, 16).toByte()
    }
}