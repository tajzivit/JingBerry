package net.tajzich.raspberry.demo.domain.periphery

import com.pi4j.io.i2c.I2CBus
import com.pi4j.io.i2c.I2CDevice
import com.pi4j.io.i2c.I2CFactory
import groovy.transform.CompileStatic
import net.tajzich.raspberry.demo.domain.AbstractPeriphery
import net.tajzich.raspberry.demo.domain.Pin
import net.tajzich.raspberry.demo.domain.PinPower

/**
 * Object representation of PCF 8574 I2C expander. It does have 8 pins to be set in/out and inverted logic:
 * 0 -> +5V out, 1 -> 0V
 *
 */
@CompileStatic
class PCF8574Periphery extends AbstractPeriphery {

    @Override
    void writeToHW(byte data) {

        I2CBus bus = getBus()
        I2CDevice device = bus.getDevice(address)
        device.write(data)
    }

    @Override
    void setupHWLayout() {

        pins.clear()

        8.times {
            Pin pin = new Pin(name: "P$it", number: it, inverted: true, address: PinPower.getPinPower(it))
            pin.low()

            pins << pin
        }
    }

    I2CBus getBus() {
        return I2CFactory.getInstance(I2CBus.BUS_1)
    }
}
