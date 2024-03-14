package com.urise.webapp.util;

import com.urise.webapp.model.*;

public class HtmlUtil {
    public static String sectionToHtml(Section section) {
        StringBuilder sb = new StringBuilder();
        if (section instanceof TextSection) {
            sb.append(((TextSection) section).getContent());
        } else if (section instanceof ListSection) {
            sb.append("<ul>");
            for (String str : ((ListSection) section).getItems()) {
                sb.append("<li>");
                sb.append(str);
                sb.append("</li>");
            }
            sb.append("</ul>");
        } else if (section instanceof OrganizationSection) {
            for (Organization organization : ((OrganizationSection) section).getOrganizations()) {
                sb.append("<div class=\"section-wrapper\">");
                sb.append("<div class=\"job-name\">");
                //sb.append("<h4>");
                Link homePage = organization.getHomePage();
                if (homePage.getUrl() != null && !homePage.getUrl().isEmpty()) {
                    sb.append("<a href=\"").append(homePage.getUrl()).append("\">").append(homePage.getName()).append("</a>");
                } else {
                    sb.append(homePage.getName());
                }
                //sb.append("</h4>");
                sb.append("</div>");
                for (Organization.Position position : organization.getPositions()) {
                    sb.append("<div class=\"period-position\">");
                    sb.append("<div class=\"period\">");
                    sb.append(position.getStartDate().toString()).append(" - ");
                    if (position.getEndDate().equals(DateUtil.NOW)) {
                        sb.append("н.в.");
                    } else {
                        sb.append(position.getEndDate().toString());
                    }
                    sb.append("</div>");
                    sb.append("<div class=\"position\">");
                    sb.append(position.getTitle());
                    sb.append("</div>");
                    if (position.getDescription() != null) {
                        sb.append("<div class=\"description\">");
                        sb.append(position.getDescription());
                        sb.append("</div>");
                    }
                    sb.append("</div>");
                }
                sb.append("</div>");
            }
        }
        return sb.toString();
    }

    public static String formatDates(Organization.Position position) {
        return DateUtil.format(position.getStartDate()) + " - " + DateUtil.format(position.getEndDate());
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
