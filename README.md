# app.suite-hr

build:
    -local: mvn clean install
    -staging: mvn clean install -P staging
    -production: mvn clean install -P production

deploy:
    -staging: 
		-copy suite-hr*.jar under /home/projects/suitehr/be (remove previous jar if it exists)
		-execute java -jar suite-hr*.jar &
    -production: 
		-copy suite-hr*.jar under /home/projects/suitehr/be (remove previous jar if it exists)
		-execute java -jar suite-hr*.jar &