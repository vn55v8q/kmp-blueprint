package com.thoughtworks.multiplatform.blueprint.platform.navigation

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.AccountViewModel
import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ChangeNameViewModel
import com.thoughtworks.multiplatform.blueprint.feature.account.presentation.LoginViewModel
import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ProfileViewModel
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.AccountScreen
import com.thoughtworks.multiplatform.blueprint.feature.avatar.ui.AvatarScreen
import com.thoughtworks.multiplatform.blueprint.feature.profile.ui.ChangeNameScreen
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.LoginScreen
import com.thoughtworks.multiplatform.blueprint.feature.account.ui.RegisterScreen
import com.thoughtworks.multiplatform.blueprint.feature.avatar.presentation.AvatarViewModel
import com.thoughtworks.multiplatform.blueprint.feature.avatar.presentation.UpdateImageViewModel
import com.thoughtworks.multiplatform.blueprint.feature.avatar.ui.ImageSelectScreen
import com.thoughtworks.multiplatform.blueprint.feature.home.presentation.HomeViewModel
import com.thoughtworks.multiplatform.blueprint.feature.home.ui.HomeScreen
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.presentation.OnboardingViewModel
import com.thoughtworks.multiplatform.blueprint.feature.onboarding.ui.OnboardingScreen
import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ChangeDescriptionViewModel
import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ChangePronounViewModel
import com.thoughtworks.multiplatform.blueprint.feature.profile.presentation.ChangeUserViewModel
import com.thoughtworks.multiplatform.blueprint.feature.profile.ui.ChangeDescriptionScreen
import com.thoughtworks.multiplatform.blueprint.feature.profile.ui.ChangePronounScreen
import com.thoughtworks.multiplatform.blueprint.feature.profile.ui.ChangeUserScreen
import com.thoughtworks.multiplatform.blueprint.feature.profile.ui.EditProfileScreen
import com.thoughtworks.multiplatform.blueprint.feature.rooms.presentation.RoomsViewModel
import com.thoughtworks.multiplatform.blueprint.feature.rooms.ui.RoomsScreen
import com.thoughtworks.multiplatform.blueprint.feature.splash.presentation.SplashPanorama
import com.thoughtworks.multiplatform.blueprint.feature.splash.presentation.SplashViewModel
import com.thoughtworks.multiplatform.blueprint.feature.splash.ui.SplashScreen
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.presentation.ThemeState
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.presentation.ThemeViewModel
import com.thoughtworks.multiplatform.blueprint.platform.designsystem.theme.ui.ThemeScreen
import org.koin.androidx.compose.koinViewModel
import platform.log.Log
import platform.theme.domain.ThemeType

