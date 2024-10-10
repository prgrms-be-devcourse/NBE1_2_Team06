package com.nbe2.infra.openapi.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.nbe2.infra.openapi.dto.OpenApiResponse;
import com.nbe2.infra.openapi.dto.OpenApiResponse.Response.Body.Items;

public class ItemDeserializer extends JsonDeserializer<Items<?>> implements ContextualDeserializer {

    private JavaType valueType;

    public ItemDeserializer() {}

    private ItemDeserializer(JavaType valueType) {
        this.valueType = valueType;
    }

    @Override
    public Items<?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {

        ObjectMapper mapper = (ObjectMapper) p.getCodec();
        JsonNode node = mapper.readTree(p);

        List<Object> items = new ArrayList<>();

        JsonNode itemNode = node.get("item");
        if (itemNode.isArray()) {
            for (JsonNode subItemNode : itemNode) {
                JavaType contentType = valueType.getContentType();
                items.add(mapper.treeToValue(subItemNode, contentType));
            }
            return new OpenApiResponse.Response.Body.Items<>(items);
        } else {
            Object item = mapper.treeToValue(itemNode, valueType);
            return new OpenApiResponse.Response.Body.Items<>(item);
        }
    }

    @Override
    public JsonDeserializer<?> createContextual(
            DeserializationContext ctxt, BeanProperty property) {
        JavaType wrapperType = ctxt.getContextualType();

        if (wrapperType != null && wrapperType.containedTypeCount() > 0) {
            JavaType itemType = wrapperType.containedType(0);
            return new ItemDeserializer(itemType);
        }

        return this;
    }
}
