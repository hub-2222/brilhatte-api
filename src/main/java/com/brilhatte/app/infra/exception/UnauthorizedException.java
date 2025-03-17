package com.brilhatte.app.infra.exception;

public class UnauthorizedException extends RuntimeException {

        public UnauthorizedException(String message) {
            super(message);
        }
}
