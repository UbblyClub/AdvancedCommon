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
public class UserUpdateInformationPacket extends StarGatePacket {

  private String username;

  @Override
  public void encodePayload(ByteBuf byteBuf) {
    PacketHelper.writeString(byteBuf, username);
  }

  @Override
  public void decodePayload(ByteBuf byteBuf) {
    username = PacketHelper.readString(byteBuf);
  }

  public byte getPacketId() {
    return ProtocolInfo.USER_UPDATE_INFORMATION;
  }

  @Override
  public boolean sendsResponse() {
    return false;
  }

  @Override
  public boolean handle(StarGatePacketHandler handler) {
    if (handler instanceof CustomPacketHandler) {
      ((CustomPacketHandler) handler).handleUserUpdateInformation(this);

      return true;
    }

    return false;
  }
}
