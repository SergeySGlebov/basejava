package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            writeCollection(dos, r.getContacts().entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            writeCollection(dos, r.getSections().entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                switch (entry.getKey()) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(((TextSection) entry.getValue()).getContent());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ListSection) entry.getValue()).getItems(), dos::writeUTF);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        writeCollection(dos, ((OrganizationSection) entry.getValue()).getOrganizations(), organization -> {
                            dos.writeUTF(organization.getHomePage().getName());
                            String url = organization.getHomePage().getUrl();
                            dos.writeUTF((url == null ? "null" : url));
                            writeCollection(dos, organization.getPositions(), position -> {
                                dos.writeUTF(position.getStartDate().toString());
                                dos.writeUTF(position.getEndDate().toString());
                                dos.writeUTF(position.getTitle());
                                String description = position.getDescription();
                                dos.writeUTF((description == null ? "null" : description));
                            });
                        });
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItem(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));
            readItem(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new TextSection(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListSection(readList(dis, dis::readUTF));
            case EDUCATION:
            case EXPERIENCE:
                return new OrganizationSection(
                        readList(dis, () -> new Organization(
                                new Link(dis.readUTF(), getStringOrNull(dis.readUTF())),
                                readList(dis, () -> new Organization.Position(
                                        LocalDate.parse(dis.readUTF()), LocalDate.parse(dis.readUTF()), dis.readUTF(), getStringOrNull(dis.readUTF()))))));
            default:
                return null;
        }
    }

    private String getStringOrNull(String string) {
        return ("null".equals(string)) ? null : string;
    }

    interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    interface ElementReader<T> {
        T reade() throws IOException;
    }

    interface ElementProcessor {
        void process() throws IOException;
    }

    private void readItem(DataInputStream dis, ElementProcessor elementProcessor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            elementProcessor.process();
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> elementReader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(elementReader.reade());
        }
        return list;
    }

    private <T> void writeCollection(DataOutputStream
                                             dos, Collection<T> collection, ElementWriter<T> elementWriter) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            elementWriter.write(item);
        }
    }
}

