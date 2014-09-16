package net.tajzich.raspberry.demo.domain.periphery

import org.junit.Before
import org.junit.Test

class PCF8574PeripheryTest {

    PCF8574Periphery periphery

    @Before
    void setUp() {
        periphery = new PCF8574Periphery()
    }

    @Test
    void testSetupHWLayout() {

        periphery.setupHWLayout()

        assert 8 == periphery.pins.size()

        assert 1 == periphery.pins[0].address
        assert 2 == periphery.pins[1].address
        assert 4 == periphery.pins[2].address
        assert 8 == periphery.pins[3].address
        assert 16 == periphery.pins[4].address
        assert 32 == periphery.pins[5].address
        assert 64 == periphery.pins[6].address
        assert 128 == periphery.pins[7].address
    }
}
