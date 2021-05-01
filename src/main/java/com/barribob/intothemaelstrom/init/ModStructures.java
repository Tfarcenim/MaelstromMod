package com.barribob.intothemaelstrom.init;

import com.barribob.intothemaelstrom.world.gen.cliff.HoleTempleTemplate;
import com.barribob.intothemaelstrom.world.gen.cliff.MapGenHoleTemple;
import com.barribob.intothemaelstrom.world.gen.golden_ruins.MapGenGoldenRuins;
import com.barribob.intothemaelstrom.world.gen.golden_ruins.RuinsTemplate;
import com.barribob.intothemaelstrom.world.gen.maelstrom_fortress.FortressTemplate;
import com.barribob.intothemaelstrom.world.gen.maelstrom_fortress.MapGenMaelstromFortress;
import com.barribob.intothemaelstrom.world.gen.maelstrom_stronghold.MapGenMaelstromStronghold;
import com.barribob.intothemaelstrom.world.gen.maelstrom_stronghold.StrongholdTemplate;
import com.barribob.intothemaelstrom.world.gen.mineshaft.AzureMineshaftTemplate;
import com.barribob.intothemaelstrom.world.gen.mineshaft.MapGenAzureMineshaft;
import net.minecraft.world.gen.structure.MapGenStructureIO;

public class ModStructures {
    public static void registerStructures() {
        MapGenStructureIO.registerStructure(MapGenAzureMineshaft.Start.class, "Azure Mineshaft");
        MapGenStructureIO.registerStructureComponent(AzureMineshaftTemplate.class, "AMP");
        MapGenStructureIO.registerStructure(MapGenMaelstromFortress.Start.class, "Maelstrom Fortress");
        MapGenStructureIO.registerStructureComponent(FortressTemplate.class, "MFP");
        MapGenStructureIO.registerStructure(MapGenMaelstromStronghold.Start.class, "Maelstrom Stronghold");
        MapGenStructureIO.registerStructureComponent(StrongholdTemplate.class, "MSP");
        MapGenStructureIO.registerStructure(MapGenGoldenRuins.Start.class, "Golden Ruins");
        MapGenStructureIO.registerStructureComponent(RuinsTemplate.class, "GRP");
        MapGenStructureIO.registerStructure(MapGenHoleTemple.Start.class, "Hole Temple");
        MapGenStructureIO.registerStructureComponent(HoleTempleTemplate.class, "HTP");
    }
}
