package net.omen.eclipsemod.client.effects.vfx.particle.firewave;

import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class FirewaveLargeParticle extends TextureSheetParticle {

    private final SpriteSet sprites;

    protected FirewaveLargeParticle(ClientLevel level, double x, double y, double z, double dx, double dy, double dz, SpriteSet sprites) {
        super(level, x, y, z, dx, dy, dz);
        this.sprites = sprites;

        this.xd = dx;
        this.yd = dy;
        this.zd = dz;
        this.quadSize *= 250.0F;
        this.lifetime = 15;
        this.setSpriteFromAge(sprites);
    }

    @Override
    public void render(VertexConsumer vertexConsumer, Camera camera, float partialTicks) {
        Vec3 cameraPos = camera.getPosition();
        float x = (float) (Mth.lerp(partialTicks, this.xo, this.x) - cameraPos.x);
        float y = (float) (Mth.lerp(partialTicks, this.yo, this.y) - cameraPos.y) + 0.01f;
        float z = (float) (Mth.lerp(partialTicks, this.zo, this.z) - cameraPos.z);

        Quaternionf rotation = new Quaternionf().identity();
        rotation.rotateX((float) Math.toRadians(90));

        Vec3[] vertices = new Vec3[]{
                new Vec3(-0.5, -0.5, 0.0),
                new Vec3(0.5, -0.5, 0.0),
                new Vec3(0.5, 0.5, 0.0),
                new Vec3(-0.5, 0.5, 0.0)
        };

        float size = this.getQuadSize(partialTicks);

        for (int i = 0; i < vertices.length; ++i) {
            Vec3 vertex = vertices[i];

            Vector3f vector = new Vector3f((float) vertex.x, (float) vertex.y, (float) vertex.z);
            vector.rotate(rotation);
            vertices[i] = new Vec3(vector.x(), vector.y(), vector.z()).scale(size).add(x, y, z);
        }

        int light = this.getLightColor(partialTicks);
        float u0 = this.getU0();
        float u1 = this.getU1();
        float v0 = this.getV0();
        float v1 = this.getV1();

        vertexConsumer.vertex(vertices[0].x, vertices[0].y, vertices[0].z).uv(u1, v1).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        vertexConsumer.vertex(vertices[1].x, vertices[1].y, vertices[1].z).uv(u1, v0).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        vertexConsumer.vertex(vertices[2].x, vertices[2].y, vertices[2].z).uv(u0, v0).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        vertexConsumer.vertex(vertices[3].x, vertices[3].y, vertices[3].z).uv(u0, v1).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();

        vertexConsumer.vertex(vertices[3].x, vertices[3].y, vertices[3].z).uv(u0, v1).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        vertexConsumer.vertex(vertices[2].x, vertices[2].y, vertices[2].z).uv(u0, v0).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        vertexConsumer.vertex(vertices[1].x, vertices[1].y, vertices[1].z).uv(u1, v0).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
        vertexConsumer.vertex(vertices[0].x, vertices[0].y, vertices[0].z).uv(u1, v1).color(this.rCol, this.gCol, this.bCol, this.alpha).uv2(light).endVertex();
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.removed) {
            this.setSpriteFromAge(sprites);
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @Override
    public int getLightColor(float partialTicks) {
        return 0xF000F0;
    }

    @OnlyIn(Dist.CLIENT)
    public static class FirewaveLargeParticleFactory implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public FirewaveLargeParticleFactory(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(SimpleParticleType type, ClientLevel level, double x, double y, double z, double dx, double dy, double dz) {
            return new FirewaveLargeParticle(level, x, y, z, dx, dy, dz, spriteSet);
        }
    }

}
