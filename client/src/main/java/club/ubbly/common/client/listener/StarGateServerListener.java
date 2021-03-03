package club.ubbly.common.client.listener;

import alemiz.stargate.client.ClientSession;
import club.ubbly.common.client.CommunicationClient;
import club.ubbly.common.stargate.handler.CustomPacketHandler;
import java.net.InetSocketAddress;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class StarGateServerListener
  extends alemiz.stargate.client.StarGateClientListener {

  private final CommunicationClient main;

  @Override
  public void onSessionCreated(
    InetSocketAddress address,
    ClientSession session
  ) {
    main.getStarGateLogger().info("Connection created on " + address);
  }

  @Override
  public void onSessionAuthenticated(ClientSession session) {
    if (main.getPacketHandler() != null) {
      session.setPacketHandler(
        CustomPacketHandler.instancePacketHandler(
          main.getPacketHandler(),
          session
        )
      );
    }

    main
      .getStarGateLogger()
      .info("Connection authenticated on " + session.getAddress());
  }

  @Override
  public void onSessionDisconnected(ClientSession session) {
    main
      .getStarGateLogger()
      .info("Connection disconnected on " + session.getAddress());
  }
}
