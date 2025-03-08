package io.github.ezberlin.vplanx.data

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.UByteVar
import kotlinx.cinterop.allocArray
import kotlinx.cinterop.convert
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.readBytes
import kotlinx.cinterop.toCValues
import platform.darwin.COMPRESSION_ZLIB
import platform.darwin.compression_decode_buffer
import platform.darwin.compression_encode_buffer

const val capacity = 10_000_000

/**
 * Decompress with gzip via Korio.
 */
@OptIn(ExperimentalForeignApi::class)
actual fun gzipDecompress(input: ByteArray): String {
    memScoped {
        val destinationBuffer = allocArray<UByteVar>(capacity)

        val inputUByte = input.toUByteArray()

        val oldSize = compression_decode_buffer(
            destinationBuffer,
            capacity.convert(),
            inputUByte.toCValues(),
            inputUByte.size.convert(),
            null,
            COMPRESSION_ZLIB
        )

        return destinationBuffer.readBytes(oldSize.convert()).decodeToString()
    }
}

/**
 * Compress the given byte array using gzip via Korio.
 */
@OptIn(ExperimentalForeignApi::class)
actual fun gzipCompress(input: String): ByteArray {
    memScoped {
        val inputData = input.encodeToByteArray().toUByteArray()
        val destinationBuffer = allocArray<UByteVar>(capacity)
        val compressedSize = compression_encode_buffer(
            destinationBuffer,
            capacity.convert(),
            inputData.toUByteArray().toCValues(),
            inputData.size.convert(),
            null,
            COMPRESSION_ZLIB
        )

        return destinationBuffer.readBytes(compressedSize.convert())
    }
}