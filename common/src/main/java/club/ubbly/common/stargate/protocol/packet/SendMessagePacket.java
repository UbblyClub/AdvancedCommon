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
public class SendMessagePacket extends StarGatePacket {

  private String user;

  private boolean language;
  private String message;
  private String permission;

  private String firstArgument;
  private String secondArgument;
  private String thirdArgument;
  private String fourthArgument;
  private String fifthArgument;
  private String sixthArgument;
  private String seventhArgument;
  private String eighthArgument;
  private String ninthArgument;
  private String tenthArgument;

  @Override
  public void encodePayload(ByteBuf byteBuf) {
    PacketHelper.writeString(byteBuf, user);

    PacketHelper.writeBoolean(byteBuf, language);
    PacketHelper.writeString(byteBuf, message);
    PacketHelper.writeString(byteBuf, permission);

    PacketHelper.writeString(byteBuf, firstArgument);
    PacketHelper.writeString(byteBuf, secondArgument);
    PacketHelper.writeString(byteBuf, thirdArgument);
    PacketHelper.writeString(byteBuf, fourthArgument);
    PacketHelper.writeString(byteBuf, fifthArgument);
    PacketHelper.writeString(byteBuf, sixthArgument);
    PacketHelper.writeString(byteBuf, eighthArgument);
    PacketHelper.writeString(byteBuf, ninthArgument);
    PacketHelper.writeString(byteBuf, tenthArgument);
  }

  @Override
  public void decodePayload(ByteBuf byteBuf) {
    user = PacketHelper.readString(byteBuf);

    language = PacketHelper.readBoolean(byteBuf);
    message = PacketHelper.readString(byteBuf);
    permission = PacketHelper.readString(byteBuf);

    firstArgument = PacketHelper.readString(byteBuf);
    secondArgument = PacketHelper.readString(byteBuf);
    thirdArgument = PacketHelper.readString(byteBuf);
    fourthArgument = PacketHelper.readString(byteBuf);
    fifthArgument = PacketHelper.readString(byteBuf);
    sixthArgument = PacketHelper.readString(byteBuf);
    eighthArgument = PacketHelper.readString(byteBuf);
    ninthArgument = PacketHelper.readString(byteBuf);
    tenthArgument = PacketHelper.readString(byteBuf);
  }

  public byte getPacketId() {
    return ProtocolInfo.SEND_MESSAGE_PACKET;
  }

  @Override
  public boolean handle(StarGatePacketHandler handler) {
    if (handler instanceof CustomPacketHandler) {
      ((CustomPacketHandler) handler).handleSendMessage(this);

      return true;
    }

    return false;
  }

  @Override
  public boolean sendsResponse() {
    return false;
  }
}
