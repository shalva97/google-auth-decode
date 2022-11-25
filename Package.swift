// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://github.com/shalva97/google-auth-decode/releases/download/0.0.17/shared.xcframework.zip"
let remoteKotlinChecksum = "71268597ff63c8a7a0015b6e132bbe761b5e6efbc97cf2d41df104629a400cac"
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
