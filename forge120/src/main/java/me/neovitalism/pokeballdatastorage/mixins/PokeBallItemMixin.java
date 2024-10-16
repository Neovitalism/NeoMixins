package me.neovitalism.pokeballdatastorage.mixins;

import com.pixelmonmod.pixelmon.api.events.ThrowPokeballEvent;
import com.pixelmonmod.pixelmon.api.pokemon.item.pokeball.PokeBall;
import com.pixelmonmod.pixelmon.battles.controller.participants.PixelmonWrapper;
import com.pixelmonmod.pixelmon.entities.pokeballs.EmptyPokeBallEntity;
import com.pixelmonmod.pixelmon.entities.pokeballs.PokeBallEntity;
import com.pixelmonmod.pixelmon.items.PixelmonItem;
import com.pixelmonmod.pixelmon.items.PokeBallItem;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.Optional;

@SuppressWarnings({"OptionalUsedAsFieldOrParameterType", "UnusedMixin", "OptionalGetWithoutIsPresent"})
@Mixin(value = PokeBallItem.class, remap = false)
public abstract class PokeBallItemMixin extends PixelmonItem {
    public PokeBallItemMixin(Properties properties) {
        super(properties);
    }

    @Inject(method = "use", at = @At(value = "INVOKE", target =
            "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"),
            locals = LocalCapture.CAPTURE_FAILSOFT, remap = true, cancellable = true)
    public void neoMixins$use(Level world, Player entityPlayer, InteractionHand handIn,
                              CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir, ItemStack itemStack,
                              Optional<PokeBall> pokeBall) {
        EmptyPokeBallEntity entity = new EmptyPokeBallEntity(world, entityPlayer, pokeBall.get(), !entityPlayer.isCreative());
        this.setBallInNBT(entity, itemStack);
        world.addFreshEntity(entity);
        if (!entityPlayer.isCreative()) {
            itemStack.shrink(1);
            entityPlayer.getInventory().setChanged();
        }
        cir.setReturnValue(new InteractionResultHolder<>(InteractionResult.SUCCESS, itemStack));
    }

    @Inject(method = "useFromBag", at = @At(value = "INVOKE", target =
            "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"),
            locals = LocalCapture.CAPTURE_FAILSOFT)
    public void neoMixins$useFromBag(PixelmonWrapper userWrapper, PixelmonWrapper targetWrapper, ItemStack stack,
                                     CallbackInfoReturnable<Boolean> cir, Player thrower, Optional<PokeBall> pokeBall,
                                     ThrowPokeballEvent throwPokeballEvent, Level world, PokeBallEntity p) {
        this.setBallInNBT((EmptyPokeBallEntity) p, stack);
    }

    @Unique
    private void setBallInNBT(EmptyPokeBallEntity entity, ItemStack ballItem) {
        ItemStack clonedItem = ballItem.copy();
        clonedItem.setCount(1);
        entity.getPersistentData().put("item", clonedItem.serializeNBT());
    }
}
