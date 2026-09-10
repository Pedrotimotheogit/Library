package Exceptions;

import java.util.InputMismatchException;

public class ValidOption extends RuntimeException {
    public ValidOption() throws InputMismatchException {
        throw new InputMismatchException("Invalid option selected.");
    }
}
