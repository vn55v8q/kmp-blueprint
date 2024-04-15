package com.thoughtworks.multiplatform.blueprint.platform.version.domain

import com.thoughtworks.multiplatform.blueprint.BuildConfig
import platform.version.domain.DeviceVersion

class AndroidDeviceVersion : DeviceVersion {
    override fun getInstalledVersion(): String {
        return BuildConfig.VERSION_NAME
    }

}