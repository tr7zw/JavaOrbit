package de.tr7zw.javaorbit.server.player;

import de.tr7zw.javaorbit.server.Location;
import de.tr7zw.javaorbit.server.Server;
import de.tr7zw.javaorbit.server.connection.Faction;
import de.tr7zw.javaorbit.server.connection.PlayerConnection;
import de.tr7zw.javaorbit.server.connection.Session;
import de.tr7zw.javaorbit.server.connection.packet.PacketOut;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutPermanentTitle;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutPlayerInfo;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSetDrones;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutSetMap;
import de.tr7zw.javaorbit.server.connection.packet.play.out.PacketPlayOutShowMessage;
import de.tr7zw.javaorbit.server.enums.Ammo;
import de.tr7zw.javaorbit.server.enums.LaserLook;
import de.tr7zw.javaorbit.server.enums.Maps;
import de.tr7zw.javaorbit.server.enums.Rank;
import de.tr7zw.javaorbit.server.enums.Rings;
import de.tr7zw.javaorbit.server.enums.ShipType;
import de.tr7zw.javaorbit.server.enums.Title;
import de.tr7zw.javaorbit.server.maps.MapManager;
import de.tr7zw.javaorbit.server.maps.entities.EntityPlayer;
import de.tr7zw.javaorbit.server.maps.entities.PlayerShip;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
public class Player implements EntityPlayer{

	private static int DEBUG_COUNTER = 0;
	
	private String name = "Debug" + (++DEBUG_COUNTER);
	@NonNull private PlayerConnection connection;
	@NonNull private Session session;
	@Setter private Location location = new Location(Server.getInstance().getMapManager().getInstance(Maps.MAP1_1), 1000, 1000);
	@Setter private Location targetLocation = location;
	@Setter private long movingStartTime = 0;
	@Setter private boolean moving = false;
	@Setter private int moveTime = 0;
	@Setter private Location startLocation = location;
	@Setter private Ammo ammo = Ammo.USB100;
	private Settings settings = new Settings();
	@Setter private PlayerView playerView = new PlayerView();
	private LaserLook laserLook = LaserLook.FULL_ELITE;
	private Faction faction = Faction.MMO;
	private Rank rank = Rank.ADMIN;
	private Title title = Title.NONE;
	private Clan clan = new Clan(1, "Admins", "Admin");
	@Setter private int viewDistance = 2000;
	private Rings rings = Rings.PYRAMIDE;
	private PlayerData playerData = new PlayerData();
	private PlayerShip playerShip = new PlayerShip();


