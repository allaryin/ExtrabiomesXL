/**
 * This work is licensed under the Creative Commons
 * Attribution-ShareAlike 3.0 Unported License. To view a copy of this
 * license, visit http://creativecommons.org/licenses/by-sa/3.0/.
 */

package extrabiomes.module.cautia;

import com.google.common.base.Optional;

import extrabiomes.Extrabiomes;
import extrabiomes.IModule;
import extrabiomes.api.Stuff;
import extrabiomes.configuration.ExtrabiomesConfig;
import extrabiomes.module.cautia.block.BlockManager;
import extrabiomes.module.fabrica.scarecrow.ItemScarecrow;

public class Cautia implements IModule {

	private int dirtClodID = 0;
	private int sandClumpID = 0; 
	
	@Override
	public void init() throws InstantiationException,
			IllegalAccessException
	{
		BlockManager.init();
		if (dirtClodID > 0) {
			Stuff.dirtClod = Optional
					.of(new ItemClump(dirtClodID)
							.setItemName("extrabiomes.dirtclod").setIconIndex(93));

			Extrabiomes.proxy.addName(Stuff.dirtClod.get(),
					"Dirt Clod");

		}
		if (sandClumpID > 0) {
			Stuff.sandClump = Optional
					.of(new ItemClump(sandClumpID)
							.setItemName("extrabiomes.sandclump").setIconIndex(94));

			Extrabiomes.proxy.addName(Stuff.sandClump.get(),
					"Sand Clump");

		}
	}

	@Override
	public void preInit(ExtrabiomesConfig config)
			throws InstantiationException, IllegalAccessException
	{
		BlockManager.preInit(config);
		dirtClodID = config.getItem("dirtclod.id",
				Extrabiomes.getNextDefaultItemID()).getInt(0);
		sandClumpID = config.getItem("sandclump.id",
				Extrabiomes.getNextDefaultItemID()).getInt(0);
	}

}
