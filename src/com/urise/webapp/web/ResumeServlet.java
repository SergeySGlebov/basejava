package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.*;
import com.urise.webapp.storage.Storage;
import com.urise.webapp.util.DateUtil;
import com.urise.webapp.util.HtmlUtil;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ResumeServlet extends HttpServlet {

    private final Storage storage = Config.get().getStorage();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uuid = request.getParameter("uuid");
        String action = request.getParameter("action");
        if (action == null) {
            request.setAttribute("resumes", storage.getAllSorted());
            request.getRequestDispatcher("/WEB-INF/jsp/list.jsp").forward(request, response);
            return;
        }
        Resume r;
        switch (action) {
            case "delete":
                storage.delete(uuid);
                response.sendRedirect("resume");
                return;
            case "view":
            case "edit":
                r = storage.get(uuid);
                break;
            case "create":
                r = new Resume();
                break;
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
        }
        if (action.equals("edit") || action.equals("create")) {
            addEmptySection(r);
        }

        request.setAttribute("resume", r);
        request.getRequestDispatcher(
                ("view".equals(action) ? "/WEB-INF/jsp/view.jsp" : "/WEB-INF/jsp/edit.jsp")
        ).forward(request, response);

      /*  request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        writeHtmlTable(response.getWriter());
     */
//        response.setHeader("Content-Type", "text/html; charset=UTF-8");
//        String name = request.getParameter("name");
//        response.getWriter().write(name == null ? "Hello Resumes!" : "Hello " + name + "!");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String uuid = request.getParameter("uuid");
        String fullName = request.getParameter("fullName");
        Resume r;
        boolean newResume = uuid.isEmpty();
        if (newResume) {
            r = new Resume(fullName);
        } else {
            r = storage.get(uuid);
        }
        r.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (!HtmlUtil.isEmpty(value)) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        for (SectionType type : SectionType.values()) {
            String value = request.getParameter(type.name());
            String[] values = request.getParameterValues(type.name());
            if (HtmlUtil.isEmpty(value) && values.length < 2) {
                r.getSections().remove(type);
                continue;
            }
            switch (type) {
                case OBJECTIVE:
                case PERSONAL:
                    r.addSection(type, new TextSection(value));
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    r.addSection(type, new ListSection(value.split("\\n")));
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    String[] urls = request.getParameterValues(type.name() + "url");
                    List<Organization> organizations = new ArrayList<>();
                    for (int i = 0; i < values.length; i++) {
                        String prfx = type.name() + i;
                        if (!HtmlUtil.isEmpty(values[i])) {
                            List<Organization.Position> positions = new ArrayList<>();
                            String[] startDates = request.getParameterValues(prfx + "startDate");
                            String[] endDates = request.getParameterValues(prfx + "endDate");
                            String[] titles = request.getParameterValues(prfx + "title");
                            String[] descriptions = request.getParameterValues(prfx + "description");
                            for (int j = 0; j < titles.length; j++) {
                                if (!HtmlUtil.isEmpty(titles[j])) {
                                    positions.add(new Organization.Position(DateUtil.parse(startDates[j]), DateUtil.parse(endDates[j]), titles[j], descriptions[j]));
                                }
                            }
                            organizations.add(new Organization(new Link(values[i], urls[i]), positions));
                        }
                    }
                    r.addSection(type, new OrganizationSection(organizations));
            }
        }

        if (newResume) {
            storage.save(r);
        } else {
            storage.update(r);
        }
        response.sendRedirect("resume");
    }

    private void addEmptySection(Resume resume) {

        for (SectionType sectionType : SectionType.values()) {
            Section section = resume.getSection(sectionType);
            switch (sectionType) {

                case OBJECTIVE:
                case PERSONAL:
                    if (section == null) {
                        resume.addSection(sectionType, new TextSection(""));
                    }
                    break;
                case ACHIEVEMENT:
                case QUALIFICATIONS:
                    if (section == null) {
                        resume.addSection(sectionType, new ListSection(""));
                    }
                    break;
                case EXPERIENCE:
                case EDUCATION:
                    Organization emptyOrganization = new Organization("", "", new Organization.Position());
                    if (section == null) {
                        resume.addSection(sectionType, new OrganizationSection(emptyOrganization));
                    } else {
                        OrganizationSection organizationSection = (OrganizationSection) resume.getSection(sectionType);
                        List<Organization> organizations = organizationSection.getOrganizations();
                        for(Organization organization : organizations) {
                            List<Organization.Position> positions = organization.getPositions();
                            positions.add(new Organization.Position());
                        }
                        organizations.add(emptyOrganization);
                    }
                    break;
            }
        }
    }

    private void writeHtmlTable(PrintWriter pw) {
        pw.write("<html>");
        pw.write("<head><title>Список резюме</title><meta charset=\"UTF-8\"></head>");
        pw.write("<body>");
        pw.write("<h1>Список резюме</h1>");
        pw.write("<table>");
        pw.write("<tr>");
        pw.write("<th>UUID</th>");
        pw.write("<th>Full Name</th>");
        pw.write("</tr>");
        for (Resume r : storage.getAllSorted()) {
            writeResumeToTable(pw, r);
        }
        pw.write("</table>");
        pw.write("</body>");
        pw.write("</html>");
    }

    private void writeResumeToTable(PrintWriter pw, Resume r) {
        pw.write("<tr>");
        pw.write("<td>" + r.getUuid() + "</td>");
        pw.write("<td>" + r.getFullName() + "</td>");
        pw.write("</tr>");
    }
}
