package com.thoughtworks.multiplatform.blueprint.platform.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.AccountViewModel
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.ChangeNameViewModel
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.LoginViewModel
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.ProfileViewModel
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.AccountScreen
import com.thoughtworks.multiplatform.blueprint.feature.avatar.ui.AvatarScreen
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.ChangeNameScreen
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.EditProfileScreen
import com.thoughtworks.multiplatform.blueprint.feature.avatar.ui.ImageSelectScreen
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.LoginScreen
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.RegisterScreen
import com.thoughtworks.multiplatform.blueprint.feature.avatar.presentation.AvatarViewModel
import com.thoughtworks.multiplatform.blueprint.feature.avatar.presentation.UpdateImageViewModel
import com.thoughtworks.multiplatform.blueprint.feature.home.presentation.HomeViewModel
import com.thoughtworks.multiplatform.blueprint.feature.home.ui.HomeScreen
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.presentation.OnboardingViewModel
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.ui.OnboardingScreen
import com.thoughtworks.multiplatform.blueprint.feature.splash.presentation.SplashPanorama
import com.thoughtworks.multiplatform.blueprint.feature.splash.presentation.SplashViewModel
import com.thoughtworks.multiplatform.blueprint.feature.splash.ui.SplashScreen
import org.koin.androidx.compose.koinViewModel
import platform.log.Log
import java.net.URLDecoder
import java.net.URLEncoder

