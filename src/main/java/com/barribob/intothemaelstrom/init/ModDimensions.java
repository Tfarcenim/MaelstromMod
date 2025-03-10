package com.barribob.intothemaelstrom.init;

import com.barribob.intothemaelstrom.config.ModConfig;
import com.barribob.intothemaelstrom.world.dimension.azure_dimension.DimensionAzure;
import com.barribob.intothemaelstrom.world.dimension.cliff.DimensionCliff;
import com.barribob.intothemaelstrom.world.dimension.crimson_kingdom.DimensionCrimsonKingdom;
import com.barribob.intothemaelstrom.world.dimension.dark_nexus.DimensionDarkNexus;
import com.barribob.intothemaelstrom.world.dimension.nexus.DimensionNexus;
import net.minecraft.world.DimensionType;
import net.minecraftforge.common.DimensionManager;

public class ModDimensions {
    public static final DimensionType AZURE = DimensionType.register("azure", "_azure", ModConfig.world.fracture_dimension_id, DimensionAzure.class, false);
    public static final DimensionType NEXUS = DimensionType.register("nexus", "_nexus", ModConfig.world.nexus_dimension_id, DimensionNexus.class, false);
    public static final DimensionType CLIFF = DimensionType.register("cliff", "_cliff", ModConfig.world.cliff_dimension_id, DimensionCliff.class, false);
    public static final DimensionType DARK_NEXUS = DimensionType.register("dark_nexus", "_dark_nexus", ModConfig.world.dark_nexus_dimension_id, DimensionDarkNexus.class, false);
    public static final DimensionType CRIMSON_KINGDOM = DimensionType.register("crimson_kingdom", "_crimson_kingdom", ModConfig.world.crimson_kingdom_dimension_id, DimensionCrimsonKingdom.class, false);

    public static void registerDimensions() {
        if(!ModConfig.world.disableDimensions) {
            DimensionManager.registerDimension(ModConfig.world.fracture_dimension_id, AZURE);
            DimensionManager.registerDimension(ModConfig.world.nexus_dimension_id, NEXUS);
            DimensionManager.registerDimension(ModConfig.world.cliff_dimension_id, CLIFF);
            DimensionManager.registerDimension(ModConfig.world.dark_nexus_dimension_id, DARK_NEXUS);
            DimensionManager.registerDimension(ModConfig.world.crimson_kingdom_dimension_id, CRIMSON_KINGDOM);
        }
    }
}
