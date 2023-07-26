/**
 * Copyright 2023 Markus Bordihn
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
 * associated documentation files (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge, publish, distribute,
 * sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or
 * substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
 * NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
 * DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package de.markusbordihn.mypersonalapartment;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

import de.markusbordihn.mypersonalapartment.block.ModBlocks;
import de.markusbordihn.mypersonalapartment.client.renderer.ClientRenderer;
import de.markusbordihn.mypersonalapartment.entity.npc.ModEntityType;
import de.markusbordihn.mypersonalapartment.item.ModItems;
import de.markusbordihn.mypersonalapartment.utils.StopModReposts;

@Mod(Constants.MOD_ID)
public class MyPersonalApartment {

  protected static final Logger log = LogManager.getLogger(Constants.LOG_NAME);

  public MyPersonalApartment() {
    final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

    StopModReposts.checkStopModReposts();

    log.info("{} Blocks ...", Constants.LOG_REGISTER_PREFIX);
    ModBlocks.BLOCKS.register(modEventBus);

    log.info("{} Items ...", Constants.LOG_REGISTER_PREFIX);
    ModItems.ITEMS.register(modEventBus);

    log.info("{} Entity Types ...", Constants.LOG_REGISTER_PREFIX);
    ModEntityType.ENTITY_TYPES.register(modEventBus);

    DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
      log.info("{} Client events ...", Constants.LOG_REGISTER_PREFIX);
      // modEventBus.addListener(ModModelLayers::registerEntityLayerDefinitions);
      modEventBus.addListener(ClientRenderer::registerEntityRenderers);
      // modEventBus.addListener(ClientScreens::registerScreens);
      // EasyNPCTab.CREATIVE_TABS.register(modEventBus);
    });
  }

}
