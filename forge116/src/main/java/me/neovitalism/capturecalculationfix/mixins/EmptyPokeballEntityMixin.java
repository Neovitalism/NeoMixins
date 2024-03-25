package me.neovitalism.capturecalculationfix.mixins;

import com.pixelmonmod.pixelmon.entities.pokeballs.EmptyPokeBallEntity;
import com.pixelmonmod.pixelmon.entities.pokeballs.PokeBallEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = EmptyPokeBallEntity.class, remap = false)
public abstract class EmptyPokeballEntityMixin extends PokeBallEntity {
    public EmptyPokeballEntityMixin(EntityType<? extends PokeBallEntity> type, World world) {
        super(type, world);
    }

    @Inject(method={"onHit"}, at={@At(value="INVOKE", target="Lcom/pixelmonmod/pixelmon/entities/pokeballs/EmptyPokeBallEntity;startCapture()V")}, remap = true)
    public void captureCalculationFix$onHit(RayTraceResult rayTraceResult, CallbackInfo ci) {
        this.doCaptureCalc(this.pixelmon);
    }
}
