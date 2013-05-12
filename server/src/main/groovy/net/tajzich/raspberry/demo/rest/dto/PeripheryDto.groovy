package net.tajzich.raspberry.demo.rest.dto

import net.tajzich.raspberry.demo.domain.Periphery

/**
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/12/13
 */
class PeripheryDto {

    String name
    int address
    List<PinDto> pins = []

    PeripheryDto() {
    }

    PeripheryDto(Periphery domain) {

        this.name = domain.name
        this.address = domain.address

        domain.pins.each {
            pins << new PinDto(it)
        }
    }
}
