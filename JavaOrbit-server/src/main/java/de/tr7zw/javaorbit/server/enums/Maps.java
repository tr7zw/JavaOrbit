package de.tr7zw.javaorbit.server.enums;

import de.tr7zw.javaorbit.server.connection.Faction;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Maps {
MAP1_1(1, Faction.MMO),
MAP1_2(2, Faction.MMO),
MAP1_3(3, Faction.MMO),
MAP1_4(4, Faction.MMO),
MAP2_1(5, Faction.EIC),
MAP2_2(6, Faction.EIC),
MAP2_3(7, Faction.EIC),
MAP2_4(8, Faction.EIC),
MAP3_1(9, Faction.VRU),
MAP3_2(10, Faction.VRU),
MAP3_3(11, Faction.VRU),
MAP3_4(12, Faction.VRU),

MAP4_1(13, Faction.NONE),
MAP4_2(14, Faction.NONE),
MAP4_3(15, Faction.NONE),
MAP4_4(16, Faction.NONE),

MAP1_5(17, Faction.MMO),
MAP1_6(18, Faction.MMO),
MAP1_7(19, Faction.MMO),
MAP1_8(20, Faction.MMO),

MAP2_5(21, Faction.EIC),
MAP2_6(22, Faction.EIC),
MAP2_7(23, Faction.EIC),
MAP2_8(24, Faction.EIC),

MAP3_5(25, Faction.VRU),
MAP3_6(26, Faction.VRU),
MAP3_7(27, Faction.VRU),
MAP3_8(28, Faction.VRU),

MAP4_5(29, Faction.NONE),

QUESTIONMARK(42, Faction.NONE),
//GATE_GG(50, Faction.NONE),
GATE_ALPHA(51, Faction.NONE),
GATE_BETA(52, Faction.NONE),
GATE_GAMMA(53, Faction.NONE),
GATE_NC(54, Faction.NONE),
GATE_DELTA(55, Faction.NONE),
GATE_ORB(56, Faction.NONE),
GATE_Y4(57, Faction.NONE),

INVASION_MMO(61, Faction.NONE),
INVASION_EIC(62, Faction.NONE),
INVASION_VRU(63, Faction.NONE),
INVASION_MMO2(64, Faction.NONE),
INVASION_EIC2(65, Faction.NONE),
INVASION_VRU2(66, Faction.NONE),
INVASION_MMO3(67, Faction.NONE),
INVASION_EIC3(68, Faction.NONE),
INVASION_VRU3(69, Faction.NONE),

MAP_DEATHMATCH(81, Faction.NONE),
MAP_DEATHMATCH2(82, Faction.NONE),

MAP5_1(91, Faction.NONE),
MAP5_2(92, Faction.NONE),
MAP5_3(93, Faction.NONE),

JP_FINAL(101, Faction.NONE),
JP_1(102, Faction.NONE),
JP_2(103, Faction.NONE),
JP_3(104, Faction.NONE),
JP_4(105, Faction.NONE),
JP_5(106, Faction.NONE),
JP_6(107, Faction.NONE),
JP_7(108, Faction.NONE),
JP_8(109, Faction.NONE),
JP_9(110, Faction.NONE),
JP_10(111, Faction.NONE),
JP_11(112, Faction.NONE),
JP_12(113, Faction.NONE),
JP_13(114, Faction.NONE),
JP_14(115, Faction.NONE),
JP_15(116, Faction.NONE),
JP_16(117, Faction.NONE),
JP_17(118, Faction.NONE),
JP_18(119, Faction.NONE),
JP_19(120, Faction.NONE),
JP_20(121, Faction.NONE),
JP_21(122, Faction.NONE),
JP_22(123, Faction.NONE),
JP_23(124, Faction.NONE),
JP_24(125, Faction.NONE),
JP_25(126, Faction.NONE),


GATE_LOW(200, Faction.NONE),
	;
	private int id;
	private Faction faction;
}
