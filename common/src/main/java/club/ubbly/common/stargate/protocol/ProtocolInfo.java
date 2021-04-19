package club.ubbly.common.stargate.protocol;

public interface ProtocolInfo {
  byte TRANSFER_PACKET = 0x50;
  byte USER_UPDATE_INFORMATION_PACKET = 0x51;
  byte USER_UPDATE_PERMISSIONS_PACKET = 0x52;
  byte RANK_UPDATE_PERMISSIONS_PACKET = 0x53;
  byte USER_UPDATE_AUTHENTICATION_PACKET = 0x54;
  byte BROADCAST_PACKET = 0x55;
}
