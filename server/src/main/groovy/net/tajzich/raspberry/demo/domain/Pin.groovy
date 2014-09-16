package net.tajzich.raspberry.demo.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.CompileStatic
import groovy.transform.EqualsAndHashCode

/**
 * Represents Pin of device / periphery. It supports inverted or direct logic of a pin.
 */
@CompileStatic
@EqualsAndHashCode(includes = 'number')
class Pin {

    int number
    int address
    String name
    boolean voltage
    boolean inverted

    /**
     * @return true if logic value is HIGH otherwise false
     */
    @JsonIgnore
    boolean isHigh() {

        if (inverted) {
            return !voltage
        } else {
            return voltage
        }
    }

    /**
     * Sets pin to high logic value
     */
    void high() {

        if(inverted) {
            voltage = false
        } else {
            voltage = true
        }
    }

    /**
     * Sets pin to low logic value
     */
    void low() {

        if(inverted) {
            voltage = true
        } else {
            voltage = false
        }
    }

    /**
     * @return true if Voltage is +X V otherwise false. NOTICE: it doesn't mean that pin is at HIGH logic level.
     * Use @Link Pin.isHigh() method instead!
     */
    boolean isVoltage() {
        return voltage
    }

    /**
     * @return true if logic is inverted
     */
    boolean isInverted() {
        return inverted
    }

    void write(boolean voltage) {
        this.setVoltage(voltage)
    }

    @Override
    String toString() {
        return "number: $number, high: ${isHigh()}"
    }
}
