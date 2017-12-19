all: target/*.jar

target/*.jar: pom.xml
	mvn package

test:
	mvn test

clean:
	rm -rf target
	
deploy:
	mvn clean install -P deploy-env