/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia.block;

import java.util.List;
import java.util.Random;

import net.minecraft.src.AxisAlignedBB;
import net.minecraft.src.Block;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.IBlockAccess;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.Material;
import net.minecraft.src.StatList;
import net.minecraft.src.World;
import cpw.mods.fml.common.Side;
import cpw.mods.fml.common.asm.SideOnly;

public class BlockGrit extends Block {

	public enum BlockType {
		GRIT(0, "Grit"), DUST(1, "Dust");

		private final int		value;
		private final String	itemName;

		BlockType(int value, String itemName) {
			this.value = value;
			this.itemName = itemName;
		}

		public String itemName() {
			return itemName;
		}

		public int metadata() {
			return value;
		}
	}

	public BlockGrit(int id) {
		super(id, 65, Material.sand);
		setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 0.015625F, 1.0F);
		setTickRandomly(true);
		setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(CreativeTabs.tabDecorations);
		setLightOpacity(0);
	}

	private boolean canGritStay(World world, int x, int y, int z) {
		if (!canPlaceBlockAt(world, x, y, z)) {
			world.setBlockWithNotify(x, y, z, 0);
			return false;
		} else
			return true;
	}

	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		final int id = world.getBlockId(x, y - 1, z);
		final Block block = Block.blocksList[id];
		return block != null
				&& (block.isLeaves(world, x, y - 1, z) || Block.blocksList[id]
						.isOpaqueCube()) ? world.getBlockMaterial(x,
				y - 1, z).blocksMovement() : false;
	}

	@Override
	protected int damageDropped(int metadata) {
		return metadata;
	}

	@Override
	public int getBlockTextureFromSideAndMetadata(int side, int metadata)
	{
		return blockIndexInTexture + metadata;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world,
			int x, int y, int z)
	{
		return null;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(int id, CreativeTabs tab, List itemList) {
		for (final BlockType blockType : BlockType.values())
			itemList.add(new ItemStack(this, 1, blockType.metadata()));
	}

	@Override
	public void harvestBlock(World world, EntityPlayer player, int x,
			int y, int z, int metadata)
	{
		dropBlockAsItem(world, x, y, z, metadata, 0);
		player.addStat(StatList.mineBlockStatArray[blockID], 1);
	}

	@Override
	public int idDropped(int par1, Random par2Random, int par3) {
		return Item.snowball.shiftedIndex;
	}

	@Override
	public boolean isBlockReplaceable(World world, int x, int y, int z)
	{
		return true;
	}

	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z,
			int neighborID)
	{
		canGritStay(world, x, y, z);
	}

	@Override
	public int quantityDropped(Random par1Random) {
		return 1;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean shouldSideBeRendered(IBlockAccess world, int x,
			int y, int z, int side)
	{
		return side == 1 ? true : super.shouldSideBeRendered(world, x,
				y, z, side);
	}

	@Override
	public void updateTick(World world, int x, int y, int z,
			Random random)
	{
		// if (world.getSavedLightValue(EnumSkyBlock.Block, x, y, z) >
		// 11)
		// world.setBlockWithNotify(x, y, z, 0);
	}
}
