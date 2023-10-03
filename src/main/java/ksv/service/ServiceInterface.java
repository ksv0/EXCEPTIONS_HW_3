package ksv.service;

import ksv.exceptions.IncorrectlyDataException;

public interface ServiceInterface {
    public  void save(String[] str);

    public  String[] parse(String str) throws IncorrectlyDataException;

    boolean check(String[] strings) throws IncorrectlyDataException;


}
