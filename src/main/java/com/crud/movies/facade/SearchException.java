package com.crud.movies.facade;

public class SearchException extends Exception{
    public static String ERR_TITLE_FRAGMENT_IS_NULL = "TITLE FRAGMENT CAN'T BE NULL";
    public static String ERR_NAME_FRAGMENT_IS_NULL = "NAME FRAGMENT CAN'T BE NULL";

    public SearchException(String message) {
        super(message);
    }
}

