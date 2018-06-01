package com.tv.program.parser;

import com.tv.program.model.Programme;
import org.junit.Before;
import org.junit.Test;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestProgrammeParser {

    private ProgrammeParser programmeParser = new ProgrammeParser(Collections.emptyMap());
    private XMLEventReader xmlEventReader;
    private StartElement startElement;

    @Before
    public void init() throws XMLStreamException {
        xmlEventReader = XMLInputFactory.newInstance()
                .createXMLEventReader(getClass().getResourceAsStream("/programmes.xml"));
        XMLEvent xmlEvent;
        startElement = null;
        do {
            xmlEvent = xmlEventReader.nextEvent();
            if (xmlEvent.isStartElement()) {
                startElement = xmlEvent.asStartElement();
            }

        } while (startElement == null || !startElement.getName().getLocalPart().equals("programme"));

    }

    @Test
    public void parseOne() throws  XMLStreamException {
        Programme programme = programmeParser.parseFrom(startElement, xmlEventReader);
        assertTrue("Ne devrait pas etre null", programme != null);
        System.out.println(programme);
    }

    @Test
    public void parseMultiple() throws  XMLStreamException {
        List<Programme> programmes = new ArrayList<>();

        while (xmlEventReader.hasNext()) {
            Programme programme = programmeParser.parseFrom(startElement, xmlEventReader);
            assertTrue("Ne devrait pas etre null", programme != null);
            programmes.add(programme);
            //TODO NE MARCHE PAS A CAUSE D'UN TYPE INCONNU CONTEMPORAIN
            /**
             * EST CE VRAIMENT UTILE D'AVOIR UNE CLASSE PROGRAMME ABSTRAITE???
             */
        }

        System.out.println(programmes);
    }
}
