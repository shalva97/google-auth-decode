// swift-tools-version:5.3
import PackageDescription

let remoteKotlinUrl = "https://github.com/shalva97/google-auth-decode/releases/download/0.0.19/shared.xcframework.zip"
let remoteKotlinChecksum = "59d52e04c78f7a91548efbc61f30ded13bd7a4bf41f55cf24f790d33d010b770"
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
