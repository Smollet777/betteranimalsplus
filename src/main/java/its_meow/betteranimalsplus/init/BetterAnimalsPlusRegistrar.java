package its_meow.betteranimalsplus.init;

import its_meow.betteranimalsplus.Ref;
import its_meow.betteranimalsplus.common.entity.projectile.EntityBadgerDirt;
import its_meow.betteranimalsplus.common.entity.projectile.EntityPheasantEgg;
import its_meow.betteranimalsplus.common.entity.projectile.EntityTarantulaHair;
import its_meow.betteranimalsplus.common.entity.projectile.EntityTurkeyEgg;
import its_meow.betteranimalsplus.common.item.ItemAdvancementIcon;
import its_meow.betteranimalsplus.common.item.ItemBetterAnimalsPlusEgg;
import its_meow.betteranimalsplus.common.item.ItemBlockSimple;
import its_meow.betteranimalsplus.util.EntityTypeContainer;
import its_meow.betteranimalsplus.util.HeadTypes;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Ref.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BetterAnimalsPlusRegistrar {

    /*
     * Blocks
     */
    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        final IForgeRegistry<Block> registry = event.getRegistry();

        registry.registerAll(
        ModBlocks.TRILLIUM,
        ModBlocks.HAND_OF_FATE,
        ModBlocks.TURKEY_RAW,
        ModBlocks.TURKEY_COOKED
        );

        for (HeadTypes type : HeadTypes.values()) {
            registry.registerAll(type.getBlockSet().toArray(new Block[0]));
        }
    }

    /*
     * Items
     */
    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();

        // ItemBlocks
        registry.registerAll(
        new ItemBlockSimple(ModBlocks.TRILLIUM),
        new ItemBlockSimple(ModBlocks.HAND_OF_FATE),
        new ItemBlockSimple(ModBlocks.TURKEY_RAW),
        new ItemBlockSimple(ModBlocks.TURKEY_COOKED)
        );
        
        // Heads
        for (HeadTypes type : HeadTypes.values()) {
            registry.registerAll(type.getItemSet().toArray(new Item[0]));
        }
        
        // Items
        registry.registerAll(
        ModItems.VENISON_RAW,
        ModItems.VENISON_COOKED,
        ModItems.HIRSCHGEIST_SKULL_WEARABLE,
        ModItems.ANTLER,
        ModItems.GOAT_MILK,
        ModItems.GOAT_CHEESE,
        ModItems.PHEASANT_RAW,
        ModItems.PHEASANT_COOKED,
        ModItems.CRAB_MEAT_RAW,
        ModItems.CRAB_MEAT_COOKED,
        ModItems.WOLF_PELT_SNOWY,
        ModItems.WOLF_PELT_TIMBER,
        ModItems.WOLF_PELT_BLACK, 
        ModItems.WOLF_CAPE_CLASSIC,
        ModItems.WOLF_CAPE_TIMBER,
        ModItems.WOLF_CAPE_BLACK,
        ModItems.RECORD_CRAB_RAVE,
        ModItems.BEAR_SKIN_BROWN,
        ModItems.BEAR_SKIN_BLACK,
        ModItems.BEAR_SKIN_KERMODE,
        ModItems.BEAR_CAPE_BROWN,
        ModItems.BEAR_CAPE_BLACK,
        ModItems.BEAR_CAPE_KERMODE,
        ModItems.PHEASANT_EGG,
        ModItems.TURKEY_EGG,
        new ItemAdvancementIcon("advancement_icon_jellyfish"),
        new ItemAdvancementIcon("advancement_icon_jellyfish_cross"),
        new ItemAdvancementIcon("advancement_icon_goat"), 
        new ItemAdvancementIcon("advancement_icon_shark"),
        new ItemAdvancementIcon("advancement_icon_lamprey"),
        new ItemAdvancementIcon("advancement_icon_squirrel"),
        new ItemAdvancementIcon("advancement_icon_badger")
        );

        // Eggs
        for (EntityTypeContainer<?> container : ModEntities.ENTITIES.values()) {
            ItemBetterAnimalsPlusEgg egg = new ItemBetterAnimalsPlusEgg(container);
            egg.setRegistryName(container.entityName.toLowerCase().toString() + "_spawn_egg");
            event.getRegistry().register(egg);
            container.egg = egg;
        }
    }

    @SubscribeEvent
    public static void registerSounds(final RegistryEvent.Register<SoundEvent> event) {
        event.getRegistry().register(ModSoundEvents.CRAB_RAVE.setRegistryName(new ResourceLocation(Ref.MOD_ID, "crabrave")));
    }

    /*
     * Tile Entities
     */
    @SubscribeEvent
    public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
        final IForgeRegistry<TileEntityType<?>> reg = event.getRegistry();
        registerTE(reg, ModTileEntities.TRILLIUM_TYPE);
        registerTE(reg, ModTileEntities.HAND_OF_FATE_TYPE);
        registerTE(reg, ModTileEntities.HEAD_TYPE);
    }

    /*
     * Entities
     */
    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        final IForgeRegistry<EntityType<?>> registry = event.getRegistry();

        registry.register(EntityTarantulaHair.HAIR_TYPE);
        registry.register(EntityBadgerDirt.DIRT_TYPE);
        registry.register(EntityPheasantEgg.PHEASANT_EGG_TYPE);
        registry.registerAll(EntityTurkeyEgg.TURKEY_EGG_TYPE);

        for(EntityTypeContainer<?> container : ModEntities.ENTITIES.values()) {
            event.getRegistry().register(container.entityType);
        }
    }

    private static void registerTE(IForgeRegistry<TileEntityType<?>> reg, TileEntityType<?> type) {
        reg.register(type);
    }

}
