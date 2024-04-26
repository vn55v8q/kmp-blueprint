package feature.onboarding.domain

import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class GetOnboardingTest {
    private val remoteClient =
        FactoryTest.newClientOnboarding("1")

    private val defaultClient =
        FactoryTest.newClientOnboarding("2")

    @Test
    fun givenACorrectRemoteAndDefaultClient_whenCallGetOnboardingUseCase_thenReturnRemoteOnboarding() = runTest {
        // Given
        val sut = GetOnboarding(
            remoteClient,
            defaultClient
        )
        // When
        val onboarding = sut.invoke()
        val remoteOnboarding = remoteClient.get()
        // Then
        assertEquals(onboarding.id, remoteOnboarding.id)
    }

    @Test
    fun givenAEmptyOnboardingOnRemoteClientAndCorrectOnboardingOnDefaultClienet_WhenCallGetOnboardingUseCase_thenResponseDefaultOnboarding() =
        runTest {
            // Given
            val incorrectClient = FactoryTest.newClientOnboarding(id = "")
            val sut = GetOnboarding(
                incorrectClient,
                defaultClient
            )
            // When
            val onboarding = sut.invoke()
            val defaultOnboarding = defaultClient.get()
            // Then
            assertEquals(onboarding.id, defaultOnboarding.id)
        }

    @Test
    fun givenAEmptyOnboardingOnRemoteClientAndEmptyOnboardingOnDefaultClienet_WhenCallGetOnboardingUseCase_thenThrowNotFoundOnboardingException() =
        runTest {
            // Given
            val incorrectClient = FactoryTest.newClientOnboarding(id = "")
            val sut = GetOnboarding(
                incorrectClient,
                incorrectClient
            )
            // Then
            assertFailsWith<NotFoundOnboardingException> {
                // When
                val onboarding = sut.invoke()
            }
        }
}