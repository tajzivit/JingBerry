package net.tajzich.raspberry.demo.domain

import ch.qos.logback.classic.Logger
import groovy.transform.EqualsAndHashCode
import org.codehaus.jackson.annotate.JsonIgnore
import org.slf4j.LoggerFactory

/**
 * Represents Pin of device / periphery. It supports inverted or direct logic of a pin.
 *
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/8/13
 */
@EqualsAndHashCode(includes = 'number')
class Pin {

    private static final Logger LOG = LoggerFactory.getLogger(Pin)

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