	public void sendLogin() {
		connection.send("A", "SET|" + settings.getSet());
		connection.send("7", "CLIENT_RESOLUTION|" + settings.getClientResolution());
		connection.send("7", "MINIMAP_SCALE," + settings.getMinimapScale());
		connection.send("7", "RESIZABLE_WINDOWS," + settings.getResizableWindows());
		connection.send("7", "DISPLAY_PLAYER_NAMES|" + toEnum(settings.isDisplayPlayerNames()));
		connection.send("7", "DISPLAY_CHAT|" + toEnum(settings.isDisplayChat()));
		connection.send("7", "PLAY_MUSIC|" + toEnum(settings.isPlayMusic()));
		connection.send("7", "PLAY_SFX|" + toEnum(settings.isPlaySfx()));
		connection.send("7", "BAR_STATUS|" + settings.getBarStatus());
		connection.send("7", "WINDOW_SETTINGS," + settings.getWindowSettings());
		connection.send("7", "AUTO_REFINEMENT|" + toEnum(settings.isAutoRefinement()));
		connection.send("7", "QUICKSLOT_STOP_ATTACK|" + toEnum(settings.isQuickSlotStopAttack()));
		connection.send("7", "DOUBLECLICK_ATTACK|" + toEnum(settings.isDoubleClickAttack()));
		connection.send("7", "AUTO_START|" + toEnum(settings.isAutoStart()));
		connection.send("7", "DISPLAY_NOTIFICATIONS|" + toEnum(settings.isDisplayNotification()));
		connection.send("7", "SHOW_DRONES|" + toEnum(settings.isShowDrones()));
		connection.send("7", "DISPLAY_WINDOW_BACKGROUND|" + toEnum(settings.isDisplayWindowBackground()));
		connection.send("7", "ALWAYS_DRAGGABLE_WINDOWS|" + toEnum(settings.isAlwaysDraggableWindows()));
		connection.send("7", "PRELOAD_USER_SHIPS|" + toEnum(settings.isPreloadUserShips()));
		connection.send("7", "QUALITY_PRESETTING|" + settings.getQualityPresseting());
		connection.send("7", "QUALITY_CUSTOMIZED|" + settings.getQualityCustomized());
		connection.send("7", "QUALITY_BACKGROUND|" + settings.getQualityBackground());
		connection.send("7", "QUALITY_POIZONE|" + settings.getQualityPoizone());
		connection.send("7", "QUALITY_SHIP|" + settings.getQualityShip());
		connection.send("7", "QUALITY_ENGINE|" + settings.getQualityEngine());
		connection.send("7", "QUALITY_COLLECTABLE|" + settings.getQualityCollectable());
		connection.send("7", "QUALITY_ATTACK|" + settings.getQualityAttack());
		connection.send("7", "QUALITY_EFFECT|" + settings.getQualityEffect());
		connection.send("7", "QUALITY_EXPLOSION|" + settings.getQualityExplosion());
		connection.send("7", "QUICKBAR_SLOT|" + settings.getQuickbarSlot());
		connection.send("7", "SLOTMENU_POSITION," + settings.getSlotmenuPosition());
		connection.send("7", "SLOTMENU_ORDER," + settings.getSlotmenuOrder());
		connection.send("7", "MAINMENU_POSITION," + settings.getMainmenuPosition());
		
		updatePlayer();
		sendPacket(new PacketPlayOutSetMap(getLocation().getInstance().getMap()));
		sendPacket(new PacketPlayOutSetDrones(getSession().getUserId()));
		String send = 
				"f|C|1|" + getPlayerShip().getType().getId() + "|3||" + getName() + "|900|1700|1|1|1|0|0|0| 0|n|d|1|3/2-25-25,3/4-25-25-25-25,3/2-25-25|\r\n" + 
				"0|1|-2|5200|6000|687| 0|1|-3|9700|2000|1535| 0|1|-4|17300|9500|3433| 0|1|-5|9100|3000|1942| 0|1|-6|15700|2700|687| 0|1|-7|7800|1700|971| 0|1|-8|7400|9800|1373|\r\n" + 
				//"0|p|1|1|32|18500|11500\r\n" + 
				//"0|p|1|" + Portal.BIRTHDAY.getId() + "|32|0|0\r\n" + 
				"0|B|20000|20000|20000|20000|20000\r\n" + 
				"0|3|1000|1000|1000|0|0|0|0|0|100|100|0|0|0|0\r\n" + 
				"0|7|HS\r\n" + 
				"0|S|CFG|1\r\n" + 
				"0|A|ITM|0|0|0|0|4|0|0|1|1|0|0|0|0|0|0|0\r\n" + 
				"0|g|a|b,1000,1,10000,C,2,500,U,3,1000,U,5,1000,U|r,100,1,10000,C,2,50000,C,3,500,U,4,700,U\r\n" + 
				"0|TX|S|1|15|0|1|15|0|1|15|0|1|15|0|1|15|0\r\n" + 
				"0|m|" + Maps.MAP1_1.getId() + "|1000|1000\r\n" + 
				"0|POI|RDY\r\n" + 
				"0|A|CLD|ISH|0\r\n" + 
				"0|A|CLD|SMB|0\r\n" + 
				"0|CSS|1\r\n" + 
				"0|SMP|1|1\r\n";
		send = send.replace("\r\n", "" + (char)0x00);
		connection.send(send);
		
		connection.send("0|A|ADM|CLI|1");
		sendMessage("Welcome on JavaOrbit");
		Server.getInstance().getMapManager().enterMap(this, getLocation().getInstance().getMap(), getLocation().getX(), getLocation().getY());

		if(true)return;
		
		/*connection.send("0|A|SET|" + settings.getSET()
                + (char)0x00 + "0|7|MINIMAP_SCALE," + settings.getMINIMAP_SCALE()
                + (char)0x00 + "0|7|DISPLAY_PLAYER_NAMES|" + ToEnum(settings.isDISPLAY_PLAYER_NAMES())
                + (char)0x00 + "0|7|DISPLAY_CHAT|" + ToEnum(settings.isDISPLAY_CHAT())
                + (char)0x00 + "0|7|BAR_STATUS|" + settings.getBAR_STATUS()
                + (char)0x00 + "0|7|WINDOW_SETTINGS," + settings.getWINDOW_SETTINGS()
                + (char)0x00 + "0|7|AUTO_REFINEMENT|" + ToEnum(settings.isAUTO_REFINEMENT())
                + (char)0x00 + "0|7|QUICKSLOT_STOP_ATTACK|" + ToEnum(settings.isQUICKSLOT_STOP_ATTACK())
                + (char)0x00 + "0|7|DOUBLECLICK_ATTACK|" + ToEnum(settings.isDOUBLECLICK_ATTACK())
                + (char)0x00 + "0|7|AUTO_START|" + ToEnum(settings.isAUTO_START())
                + (char)0x00 + "0|7|DISPLAY_NOTIFICATIONS|" + ToEnum(settings.isDISPLAY_NOTIFICATIONS())
                + (char)0x00 + "0|7|SHOW_DRONES|" + ToEnum(settings.isSHOW_DRONES())
                + (char)0x00 + "0|7|DISPLAY_WINDOW_BACKGROUND|" + ToEnum(settings.isDISPLAY_WINDOW_BACKGROUND())
                + (char)0x00 + "0|7|ALWAYS_DRAGGABLE_WINDOWS|" + ToEnum(settings.isALWAYS_DRAGGABLE_WINDOWS())
                + (char)0x00 + "0|7|PRELOAD_USER_SHIPS|" + ToEnum(settings.isPRELOAD_USER_SHIPS())
                + (char)0x00 + "0|7|QUALITY_PRESETTING|" + settings.getQUALITY_PRESETTING()
                + (char)0x00 + "0|7|QUALITY_CUSTOMIZED|" + ToEnum(settings.isQUALITY_CUSTOMIZED())
                + (char)0x00 + "0|7|QUALITY_BACKGROUND|" + settings.getQUALITY_BACKGROUND()
                + (char)0x00 + "0|7|QUALITY_POIZONE|" + settings.getQUALITY_POIZONE()
                + (char)0x00 + "0|7|QUALITY_SHIP|" + settings.getQUALITY_SHIP()
                + (char)0x00 + "0|7|QUALITY_ENGINE|" + settings.getQUALITY_ENGINE()
                + (char)0x00 + "0|7|QUALITY_COLLECTABLE|" + settings.getQUALITY_COLLECTABLE()
                + (char)0x00 + "0|7|QUALITY_ATTACK|" + settings.getQUALITY_ATTACK()
                + (char)0x00 + "0|7|QUALITY_EFFECT|" + settings.getQUALITY_EFFECT()
                + (char)0x00 + "0|7|QUALITY_EXPLOSION|" + settings.getQUALITY_EXPLOSION()
                + (char)0x00 + "0|7|QUICKBAR_SLOT|" + settings.getQUICKBAR_SLOT()
                + (char)0x00 + "0|7|SLOTMENU_POSITION|" + settings.getSLOTMENU_POSITION()
                + (char)0x00 + "0|7|SLOTMENU_ORDER|" + settings.getSLOTMENU_ORDER());

		connection.send("RDY|I|" + session.getUserId() + "|" + "Debug" + "|" + ShipType.GOLIATH.getId() + "|" + 100 + "|" + 15000 + "|" + 30000 + "|" + 10000 + "|" + 20000 + "|5|100|" + Math.round(location.getX()) + "|" + Math.round(location.getY()) + "|" + location.getMapId() + "|" + 1 + "|0|1500|0|15|0|3|0|1|10|10000000|10|21|ADM|7|1|0|50|25");
		connection.send("0|m|1|" + location.getX() + "|" + location.getY());
		connection.send("0|POI|RDY");
		connection.send("0|A|STD|Welcome to Azure 0.6");
		connection.send("0|msg|epic_msg_happy_hour_started||");*/

		

		//Session.SendData(UserDataComposer.Compose(Session));

		//MapManager.enterMap(this, new MapInstance(Maps.MAP1_1), 1000, 1000);
		
		//getConnection().send("f|C|1|10|3||tr7zw|900|1700|1|1|1|0|0|0| 0|n|d|1|3/2-25-25,3/4-25-25-25-25,3/2-25-25|");

		//getConnection().send("0|1|-2|5200|6000|687| 0|1|-3|9700|2000|1535| 0|1|-4|17300|9500|3433| 0|1|-5|9100|3000|1942| 0|1|-6|15700|2700|687| 0|1|-7|7800|1700|971| 0|1|-8|7400|9800|1373|");
		//getConnection().send("0|s|0|1|redStation|1|0|1000|1000");
		
		//connection.send("0|m|1|" + location.getX() + "|" + location.getY());

		
		
		//Maps.MapHandler.OpenPublicConnection(Session, Session.CharacterInfo.MapId);

		//connection.send("p", "1|1|32|18500|11500");
		//connection.send("B", "20000|20000|20000|20000|20000"); // LCB-10(x1)|MCB-25(x2)|MCB-50(x3)|UCB-100(x4)|SAB-50(roba escudo)|RSB-75(x5)
		//3|R-310|PLT_2026|PLT_2021|PLT_3030|PLD_8|DCR_250|WIZ|MINE|SMARTBOMB|INSTASHIELD|EMP|MINE_EMP|MINE_SAB|MINE_DDM
		//connection.send("3", "1000|1000|1000|0|0|0|0|0|100|100|0|0|0|0");
		//connection.send("7", "HS");
		//connection.send("S", "CFG|1");
		//ITM|TYPE_DRONE_REPAIR|TYPE_RADAR|TYPE_JUMP|TYPE_AMMOBUY|TYPE_ROBOT|TYPE_HM7|0|TYPE_SMARTBOMB|TYPE_INSTASHIELD|TYPE_MINETURBO|TYPE_AIM|TYPE_AROL|TYPE_CLOAK|TYPE_RLLB|TYPE_ROCKETBUY|TYPE_ADVANCED_JUMP
		//connection.send("A", "ITM|0|0|0|0|4|0|0|1|1|0|0|0|0|0|0|0"); // CPU INFO
		//connection.send("g", "a|b,1000,1,10000,C,2,500,U,3,1000,U,5,1000,U|r,100,1,10000,C,2,50000,C,3,500,U,4,700,U"); // STORE
		//connection.send("TX", "S|1|15|0|1|15|0|1|15|0|1|15|0|1|15|0"); // TECH UPDATE
		//connection.send("ps", "blk|0"));
		//connection.send("m", location.getMap().getMapId() + "|" + (int)location.getX() + "|" + (int)location.getY()); // Map
		//connection.send("0|POI|RDY");
		// 3/4-25-25-25-25,3/4-25-25-25-25,3/4-25-25-25-25,3/4-25-25-25-25
		//connection.send("n", "d|" + Session.CharacterId + "|3/2-25-25,3/4-25-25-25-25,3/2-25-25")); // Send drones
		// 3/4-0,0-25,1-25,0-0,0,0/4-25,1-25,2-25,2-25,0/4-0,0-25,1-25,0-0,0
		//connection.send("A", "STD|Welcome on JavaOrbit");
		//connection.send("A", "CLD|ISH|0");//Cooldown instashield
		//connection.send("A", "CLD|SMB|0");//Cooldown smartbomb
		//connection.send("s", "0|1|redStation|1|0|1000|1000")); // id|nothing|stationname|companyid|keepempty|posx|posy
		//connection.send("s", "1|1|blueStation|2|0|20500|1000"));
		//connection.send("s", "0|9|greenStation|3|0|10500|12500"));
		//connection.send("CSS", "1");
		//connection.send("SMP", "1|1");
		//connection.send("UI", "W|HW|23"); // Hide group system icon
		//connection.send("UI", "W|HW|10"); // Hide quest icon
		//connection.send("n", "t|" + getSession().getUserId() + "|" + "Developer");
		
		//connection.send("0|HL|" + session.getUserId() + "|SHD|" + new Random().nextInt(50000) + "|0");

	}

