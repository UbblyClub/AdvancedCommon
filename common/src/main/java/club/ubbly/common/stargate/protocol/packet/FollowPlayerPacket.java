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
public class FollowPlayerPacket extends StarGatePacket {

  private String user;
  private String target;
  private boolean instant;

  @Override
  public void encodePayload(ByteBuf byteBuf) {
    PacketHelper.writeString(byteBuf, user);
    PacketHelper.writeString(byteBuf, target);
    PacketHelper.writeBoolean(byteBuf, instant);
  }

  @Override
  public void decodePayload(ByteBuf byteBuf) {
    user = PacketHelper.readString(byteBuf);
    target = PacketHelper.readString(byteBuf);
    instant = PacketHelper.readBoolean(byteBuf);
  }

  public byte getPacketId() {
    return ProtocolInfo.FOLLOW_PLAYER_PACKET;
  }

  @Override
  public boolean handle(StarGatePacketHandler handler) {
    if (handler instanceof CustomPacketHandler) {
      ((CustomPacketHandler) handler).handleFollow(this);

      return true;
    }

    return false;
  }

  @Override
  public boolean sendsResponse() {
    return false;
  }
}
