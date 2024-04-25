//
//  OnboardingScreen.swift
//  iosApp
//
//  Created by Harttin Andrés Arce Gajardo on 23-04-24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import SDWebImageSwiftUI
import Shared

struct OnboardingScreen: View {
    
    @State private var onboarding: Onboarding? = nil
    @State private var currentPage = 0
    let getOnboarding = GetOnboarding(
        remoteClient: RemoteClientOnboarding(),
        defaultClient: DefaultClientOnboarding()
    )
    
    var body: some View {
        VStack {
            if let safeOnboarding = onboarding {
                TabView(selection: $currentPage) {
                    ForEach(0..<safeOnboarding.pages.count, id: \.self) { index in
                        WebImage(url: URL(string: safeOnboarding.pages[index].url)) { image in
                            image.resizable()
                                .aspectRatio(contentMode: .fill)
                                .frame(width: 350, height: 350)
                                .clipShape(Circle())
                                .padding(16)
                        } placeholder: {
                            Rectangle().foregroundColor(.gray)
                        }
                        .onSuccess { image, data, cacheType in
                            // Success
                            // Note: Data exist only when queried from disk cache or network. Use `.queryMemoryData` if you really need data
                        }
                        .indicator(.activity) // Activity Indicator
                        .transition(.fade(duration: 0.5)) // Fade Transition with duration
                        .scaledToFit()
                        .frame(maxWidth: .infinity, maxHeight: .infinity)
                    }
                    .tabViewStyle(PageTabViewStyle(indexDisplayMode: .automatic))
                }
                .tabViewStyle(PageTabViewStyle(indexDisplayMode: .automatic))
                
                HStack {
                    ForEach(0..<safeOnboarding.pages.count, id: \.self) { index in
                        Circle()
                            .fill(index == currentPage ? Color.black : Color.gray)
                            .frame(width: 10, height: 10)
                    }
                }
                .padding(.top, 8)
                
                if currentPage == safeOnboarding.pages.count - 1 {
                    Button(
                        action: {
                            DispatchQueue.main.asyncAfter(deadline: .now()) {
                                UIApplication.shared.perform(#selector(NSXPCConnection.suspend))
                            }
                        }) {
                            Text("Finalizar")
                                .background(Color.blue)
                                .foregroundColor(.white)
                                .cornerRadius(8)
                        }
                        .buttonStyle(.borderedProminent)
                        .controlSize(.regular)
                        .frame(height: 40, alignment: .center)
                    
                } else {
                    Spacer()
                        .frame(height: 48)
                }
            }
        }
        .task {
            do {
                let asyncResult = try await getOnboarding.invoke()
                DispatchQueue.main.async {
                    self.onboarding = asyncResult
                }
            } catch {
                print("Error: \(error)")
            }
        }
    }
}

/*
 #Preview {
 OnboardingScreen()
 }
 */
