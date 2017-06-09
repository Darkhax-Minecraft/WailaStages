# Waila Stages [![](http://cf.way2muchnoise.eu/269452.svg)](https://minecraft.curseforge.com/projects/waila-stages) [![](http://cf.way2muchnoise.eu/versions/269452.svg)](https://minecraft.curseforge.com/projects/waila-stages)

Allows access to Waila HUD and specific tool tip info to be restricted based on stages,

[![Nodecraft](https://i.imgur.com/sz9PUmK.png)](https://nodecraft.com/r/darkhax)    
This project is sponsored by Nodecraft. Use code [Darkhax](https://nodecraft.com/r/darkhax) for 30% off your first month of service!

This mod is an addon for the GameStage API. This allows for access to the Waila HUD to be gated behind a stage. You can gate specific tool tip entries behind a stage as well. To learn more about stages, you should check out the GameStage API mod's description. To give a brief rundown, stages are an abstract concept, and players can unlock them. The way players can go about unlocking them is up to the modpack/server. 

To add restrictions, you have to use CraftTweaker. There are two script methods available.

- mods.WailaStages.addWailaStage(String stage); - Replace the stage string with the name of the stage. This will then make it so players require that stage to view the Waila HUD. If multiple stages are added, players will require at least one.
- mods.WailaStages.addRequirement(String stage, String prefix); - Replace the stage string with the name of the stage. Then replace the prefix with with text of the info you want to restrict. If the tooltip line starts with the word Power, adding Power as the prefix will restrict that line to the stage. 