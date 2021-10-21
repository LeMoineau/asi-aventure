
all : main tests

main :
	javac -classpath ./classes/ -sourcepath ./src/ -d ./classes/ $(shell find ./src/ -name *.java)

run :
	java -classpath classes fr.insarouen.asi.prog.asiaventure.Main

doc :
	javadoc -d doc -sourcepath src -subpackages fr.insarouen.asi.prog.asiaventure

tests :
	javac -classpath /usr/share/java/junit4.jar:classes/:classestest/ -sourcepath ./srctest/ -d classestest $(shell find ./srctest/ -name *.java) 

run-tests :
	java -classpath /usr/share/java/junit4.jar:classes/:classestest/ org.junit.runner.JUnitCore fr.insarouen.asi.prog.asiaventure.AllTests

clean :
	rm -rf classes/*
	rm -rf doc
	rm -rf classestest/*
