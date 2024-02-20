package com.urise.webapp;

import com.urise.webapp.model.*;

import java.time.Month;

public class ResumeTestData {

    public static Resume fillResume(String uuid, String fullName) {
        Resume r = new Resume(uuid, fullName);
        r.addContact(ContactType.PHONE, "phone"+uuid);
        r.addContact(ContactType.HOME_PHONE, "homephone"+uuid);
        r.addContact(ContactType.SKYPE, "skype"+uuid);
        r.addContact(ContactType.GITHUB, "github"+uuid);
        r.addContact(ContactType.LINKEDIN, "linkedin"+uuid);
        r.addContact(ContactType.MAIL, "mail"+uuid);
        r.addContact(ContactType.HOME_PAGE, "homepage"+uuid);
        r.addContact(ContactType.MOBILE, "mobile"+uuid);
        r.addContact(ContactType.STATCKOVERFLOW, "stackoverflow"+uuid);

        r.addSection(SectionType.PERSONAL, new TextSection(fullName+uuid+"Personal"));
        r.addSection(SectionType.OBJECTIVE, new TextSection(fullName+uuid+"Objective"));
        r.addSection(SectionType.QUALIFICATIONS, new ListSection(fullName+uuid+"Qualification1",
                fullName+uuid+"Qualification2"));
        r.addSection(SectionType.ACHIEVEMENT, new ListSection(fullName+uuid+"Achievement1",
                fullName+uuid+"Achievement2"));
        r.addSection(SectionType.EXPERIENCE, new OrganizationSection(
                new Organization(uuid, "https://"+uuid,
                        new Organization.Position(2015, Month.JANUARY, "Position2", "About position 2"),
                        new Organization.Position(2010, Month.JANUARY, 2014, Month.DECEMBER, "Position1", "About position 1"))));
        r.addSection(SectionType.EDUCATION, new OrganizationSection(
                new Organization(uuid+"Edu", null,
                        new Organization.Position(2008, Month.SEPTEMBER, 2009, Month.JULY, "Edu2", null),
                        new Organization.Position(2005, Month.SEPTEMBER, 2008, Month.JULY, "Edu1", null))));
        return r;
    }

    public static void main(String[] args) {
//        Resume resume = new Resume("Григорий Кислин");
//        resume.addContact(ContactType.PHONE, new Contact("Тел.: +7(921) 855-0482"));
//        resume.addContact(ContactType.SKYPE, new Contact("Skype: skype:grigory.kislin"));
//        resume.addContact(ContactType.EMAIL, new Contact("Почта: gkislin@yandex.ru"));
//        resume.addContact(ContactType.LINKEDIN, new Contact("Профиль LinkedIn", "https://www.linkedin.com/in/gkislin"));
//        resume.addContact(ContactType.GITHUB, new Contact("Профиль GitHub", "https://github.com/gkislin"));
//        resume.addContact(ContactType.STACKOVERFLOW, new Contact("Профиль Stackoverflow", "https://stackoverflow.com/users/548473"));
//        resume.addContact(ContactType.HOMEPAGE, new Contact("Домашняя страница", "http://gkislin.ru/"));
//
//        resume.addSection(SectionType.OBJECTIVE, new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));
//        resume.addSection(SectionType.PERSONAL, new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));
//        ListSection listSection = new ListSection();
//        listSection.addDescription("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
//        listSection.addDescription("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
//        listSection.addDescription("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
//        listSection.addDescription("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
//        listSection.addDescription("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
//        listSection.addDescription("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
//        listSection.addDescription("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
//        resume.addSection(SectionType.ACHIEVEMENT, listSection);
//        listSection = new ListSection();
//        listSection.addDescription("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
//        listSection.addDescription("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
//        listSection.addDescription("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
//        listSection.addDescription("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
//        listSection.addDescription("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
//        listSection.addDescription("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
//        listSection.addDescription("Python: Django.");
//        listSection.addDescription("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
//        listSection.addDescription("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
//        listSection.addDescription("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
//        listSection.addDescription("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
//        listSection.addDescription("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer");
//        listSection.addDescription("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
//        listSection.addDescription("Родной русский, английский \"upper intermediate\"");
//        resume.addSection(SectionType.QUALIFICATIONS, listSection);
//
//        WorkSection workSection = new WorkSection();
//        Work work = new Work("Java Online Projects", "http://javaops.ru/");
//        work.addWorkDescription("10/2013", "Сейчас", "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
//        workSection.addWork(work);
//        work = new Work("Wrike", "https://www.wrike.com/");
//        work.addWorkDescription("10/2014", "01/2016", "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
//        workSection.addWork(work);
//        work = new Work("RIT Center", null);
//        work.addWorkDescription("04/2012", "10/2014", "Java архитектор", "Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
//        workSection.addWork(work);
//        work = new Work("Luxoft (Deutsche Bank)", "http://www.luxoft.ru/");
//        work.addWorkDescription("12/2010", "04/2012", "Ведущий программист", "Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
//        workSection.addWork(work);
//        work = new Work("Yota", "https://www.yota.ru/");
//        work.addWorkDescription("06/2008", "12/2010", "Ведущий специалист", "Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
//        workSection.addWork(work);
//        work = new Work("Enkata", "http://enkata.com/");
//        work.addWorkDescription("03/2007", "06/2008", "Разработчик ПО", "Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
//        workSection.addWork(work);
//        work = new Work("Siemens AG", "http://www.siemens.ru/");
//        work.addWorkDescription("01/2005", "02/2007", "Разработчик ПО", "Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
//        workSection.addWork(work);
//        work = new Work("Alcatel", "http://www.alcatel.ru/");
//        work.addWorkDescription("09/1997", "01/2005", "Инженер по аппаратному и программному тестированию", "Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
//        workSection.addWork(work);
//        resume.addSection(SectionType.EXPERIENCE, workSection);
//
//        workSection = new WorkSection();
//        work = new Work("Coursera", "https://www.coursera.org/course/progfun");
//        work.addWorkDescription("03/2013", "05/2013", "'Functional Programming Principles in Scala' by Martin Odersky");
//        workSection.addWork(work);
//        work = new Work("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366");
//        work.addWorkDescription("03/2011", "04/2011", "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'");
//        workSection.addWork(work);
//        work = new Work("Siemens AG", "http://www.siemens.ru/");
//        work.addWorkDescription("01/2005", "04/2005", "3 месяца обучения мобильным IN сетям (Берлин)");
//        workSection.addWork(work);
//        work = new Work("Alcatel", "http://www.alcatel.ru/");
//        work.addWorkDescription("09/1997", "03/1998", "6 месяцев обучения цифровым телефонным сетям (Москва)");
//        workSection.addWork(work);
//        work = new Work("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/");
//        work.addWorkDescription("09/1993", "07/1996", "Аспирантура (программист С, С++)");
//        work.addWorkDescription("09/1987", "07/1993", "Инженер (программист Fortran, C)");
//        workSection.addWork(work);
//        work = new Work("Заочная физико-техническая школа при МФТИ", "https://mipt.ru/");
//        work.addWorkDescription("09/1984", "06/1987", "Закончил с отличием");
//        workSection.addWork(work);
//        resume.addSection(SectionType.EDUCATION, workSection);
//
//        resume.print();

    }
}
