package com.integral.enigmaticlegacy.handlers;

import java.util.ArrayList;
import java.util.Collection;

import com.integral.enigmaticlegacy.EnigmaticLegacy;
import com.integral.enigmaticlegacy.api.items.IHidden;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes;
import mezz.jei.api.recipe.IRecipeManager;
import mezz.jei.api.registration.IModIngredientRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import mezz.jei.api.runtime.IIngredientManager;
import mezz.jei.api.runtime.IJeiRuntime;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

@JeiPlugin
public class EnigmaticJEIPlugin implements IModPlugin {
	private static final ResourceLocation ID = new ResourceLocation(EnigmaticLegacy.MODID, "common_plugin");

	@Override
	public ResourceLocation getPluginUid() {
		return ID;
	}

	@Override
	public void registerIngredients(IModIngredientRegistration registration) {
		//registration.
	}

	@Override
	public void registerRecipes(IRecipeRegistration registration) {
		IIngredientManager manager = registration.getIngredientManager();

		Collection<ItemStack> ingredients = manager.getAllIngredients(VanillaTypes.ITEM_STACK);
		Collection<ItemStack> removals = new ArrayList<>();

		ingredients.forEach(stack -> {
			if (stack != null) {
				if (stack.getItem().getRegistryName().getNamespace().equals("enigmaticlegacy")) {
					if (stack.getItem().getRegistryName().getPath().equals("cosmic_scroll")) {
						removals.add(stack);
					}
				}

				if (stack.getItem() instanceof IHidden) {
					removals.add(stack);
				}
			}
		});

		manager.removeIngredientsAtRuntime(VanillaTypes.ITEM_STACK, removals);
	}

}
