package com.nbe2.infra.openapi.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nbe2.infra.openapi.dto.OpenApiResponse;
import com.nbe2.infra.openapi.dto.RealTimeEmergencyDataResponse;

public class ItemDeserializer
        extends JsonDeserializer<
                OpenApiResponse.Response.Body.Items<List<RealTimeEmergencyDataResponse>>> {

    @Override
    public OpenApiResponse.Response.Body.Items<List<RealTimeEmergencyDataResponse>> deserialize(
            JsonParser p, DeserializationContext ctxt) throws IOException {

        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        List<RealTimeEmergencyDataResponse> items = new ArrayList<>();

        JsonNode itemNode = node.get("item");
        if (itemNode.isArray()) {
            for (JsonNode subItemNode : itemNode) {
                RealTimeEmergencyDataResponse item =
                        mapper.treeToValue(subItemNode, RealTimeEmergencyDataResponse.class);
                items.add(item);
            }
        } else {
            RealTimeEmergencyDataResponse item =
                    mapper.treeToValue(itemNode, RealTimeEmergencyDataResponse.class);
            items.add(item);
        }

        return new OpenApiResponse.Response.Body.Items<>(items);
    }
}
