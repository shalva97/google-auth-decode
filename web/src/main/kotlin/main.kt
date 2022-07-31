import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.dom.*
import org.jetbrains.compose.web.renderComposable

fun main() {

    var code by mutableStateOf("")
    var decoded by mutableStateOf("")
    renderComposable(rootElementId = "root") {
        Div({ style { padding(25.px) } }) {
            Input(InputType.Text) {
                onInput {
                    code = it.value
                }
            }
            Button(attrs = {
                onClick {
                    decoded = try {
                        code.decodeGoogleAuthMigrationURI().toString()
                    } catch (e: Exception) {
                        e.message ?: "Unknown error"
                    }
                }
            }) {
                Text("decode")
            }
            Br()
            Text(decoded)
        }
    }
}