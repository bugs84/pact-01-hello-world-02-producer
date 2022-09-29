package cz.vondr.pact.provider

import com.sun.net.httpserver.HttpServer
import java.net.InetSocketAddress
import java.net.ServerSocket

/**
 * This is example of provider application.
 * Server application, that provide list of articles via. REST interface.
 */
class OurProvider : AutoCloseable {


    val port = getFreePort()
    val url = "http://localhost:$port/"
    private val server: HttpServer = HttpServer.create(InetSocketAddress(port), 0)

    init {
        createArticlesResponse()
        start()
    }

    private fun start() {
        server.executor = null // creates a default executor
        server.start()
    }

    override fun close() {
        stop()
    }

    fun stop() {
        server.stop(0)
    }

    /** Server will listen on "path", and your callBack will be executed if this path request for this path was called.
     * After that it will return default 200 response */
    fun createArticlesResponse() {
        server.createContext("/articles.json") { httpExchange ->
            //Return
            val response = """
                        {
                          "responsetest": true
                        }
                        """
            httpExchange.responseHeaders.add("Content-Type", "application/json")
            httpExchange.sendResponseHeaders(200, response.length.toLong())
            httpExchange.responseBody.use { os ->
                os.write(response.toByteArray())
            }
        }
    }

    private fun getFreePort(): Int {
        var port = -1
        var found = false
        while (!found) {
            var s: ServerSocket?
            s = ServerSocket(0)
            port = s.localPort
            if (port != -1) {
                s.close()
                found = true
            }
        }
        return port
    }

}