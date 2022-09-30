# CDC - Consumer Driven Contract tests - Usage example of pact.io for test
This is example pact.io tests for "Provider" application.

## Associated projects:
- https://github.com/bugs84/pact-01-hello-world-01-consumer
- https://github.com/bugs84/pact-01-hello-world-02-producer
- https://github.com/bugs84/pact-01-hello-world-pact-broker

sources:
https://github.com/DiUS/pact-jvm/tree/master/provider/junit5




VYMENA PACT FILU:
jak pisou zde:  https://github.com/Mikuu/Pact-JVM-Example#11-example-provider

použít:
https://github.com/pact-foundation/pact_broker
(ukazuje pak i graf)


## Jak to funguje
Zde se nahraje pact file z consumera (v tomto samplu ručně jinak lepší vyměnovat přes pact broker/artifactory/...)
'src/test/resources/pacts/test_consumer-ArticlesProvider.json'

Třída 'OurProvider' představuje Náš provider_server, který poskytuje restové api

Test 'FirstProviderTest'  