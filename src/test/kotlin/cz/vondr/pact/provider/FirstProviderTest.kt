package cz.vondr.pact.provider

import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactFolder
import au.com.dius.pact.provider.junitsupport.loader.PactUrl
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import java.net.URL
import java.nio.file.Paths


@Provider("myAwesomeService")
// Now it reads pact file from hardcoded path. In real example it would read from pact provider/artifactory or better from Pact Broker
//@PactUrl(urls = arrayOf("file:///C:/PRAC/pact/pact-01-hello-world-02-producer/src/test/resources/pacts/test_consumer-ArticlesProvider.json"))
@PactUrl(urls = arrayOf("file:///home/j.vondrous/repos/my-repos/pact-01-hello-world-01-consumer/build/pacts/test_consumer-ArticlesProvider.json"))
class FirstProviderTest {

    lateinit var ourProvider: OurProvider

    // PROVIDER PROVIDE OWN REST SERVICE
    @BeforeEach
    fun setupProvider(context: PactVerificationContext) {
        //setup our service
        ourProvider = OurProvider()

        //setup url
        context.target = HttpTestTarget.fromUrl(URL(ourProvider.url))
        // or something like
        // context.setTarget(new HttpTestTarget("localhost", myProviderPort, "/"));
    }

    @AfterEach
    fun tearDownProvider() {
        ourProvider.close()
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