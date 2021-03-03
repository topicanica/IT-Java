package com.anica.vjezba2;
import java.util.concurrent.ThreadLocalRandom;

public class Sensor 
{
	private String name = "";
	private int value = 0;
	private int factor=0;
	private int min=0;
	private int max=0;
	private String unit = "";
	
	public Sensor(String name, int factor, int min,int max, String unit )
	{
		this.name = name;
		this.factor = factor;
		if (factor != 0){
			this.min = min/factor;
			this.max = max/factor;
		}else {
			this.min = min;
			this.max = max;
		}
		this.value = setData();
		this.unit=unit;
	}
	public String getData()
	{
		return this.name + ": " + Integer.toString(this.value) + this.unit;
	}
	public int setData()
	{
		return ThreadLocalRandom.current().nextInt(this.min,this.max + 1);
	}
	public String getName()
	{
		return this.name;
	}
}

