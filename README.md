# Wordle Game Backend

Jest to REST API, które ma za zadanie dostarczać informacji do warstwy graficznej aplikacji.

## Technologie

- Java 17
- Spring Boot
- Postgreql
- Docker

### Uruchomienie

1. Należy sklonować projekt `git clone git@github.com:Krzykoz/wordle_backend.git`
2. Należy przejść do głównego katalogu w celu zbudowania projektu a następnie wykonać komendę `./mvnw clean install`
3. Uruchamiamy dockera a następnie w tym samym katalogu wpisujemy komendę `docker compose up`
4. Jeżeli wszystko poszło poprawnie w kontenerze powinny być uruchomione 3 obrazy

### Dokumentacja endpointów:

Dokumentacja endpointów dostępna jest pod adresem: https://documenter.getpostman.com/view/4469511/2s93eR3vBF