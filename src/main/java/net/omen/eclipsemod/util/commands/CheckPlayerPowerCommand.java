package net.omen.eclipsemod.util.commands;

import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.omen.eclipsemod.power.PlayerPowerProvider;

public class CheckPlayerPowerCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("Eclipse")
            .then(Commands.literal("Power")
                .then(Commands.literal("Check")
                    .then(Commands.argument("target", EntityArgument.player())
                        .executes(context -> {
                            ServerPlayer target = EntityArgument.getPlayer(context, "target");
                            return checkPlayerPower(context.getSource(), target);
                        })
                    )
                )
            )
        );
    }

    private static int checkPlayerPower(CommandSourceStack source, ServerPlayer target) {
        target.getCapability(PlayerPowerProvider.ECLIPSE_POWER_CAPABILITY).ifPresent(cap -> {
            String power = cap.getPower();
            source.sendSuccess(() -> Component.literal(target.getName().getString() + "'s current Power is: " + power), false);
        });
        return 1;
    }
}