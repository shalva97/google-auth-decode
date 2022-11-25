// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://github.com/shalva97/google-auth-decode/releases/download/0.0.18/shared.xcframework.zip"
let remoteKotlinChecksum = "abb3feeb765f83a1cc27ad48c880f68f9e51dc2598b38be2869dfa53ce61f024"
let packageName = "com_github_shalva97"

let package = Package(
    name: packageName,
    platforms: [
        .macOS(.v13)
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
