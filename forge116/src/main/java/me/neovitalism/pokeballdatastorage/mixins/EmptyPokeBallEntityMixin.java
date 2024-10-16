package me.neovitalism.pokeballdatastorage.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.item.pokeball.PokeBall;
import com.pixelmonmod.pixelmon.entities.pokeballs.EmptyPokeBallEntity;
import com.pixelmonmod.pixelmon.entities.pokeballs.PokeBallEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@SuppressWarnings("UnusedMixin")
@Mixin(value = EmptyPokeBallEntity.class, remap = false)
public abstract class EmptyPokeBallEntityMixin extends PokeBallEntity {
    public EmptyPokeBallEntityMixin(EntityType<? extends PokeBallEntity> type, World world) {
        super(type, world);
    }

    @Redirect(method = "onHit", at = @At(value = "INVOKE",
            target = "Lcom/pixelmonmod/pixelmon/api/pokemon/item/pokeball/PokeBall;getBallItem()Lnet/minecraft/item/ItemStack;"),
            remap = true)
    public ItemStack neoMixins$redirectBallItemOnHit(PokeBall instance) {
        ItemStack item = this.getItemFromNBT();
        if(!item.isEmpty()) return item;
        return instance.getBallItem();
    }

    @Inject(method = "onHit", at = @At(value = "INVOKE",
            target = "Lcom/pixelmonmod/pixelmon/entities/pokeballs/EmptyPokeBallEntity;setPairedEntity(Lnet/minecraft/entity/Entity;)V"),
            remap = true)
    public void neoMixins$resetDropItem(RayTraceResult movingObjectPosition, CallbackInfo ci) {
        if (this.getOwner() instanceof PlayerEntity) {
            this.dropItem = !((PlayerEntity)this.getOwner()).isCreative();
        } else this.dropItem = true;
    }

    @Redirect(method = "catchPokemon", at = @At(value = "INVOKE",
            target = "Lcom/pixelmonmod/pixelmon/api/pokemon/item/pokeball/PokeBall;getBallItem()Lnet/minecraft/item/ItemStack;"))
    public ItemStack neoMixins$redirectBallItemOnCatch(PokeBall instance) {
        ItemStack item = this.getItemFromNBT();
        if(!item.isEmpty()) return item;
        return instance.getBallItem();
    }

    @Override
    public ItemStack[] getComponentItems() {
        CompoundNBT itemNBT = this.getPersistentData().getCompound("item");
        if (itemNBT.getCompound("tag").getBoolean("Unbreakable")) return new ItemStack[]{ItemStack.of(itemNBT)};
        return super.getComponentItems();
    }

    @Override
    public UUID getOwnerId() {
        return this.thrower.getUUID();
    }

    @Unique
    public ItemStack getItemFromNBT() {
        CompoundNBT itemNBT = this.getPersistentData().getCompound("item");
        return ItemStack.of(itemNBT);
    }
}
