package cz.vondr.pact.provider

import au.com.dius.pact.provider.junit5.HttpTestTarget
import au.com.dius.pact.provider.junit5.PactVerificationContext
import au.com.dius.pact.provider.junit5.PactVerificationInvocationContextProvider
import au.com.dius.pact.provider.junitsupport.Consumer
import au.com.dius.pact.provider.junitsupport.Provider
import au.com.dius.pact.provider.junitsupport.State
import au.com.dius.pact.provider.junitsupport.loader.PactBroker
import cz.vondr.pact.provider.dto.Article
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.TestTemplate
import org.junit.jupiter.api.extension.ExtendWith
import java.net.URL

@PactBroker(host = "localhost", port = "9292")
@Provider("ArticlesProvider")
@Consumer("ArticlesConsumer")
class ArticlesConsumerTest {
    lateinit var articlesProvider: ArticlesProvider
    @BeforeEach
    fun setupProvider(context: PactVerificationContext) {
        articlesProvider = ArticlesProvider()
        context.target = HttpTestTarget.fromUrl(URL(articlesProvider.url))
    }

    @AfterEach
    fun tearDownProvider() {
        articlesProvider.close()
    }

    @State("Provider with three articles")
    fun setStateWithThreeArticles() = 
        articlesProvider
            .addArticle(Article("Basic news", 104))
            .addArticle(Article("About dogs", 5684))
            .addArticle(Article("Gossip", 4512384))


    @TestTemplate
    @ExtendWith(PactVerificationInvocationContextProvider::class)
    fun pactVerificationTestTemplate(context: PactVerificationContext) {
        context.verifyInteraction()
    }
}