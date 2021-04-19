package club.ubbly.common.server.stargate;

import alemiz.stargate.codec.ProtocolCodec;
import alemiz.stargate.server.StarGateServer;
import alemiz.stargate.utils.ServerLoader;
import alemiz.stargate.utils.StarGateLogger;
import club.ubbly.common.server.stargate.listener.StarGateServerListener;
import club.ubbly.common.server.stargate.logger.Logger;
import club.ubbly.common.stargate.handler.CustomPacketHandler;
import club.ubbly.common.stargate.protocol.ProtocolInfo;
import club.ubbly.common.stargate.protocol.packet.*;
import dev.waterdog.plugin.Plugin;
import java.net.InetSocketAddress;
import lombok.Getter;

public class CommunicationServer implements ServerLoader {

  @Getter
  private final Plugin plugin;

  @Getter
  private final String host;

  @Getter
  private final int port;

  @Getter
  private final String password;

  @Getter
  private final StarGateServer server;

  @Getter
  private final StarGateLogger starGateLogger;

  @Getter
  private final Class<? extends CustomPacketHandler> packetHandler;

  public CommunicationServer(
    String host,
    int port,
    String password,
    Plugin plugin,
    Class<? extends CustomPacketHandler> packetHandler
  ) {
    this.plugin = plugin;
    this.host = host;
    this.port = port;
    this.password = password;
    this.starGateLogger = new Logger(plugin);
    this.packetHandler = packetHandler;

    InetSocketAddress address = new InetSocketAddress(host, port);
    server = new StarGateServer(address, password, this);
    server.setServerListener(new StarGateServerListener(this));
    registerPackets(server);
    server.start();
  }

  private void registerPackets(StarGateServer server) {
    ProtocolCodec protocol = server.getProtocolCodec();

    protocol.registerPacket(ProtocolInfo.TRANSFER_PACKET, TransferPacket.class);

    protocol.registerPacket(
      ProtocolInfo.USER_UPDATE_INFORMATION_PACKET,
      UserUpdateInformationPacket.class
    );

    protocol.registerPacket(
      ProtocolInfo.USER_UPDATE_AUTHENTICATION_PACKET,
      UserUpdateAuthenticationPacket.class
    );

    protocol.registerPacket(
      ProtocolInfo.USER_UPDATE_PERMISSIONS_PACKET,
      UserUpdatePermissionsPacket.class
    );

    protocol.registerPacket(
      ProtocolInfo.RANK_UPDATE_PERMISSIONS_PACKET,
      RankUpdatePermissionsPacket.class
    );

    protocol.registerPacket(
      ProtocolInfo.BROADCAST_PACKET,
      BroadcastPacket.class
    );

    protocol.registerPacket(
      ProtocolInfo.FOLLOW_PLAYER_PACKET,
      FollowPlayerPacket.class
    );

    protocol.registerPacket(
      ProtocolInfo.SEND_MESSAGE_PACKET,
      SendMessagePacket.class
    );
  }
}
