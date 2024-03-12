package com.urise.webapp.util;

import com.urise.webapp.ResumeTestData;
import com.urise.webapp.model.ListSection;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.Section;
import org.junit.Test;

import static org.junit.Assert.*;

public class JsonParserTest {

    @Test
    public void testResume() {
        String string = JsonParser.write(ResumeTestData.R1, Resume.class);
        System.out.println(string);
        Resume r = JsonParser.read(string, Resume.class);
        assertEquals(ResumeTestData.R1, r);
    }

    @Test
    public void testSection() {
        ListSection section1 = new ListSection("Test1", "Test2");
        String string = JsonParser.write(section1, Section.class);
        System.out.println(string);
        Section section2 = JsonParser.read(string, Section.class);
        assertEquals(section1, section2);
    }
}