package org.ekg.front.dto.responses;

public class EkgDTO {
    private int id;
    private int tick;
    private double value;
    private String type;

    public EkgDTO() {
    }

    public EkgDTO(int id, int tick, double value, String type) {
        this.id = id;
        this.tick = tick;
        this.value = value;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
