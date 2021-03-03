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
public class UserUpdateAuthenticationPacket extends StarGatePacket {

  private String username;
  private boolean value;

  @Override
  public void encodePayload(ByteBuf byteBuf) {
    PacketHelper.writeString(byteBuf, username);
    PacketHelper.writeBoolean(byteBuf, value);
  }

  @Override
  public void decodePayload(ByteBuf byteBuf) {
    username = PacketHelper.readString(byteBuf);
    value = PacketHelper.readBoolean(byteBuf);
  }

  public byte getPacketId() {
    return ProtocolInfo.USER_UPDATE_AUTHENTICATION;
  }

  @Override
  public boolean handle(StarGatePacketHandler handler) {
    if (handler instanceof CustomPacketHandler) {
      ((CustomPacketHandler) handler).handleUserUpdateAuthentication(this);

      return true;
    }

    return false;
  }

  @Override
  public boolean sendsResponse() {
    return false;
  }
}
