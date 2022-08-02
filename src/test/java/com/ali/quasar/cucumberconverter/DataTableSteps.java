package com.ali.quasar.cucumberconverter;

import com.ali.quasar.entities.Location;
import com.ali.quasar.entities.SatelliteMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.DataTableType;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class DataTableSteps {

    private final ObjectMapper objectMapper =
            new ObjectMapper();

    @DefaultParameterTransformer
    @DefaultDataTableEntryTransformer(replaceWithEmptyString = "[blank]")
    @DefaultDataTableCellTransformer
    public Object defaultTransformer(Object fromValue, Type toValueType) {
        JavaType javaType = objectMapper.constructType(toValueType);
        return objectMapper
                .convertValue(fromValue, javaType);
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public SatelliteMessage satelliteMessageTransform(Map<String, String> map) throws JsonProcessingException {

        return SatelliteMessage.builder().name(map.get("name")).distance(Double.parseDouble(map.get("distance")))
                .message(
                        Arrays.asList(map.get("message").split(",")).stream().map(e ->
                        e.replace("_", "")).collect(Collectors.toList())).build();
    }

    @DataTableType(replaceWithEmptyString = "[blank]")
    public Location locationTransform(Map<String, String> map) throws JsonProcessingException {

        return Location.builder().x(Double.parseDouble(map.get("x"))).y(Double.parseDouble(map.get("y"))).build();
    }
}
