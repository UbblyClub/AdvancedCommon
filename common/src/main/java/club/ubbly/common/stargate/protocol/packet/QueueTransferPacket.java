package club.ubbly.common.stargate.protocol.packet;

import alemiz.stargate.handler.StarGatePacketHandler;
import alemiz.stargate.protocol.StarGatePacket;
import alemiz.stargate.protocol.types.PacketHelper;
import club.ubbly.common.stargate.handler.CustomPacketHandler;
import club.ubbly.common.stargate.protocol.ProtocolInfo;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class QueueTransferPacket extends StarGatePacket {

  private String username;
  private String serverName;

  @Override
  public void encodePayload(ByteBuf byteBuf) {
    PacketHelper.writeString(byteBuf, username);
    PacketHelper.writeString(byteBuf, serverName);
  }

  @Override
  public void decodePayload(ByteBuf byteBuf) {
    username = PacketHelper.readString(byteBuf);
    serverName = PacketHelper.readString(byteBuf);
  }

  public byte getPacketId() {
    return ProtocolInfo.QUEUE_TRANSFER_PACKET;
  }

  @Override
  public boolean handle(StarGatePacketHandler handler) {
    if (handler instanceof CustomPacketHandler) {
      ((CustomPacketHandler) handler).handleQueueTransfer(this);

      return true;
    }

    return false;
  }

  @Override
  public boolean sendsResponse() {
    return false;
  }
}
