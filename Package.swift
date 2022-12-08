// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://github.com/shalva97/google-auth-decode/releases/download/0.0.19/shared.xcframework.zip"
let remoteKotlinChecksum = "6419a9234831015b49a7a3f3542c9810ccf7b160e7159aec2206d318d1641238"
let packageName = "shared"

let package = Package(
    name: packageName,
    platforms: [
        .macOS(.v10_13)
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
