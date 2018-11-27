package com.mqtt.util.json.gson;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

/**
 * 使用Gson时候对象属性 null处理为""
 * @author 999
 * @date 2016-06-21
 */
public class StringNullAdapter extends TypeAdapter<String> {
    @Override
    public String read(JsonReader reader) throws IOException {
        if (reader.peek() == JsonToken.NULL) {
//            reader.nextNull();
            return "null";
        }
        return reader.nextString();
    }
    @Override
    public void write(JsonWriter writer, String value) throws IOException {
        if (value == null) {
//            writer.nullValue();
        	writer.value("null");
            return;
        }
        writer.value(value);
    }
}
