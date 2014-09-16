package net.tajzich.raspberry.demo.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.CompileStatic
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@CompileStatic
public abstract class AbstractDevice implements Device {

    private static final Logger LOG = LoggerFactory.getLogger(AbstractDevice)

    String name
    List<Pin> pins = []
    List<Periphery> peripherals = []

    @Override
    Pin getPin(int number) {
        return pins.find {it.number == number}
    }

    @JsonIgnore
    void write(List<Pin> pins) {

        pins.each { newPin ->

            Pin pin = this.pins.find {newPin.number == it.number}

            LOG.debug("writing new valu for pin: $pin")

            pin.write(newPin.voltage)
        }
    }

    Periphery getPeriphery(int address) {
        return peripherals.find { it.address == address }
    }
}
