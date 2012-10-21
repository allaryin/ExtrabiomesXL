/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia;

import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Item;

public class ItemClump extends Item {
	public ItemClump(int id) {
		super(id);
		setTextureFile("/extrabiomes/extrabiomes.png");
		setCreativeTab(CreativeTabs.tabMaterials);
	}
}
