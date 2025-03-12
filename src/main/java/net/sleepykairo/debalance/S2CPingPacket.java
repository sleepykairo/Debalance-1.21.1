package net.sleepykairo.debalance;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record S2CPingPacket(String userName) implements CustomPayload {

    public static final CustomPayload.Id<S2CPingPacket> PACKET_ID = new CustomPayload.Id<>(Identifier.of(Debalance.MOD_ID, "ping"));

    public static final PacketCodec<RegistryByteBuf, S2CPingPacket> PACKET_CODEC = PacketCodec.of((value, buf) -> {
        buf.writeString(value.userName);
    }, buf -> {
        return new S2CPingPacket(buf.readString());
    });

    @Override
    public Id<? extends CustomPayload> getId() {
        return PACKET_ID;
    }
}