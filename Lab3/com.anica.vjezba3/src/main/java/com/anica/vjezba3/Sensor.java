package com.anica.vjezba3;

import java.util.concurrent.ThreadLocalRandom;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Sensor 
{
	@JsonProperty
    private final String name;
    private float value;
    @JsonProperty
    private final int factor;
    @JsonProperty
    private final int min;
    @JsonProperty
    private final int max;
    @JsonProperty
    private final String unit;
    
    @JsonCreator
    public Sensor(@JsonProperty("name") String name, @JsonProperty("factor") int factor, 
            @JsonProperty("min") int min, @JsonProperty("max") int max, 
            @JsonProperty("unit") String unit) {
        this.name = name;
        this.factor = factor;
        this.min = min;
        this.max = max;
        this.value = setData();
        this.unit = unit;
    }
   
    @JsonIgnore
	public String getData()
	{
		return this.name + ": " + Float.toString(this.value) + this.unit;
	}
	
	public float setData()
	{
		if (this.factor != 0) {
            return (ThreadLocalRandom.current().nextInt(this.min, this.max + 1)) / (float)this.factor;
        } else {
        	
            return ThreadLocalRandom.current().nextInt(this.min, this.max + 1);
        }
	}
	public String getName()
	{
		return this.name;
	}
}

