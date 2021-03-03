package club.ubbly.common.server.stargate.listener;

import alemiz.stargate.server.ServerSession;
import club.ubbly.common.server.stargate.CommunicationServer;
import dev.waterdog.plugin.Plugin;
import java.net.InetSocketAddress;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StarGateServerListener
  extends alemiz.stargate.server.StarGateServerListener {

  private final CommunicationServer main;

  @Override
  public boolean onSessionCreated(
    InetSocketAddress address,
    ServerSession session
  ) {
    main.getStarGateLogger().info("Connection created from " + address);

    return true;
  }

  @Override
  public void onSessionAuthenticated(ServerSession session) {
    if (main.getPacketHandler() != null) {
      session.setPacketHandler(main.instancePacketHandler(session));
    }

    main
      .getStarGateLogger()
      .info("Connection authenticated from " + session.getAddress());
  }

  @Override
  public void onSessionDisconnected(ServerSession session) {
    main
      .getStarGateLogger()
      .info("Connection disconnected from " + session.getAddress());
  }
}
