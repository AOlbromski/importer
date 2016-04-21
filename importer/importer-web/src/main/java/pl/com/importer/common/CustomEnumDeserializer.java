package pl.com.importer.common;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.Optional;

public class CustomEnumDeserializer<T extends CustomEnumSupport> extends JsonDeserializer<T> {

    private final Class<T> typeClass;

    @SuppressWarnings("unchecked")
    public CustomEnumDeserializer() {
        this.typeClass = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public T deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
        TreeNode treeNode = jp.getCodec().readTree(jp);
        String name = ((JsonNode) treeNode.get("name")).asText();
        if (name == null) {
            return null;
        }
        return forName(name)
                .orElseThrow(() -> ctxt.mappingException(String.format("No key %s in enum %s", name, typeClass.getName())));
    }

    private Optional<T> forName(String name) {
        return Arrays.stream(typeClass.getEnumConstants())
                .filter(t -> t.name().equals(name))
                .findFirst();
    }

}
