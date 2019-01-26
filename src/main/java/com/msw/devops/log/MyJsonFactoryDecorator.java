package com.msw.devops.log;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.MappingJsonFactory;
import net.logstash.logback.decorate.JsonFactoryDecorator;

/**
 * @author mashuangwei
 * @date 2018-12-17 12:14
 * @description:
 */
public class MyJsonFactoryDecorator implements JsonFactoryDecorator {
    @Override
    public MappingJsonFactory decorate(MappingJsonFactory factory) {
        // 禁用对非ascii码进行escape编码的特性
        factory.disable(JsonGenerator.Feature.ESCAPE_NON_ASCII);
        return factory;
    }
}