@Composable
fun BlueprintNavigation(
    themeState: ThemeState,
    onFinish: () -> Unit,
    onLoginSuccess: () -> Unit,
    onClickItem: (ThemeType, Boolean) -> Unit,
) {
    val navController = rememberNavController()
    val splashViewModel: SplashViewModel = koinViewModel()
    val onboardingViewModel: OnboardingViewModel = koinViewModel()
    val accountViewModel: AccountViewModel = koinViewModel()
    val loginViewModel: LoginViewModel = koinViewModel()
    val changeNameViewModel: ChangeNameViewModel = koinViewModel()
    val changeUserViewModel: ChangeUserViewModel = koinViewModel()
    val changeDescriptionViewModel: ChangeDescriptionViewModel = koinViewModel()
    val changePronounViewModel: ChangePronounViewModel = koinViewModel()
    val profileViewModel: ProfileViewModel = koinViewModel()
    val avatarViewModel: AvatarViewModel = koinViewModel()
    val roomViewModel: RoomsViewModel = koinViewModel()


    NavHost(navController = navController, startDestination = "rooms") {
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
                onLoginSuccess()
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
                },
                onClickSettings = {
                    navController.navigate("settings")
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
            val lifecycleOwner = LocalLifecycleOwner.current
            val lifecycleState by lifecycleOwner.lifecycle.currentStateFlow.collectAsState()

            LaunchedEffect(lifecycleState) {
                // Do something with your state
                // You may want to use DisposableEffect or other alternatives
                // instead of LaunchedEffect
                when (lifecycleState) {
                    Lifecycle.State.DESTROYED -> {}
                    Lifecycle.State.INITIALIZED -> {}
                    Lifecycle.State.CREATED -> {}
                    Lifecycle.State.STARTED -> {}
                    Lifecycle.State.RESUMED -> {
                        profileViewModel.fetch()
                    }
                }
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
                    navController.navigate("change-user")
                },
                onClickChangePronoun = {
                    navController.navigate("change-pronoun")
                },
                onClickChangeDescription = {
                    navController.navigate("change-description")
                })
        }
        composable("change-name") {
            val changeNameState by changeNameViewModel.state.collectAsState()
            ChangeNameScreen(modifier = Modifier.fillMaxSize(),
                state = changeNameState,
                onBackClick = {
                    navController.popBackStack()
                },
                onChangeName = changeNameViewModel::processName,
                onSaveName = { newName ->
                    changeNameViewModel.saveName()
                    profileViewModel.updateName(newName)
                },
                onFinish = {
                    changeNameViewModel.reset()
                    navController.popBackStack()
                })
        }
        composable("change-image") {
            val updateImageViewModel: UpdateImageViewModel = koinViewModel()
            val state by updateImageViewModel.state.collectAsState()
            ImageSelectScreen(modifier = Modifier.fillMaxSize(),
                state = state,
                onProcessImage = updateImageViewModel::uploadImage,
                onBackClick = {
                    profileViewModel.setUrl(state.urlAvatar)
                    navController.popBackStack()
                })
        }

        composable("change-user") {
            val changeUserState by changeUserViewModel.state.collectAsState()
            ChangeUserScreen(modifier = Modifier.fillMaxSize(),
                state = changeUserState,
                onBackClick = {
                    navController.popBackStack()
                },
                onChangeUser = changeUserViewModel::processUser,
                onSaveUser = { newUser ->
                    changeUserViewModel.saveUser(newUser)
                    profileViewModel.updateUser(newUser)
                },
                onFinish = {
                    changeUserViewModel.reset()
                    navController.popBackStack()
                })
        }

        composable("change-description") {
            val changeDescriptionState by changeDescriptionViewModel.state.collectAsState()
            val configuration = LocalConfiguration.current
            val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
            ChangeDescriptionScreen(modifier = Modifier.fillMaxSize(),
                isLandscape = isLandscape,
                state = changeDescriptionState,
                onBackClick = {
                    navController.popBackStack()
                },
                onChangeDescription = { newDescription ->
                    changeDescriptionViewModel.changeDescription(newDescription)
                },
                onSaveDescription = {
                    changeDescriptionViewModel.saveDescription()
                    profileViewModel.updateDescriprion(changeDescriptionState.description)
                },
                onFinish = {
                    changeDescriptionViewModel.reset()
                    navController.popBackStack()
                })
        }

        composable("change-pronoun") {
            val changePronounState by changePronounViewModel.state.collectAsState()
            val configuration = LocalConfiguration.current
            val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE
            ChangePronounScreen(modifier = Modifier.fillMaxSize(),
                isLandscape = isLandscape,
                state = changePronounState,
                onBackClick = {
                    navController.popBackStack()
                },
                onChangePronoun = { newPronoun ->
                    changePronounViewModel.changePronoun(newPronoun)
                },
                onSavePronoun = {
                    changePronounViewModel.savePronoun()
                    profileViewModel.updatePronoun(changePronounState.pronoun)
                },
                onFinish = {
                    changePronounViewModel.reset()
                    navController.popBackStack()
                })
        }
        composable("settings"){
            ThemeScreen(
                name = themeState.theme.type.name,
                onClickItem = onClickItem,
                onFinishTheme = {
                    navController.popBackStack()
                }
            )
        }
        composable("rooms") {
            val roomState by roomViewModel.state.collectAsState()
            LaunchedEffect(key1 = Unit) {
                roomViewModel.fetch()
            }
            RoomsScreen(
                state = roomState,
                onCreate = roomViewModel::createRoom,
                onJoin = roomViewModel::joinRoom
            )
        }
    }
}