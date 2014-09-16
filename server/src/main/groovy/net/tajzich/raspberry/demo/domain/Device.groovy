package net.tajzich.raspberry.demo.domain

import net.tajzich.raspberry.demo.exception.NoSuchPeripheryException

/**
 * Device contains peripherals, pins etc
 */
public interface Device extends PinDevice {

    /**
     * @return name of the device
     */
    String getName()

    /**
     * @return list of connected peripherals
     */
    List<Periphery> getPeripherals()

    /**
     *
     * @param address address of periphery
     * @return periphery for given address
     * @throws NoSuchPeripheryException
     */
    Periphery getPeriphery(int address) throws NoSuchPeripheryException

    /**
     * Writes voltage to real pins
     *
     * @param pins pin list to be updated
     */
    void write(List<Pin> pins)
}
