import kotlinx.cli.ArgParser
import kotlinx.cli.ArgType

internal fun main(args: Array<String>) {
    val parser = ArgParser("google-auth-decode")
    val migrationData by parser.argument(
        ArgType.String,
        description = "Text from scanned QR code from Google Authenticator, like otp-migration//offline/..."
    )

    parser.parse(args)

    migrationData.decodeGoogleAuthMigrationURI().otpParameters.forEach(::println)

}