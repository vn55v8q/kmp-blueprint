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
    var body: some View {
        VStack {
            Text(/*@START_MENU_TOKEN@*/"Hello, World!"/*@END_MENU_TOKEN@*/)
        }.task {
            versionStatus = GetVersionStatus(
            
            ).invoke()
        }
    }
}

#Preview {
    SplashScreen()
}
