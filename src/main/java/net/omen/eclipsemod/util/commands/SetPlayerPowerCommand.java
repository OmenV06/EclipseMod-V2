package net.omen.eclipsemod.util.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.Commands;
import net.minecraft.commands.arguments.EntityArgument;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.omen.eclipsemod.power.PlayerPowerProvider;
import net.omen.eclipsemod.power.PowerPool;
import net.omen.eclipsemod.power.powers.EmptyPower;

import java.util.stream.Collectors;

public class SetPlayerPowerCommand {

    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(Commands.literal("Eclipse")
            .then(Commands.literal("Power")
                .then(Commands.literal("Set")
                    .then(Commands.argument("target", EntityArgument.player())
                        .then(Commands.argument("power", StringArgumentType.word())
                            .suggests((context, builder) -> {
                                // Suggest all registered powers including EmptyPower
                                PowerPool.REGISTERED_POWERS.keySet().forEach(builder::suggest);
                                return builder.buildFuture();
                            })
                            .executes(context -> {
                                ServerPlayer target = EntityArgument.getPlayer(context, "target");
                                String powerName = StringArgumentType.getString(context, "power");
                                return setPlayerPower(context.getSource(), target, powerName);
                            })
                        )
                    )
                )
            )
        );
    }

    private static int setPlayerPower(CommandSourceStack source, ServerPlayer target, String powerName) {
        if (!PowerPool.REGISTERED_POWERS.containsKey(powerName)) {
            source.sendFailure(Component.literal("Invalid Power: " + powerName));
            return 0;
        }

        target.getCapability(PlayerPowerProvider.ECLIPSE_POWER_CAPABILITY).ifPresent(cap -> {
            // Remove the current power's effects
            PowerPool.REGISTERED_POWERS.getOrDefault(cap.getPower(), new EmptyPower()).removePower(target);

            // Set the new power and apply its effects
            cap.setPower(powerName);
            PowerPool.REGISTERED_POWERS.get(powerName).applyPower(target);
            source.sendSuccess(() -> Component.literal(target.getName().getString() + "'s Power set to: " + powerName), true);
        });
        return 1;
    }
}