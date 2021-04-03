package club.ubbly.common.stargate.handler;

import alemiz.stargate.handler.StarGatePacketHandler;
import alemiz.stargate.session.StarGateSession;
import club.ubbly.common.stargate.protocol.packet.*;
import java.lang.reflect.Constructor;

public interface CustomPacketHandler {
  void handleTransfer(TransferPacket packet);

  void handleUserUpdateInformation(UserUpdateInformationPacket packet);

  void handleUserUpdatePermissions(UserUpdatePermissionsPacket packet);

  void handleUserUpdateAuthentication(UserUpdateAuthenticationPacket packet);

  void handleRankUpdatePermissions(RankUpdatePermissionsPacket packet);

  static StarGatePacketHandler instancePacketHandler(
    Class<? extends CustomPacketHandler> packetHandler,
    StarGateSession serverSession
  ) {
    if (packetHandler == null) return null;

    Constructor<?>[] constructors = packetHandler.getDeclaredConstructors();

    try {
      if (constructors.length == 1) {
        Constructor<?> constructor = constructors[0];

        if (constructor.getParameterCount() == 0) {
          return (StarGatePacketHandler) constructor.newInstance();
        }

        return (StarGatePacketHandler) constructor.newInstance(serverSession);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }
}
