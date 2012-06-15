package me.simplex.pluginkickstarter.storage;

import java.util.ArrayList;

import me.simplex.pluginkickstarter.util.ListenerType;

public class ListenerData {
	private ArrayList<ListenerContainer> listener_Block;
	private ArrayList<ListenerContainer> listener_Player;
	private ArrayList<ListenerContainer> listener_Entity;
	private ArrayList<ListenerContainer> listener_World;
	private ArrayList<ListenerContainer> listener_Weather;
	private ArrayList<ListenerContainer> listener_Vehicle;
	private ArrayList<ListenerContainer> listener_Inventory;
	private ArrayList<ListenerContainer> listener_Server;
	private ArrayList<ListenerContainer> listener_Painting;
	private ArrayList<ListenerContainer> listener_Enchantment;

	public ListenerData() {
		listener_Block 			= new ArrayList<ListenerContainer>();
		listener_Player 		= new ArrayList<ListenerContainer>();
		listener_Entity 		= new ArrayList<ListenerContainer>();
		listener_World 			= new ArrayList<ListenerContainer>();
		listener_Weather 		= new ArrayList<ListenerContainer>();
		listener_Vehicle 		= new ArrayList<ListenerContainer>();
		listener_Inventory 		= new ArrayList<ListenerContainer>();
		listener_Server 		= new ArrayList<ListenerContainer>();
		listener_Painting		= new ArrayList<ListenerContainer>();
		listener_Enchantment	= new ArrayList<ListenerContainer>();
		
		fillListener_Player();
		fillListener_Block();
		fillListener_Entity();
		fillListener_World();
		fillListener_Weather();
		fillListener_Vehicle();
		fillListener_Inventory();
		fillListener_Server();
		fillListener_Painting();
		fillListener_Enchantment();
	}
	
