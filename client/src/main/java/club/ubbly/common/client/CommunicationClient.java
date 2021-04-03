package club.ubbly.common.client;

import alemiz.sgu.nukkit.StarGateUniverse;
import alemiz.stargate.client.StarGateClient;
import alemiz.stargate.codec.ProtocolCodec;
import alemiz.stargate.protocol.types.HandshakeData;
import alemiz.stargate.utils.ServerLoader;
import alemiz.stargate.utils.StarGateLogger;
import club.ubbly.common.client.logger.Logger;
import club.ubbly.common.stargate.handler.CustomPacketHandler;
import club.ubbly.common.stargate.protocol.ProtocolInfo;
import club.ubbly.common.stargate.protocol.packet.*;
import cn.nukkit.plugin.PluginBase;
import java.net.InetSocketAddress;
import lombok.Getter;

public class CommunicationClient implements ServerLoader {

  public static final int STARGATE_VERSION = StarGateUniverse.STARGATE_VERSION;

  @Getter
  private final String name;

  @Getter
  private final String host;

  @Getter
  private final int port;

  @Getter
  private final String password;

  @Getter
  private final StarGateClient client;

  @Getter
  private final PluginBase plugin;

  @Getter
  private final StarGateLogger starGateLogger;

  @Getter
  private final Class<? extends CustomPacketHandler> packetHandler;

  public CommunicationClient(
    String name,
    String host,
    int port,
    String password,
    PluginBase plugin,
    Class<? extends CustomPacketHandler> packetHandler
  ) {
    this.name = name;
    this.host = host;
    this.port = port;
    this.password = password;
    this.plugin = plugin;
    this.packetHandler = packetHandler;
    this.starGateLogger = new Logger(plugin);

    InetSocketAddress address = new InetSocketAddress(host, port);

    HandshakeData handshakeData = new HandshakeData(
      name,
      password,
      HandshakeData.SOFTWARE.NUKKIT,
      STARGATE_VERSION
    );

    client = new StarGateClient(address, handshakeData, this);
    registerPackets(client);
    client.start();
  }

  private void registerPackets(StarGateClient client) {
    ProtocolCodec protocol = client.getProtocolCodec();

    protocol.registerPacket(
      ProtocolInfo.TRANSFER_PACKET,
      TransferPacket.class
    );

    protocol.registerPacket(
      ProtocolInfo.USER_UPDATE_INFORMATION,
      UserUpdateInformationPacket.class
    );

    protocol.registerPacket(
      ProtocolInfo.USER_UPDATE_AUTHENTICATION,
      UserUpdateAuthenticationPacket.class
    );

    protocol.registerPacket(
      ProtocolInfo.USER_UPDATE_PERMISSIONS,
      UserUpdatePermissionsPacket.class
    );

    protocol.registerPacket(
      ProtocolInfo.RANK_UPDATE_PERMISSIONS,
      RankUpdatePermissionsPacket.class
    );
  }
}
