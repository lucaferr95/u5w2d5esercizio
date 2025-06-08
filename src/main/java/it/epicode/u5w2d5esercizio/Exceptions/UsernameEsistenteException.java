package it.epicode.u5w2d5esercizio.Exceptions;

public class UsernameEsistenteException extends RuntimeException {
    public UsernameEsistenteException(String message) {
        super(message);
    }
}
