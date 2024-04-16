//
//  IosDeviceVersion.swift
//  iosApp
//
//  Created by Harttin Andrés Arce Gajardo on 15-04-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation
import Shared

class IosDeviceVersion : DeviceVersion {
    func getInstalledVersion() -> String {
        if let version = Bundle.main.infoDictionary?["CFBundleShortVersionString"] as? String {
            return version
        } else {
            return "?.?.?"
        }
    }
}
