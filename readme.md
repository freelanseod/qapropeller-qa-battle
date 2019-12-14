How to run tests:
1 - docker pull selenoid/vnc:chrome_67.0

2 - export SERVICES_HOST=http://localhost

3 - docker run -d -p 8080:8080 qapropeller/qa-battle:latest

4 - ./gradlew clean test -Denv=local or ./gradlew clean test -Denv=ci

PROFIT!


p.s. don't forget that there are several bugs that's why build failed
