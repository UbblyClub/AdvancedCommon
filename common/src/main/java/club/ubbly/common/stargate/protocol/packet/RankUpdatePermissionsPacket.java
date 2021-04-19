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
public class RankUpdatePermissionsPacket extends StarGatePacket {

  private int rankId;

  @Override
  public void encodePayload(ByteBuf byteBuf) {
    PacketHelper.writeInt(byteBuf, rankId);
  }

  @Override
  public void decodePayload(ByteBuf byteBuf) {
    rankId = PacketHelper.readInt(byteBuf);
  }

  public byte getPacketId() {
    return ProtocolInfo.RANK_UPDATE_PERMISSIONS_PACKET;
  }

  @Override
  public boolean handle(StarGatePacketHandler handler) {
    if (handler instanceof CustomPacketHandler) {
      ((CustomPacketHandler) handler).handleRankUpdatePermissions(this);

      return true;
    }

    return false;
  }

  @Override
  public boolean sendsResponse() {
    return false;
  }
}
