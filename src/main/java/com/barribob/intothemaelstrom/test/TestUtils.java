package com.barribob.intothemaelstrom.test;

import com.barribob.intothemaelstrom.IntoTheMaelstrom;
import com.typesafe.config.Config;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.nbt.NBTTagCompound;

public class TestUtils {
    public static final String getMobString(Entity entity) {
        String mobString = "Name: " + entity.getDisplayName();
        if(entity instanceof EntityLivingBase) {
            NBTTagCompound compound = new NBTTagCompound();
            ((EntityLivingBase)entity).writeEntityToNBT(compound);
            mobString += "\nNBT: " + compound;
        }

        return mobString;
    }

    public static Config testingConfig() {
        return IntoTheMaelstrom.CONFIG_MANAGER.loadDefaultConfig("testing");
    }

    public static void AssertEquals(Object expected, Object actual) throws Exception {
        if (expected == null && actual == null) {
            return;
        }
        if (expected == null || !expected.equals(actual)) {
            throw new Exception("Expected " + expected + " Got " + actual);
        }
    }

    public static void AssertAlmostEquals(Double expected, Double actual, double precision) throws Exception {
        if (Math.abs(expected - actual) < 1.5 * Math.pow(10, -precision)) {
            return;
        }
        throw new Exception("Expected " + expected + " to be almost equal to " + actual);
    }

    public static void AssertTrue(boolean value, String message) throws Exception {
        if(!value) {
            throw new Exception(message);
        }
    }
}
