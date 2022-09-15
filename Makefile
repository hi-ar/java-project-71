run-dist:
    ./build/install/app/bin/app

build:
	./gradlew build

.PHONY:
    build

say-hello:
	echo 'Hello, World!'