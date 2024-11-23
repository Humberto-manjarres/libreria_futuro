package com.libreria.libreria.domain.model.ex;

public class BusinessException extends ApplicationException{

    public enum Type{

        ESCRITOR_NO_EXISTE("Escritor no existe!");

        private final String message;

        Type(String message){
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

    }

    public BusinessException(Type type) {
        super(type.getMessage());
    }

}
