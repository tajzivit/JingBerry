package net.tajzich.raspberry.demo.rest.dto

import net.tajzich.raspberry.demo.domain.Pin

/**
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/12/13
 */
class PinDto {

    int number
    String name
    boolean high

    PinDto() {
    }

    PinDto(Pin domain) {

        this.number = domain.number
        this.name = domain.name
        this.high = domain.isHigh()
    }

    boolean isHigh() {
        return high
    }
}
