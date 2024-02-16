package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class WorkSection extends Section {
    private final List<Work> listOfWorks = new ArrayList<>();

    public List<Work> getListOfWorks() {
        return listOfWorks;
    }

    public void addWork(Work work) {
        listOfWorks.add(work);
    }

    @Override
    public void printText() {
        for (Work work : listOfWorks) {
            work.printText();
        }
    }
}
