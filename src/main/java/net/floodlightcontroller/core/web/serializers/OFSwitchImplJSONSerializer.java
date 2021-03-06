package net.floodlightcontroller.core.web.serializers;

import java.io.IOException;

import net.floodlightcontroller.core.internal.OFSwitchImpl;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.openflow.util.HexString;

public class OFSwitchImplJSONSerializer extends JsonSerializer<OFSwitchImpl>
{
    /**
     * Handles serialization for OFSwitchImpl
     */
    @Override
    public void serialize(OFSwitchImpl switchImpl, JsonGenerator jGen, SerializerProvider arg2) throws IOException, JsonProcessingException
    {
        jGen.writeStartObject();
        jGen.writeStringField("dpid", HexString.toHexString(switchImpl.getId()));
        jGen.writeEndObject();
    }

    /**
     * Tells SimpleModule that we are the serializer for OFSwitchImpl
     */
    @Override
    public Class<OFSwitchImpl> handledType()
    {
        return OFSwitchImpl.class;
    }

}