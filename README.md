# Elytra/Chestplate Swapper 2

A simple quality of life mod that swaps between chestplate and elytra on a keystroke.

## How to use it

The default key is "grave accent" ("²" on the AZERTY layout) but it can be changed in the options of the game.  
It can be triggered outside of the inventory and inside too. 

## Requirements

This mod requires the Fabric API

## Disclaimer

Some public servers do not allow this kind of mods and can detect them if it is used in some circumstances (spam the key, swap while pressing a movement key). You should check the server's rules before using it on a public server. I am not responsible for the consequences of inappropriate use of this mod.

## Development

Feel free to contribute to this project!  

### Setup

Refer to the [documentation](https://docs.fabricmc.net/) for more information on how to set up a development environment.  
- IntelliJ IDEA: Use `Import Gradle Project`
- VSCode: Use `./gradlew vscode` to generate the necessary files

### Running

- IntelliJ IDEA: [Use the documentation](https://docs.fabricmc.net/develop/getting-started/launching-the-game)
- VSCode: Use the `Minecraft Client` task in the `Run and Debug` sidebar
- Command Line: Use `./gradlew runClient` to start the game with the mod loaded


### Building

- Command Line: Use `./gradlew remapJar` to build the mod jar file. The output will be in `build/libs/` directory.