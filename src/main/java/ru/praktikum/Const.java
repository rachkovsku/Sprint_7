package ru.praktikum;

public class Const {
    //Константы кодов ответа от бэкенда
    public final static int CREATED201  = 201;
    public final static int OK200 = 200;
    public final static int CONFLICT409 = 409;
    public final static int BAD_REQUEST400 = 400;
    public final static int NOT_FOUND404 = 404;

    //Константы ручек
    public final static String API_COURIER_CREATE = "/api/v1/courier";
    public final static String API_COURIER_LOGIN = "/api/v1/courier/login";

    public final static String API_ORDERS = "/api/v1/orders";

    public final static String API_DELETECOURIER = "/api/v1/courier/";

    public final static String BaseURI = "https://qa-scooter.praktikum-services.ru/";

}
