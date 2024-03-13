package org.ekg.front.dto.responses;

public class SendEKGDTO {
    private double value;
    private int tick;

    public SendEKGDTO() {
    }

    public SendEKGDTO(double value, int tick) {
        this.value = value;
        this.tick = tick;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getTick() {
        return tick;
    }

    public void setTick(int tick) {
        this.tick = tick;
    }

}
