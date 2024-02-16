package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class ListSection extends Section {

    private final List<String> listOfDescriptions = new ArrayList<>();

    public List<String> getListOfDescriptions() {
        return listOfDescriptions;
    }

    public void addDescription(String description) {
        listOfDescriptions.add(description);
    }

    @Override
    public void printText() {
        for (String description : listOfDescriptions) {
            System.out.println(description);
        }
    }
}
