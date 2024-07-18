//package com.example.glife.config;
//
//import com.fasterxml.jackson.core.JsonGenerator;
//import com.fasterxml.jackson.databind.JsonSerializer;
//import com.fasterxml.jackson.databind.SerializerProvider;
//import com.fasterxml.jackson.databind.module.SimpleModule;
//import com.fasterxml.jackson.databind.ser.std.StdSerializer;
//import org.springframework.boot.jackson.JsonComponent;
//import org.springframework.data.geo.Point;
//
//import java.io.IOException;
//
//@JsonComponent
//public class PointJsonSerializer {
//
//    public static class Serializer extends StdSerializer<Point> {
//
//        public Serializer() {
//            this(null);
//        }
//
//        protected Serializer(Class<Point> t) {
//            super(t);
//        }
//
//        @Override
//        public void serialize(Point value, JsonGenerator gen, SerializerProvider provider) throws IOException {
//            gen.writeStartObject();
//            gen.writeNumberField("x", value.getX());
//            gen.writeNumberField("y", value.getY());
//            gen.writeEndObject();
//        }
//    }
//
//    public static class Deserializer extends JsonSerializer<Point> {
//
//        @Override
//        public void serialize(Point value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
//            gen.writeStartObject();
//            gen.writeNumberField("x", value.getX());
//            gen.writeNumberField("y", value.getY());
//            gen.writeEndObject();
//        }
//    }
//}
