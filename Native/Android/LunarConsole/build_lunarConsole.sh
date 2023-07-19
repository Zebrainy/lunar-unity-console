#!/bin/bash -e

./gradlew clean -p lunarConsole
./gradlew assembleRelease -p lunarConsole

cp ./lunarConsole/build/outputs/aar/lunarConsole-free-release.aar ./lunarConsole-free-release.aar