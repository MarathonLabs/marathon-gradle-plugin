package com.malinskiy.marathon.gradle.service

import org.gradle.api.file.DirectoryProperty
import org.gradle.api.provider.Property
import org.gradle.api.services.BuildService
import org.gradle.api.services.BuildServiceParameters
import org.slf4j.LoggerFactory
import java.io.BufferedInputStream
import java.io.BufferedOutputStream
import java.io.File
import java.util.zip.ZipInputStream

abstract class MarathonCliService : BuildService<MarathonCliService.Params> {

    interface Params : BuildServiceParameters {
        val cliMd5: Property<String>
        val marathonDir: DirectoryProperty
    }

    private val logger = LoggerFactory.getLogger(MarathonCliService::class.java)
    private val lock = Any()

    fun getCliDirectory(): File {
        val marathonDir = parameters.marathonDir.get().asFile
        val cliDir = File(marathonDir, "cli")
        val md5File = File(marathonDir, ".md5")
        val expectedMd5 = parameters.cliMd5.get()

        if (cliDir.exists() && md5File.exists() && md5File.readText() == expectedMd5) {
            logger.info("Marathon CLI already extracted at {}", cliDir)
            return cliDir
        }

        synchronized(lock) {
            // Double-check after acquiring lock
            if (cliDir.exists() && md5File.exists() && md5File.readText() == expectedMd5) {
                logger.info("Marathon CLI already extracted at {}", cliDir)
                return cliDir
            }

            if (cliDir.exists()) {
                cliDir.deleteRecursively()
            }
            marathonDir.mkdirs()

            logger.info("Extracting marathon CLI to {}", cliDir)

            val cliZipStream = MarathonCliService::class.java.getResourceAsStream("/marathon-cli.zip")
                ?: throw IllegalStateException("marathon-cli.zip not found in plugin resources")

            ZipInputStream(BufferedInputStream(cliZipStream)).use { zis ->
                var entry = zis.nextEntry
                while (entry != null) {
                    // Drop first path segment (replicating Sync task's relativePath.segments.drop(1))
                    val segments = entry.name.split("/")
                    if (segments.size > 1) {
                        val relativePath = segments.drop(1).joinToString("/")
                        if (relativePath.isNotEmpty()) {
                            val outFile = File(cliDir, relativePath)
                            if (entry.isDirectory) {
                                outFile.mkdirs()
                            } else {
                                outFile.parentFile.mkdirs()
                                BufferedOutputStream(outFile.outputStream()).use { os ->
                                    zis.copyTo(os)
                                }
                            }
                        }
                    }
                    zis.closeEntry()
                    entry = zis.nextEntry
                }
            }

            md5File.writeText(expectedMd5)
        }

        return cliDir
    }
}
