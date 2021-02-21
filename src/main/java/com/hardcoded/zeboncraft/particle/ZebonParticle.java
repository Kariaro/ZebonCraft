package com.hardcoded.zeboncraft.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ZebonParticle extends SpriteTexturedParticle {
	protected ZebonParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ) {
		super(world, x, y, z, motionX, motionY, motionZ);
		
		float f = rand.nextFloat() * 1.0f;
		particleRed = f;
		particleGreen = f;
		particleBlue = f;
		
		this.setSize(0.025f, 0.025f);
		this.particleScale *= rand.nextFloat() * 1.1;
		this.motionX *= 0.25;
		this.motionY *= 0.25;
		this.motionZ *= 0.25;
		this.maxAge = (int)(40);
	}
	
	public IParticleRenderType getRenderType() {
		return IParticleRenderType.PARTICLE_SHEET_OPAQUE;
	}
	
	public void tick() {
		prevPosX = posX;
		prevPosY = posY;
		prevPosZ = posZ;
		
		if(age++ >= maxAge) {
			setExpired();
		} else {
			move(motionX, motionY, motionZ);
			
			particleScale = (float)((maxAge - age) / (maxAge + 0.0)) * 0.2f;
			motionX *= 1.0f;
			motionX *= 1.0f;
			motionX *= 1.0f;
		}
	}
	
	@OnlyIn(Dist.CLIENT)
	public static final class Factory implements IParticleFactory<BasicParticleType> {
		private final IAnimatedSprite spriteSet;
		public Factory(IAnimatedSprite sprite) {
			this.spriteSet = sprite;
		}
		
		public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
			ZebonParticle particle = new ZebonParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
			particle.selectSpriteRandomly(spriteSet);
			return particle;
		}
	}
}
