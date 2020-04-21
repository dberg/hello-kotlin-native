# Hello Kotlin Native

An experiment with kotlin native and ncurses in macos.

![demo](docs/demo.gif)

## Development

### Setup

```
brew install kotlin
brew cask install kotlin-native
brew install ncurses
```

Building

```bash
./gradlew build
```

Running

```bash
./build/bin/macos/releaseExecutable/hello-kotlin-native.kexe
```

# Issues

The issue `MissingXcodeException`

```
e: org.jetbrains.kotlin.konan.MissingXcodeException: An error occurred during an xcrun execution. Make sure that Xcode and its command line tools are properly installed.
```

This was resolved following the procedure found [here](https://stackoverflow.com/questions/40743713/command-line-tool-error-xcrun-error-unable-to-find-utility-xcodebuild-n).

MacOS blocked opening the dylibs.

```bash
open /usr/local/Caskroom/kotlin-native/1.3.61/kotlin-native-macos-1.3.61/konan/nativelib
```

Enabled under Settings -> Security & Privacy.

# Misc

Just a few notes for future reference.

## cinterop

Running cinterop manually

```bash
cinterop -def ./src/nativeInterop/cinterop/ncurses.def \
  -compiler-option -I/usr/local/opt/ncurses/include -o ncurses
```

## ncurses

ncurses flags

```bash
export LDFLAGS="-L/usr/local/opt/ncurses/lib"
export CPPFLAGS="-I/usr/local/opt/ncurses/include"
```
