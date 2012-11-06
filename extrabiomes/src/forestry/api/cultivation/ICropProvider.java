package forestry.api.cultivation;

import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public abstract interface ICropProvider {
	public abstract boolean isGermling(ItemStack germling);

	public abstract boolean isCrop(World world, int x, int y, int z);

	public abstract ItemStack[] getWindfall();

	public abstract boolean doPlant(ItemStack germling, World world, int x, int y, int z);

	public abstract ICropEntity getCrop(World world, int x, int y, int z);
}