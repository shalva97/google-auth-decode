import java.net.URLDecoder
import kotlin.test.Test

class DecoderTest {

    private val key = "SHIT"
    private val account = "toilet"
    private val issuer = "ass"

    private val exportedFromGoogleAuth =
        "otpauth-migration://offline?data=ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1%2F3%2F%2F%2F%2F%2FAQ%3D%3D"

    private val data = "ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1%2F3%2F%2F%2F%2F%2FAQ%3D%3D"

    private val urlDecodedData = "ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1/3/////AQ=="

    @Test
    fun decodeGoogleAuthKeys() {
        val result = decodeGoogleAuth(urlDecodedData)

        println(result)
        assert(result == key)
    }

    @Test
    fun decodeHTML() {
        URLDecoder.decode(data)
            .run(::println)
    }

    @Test
    fun extractQueryData_returnsCorrectValues() {

        val exportedFromGoogleAuth =
            "otpauth-migration://offline?data=ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1%2F3%2F%2F%2F%2F%2FAQ%3D%3D"

        val expectedValue = "ChsKApHREgphc3M6dG9pbGV0GgNhc3MgASgBMAIQARgBIAAo05KP1/3/////AQ"
        val data = extractQueryData(exportedFromGoogleAuth)

        assert(data == expectedValue)
    }
}