	public void changeShip(ShipType type) {
		getPlayerShip().setType(type);
		refreshPlayer();
	}
	
	public void changeRank(Rank rank) {
		this.rank = rank;
		updatePlayer();
	}
	
	public void warp(Location targetLocation){
		boolean sameMap = targetLocation.getInstance().equals(getLocation().getInstance());
		MapManager manager = Server.getInstance().getMapManager();
		if(sameMap)
			manager.enterMap(this, Maps.QUESTIONMARK, 1000, 1000);
		manager.enterMapInstance(this, targetLocation.getInstance(), targetLocation.getX(), targetLocation.getY());
	}

	public void refreshPlayer() {
		Maps map = getLocation().getInstance().getMap();
		int posX = getLocation().getX();
		int posY = getLocation().getY();
		MapManager manager = Server.getInstance().getMapManager();
		manager.enterMap(this, Maps.QUESTIONMARK, 1000, 1000);
		manager.enterMap(this, map, posX, posY);
	}
	
	public void sendMessage(String message) {
		sendPacket(new PacketPlayOutShowMessage(message));
	}
	
	public void sendPacket(PacketOut packet) {
		getConnection().send(packet.buildData().toString());
	}
	
	public void updatePlayer() {
		sendPacket(new PacketPlayOutPlayerInfo(session.getUserId(), getName(), getPlayerShip().getType(), getPlayerShip().getSpeed(), getPlayerShip().getShield(), getPlayerShip().getMaxShield(), getPlayerShip().getHp(), getPlayerShip().getMaxHp(), getPlayerShip().getCargo(), getPlayerShip().getMaxCargo(), (int)getLocation().getX(), (int)getLocation().getY(), getLocation().getInstance().getMap(), faction,
				1, 10000, 10000, LaserLook.FULL_ELITE, getPlayerData().isPremium(), getPlayerData().getExp(), getPlayerData().getHonor(), getPlayerData().getLevel(), getPlayerData().getCredits(), getPlayerData().getUridium(), getPlayerData().getJackpot(), rank, clan != null ? clan.getTag() : "", rings, false));
		sendPacket(new PacketPlayOutSetDrones(getSession().getUserId()));
		sendPacket(new PacketPlayOutPermanentTitle(session.getUserId(), title));
	}
	
	public static String toEnum(boolean isTrue)
	{
		return (isTrue) ? "1" : "0";
	}

	@Override
	public int getId() {
		return getSession().getUserId();
	}

}
