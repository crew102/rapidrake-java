all: target/*.jar

target/*.jar: pom.xml
	mvn package

test:
	mvn test

clean:
	rm -rf target

deploy:
	mvn clean deploy -P deploy-env

