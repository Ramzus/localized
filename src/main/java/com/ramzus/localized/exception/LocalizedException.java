package com.ramzus.localized.exception;

/**
 * Base Exception in this package.
 *
 * @author Victor Zhivotikov
 * @author Marcel Marquez
 * @since 0.1
 */
public class LocalizedException extends Exception {
    private static final long serialVersionUID = 4329445963909360555L;

    public LocalizedException(String message) {
        super(message);
    }

    public LocalizedException(Throwable cause) {
        super(cause);
    }

    public LocalizedException(String message, Throwable cause) {
        super(message, cause);
    }
}
