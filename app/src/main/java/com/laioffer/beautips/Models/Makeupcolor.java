package com.laioffer.beautips.Models;

import java.util.Objects;

public class Makeupcolor {
    String hex_value;
    String colour_name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Makeupcolor that = (Makeupcolor) o;
        return Objects.equals(hex_value, that.hex_value) &&
                Objects.equals(colour_name, that.colour_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hex_value, colour_name);
    }

    public String getHex_value() {
        return hex_value;
    }

    public void setHex_value(String hex_value) {
        this.hex_value = hex_value;
    }

    public String getColour_name() {
        return colour_name;
    }

    public void setColour_name(String colour_name) {
        this.colour_name = colour_name;
    }

    public Makeupcolor() {
    }
}
