package com.barribob.intothemaelstrom.commands;

import com.barribob.intothemaelstrom.IntoTheMaelstrom;
import com.barribob.intothemaelstrom.test.InGameTests;
import com.google.common.collect.Lists;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.text.TextComponentTranslation;

import java.util.List;

public class CommandRunUnitTests extends CommandBase {
    @Override
    public String getName() {
        return "test";
    }

    @Override
    public String getUsage(ICommandSender sender) {
        return "test <testName>";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 2;
    }

    @Override
    public List<String> getAliases() {
        return Lists.newArrayList("unitTest", "tests", "unitTests");
    }

    @Override
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        try {
            if (args.length == 0) {
                InGameTests.runAllTests(server, sender);
            } else {
                InGameTests.runSingleTest(server, sender, args[0]);
            }
            sender.sendMessage(new TextComponentTranslation("intothemaelstrom.test_success"));
        } catch (Exception e) {
            IntoTheMaelstrom.log.error("Test case failed: ");
            e.printStackTrace();
            throw new CommandException("intothemaelstrom.unit_test_failed");
        }
    }
}