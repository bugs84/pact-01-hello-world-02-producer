package cz.vondr.pact.provider

import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import au.com.dius.pact.provider.junitsupport.Consumer
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactBroker
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import java.net.URL


@PactBroker(host = "localhost", port = "9292")
@Provider("ArticlesProvider")
@Consumer("test_consumer")
class FirstProviderTestContractExchangedViaPactBroker {

    lateinit var articlesProvider: ArticlesProvider

    // PROVIDER PROVIDE OWN REST SERVICE
    @BeforeEach
    fun setupProvider(context: PactVerificationContext) {
        //setup our service
        articlesProvider = ArticlesProvider()

        //setup url
        context.target = HttpTestTarget.fromUrl(URL(articlesProvider.url))
        // or something like
        // context.setTarget(new HttpTestTarget("localhost", myProviderPort, "/"));
    }

    @AfterEach
    fun tearDownProvider() {
        articlesProvider.close()
    }

    // IF PROVIDER NEEDS SOME SPECIFIC STATE - here it can be setup
    @State("test state") // Method will be run before testing interactions that require "default" or "no-data" state
    fun toTestState() {
        // Prepare service before interaction that require "test state" state
        // ...
        System.out.println("Now service in default state");
    }

    // THIS START Tests provided by Consumer
    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }


}