build:
	mvn clean package
	cp ./target/PSI-1.0-SNAPSHOT.jar ./psi_1-0-1.jar

run:
	LD_LIBRARY_PATH=$(PWD) java -jar ./psi1-0-1.jar Server CompanyA.csv 8000 &
	sleep 0.5
	LD_LIBRARY_PATH=$(PWD) java -jar ./psi1-0-1.jar Client CompanyB.csv 127.0.0.1 8000

clean:
	rm -rf ./classpath
	rm -rf ./project
	rm -rf ./target
	rm -rf ./psi_demo.jar

.PHONY: build run clean
