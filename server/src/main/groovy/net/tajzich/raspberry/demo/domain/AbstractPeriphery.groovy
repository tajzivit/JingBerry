package net.tajzich.raspberry.demo.domain

import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import groovy.transform.CompileStatic
import groovy.transform.ToString
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@CompileStatic
@ToString(includes = ['name', 'address'])
@JsonAutoDetect(getterVisibility = JsonAutoDetect.Visibility.NONE, isGetterVisibility = JsonAutoDetect.Visibility.NONE)
public abstract class AbstractPeriphery implements Periphery {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractPeriphery)

    @JsonProperty
    String name

    @JsonProperty
    int address

    @JsonProperty
    List<Pin> pins = []

    void init() {
        setupHWLayout()
        clearOutput()
    }

    @Override
    Pin getPin(int number) {
        return pins.find { it.number == number }
    }

    void write(byte data) {
        writeToHW(data)
    }

    byte write() {

        def bits = pins.collect { new Bit(it.number, it.voltage) }

        byte data = PinPower.getValueInByte(bits)

        LOG.info("[address=\"$address\"] Writing data '${data & 0xff}'")
        LOG.info("Pins status: $pins")
        LOG.info("Bits: $bits")

        write(data)

        return data
    }

    @Override
    byte setOutput(List<Pin> pins) {

        pins.each {

            Pin pin = findPin(it.number, it.address)
            pin.voltage = it.voltage
        }

        return write()
    }

    Pin findPin(int number, int address) {
        return pins.find { it.number == number && it.address == address }
    }

    /**
     * Write given data to real HW
     * @param data data to be written
     */
    abstract void writeToHW(byte data)

    /**
     * Let's the real periphery implementation to setup it's HW layout.
     * e.g. create a pins etc
     */
    abstract void setupHWLayout()

    /**
     * set all pins to logic LOW
     */
    void clearOutput() {

        pins.each { it.low() }

        LOG.info("[pins=\"$pins\"] Clearing output.")

        write()
    }

    void toggle(int pinNumber) {

        Pin pin = pins.find { it.number == pinNumber }
        pin.voltage = !pin.voltage

        write()
    }
}
