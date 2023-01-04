package com.iut.points;

import com.iut.model.IColumn;
import com.opencsv.bean.CsvBindByName;

public class Pokemon implements IPoint {
    public static final String HEADER = "name,base_egg_steps,capture_rate,experience_growth,speed,is_legendary";

    @CsvBindByName(column = "is_legendary")
    private boolean isLegendary;
    
    @CsvBindByName(column = "name")
    private String name;

    @CsvBindByName(column = "base_egg_steps")
    private int baseEggSteps;

    @CsvBindByName(column = "capture_rate")
    private double captureRate;

    @CsvBindByName(column = "experience_growth")
    private int experienceGrowth;

    @CsvBindByName(column = "speed")
    private double speed;

    public Pokemon() {
    }

    public Pokemon(String name, int baseEggSteps, double captureRate, int experienceGrowth, double speed,
            boolean isLegendary) {
        this.name = name;
        this.baseEggSteps = baseEggSteps;
        this.captureRate = captureRate;
        this.experienceGrowth = experienceGrowth;
        this.speed = speed;
        this.isLegendary = isLegendary;
    }

    @Override
    public String toString() {
        return "Pokemon [name=" + name + ", baseEggSteps=" + baseEggSteps + ", captureRate=" + captureRate
                + ", experienceGrowth=" + experienceGrowth + ", speed=" + speed + ", isLegendary=" + isLegendary + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + baseEggSteps;
        long temp;
        temp = Double.doubleToLongBits(captureRate);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + experienceGrowth;
        temp = Double.doubleToLongBits(speed);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + (isLegendary ? 1231 : 1237);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pokemon other = (Pokemon) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (baseEggSteps != other.baseEggSteps)
            return false;
        if (Double.doubleToLongBits(captureRate) != Double.doubleToLongBits(other.captureRate))
            return false;
        if (experienceGrowth != other.experienceGrowth)
            return false;
        if (Double.doubleToLongBits(speed) != Double.doubleToLongBits(other.speed))
            return false;
        if (isLegendary != other.isLegendary)
            return false;
        return true;
    }

    @Override
    public Object getValue(IColumn col) {
        if (col.getName().equalsIgnoreCase("name"))
            return this.name;
        else if (col.getName().equalsIgnoreCase("baseeggsteps"))
            return this.baseEggSteps;
        else if (col.getName().equalsIgnoreCase("capturerate"))
            return this.captureRate;
        else if (col.getName().equalsIgnoreCase("experiencegrowth"))
            return this.experienceGrowth;
        else if (col.getName().equalsIgnoreCase("speed"))
            return this.speed;
        else
            return this.isLegendary;
    }

    @Override
    public double getNormalizedValue(IColumn xcol) {
        return xcol.getNormalizedValue(this);
    }

    @Override
    public Object getCategory() {
        return this.isLegendary;
    }

    @Override
    public String getHeader() {
        return HEADER;
    }
}
