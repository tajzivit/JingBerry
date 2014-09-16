package net.tajzich.raspberry.demo.domain

/**
 * Periphery interface
 */
public interface Periphery extends PinDevice {

    /**
     * @return name of periphery
     */
    String getName()

    /**
     * @return address of periphery
     */
    int getAddress()

    /**
     * Writes give data to real HW
     * @param data data to be written
     */
    void write(byte data)

    /**
     * Toggle pin's voltage
     * @param pinNumber
     */
    void toggle(int pinNumber)

    /**
     * Same as (@Link Periphery.write) it byte data will be generated against pins
     * @param pins
     * @return real written data
     */
    byte setOutput(List<Pin> pins)
}
