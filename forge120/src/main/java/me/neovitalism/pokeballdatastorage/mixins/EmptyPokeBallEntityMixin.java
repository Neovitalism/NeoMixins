package me.neovitalism.pokeballdatastorage.mixins;

import com.pixelmonmod.pixelmon.api.pokemon.item.pokeball.PokeBall;
import com.pixelmonmod.pixelmon.entities.pokeballs.EmptyPokeBallEntity;
import com.pixelmonmod.pixelmon.entities.pokeballs.PokeBallEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.UUID;

@SuppressWarnings("UnusedMixin")
@Mixin(value = EmptyPokeBallEntity.class, remap = false)
public abstract class EmptyPokeBallEntityMixin extends PokeBallEntity {
    public EmptyPokeBallEntityMixin(EntityType<? extends PokeBallEntity> type, Level world) {
        super(type, world);
    }

    @Redirect(method = "onHit", at = @At(value = "INVOKE",
            target = "Lcom/pixelmonmod/pixelmon/api/pokemon/item/pokeball/PokeBall;getBallItem()Lnet/minecraft/world/item/ItemStack;"),
            remap = true)
    public ItemStack neoMixins$redirectBallItemOnHit(PokeBall instance) {
        ItemStack item = this.getItemFromNBT();
        if(!item.isEmpty()) return item;
        return instance.getBallItem();
    }

    @Redirect(method = "catchPokemon", at = @At(value = "INVOKE",
            target = "Lcom/pixelmonmod/pixelmon/api/pokemon/item/pokeball/PokeBall;getBallItem()Lnet/minecraft/world/item/ItemStack;"))
    public ItemStack neoMixins$redirectBallItemOnCatch(PokeBall instance) {
        ItemStack item = this.getItemFromNBT();
        if(!item.isEmpty()) return item;
        return instance.getBallItem();
    }

    @Override
    public ItemStack[] getComponentItems() {
        CompoundTag itemNBT = this.getPersistentData().getCompound("item");
        if (itemNBT.getCompound("tag").getBoolean("Unbreakable")) return new ItemStack[]{ItemStack.of(itemNBT)};
        return super.getComponentItems();
    }

    @Override
    public UUID getOwnerId() {
        return this.thrower.getUUID();
    }

    @Unique
    public ItemStack getItemFromNBT() {
        CompoundTag itemNBT = this.getPersistentData().getCompound("item");
        return ItemStack.of(itemNBT);
    }
}
