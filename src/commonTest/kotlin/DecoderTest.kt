import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class DecoderTest {

    private val exportedFromGoogleAuth =
        "otpauth-migration://offline?data=ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1%2F3%2F%2F%2F%2F%2FAQ%3D%3D"

    // from dim13/otpauth README
    private val exportedFromGoogleAuth2 =
        "otpauth-migration://offline?data=CjEKCkhlbGxvId6tvu8SGEV4YW1wbGU6YWxpY2VAZ29vZ2xlLmNvbRoHRXhhbXBsZTAC"

    @Test
    fun extractQueryData_returnsCorrectValues() {

        val exportedFromGoogleAuth =
            "otpauth-migration://offline?data=ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1%2F3%2F%2F%2F%2F%2FAQ%3D%3D"

        val expectedValue = "ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1/3/////AQ=="
        val data = extractQueryData(exportedFromGoogleAuth)

        assertEquals(expectedValue, data)
    }

    @Test
    fun decodeOTPSecret_returnsCorrectValues() {
        // from https://github.com/digitalduke/otpauth-migration-decoder/blob/master/tests/test_decode_secret.py#L11
        //        (b'Hello!\xde\xad\xbe\xef', 'JBSWY3DPEHPK3PXP', ),
        //        (b'Hello!', 'JBSWY3DPEE',),
        //        (b'\xde\xad\xbe\xef', '32W353Y',),

        val rawSecret = "Hello!"
        val secret = "JBSWY3DPEE"

        assertEquals(decodeOTPSecret(rawSecret.encodeToByteArray()), secret)

        // TODO convert deadbeef to byte array fails
//        val rawSecret2 = "deadbeef".hexToByteArray()
//        val secret2 = "32W353Y"
//
//        assertEquals(decodeOTPSecret(rawSecret2), secret2)
    }

    @Test
    fun decodeGoogleAuthMigrationData_decodesDataCorrectly() {
        val data = exportedFromGoogleAuth.decodeGoogleAuthMigrationURI()
        assertTrue(data.otpParameters.first().secret == "SHIQ")

        val data2 = exportedFromGoogleAuth2.decodeGoogleAuthMigrationURI()
        assertTrue(data2.otpParameters.first().secret == "JBSWY3DPEHPK3PXP")
    }
}

@OptIn(ExperimentalUnsignedTypes::class)
private fun String.hexToByteArray(): UByteArray {
    check(length % 2 == 0) { "Must have an even length" }

    return UByteArray(length / 2) {
        substring(it..(it + 1)).toUByte(16)
    }
}
