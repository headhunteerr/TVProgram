package com.tv.program.parser;

import com.tv.program.model.Chaine;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@FixMethodOrder(MethodSorters.JVM)
public class TestChaineParser {

    private ChaineParser chaineParser = new ChaineParser();
    private XMLEventReader xmlEventReader;
    private StartElement startElement;
    private InputStream xmlInputStream;

    @Before
    public void init() throws XMLStreamException {
        xmlEventReader = XMLInputFactory.newInstance()
                .createXMLEventReader(xmlInputStream = getClass().getResourceAsStream("/chaines.xml"));
        XMLEvent xmlEvent;
        startElement = null;

        do { //cherche le début des chaines
            xmlEvent = xmlEventReader.nextEvent();
            if (xmlEvent.isStartElement()) {
                startElement = xmlEvent.asStartElement();
            }

        } while (startElement == null ||
                !startElement.getName().getLocalPart()
                        .equals(ChaineParser.START_ELEMENT_NAME));

    }

    @After
    public void closeStreams() throws XMLStreamException, IOException {
        xmlEventReader.close();
        xmlInputStream.close();
    }

    @Test
    public void parseOne() throws  XMLStreamException {
        Chaine chaine = chaineParser.parseFrom(startElement, xmlEventReader);
        assertNotNull("Ne devrait pas etre null", chaine);
        System.out.println(chaine);
    }

    @Test
    public void parseMultiple() throws  XMLStreamException {
        List<Chaine> chaines = new ArrayList<>();

        while (xmlEventReader.hasNext()) {
            Chaine chaine = chaineParser.parseFrom(startElement, xmlEventReader);
            assertNotNull("Ne devrait pas etre null", chaine);
            chaines.add(chaine);
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

        System.out.println(chaines);
        System.out.println("length: " + chaines.size());
    }
}
