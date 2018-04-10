package org.mongodb.morphia.mapping.codec;

import org.bson.codecs.Codec;
import org.bson.codecs.ValueCodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.mongodb.morphia.mapping.Mapper;

import java.util.List;

@SuppressWarnings("unchecked")
public class MorphiaCodecProvider extends ValueCodecProvider {
    private final Codec<?> arrayCodec;
    private final Mapper mapper;

    public MorphiaCodecProvider(final Mapper mapper) {
        addCodec(new KeyCodec(mapper));
        arrayCodec = new ArrayCodec(mapper);
        this.mapper = mapper;
    }

    @Override
    public <T> Codec<T> get(final Class<T> clazz, final CodecRegistry registry) {
        return clazz.isArray() ? (Codec<T>) mapper.getCodecRegistry().get(List.class) : super.get(clazz, registry);
    }

}