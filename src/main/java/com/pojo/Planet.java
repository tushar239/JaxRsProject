package com.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Planet {
    public int id;

    public String name;

    public double radius;
    
    public String getName() {
        return name;
    }
}