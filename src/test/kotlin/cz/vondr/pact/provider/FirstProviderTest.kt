package cz.vondr.pact.provider

import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactUrl
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import java.net.URL


@Provider("myAwesomeService")
//@PactFolder("pacts")
@PactUrl(urls = arrayOf("file:///C:/PRAC/pact/pact-01-hello-world-02-producer/src/test/resources/pacts/test_consumer-ArticlesProvider.json"))
class FirstProviderTest {

    lateinit var ourProvider: OurProvider

    @BeforeEach
    fun setupProvider(context: PactVerificationContext) {
        ourProvider = OurProvider()
        val myProviderUrl = ourProvider.url
        context.target = HttpTestTarget.fromUrl(URL(myProviderUrl))
        // or something like
        // context.setTarget(new HttpTestTarget("localhost", myProviderPort, "/"));
    }

    @AfterEach
    fun tearDownProvider() {
        ourProvider.close()
    }

    @State("test state") // Method will be run before testing interactions that require "default" or "no-data" state
    fun toTestState() {
        // Prepare service before interaction that require "default" state
        // ...
        System.out.println("Now service in default state");
    }

//    @State("default", "no-data") // Method will be run before testing interactions that require "default" or "no-data" state
//    fun toDefaultState() {
//        // Prepare service before interaction that require "default" state
//        // ...
//        System.out.println("Now service in default state");
//    }
//
//    @State("with-data") // Method will be run before testing interactions that require "with-data" state
//    fun toStateWithData(data: Map<String, Any>) {
//        // Prepare service before interaction that require "with-data" state. The provider state data will be passed
//        // in the data parameter
//        // ...
//        System.out.println("Now service in state using data " + data);
//    }


    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }


}