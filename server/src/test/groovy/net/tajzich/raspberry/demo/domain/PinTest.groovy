package net.tajzich.raspberry.demo.domain

import org.junit.Before
import org.junit.Test

/**
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/11/13
 */
class PinTest {

    Pin pin

    @Before
    public void setup() {

        pin = new Pin()
    }

    @Test
    void testIsHighNoVoltageNotInverted() {

        pin.inverted = false
        pin.voltage = false

        assert pin.isHigh() == false
    }

    @Test
    void testIsHighVoltageNotInverted() {

        pin.inverted = false
        pin.voltage = true

        assert pin.isHigh()
    }

    @Test
    void testIsHighNoVoltageInverted() {

        pin.inverted = true
        pin.voltage = false

        assert pin.isHigh()
    }

    @Test
    void testIsHighVoltageInverted() {

        pin.inverted = true
        pin.voltage = true

        assert pin.isHigh() == false
    }

    @Test
    void testHighNotInvertedSetToLow() {

        Pin pin = new Pin(inverted: false)
        pin.low()

        assert pin.isHigh() == false
        assert pin.voltage == false
    }

    @Test
    void testHighNotInvertedSetToHigh() {

        Pin pin = new Pin(inverted: false)
        pin.high()

        assert pin.isHigh()
        assert pin.voltage
    }

    @Test
    void testHighInvertedSetToLow() {

        Pin pin = new Pin(inverted: true)
        pin.low()

        assert pin.isHigh() == false
        assert pin.voltage
    }

    @Test
    void testHighInvertedSetToHigh() {

        Pin pin = new Pin(inverted: true)
        pin.high()

        assert pin.isHigh()
        assert pin.voltage == false
    }
}
