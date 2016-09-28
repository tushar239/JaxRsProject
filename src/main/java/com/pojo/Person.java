package com.pojo;

import org.codehaus.jackson.annotate.JsonAutoDetect;
import org.codehaus.jackson.annotate.JsonProperty;
@JsonAutoDetect
public class Person {
    @JsonProperty("nm")
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Person [Name=" + name +"]";
    }
}
