// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://github.com/shalva97/google-auth-decode/releases/download/0.0.17/shared.xcframework.zip"
let remoteKotlinChecksum = "243646153ba4a0d04034a3d58d4910098b2332ce4e8a5e4e814829280dcffef9"
let packageName = "shared"

let package = Package(
    name: packageName,
    platforms: [
        .macOS(.v11)
    ],
    products: [
        .library(
            name: packageName,
            targets: [packageName]
        ),
    ],
    targets: [
        .binaryTarget(
            name: packageName,
            url: remoteKotlinUrl,
            checksum: remoteKotlinChecksum
        )
    ]
)
