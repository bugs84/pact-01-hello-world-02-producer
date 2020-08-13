Producer sample based on pact file created in consumer sample project


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