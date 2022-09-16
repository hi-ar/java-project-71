 run-dist:
	./build/install/app/bin/app

 build-test-linter:
	./gradlew build test checkstyleMain checkstyleTest

 say-hello:
	echo 'Hello, World!'

 report:
	./gradlew jacocoTestReport

 .PHONY:
	build