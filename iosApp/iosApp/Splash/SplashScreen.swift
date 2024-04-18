//
//  SplashScreen.swift
//  iosApp
//
//  Created by Harttin Andrés Arce Gajardo on 14-04-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared


struct SplashScreen: View {
    @State private var versionStatus: VersionStatus? = nil
    let getVersionStatus = GetVersionStatus(
        remoteVersion: SharedRemoteVersion(),
        deviceVersion: IosDeviceVersion()
    )
    
    var body: some View {
        VStack {
            VersionDeviceMolecule(versionStatus : self.versionStatus)
        }.task {
            do {
                let asyncResult = try await getVersionStatus.invoke()
                DispatchQueue.main.async {
                    self.versionStatus = asyncResult
                }
            } catch {
                print("Error: \(error)")
            }
        }
    }
}

#Preview {
    SplashScreen()
}
