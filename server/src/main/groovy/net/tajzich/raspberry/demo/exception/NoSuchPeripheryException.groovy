package net.tajzich.raspberry.demo.exception

/**
 * Created with IntelliJ IDEA.
 * User: vtajzich
 * Date: 5/12/13
 */
class NoSuchPeripheryException extends CoreException {

    NoSuchPeripheryException(int address) {
        super("Cannot get periphery at address $address!")
    }
}
