package com.urise.webapp.web;

import com.urise.webapp.Config;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.storage.Storage;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

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
            default:
                throw new IllegalArgumentException("Action " + action + " is illegal");
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
        Resume r = storage.get(uuid);
        r.setFullName(fullName);
        for (ContactType type : ContactType.values()) {
            String value = request.getParameter(type.name());
            if (value != null && value.trim().length() != 0) {
                r.addContact(type, value);
            } else {
                r.getContacts().remove(type);
            }
        }
        storage.update(r);
        response.sendRedirect("resume");
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
