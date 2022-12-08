// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://github.com/shalva97/google-auth-decode/releases/download/0.0.19/shared.xcframework.zip"
let remoteKotlinChecksum = "7989f382aa846853cfa6d24b06e0f62c6eabef47160c9b1055273b71d33e7a82"
let packageName = "com_github_shalva97"

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
