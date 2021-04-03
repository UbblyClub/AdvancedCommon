package club.ubbly.common.stargate.protocol;

public interface ProtocolInfo {
  byte TRANSFER_PACKET = 0x50;
  byte USER_UPDATE_INFORMATION = 0x51;
  byte USER_UPDATE_PERMISSIONS = 0x52;
  byte RANK_UPDATE_PERMISSIONS = 0x53;
  byte USER_UPDATE_AUTHENTICATION = 0x54;
}