	private void fillListener_Player(){
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerJoin", "PlayerJoinEvent", "Called when a player joins a server"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerQuit", "PlayerQuitEvent", "Called when a player leaves a server"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerKick", "PlayerKickEvent", "Called when a player gets kicked from the server"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerChat", "PlayerChatEvent", "Called when a player sends a chat message"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerCommandPreprocess", "PlayerCommandPreprocessEvent", "Called early in the command handling process"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerMove", "PlayerMoveEvent", "Called when a player attempts to move location in a world"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerTeleport", "PlayerTeleportEvent", "Called when a player attempts to teleport to a listener_Player.add(new location in a world"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerRespawn", "PlayerRespawnEvent", "Called when a player respawns"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerInteract", "PlayerInteractEvent", "Called when a player interacts with an object or air"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerInteractEntity", "PlayerInteractEntityEvent", "Called when a player right clicks an entity"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerLogin", "PlayerLoginEvent", "Called when a player attempts to log in to the server"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerPreLogin", "PlayerPreLoginEvent", "Called when a player has just been authenticated"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerEggThrow", "PlayerEggThrowEvent", "Called when a player throws an egg and it might hatch"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerAnimation", "PlayerAnimationEvent", "Called when a player plays an animation, such as an arm swing"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onInventoryOpen", "PlayerInventoryEvent", "Called when a player opens an inventory"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onItemHeldChange", "PlayerItemHeldEvent", "Called when a player changes their held item"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerDropItem", "PlayerDropItemEvent", "Called when a player drops an item from their inventory"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerPickupItem", "PlayerPickupItemEvent", "Called when a player picks an item up off the ground"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerToggleSneak", "PlayerToggleSneakEvent", "Called when a player toggles sneak mode"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerBucketFill", "PlayerBucketFillEvent", "Called when a player fills a bucket"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerBucketEmpty", "PlayerBucketEmptyEvent", "Called when a player empties a bucket"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerBedEnter", "PlayerBedEnterEvent", "Called when a player enters a bed"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerBedLeave", "PlayerBedLeaveEvent", "Called when a player leaves a bed"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerPortal", "PlayerPortalEvent", "Called when a player is teleporting in a portal (after the animation)"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerFish", "PlayerFishEvent", "Called when a player is fishing"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerVelocity", "PlayerVelocityEvent", "Called before a player gets a velocity vector sent, which will \"push\" the player in a certain direction"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerGameModeChange", "PlayerGameModeChangeEvent", "Called when a player's game mode is changed"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerChangedWorld", "PlayerChangedWorldEvent", "Called when a player changes the world"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerExpChange", "PlayerExpChangeEvent", "Called when a players experience changes naturally"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerLevelChange", "PlayerLevelChangeEvent", "Called when a players level changes"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerShearEntity", "PlayerShearEntityEvent", "Called when a player shears an entity"));
		listener_Player.add(new ListenerContainer(ListenerType.Player, "onPlayerToggleSprint", "PlayerToggleSprintEvent", "Called when a player toggles their sprinting state"));
	} 
	
	private void fillListener_Block(){
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockDamage", "BlockDamageEvent", "Called when a block is damaged by a player"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockCanBuild", "BlockCanBuildEvent", "Called when we try to place a block, to see if we can build it here or not"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockFromTo", "BlockFromToEvent", "Represents events with a source block and a destination block, currently only applies to liquid (lava and water)"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockIgnite", "BlockIgniteEvent", "Called when a block is ignited"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockPhysics", "BlockPhysicsEvent", "Called when block physics occurs"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockPlace", "BlockPlaceEvent", "Called when a block is placed by a player"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockRedstoneChange", "BlockRedstoneEvent", "Called when redstone changes"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onLeavesDecay", "LeavesDecayEvent", "Called when leaves are decaying naturally"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onSignChange", "SignChangeEvent", "Called when a sign is changed by a player"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockBurn", "BlockBurnEvent", "Called when a block is destroyed as a result of being burnt by fire"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockBreak", "BlockBreakEvent", "Called when a block is broken by a player"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockForm", "BlockFormEvent", "Called when a block is formed or spreads based on world conditions"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockSpread", "BlockSpreadEvent", "Called when a block spreads based on world conditions"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockFade", "BlockFadeEvent", "Called when a block fades, melts or disappears based on world conditions"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockDispense", "BlockDispenseEvent", "Called when an item is dispensed from a block"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockPistonRetract", "BlockPistonRetractEvent", "Called when a piston retracts"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockPistonExtend", "BlockPistonExtendEvent", "Called when a piston extends"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onBlockGrow", "BlockGrowEvent", "Called when a block grows naturally in the world"));
		listener_Block.add(new ListenerContainer(ListenerType.Block, "onEntityBlockForm", "EntityBlockFormEvent", "Called when a block is formed by entities"));
	}
	
	private void fillListener_Enchantment(){
		listener_Enchantment.add(new ListenerContainer(ListenerType.Enchantment, "onEnchantItem", "EnchantItemEvent", "Called when an ItemStack is successfully enchanted (currently at enchantment table)"));
		listener_Enchantment.add(new ListenerContainer(ListenerType.Enchantment, "onPrepareItemEnchant", "PrepareItemEnchantEvent", "Called when an ItemStack is inserted in an enchantment table - can be called multiple times"));
	}
	
	private void fillListener_Entity(){
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onCreatureSpawn", "CreatureSpawnEvent", "Called when a creature is spawned into a world"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onItemSpawn", "ItemSpawnEvent", "Called when an item is spawned into a world"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityCombust", "EntityCombustEvent", "Called when an entity combusts due to the sun"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityDamage", "EntityDamageEvent", "Called when an entity is damaged"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityExplode", "EntityExplodeEvent", "Called when an entity explodes"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onExplosionPrime", "ExplosionPrimeEvent", "Called when an entity's fuse is lit"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityDeath", "EntityDeathEvent", "Called when an entity dies"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityTarget", "EntityTargetEvent", "Called when a creature targets another entity"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityInteract", "EntityInteractEvent", "Called when an entity interacts with an object"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityPortalEnter", "EntityPortalEnterEvent", "Called when an entity enters a portal"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onPigZap", "PigZapEvent", "Called when a Pig is struck by lightning"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onCreeperPower", "CreeperPowerEvent", "Called when a Creeper is struck by lightning"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityTame", "EntityTameEvent", "Called when an entity is tamed (currently only applies to Wolves)"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityRegainHealth", "EntityRegainHealthEvent", "Called when an entity regains health (currently only applies to Players)"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onProjectileHit", "ProjectileHitEvent", "Called when a project hits an object"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onFoodLevelChange", "FoodLevelChangeEvent", "Called when a human entity's food level changes"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityChangeBlock", "EntityChangeBlockEvent", "Called when an Enderman or Sheep changes a Block"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityBreakDoor", "EntityBreakDoorEvent", "Called when an Entity breaks a door"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityCombustByBlock", "EntityCombustByBlockEvent", "Called when an Entity is set on fire by a block"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityCombustByEntity", "EntityCombustByEntityEvent", "Called when an Entity is set on fire by a Entity"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityCreatePortal", "EntityCreatePortalEvent", "Called when a Living Entity creates a portal in a world."));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityDamageByBlock", "EntityDamageByBlockEvent", "Called when an entity is damaged by a block"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityDamageByEntity", "EntityDamageByEntityEvent", "Called when an entity is damaged by an entity"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityShootBow","EntityShootBowEvent", "Called when a LivingEntity shoots a bow firing an arrow"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityTargetLivingEntity", "EntityTargetLivingEntityEvent", "Called when an Entity targets a LivingEntity and can only target LivingEntity's."));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onEntityTeleport", "EntityTeleportEvent", "Called when a non-player entity (such as an Enderman) tries to teleport from one location to another."));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onExpBottle", "ExpBottleEvent", "Called when a ThrownExpBottle hits and releases experience."));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onItemDespawn", "ItemDespawnEvent", "Called when an Item despawns"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onPlayerDeath", "PlayerDeathEvent", "Thrown whenever a Player dies"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onPotionSplash", "PotionSplashEvent", "Called when a splash potion hits an area"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onProjectileLaunch", "ProjectileLaunchEvent", "Called when a projectile is launched"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onSheepDyeWool", "SheepDyeWoolEvent", "Called when a sheep's wool is dyed"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onSheepRegrowWool", "SheepRegrowWoolEvent", "Called when a sheep regrows its wool"));
		listener_Entity.add(new ListenerContainer(ListenerType.Entity, "onSlimeSplit", "SlimeSplitEvent", "Called when a Slime splits into smaller Slimes upon death"));
	}
	
	private void fillListener_Painting(){
		listener_Painting.add(new ListenerContainer(ListenerType.Painting, "onPaintingBreak", "PaintingBreakEvent", "Called when a painting breaks"));
		listener_Painting.add(new ListenerContainer(ListenerType.Painting, "onPaintingBreakByEntity", "PaintingBreakByEntityEvent", "Called when a painting breaks"));
		listener_Painting.add(new ListenerContainer(ListenerType.Painting, "onPaintingPlace", "PaintingPlaceEvent", "Called when a painting is placed"));
	}
	
	private void fillListener_World(){
		listener_World.add(new ListenerContainer(ListenerType.World, "onChunkLoad", "ChunkLoadEvent", "Called when a chunk is loaded"));
		listener_World.add(new ListenerContainer(ListenerType.World, "onChunkPopulate", "ChunkPopulateEvent", "Called when a newly created chunk has been populated"));
		listener_World.add(new ListenerContainer(ListenerType.World, "onChunkUnload", "ChunkUnloadEvent", "Called when a chunk is unloaded"));
		listener_World.add(new ListenerContainer(ListenerType.World, "onSpawnChange", "SpawnChangeEvent", "Called when a World's spawn is changed"));
		listener_World.add(new ListenerContainer(ListenerType.World, "onPortalCreate", "PortalCreateEvent", "Called when the world generates a portal end point"));
		listener_World.add(new ListenerContainer(ListenerType.World, "onWorldSave", "WorldSaveEvent", "Called when a world is saved"));
		listener_World.add(new ListenerContainer(ListenerType.World, "onWorldInit", "WorldInitEvent", "Called when a World is initializing"));
		listener_World.add(new ListenerContainer(ListenerType.World, "onWorldLoad", "WorldLoadEvent", "Called when a World is loaded"));
		listener_World.add(new ListenerContainer(ListenerType.World, "onWorldUnload", "WorldUnloadEvent", "Called when a World is unloaded"));
		listener_World.add(new ListenerContainer(ListenerType.World, "onStructureGrowEvent", "StructureGrowEvent", "Called when a structure grows"));
	}
	
	private void fillListener_Weather(){
		listener_Weather.add(new ListenerContainer(ListenerType.Weather, "onWeatherChange", "WeatherChangeEvent", "Called when a weather change occurs"));
		listener_Weather.add(new ListenerContainer(ListenerType.Weather, "onThunderChange", "ThunderChangeEvent", "Called when the state of thunder changes"));
		listener_Weather.add(new ListenerContainer(ListenerType.Weather, "onLightningStrike", "LightningStrikeEvent", "Called when lightning strikes"));
	}
	
	private void fillListener_Vehicle(){
		listener_Vehicle.add(new ListenerContainer(ListenerType.Vehicle, "onVehicleCreate", "VehicleCreateEvent", "Called when a vehicle is created by a player. This hook will be called"));
		listener_Vehicle.add(new ListenerContainer(ListenerType.Vehicle, "onVehicleDamage", "VehicleDamageEvent", "Called when a vehicle is damaged by the player"));
		listener_Vehicle.add(new ListenerContainer(ListenerType.Vehicle, "onVehicleBlockCollision", "VehicleBlockCollisionEvent", "Called when a vehicle collides with a block"));
		listener_Vehicle.add(new ListenerContainer(ListenerType.Vehicle, "onVehicleEntityCollision", "VehicleEntityCollisionEvent", "Called when a vehicle collides with an entity"));
		listener_Vehicle.add(new ListenerContainer(ListenerType.Vehicle, "onVehicleEnter", "VehicleEnterEvent", "Called when an entity enters a vehicle"));
		listener_Vehicle.add(new ListenerContainer(ListenerType.Vehicle, "onVehicleExit", "VehicleExitEvent", "Called when an entity exits a vehicle"));
		listener_Vehicle.add(new ListenerContainer(ListenerType.Vehicle, "onVehicleMove", "VehicleMoveEvent", "Called when an vehicle moves"));
		listener_Vehicle.add(new ListenerContainer(ListenerType.Vehicle, "onVehicleDestroy", "VehicleDestroyEvent", "Called when a vehicle is destroyed"));
		listener_Vehicle.add(new ListenerContainer(ListenerType.Vehicle, "onVehicleUpdate", "VehicleUpdateEvent", "Called when a vehicle goes through an update cycle"));
	}
	
	private void fillListener_Inventory(){
		listener_Inventory.add(new ListenerContainer(ListenerType.Inventory, "onFurnaceSmelt", "FurnaceSmeltEvent", "Called when an ItemStack is successfully smelted in a furnace"));
		listener_Inventory.add(new ListenerContainer(ListenerType.Inventory, "onFurnaceBurn", "FurnaceBurnEvent", "Called when an ItemStack is successfully burned as fuel in a furnace"));
		listener_Inventory.add(new ListenerContainer(ListenerType.Inventory, "onBrew", "BrewEvent", "Called when an Item is brewes"));
		listener_Inventory.add(new ListenerContainer(ListenerType.Inventory, "onCraftItem", "CraftItemEvent", "Called when an Item is crafted"));
		listener_Inventory.add(new ListenerContainer(ListenerType.Inventory, "onInventoryClick", "InventoryClickEvent", "Called when a Player clicks on the Inventory"));
		listener_Inventory.add(new ListenerContainer(ListenerType.Inventory, "onInventoryClose", "InventoryCloseEvent", "Called when a Player closes his Inventory"));
		listener_Inventory.add(new ListenerContainer(ListenerType.Inventory, "onInventoryOpenEvent", "InventoryOpenEvent", "Called when a Player opens his Inventory"));
	}
	
	private void fillListener_Server(){
		listener_Server.add(new ListenerContainer(ListenerType.Server, "onPluginEnable", "PluginEnableEvent", "Called when a plugin is enabled"));
		listener_Server.add(new ListenerContainer(ListenerType.Server, "onPluginDisable", "PluginDisableEvent", "Called when a plugin is disabled"));
		listener_Server.add(new ListenerContainer(ListenerType.Server, "onServerCommand", "ServerCommandEvent", "Called when a server command is used"));
		listener_Server.add(new ListenerContainer(ListenerType.Server, "onMapInitialize", "MapInitializeEvent", "Called when a map item is initialized (created or loaded into memory)"));
		listener_Server.add(new ListenerContainer(ListenerType.Server, "onRemoveServerCommand", "RemoteServerCommandEvent", "Called when a remote server alls a command"));
		listener_Server.add(new ListenerContainer(ListenerType.Server, "onServiceRegister", "ServiceRegisterEvent", "Called when a service is registered"));
		listener_Server.add(new ListenerContainer(ListenerType.Server, "onServiceUnregister", "ServiceUnregisterEvent", "Called when a service is unregistered"));
	}
	
	public ArrayList<ListenerContainer> getDataByType(ListenerType type){
		switch (type) {
		case Block:			return listener_Block;
		case Entity:		return listener_Entity;
		case Inventory:		return listener_Inventory;
		case Player:		return listener_Player;
		case Server:		return listener_Server;
		case Vehicle:		return listener_Vehicle;
		case Weather:		return listener_Weather;
		case World:			return listener_World;
		case Painting:		return listener_Painting;
		case Enchantment: 	return listener_Enchantment;
		}
		return null;
	}
}
