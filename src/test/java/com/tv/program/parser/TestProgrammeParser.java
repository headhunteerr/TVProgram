package com.tv.program.parser;

import com.tv.program.model.programmes.Programme;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertNotNull;

public class TestProgrammeParser {

    private ProgrammeParser programmeParser = new ProgrammeParser(Collections.emptyMap());
    private XMLEventReader xmlEventReader;
    private StartElement startElement;
    private InputStream xmlInputStream;

    @Before
    public void init() throws XMLStreamException {
        xmlEventReader = XMLInputFactory.newInstance()
                .createXMLEventReader(xmlInputStream = getClass().getResourceAsStream("/programmes.xml"));
        XMLEvent xmlEvent;
        startElement = null;

        do { //cherche le début des programmes
            xmlEvent = xmlEventReader.nextEvent();
            if (xmlEvent.isStartElement()) {
                startElement = xmlEvent.asStartElement();
            }

        } while (startElement == null ||
                !startElement.getName().getLocalPart()
                        .equals(ProgrammeParser.START_ELEMENT_NAME));
    }

    @After
    public void closeStreams() throws XMLStreamException, IOException {
        xmlEventReader.close();
        xmlInputStream.close();
    }

    @Test
    public void parseOne() throws  XMLStreamException {
        Programme programme = programmeParser.parseFrom(startElement, xmlEventReader);
        assertNotNull("Ne devrait pas etre null", programme);
        System.out.println(programme);
    }

    @Test
    public void parseMultiple() throws  XMLStreamException {
        List<Programme> programmes = new ArrayList<>();

        while (xmlEventReader.hasNext()) {
            Programme programme = programmeParser.parseFrom(startElement, xmlEventReader);
            assertNotNull("Ne devrait pas etre null", programme);
            programmes.add(programme);
            xmlEventReader.nextEvent(); //\n
            XMLEvent event = xmlEventReader.nextEvent();
            if (event.isStartElement()) {
                startElement = event.asStartElement();
            } else if (event.isEndElement() && event.asEndElement().getName().getLocalPart().equals("tv")) {
                break; //fin des programmes
            } else {
                throw new RuntimeException("xml mal formatté");
            }
        }

        System.out.println("length: " + programmes.size());
        System.out.println(programmes);
    }
}
