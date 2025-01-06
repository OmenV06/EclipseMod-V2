package net.omen.eclipsemod.power.powers;

import com.mojang.datafixers.util.Pair;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientboundLevelParticlesPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.omen.eclipsemod.client.effects.vfx.particle.ParticleRegistry;
import net.omen.eclipsemod.networking.ModPackets;
import net.omen.eclipsemod.networking.packets.SpawnParticlePacketS2C;
import net.omen.eclipsemod.power.AbilityBase;
import net.omen.eclipsemod.power.PowerBase;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BurstPower implements PowerBase {

    @Override
    public void applyPower(ServerPlayer player) {

    }

    @Override
    public void removePower(ServerPlayer player) {

    }

    @Override
    public String getPowerName() {
        return "Burst";
    }

    @Override
    public String getPowerTier() {
        return "B";
    }

    @Override
    public String getPowerDescription() {
        return "Gives the Player the ability to explode on command";
    }

    private static final List<AbilityBase> ABILITIES = List.of(new BurstExplodeAbility(), new BurstOutputAbility());

    @Override
    public List<AbilityBase> getAbilities() {
        return ABILITIES;
    }

    public static class BurstExplodeAbility implements AbilityBase {

        private static final float[] EXPLOSION_RADII = {5.0f, 12.5f, 30.0f};
        private static final float[] DAMAGE_RADII = {5.0f, 10.0f, 25.0f};
        private static final float[] EXPLOSION_DAMAGES = {5.0f, 10.0f, 20.0f};
        private static final int[] CHARGE_TIMES = {20, 50, 120};
        private static final int[] COOLDOWN_TIMES = {60, 150, 360};

        private static final Map<UUID, Integer> chargeUpTicks = new HashMap<>();
        private static final Map<UUID, Pair<Long, Integer>> cooldowns = new HashMap<>();

        public BurstExplodeAbility() {
            MinecraftForge.EVENT_BUS.register(this);
        }

        @Override
        public void executeAbility(ServerPlayer player) {
            var level = player.serverLevel();
            long currentTime = level.getGameTime();
            UUID playerId = player.getUUID();
            int powerLevel = BurstOutputAbility.getCurrentPowerLevel();

            if (isAbilityLocked(player, currentTime)) {
                player.sendSystemMessage(Component.literal("Ability is on cooldown!"));
                return;
            }

            if (!chargeUpTicks.containsKey(playerId)) {
                chargeUpTicks.put(playerId, CHARGE_TIMES[powerLevel]);
                player.sendSystemMessage(Component.literal("Charging explosion..."));
            }
        }

        private static boolean isAbilityLocked(Player player, long currentTime) {
            UUID playerId = player.getUUID();

            if (chargeUpTicks.containsKey(playerId)) {
                return true;
            }

            var cooldownData = cooldowns.get(playerId);
            if (cooldownData != null) {
                long lastUseTime = cooldownData.getFirst();
                int powerLevel = cooldownData.getSecond();
                if (currentTime - lastUseTime < COOLDOWN_TIMES[powerLevel]) {
                    return true;
                }
            }

            return false;
        }

        @SubscribeEvent
        public void onPlayerTick(TickEvent.PlayerTickEvent event) {
            if (event.phase != TickEvent.Phase.START || !(event.player instanceof ServerPlayer player)) return;

            UUID playerId = player.getUUID();
            if (chargeUpTicks.containsKey(playerId)) {
                int ticksLeft = chargeUpTicks.get(playerId) - 1;
                if (ticksLeft <= 0) {
                    chargeUpTicks.remove(playerId);
                    int currentPowerLevel = BurstOutputAbility.getCurrentPowerLevel();
                    performBurstExplosion(player);
                    cooldowns.put(playerId, Pair.of(player.serverLevel().getGameTime(), currentPowerLevel));
                } else {
                    chargeUpTicks.put(playerId, ticksLeft);
                }
            }
        }

        private void performBurstExplosion(ServerPlayer player) {
            var level = player.serverLevel();
            var explosionPosition = player.position();

            int powerLevel = BurstOutputAbility.getCurrentPowerLevel();
            float blockRadius = EXPLOSION_RADII[powerLevel];
            float damageRadius = DAMAGE_RADII[powerLevel];
            float damage = EXPLOSION_DAMAGES[powerLevel];

            Explosion burstExplosion = new Explosion(
                    level,
                    player,
                    explosionPosition.x,
                    explosionPosition.y,
                    explosionPosition.z,
                    blockRadius,
                    false,
                    Explosion.BlockInteraction.DESTROY_WITH_DECAY
            );

            spawnBurstFireball(player);
            spawnBurstFirewave(player);

            burstExplosion.explode();
            burstExplosion.finalizeExplosion(true);

            applyExplosionDamage(player, level, explosionPosition, damageRadius, damage);
        }

        private void applyExplosionDamage(ServerPlayer source, ServerLevel level, Vec3 explosionPosition, float radius, float damage) {
            var aabb = new AABB(
                    explosionPosition.x - radius, explosionPosition.y - radius, explosionPosition.z - radius,
                    explosionPosition.x + radius, explosionPosition.y + radius, explosionPosition.z + radius
            );

            var entities = level.getEntities(source, aabb);

            for (Entity entity : entities) {
                if (entity == source) continue;

                if (entity instanceof LivingEntity livingEntity) {
                    livingEntity.hurt(level.damageSources().explosion(null, source), damage);
                }
            }
        }

        @Override
        public String getAbilityName() {
            return "Detonate";
        }
    }

    public static class BurstOutputAbility implements AbilityBase {
        private static int currentPowerLevel = 0;

        @Override
        public void executeAbility(ServerPlayer player) {
            var level = player.serverLevel();
            long currentTime = level.getGameTime();

            if (BurstExplodeAbility.isAbilityLocked(player, currentTime)) {
                player.sendSystemMessage(Component.literal("Cannot change output while explosion is charging or on cooldown!"));
                return;
            }

            currentPowerLevel = (currentPowerLevel + 1) % 3;
            player.sendSystemMessage(Component.literal("Burst Output: " + (currentPowerLevel + 1)));
        }

        @Override
        public String getAbilityName() {
            return "Change Burst Output";
        }

        public static int getCurrentPowerLevel() {
            return currentPowerLevel;
        }
    }

    private static void spawnBurstFireball(ServerPlayer player) {
        int currentPowerLevel = BurstOutputAbility.getCurrentPowerLevel();

        var particleType = switch (currentPowerLevel) {
            case 0 -> ParticleRegistry.FIREBALL_SMALL.get();
            case 1 -> ParticleRegistry.FIREBALL_MEDIUM.get();
            case 2 -> ParticleRegistry.FIREBALL_LARGE.get();
            default -> ParticleRegistry.FIREBALL_SMALL.get();
        };

        ModPackets.sendToPlayer(new SpawnParticlePacketS2C(
                particleType,
                player.getX(),
                player.getY() + 1.0D,
                player.getZ(),
                0,
                0,
                0
        ), player);
    }

    private static void spawnBurstFirewave(ServerPlayer player) {
        int currentPowerLevel = BurstOutputAbility.getCurrentPowerLevel();

        var particleType = switch (currentPowerLevel) {
            case 0 -> ParticleRegistry.FIREWAVE_SMALL.get();
            case 1 -> ParticleRegistry.FIREWAVE_MEDIUM.get();
            case 2 -> ParticleRegistry.FIREWAVE_LARGE.get();
            default -> ParticleRegistry.FIREWAVE_SMALL.get();
        };

        double posX = player.getX();
        double posY = player.getY();
        double posZ = player.getZ();

        spawnParticlesWithRange(
                particleType,
                player.serverLevel(),
                posX, posY, posZ,
                0f, 0f, 0f,
                1,
                128
        );
    }

    private static void spawnParticlesWithRange(ParticleOptions particleData, ServerLevel world, double posX, double posY, double posZ, float offsetX, float offsetY, float offsetZ, int amount, double maxDistance) {

        Packet<?> packet = new ClientboundLevelParticlesPacket(
                particleData,
                true,
                (float) posX, (float) posY, (float) posZ,
                offsetX, offsetY, offsetZ,
                0,
                amount
        );

        for (ServerPlayer serverPlayer : world.players()) {
            if (serverPlayer.distanceToSqr(posX, posY, posZ) <= maxDistance * maxDistance) {
                serverPlayer.connection.send(packet);
            }
        }
    }

}