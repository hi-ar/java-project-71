 run-dist:
	./build/install/app/bin/app

 build-test-linter:
	./gradlew build
	./gradlew test
	./gradlew checkstyleMain
	./gradlew checkstyleTest

 say-hello:
	echo 'Hello, World!'

 .PHONY:
	build