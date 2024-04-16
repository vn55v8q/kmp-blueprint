//
//  VersionDeviceMolecule.swift
//  iosApp
//
//  Created by Harttin Andrés Arce Gajardo on 15-04-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared

struct VersionDeviceMolecule: View {
    let versionStatus: VersionStatus?
    
    var body: some View {
        if let safeVersionInfo = versionStatus {
            Text("Version Info")
            Text("stable: \(safeVersionInfo.stable)")
            Text("min: \(safeVersionInfo.min)")
            Text("installed: \(safeVersionInfo.installed)")
        }
    }
}

