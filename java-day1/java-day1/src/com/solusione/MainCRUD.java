package com.solusione;

import com.solusione.interfaces.CRUDInterface;

public class MainCRUD implements CRUDInterface {
    @Override
    public void create(String create) {
        System.out.println(create);
    }

    @Override
    public void read(String read) {
        System.out.println(read);
    }

    @Override
    public void update(String update) {
        System.out.println(update);
    }

    @Override
    public void delete(String delete) {
        System.out.println(delete);
    }
}
