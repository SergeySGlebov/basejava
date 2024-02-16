package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class Work {

    private final String title;
    private final String link;
    private final List<WorkDescription> workDescriptionList = new ArrayList<>();

    public Work(String title, String link) {
        this.title = title;
        this.link = link;
    }

    public void addWorkDescription(String startDate, String endDate, String title, String additional) {
        workDescriptionList.add(new WorkDescription(startDate, endDate, title, additional));
    }

    public void addWorkDescription(String startDate, String endDate, String title) {
        workDescriptionList.add(new WorkDescription(startDate, endDate, title, null));
    }

    public void printText() {
        System.out.println(title);
        for (WorkDescription workDescription : workDescriptionList) {
            System.out.println(workDescription.startDate + " - " + workDescription.endDate + " " + workDescription.title);
            if (workDescription.additional != null) {
                System.out.println(workDescription.additional);
            }
        }
    }

    private static class WorkDescription {
        private final String startDate;
        private final String endDate;
        private final String title;
        private final String additional;

        public WorkDescription(String startDate, String endDate, String title, String additional) {
            this.startDate = startDate;
            this.endDate = endDate;
            this.title = title;
            this.additional = additional;
        }
    }
}
