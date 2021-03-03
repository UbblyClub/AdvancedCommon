package club.ubbly.common.stargate.handler;

import club.ubbly.common.stargate.protocol.packet.*;

public interface CustomPacketHandler {
  void handleQueueTransfer(QueueTransferPacket packet);

  void handleUserUpdateInformation(UserUpdateInformationPacket packet);

  void handleUserUpdatePermissions(UserUpdatePermissionsPacket packet);

  void handleUserUpdateAuthentication(UserUpdateAuthenticationPacket packet);

  void handleRankUpdatePermissions(RankUpdatePermissionsPacket packet);
}
