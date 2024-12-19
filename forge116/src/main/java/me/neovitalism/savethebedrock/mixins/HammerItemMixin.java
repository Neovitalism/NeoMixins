package me.neovitalism.savethebedrock.mixins;

import com.pixelmonmod.pixelmon.items.HammerItem;
import net.minecraft.block.BlockState;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistries;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(value = HammerItem.class, remap = false)
public class HammerItemMixin {
    @Unique
    private static final ResourceLocation BEDROCK = new ResourceLocation("minecraft", "bedrock");

    @Inject(method = "isCorrectToolForDrops", at = @At(value = "HEAD"), cancellable = true, remap = true)
    public void isCorrectToolForDrops(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (HammerItemMixin.BEDROCK.equals(ForgeRegistries.ITEMS.getKey(state.getBlock().asItem()))) cir.setReturnValue(false);
    }
}
