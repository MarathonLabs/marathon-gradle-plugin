package com.malinskiy.marathon.gradle.task

import com.malinskiy.marathon.gradle.service.MarathonCliService
import org.gradle.api.file.DirectoryProperty
import org.gradle.api.file.RegularFileProperty
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import org.gradle.api.tasks.AbstractExecTask
import org.gradle.api.tasks.InputFile
import org.gradle.api.tasks.Internal
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.VerificationTask
import org.gradle.internal.os.OperatingSystem
import org.gradle.kotlin.dsl.property
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.PosixFilePermission
import javax.inject.Inject

open class MarathonRunTask @Inject constructor(objects: ObjectFactory) : AbstractExecTask<MarathonRunTask>(MarathonRunTask::class.java),
    VerificationTask {

    @InputFile
    val marathonfile: RegularFileProperty = objects.fileProperty()

    @Internal
    val cliService: Property<MarathonCliService> = objects.property()

    @OutputDirectory
    val fakeLockingOutput: DirectoryProperty = objects.directoryProperty().convention(
        project.layout.buildDirectory.dir("fake-marathon-locking-output")
    )

    private var ignoreFailure: Boolean = false

    override fun exec() {
        val cliDir = cliService.get().getCliDirectory()
        setExecutable(getPlatformScript(cliDir))
        setArgs(listOf("-m", marathonfile.get().asFile.canonicalPath))

        super.exec()
    }

    override fun getIgnoreFailures(): Boolean = ignoreFailure

    override fun setIgnoreFailures(ignoreFailures: Boolean) {
        ignoreFailure = ignoreFailures
    }

    private fun getPlatformScript(marathonBuildDir: File) = when (OperatingSystem.current()) {
        OperatingSystem.WINDOWS -> {
            Paths.get(marathonBuildDir.canonicalPath, "bin", "marathon.bat").toFile()
        }

        else -> {
            val cliPath = Paths.get(marathonBuildDir.canonicalPath, "bin", "marathon")
            cliPath.apply {
                val permissions = Files.getPosixFilePermissions(this)
                Files.setPosixFilePermissions(this, permissions + PosixFilePermission.OWNER_EXECUTE)
            }.toFile()
        }
    }
}
