package net.tajzich.raspberry.demo.domain

/**
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/10/13
 */
class Bit {

    int number
    boolean set

    Bit(int number, boolean set) {
        this.number = number
        this.set = set
    }

    @Override
    String toString() {
        return "number: $number, set: $set"
    }
}