@Composable
fun BlueprintNavigation(
    onFinish: () -> Unit
) {
    Log.d("Profile", "BlueprintNavigation - init")
    val navController = rememberNavController()
    val splashViewModel: SplashViewModel = koinViewModel()
    val onboardingViewModel: OnboardingViewModel = koinViewModel()
    val accountViewModel: AccountViewModel = koinViewModel()
    val loginViewModel: LoginViewModel = koinViewModel()
    val changeNameViewModel: ChangeNameViewModel = koinViewModel()
    val profileViewModel: ProfileViewModel = koinViewModel()
    val avatarViewModel: AvatarViewModel = koinViewModel()


    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            val state = splashViewModel.state.collectAsState()
            SplashScreen(modifier = Modifier.fillMaxSize(),
                state = state.value,
                onNavigateToPanorama = { panorama ->
                    when (panorama) {
                        SplashPanorama.DEFAULT -> Log.d("Splash", "Init state")
                        SplashPanorama.ONBOARDING -> {
                            navController.navigate("onboarding") {
                                popUpTo("splash") {
                                    inclusive = true
                                }
                            }
                        }

                        SplashPanorama.HOME -> {
                            navController.navigate("home") {
                                popUpTo("splash") {
                                    inclusive = true
                                }
                            }
                        }

                        SplashPanorama.CREATE_ACCOUNT -> {
                            navController.navigate("account") {
                                popUpTo("splash") {
                                    inclusive = true
                                }
                            }
                        }

                        SplashPanorama.ROOTED_DECIVE -> {

                        }

                        SplashPanorama.FORCE_UPDATE -> {

                        }

                        SplashPanorama.NOTIFICATION_INFO -> {

                        }
                    }

                })
        }
        composable("onboarding") {
            LaunchedEffect(Unit) {
                onboardingViewModel.fetch()
            }
            val state = onboardingViewModel.state.collectAsState()
            OnboardingScreen(state = state.value, onFinish = {
                onboardingViewModel.finish()
                navController.navigate("account") {
                    popUpTo("onboarding") {
                        inclusive = true
                    }
                }
            })
        }
        composable("account") {
            AccountScreen(modifier = Modifier.fillMaxSize(), onClickLogin = {
                navController.navigate("login")
            }, onClickRegister = {
                navController.navigate("register")
            })
        }
        composable("register") {
            val state = accountViewModel.state.collectAsState()
            RegisterScreen(state = state.value, onBackClick = {
                navController.popBackStack()
            }, onUserClick = { newUser ->
                accountViewModel.processUser(newUser)
            }, onNameChange = { newName ->
                accountViewModel.changedName(newName)
            }, onNameClick = { newName ->
                accountViewModel.processName(newName)
            }, onEmailClick = { newEmail ->
                accountViewModel.processEmail(newEmail)
            }, onPasswordClick = { newPass ->
                accountViewModel.confirmPass(newPass)
            }, onPasswordChange = { newPass ->
                accountViewModel.processPass(newPass)
            }, onLastStepClick = {
                accountViewModel.onLastStepProcess()
            }, onLoginEmailClick = {
                accountViewModel.emailLoginConfirm()
            }, onLoginPasswordClick = { password ->
                accountViewModel.passwordLoginConfirm(password)
            }, onClickPasswordRecovery = {}, goToHomeScreen = {
                navController.navigate("home") {
                    popUpTo("account") {
                        inclusive = true
                    }
                }
            })
        }
        composable("login") {
            val state by loginViewModel.state.collectAsState()
            LoginScreen(state = state, onEmailClick = { email ->
                loginViewModel.processEmail(email)
            }, onPasswordClick = { password ->
                loginViewModel.processPass(password)
            }, onLastStepClick = {
                loginViewModel.onLastStepProcess()
            }, onCloseScreen = onFinish, goToHomeScreen = {
                navController.navigate("home") {
                    popUpTo("account") {
                        inclusive = true
                    }
                }
            }, onPasswordRecovery = {
                navController.navigate("recovery-password")
            })
        }
        composable("home") {
            val viewModel: HomeViewModel = koinViewModel()
            val state by viewModel.state.collectAsState()
            HomeScreen(
                modifier = Modifier.fillMaxSize(),
                state = state,
                onClickProfile = {
                    navController.navigate("avatar")
                },
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
        composable("recovery-password") {
            Text(text = "TODO: Recovery Screen")
        }
        composable("avatar") {
            LaunchedEffect(key1 = Unit) {
                avatarViewModel.fetch()
            }
            val state by avatarViewModel.state.collectAsState()
            AvatarScreen(modifier = Modifier.fillMaxSize(), state = state, onClickEditProfile = {
                profileViewModel.setUrl(state.urlImage)
                navController.navigate("avatar-edit")
            }, onClickChangeImage = {
                navController.navigate("change-image")
            }, onBackClick = {
                navController.popBackStack()
            })
        }
        composable("avatar-edit") {
            val stateProfile by profileViewModel.state.collectAsState()
            LaunchedEffect(key1 = Unit) {
                profileViewModel.fetch()
            }
            EditProfileScreen(modifier = Modifier.fillMaxSize(),
                state = stateProfile,
                onBackClick = {
                    navController.popBackStack()
                },
                onClickChangeImage = {
                    navController.navigate("change-image")
                },
                onClickChangeName = {
                    navController.navigate("change-name")
                },
                onClickChangeUser = {

                },
                onClickChangePronoun = {

                },
                onClickChangeDescription = {

                })
        }
        composable("change-name") {
            val changeNameState by changeNameViewModel.state.collectAsState()
            ChangeNameScreen(modifier = Modifier.fillMaxSize(),
                state = changeNameState,
                onBackClick = {},//profileViewModel::previousScreen,
                onChangeName = changeNameViewModel::processName,
                onSaveName = { newName ->
                    //changeNameViewModel.saveName(newName)
                    //profileViewModel.updateName(newName)
                    //profileViewModel.fetch()
                },
                onFinish = {
                    //changeNameViewModel.reset()
                    //navController.popBackStack()
                })
        }
        composable("change-image") {
            val updateImageViewModel: UpdateImageViewModel = koinViewModel()
            val state by updateImageViewModel.state.collectAsState()
            ImageSelectScreen(modifier = Modifier.fillMaxSize(),
                state = state,
                onProcessImage = updateImageViewModel::uploadImage,
                onBackClick = {
                    navController.popBackStack()
                })
        }
    }
}