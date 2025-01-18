package com.libreria.libreria.domain.model.ex;

public class BusinessException extends ApplicationException{

    public enum Type{

        ESCRITOR_NO_EXISTE("Escritor no existe!"),
        EDITORIAL_NO_EXISTE("Editorial no existe!"),
        CATEGORIA_NO_EXISTE("Categoria no existe!"),
        LIBRO_NO_EXISTE("Libro no existe!"),

        SERVICE_SERVER_ERROR("Ocurri\u00f3 un error al consumir el servicio"),
        SERVICE_CLIENT_ERROR("El servicio no pudo procesar la solicitud por error en los datos de entrada");

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
