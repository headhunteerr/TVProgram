package com.tv.program.parser;

import com.tv.program.model.Chaine;
import com.tv.program.model.programmes.Programme;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProgrammeLoader {

    public static void load(InputStream xmlStream, List<Chaine> chaines, List<Programme> programmes) {
        XMLEventReader xmlEventReader;
        try {
            xmlEventReader = XMLInputFactory.newInstance()
                    .createXMLEventReader(xmlStream);
        } catch (XMLStreamException e) {
            throw new ParsingException("Erreur pendant l'instanciation de xmlEventReader", e);
        }
        XMLEvent xmlEvent;
        StartElement startElement = null;

        do { //cherche le début des chaines
            try {
                xmlEvent = xmlEventReader.nextEvent();
            } catch (XMLStreamException e) {
                throw new ParsingException("Erreur pendant la recherche des chaines", e);
            }
            if (xmlEvent.isStartElement()) {
                startElement = xmlEvent.asStartElement();
            }

        } while (startElement == null ||
                !startElement.getName().getLocalPart()
                        .equals(ChaineParser.START_ELEMENT_NAME));

        ChaineParser chaineParser = new ChaineParser();
        Map<String, Chaine> chaineMap = new HashMap<>();
        while (xmlEventReader.hasNext() && (!xmlEvent.isStartElement() || !xmlEvent.asStartElement().getName().getLocalPart().equals(ProgrammeParser.START_ELEMENT_NAME))) {
            try {
                Chaine chaine = chaineParser.parseFrom(startElement, xmlEventReader);
                chaines.add(chaine);
                chaineMap.put(chaine.getId(), chaine);
                xmlEventReader.nextEvent(); //\n
                xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()) {
                    startElement = xmlEvent.asStartElement();
                } else if (xmlEvent.isEndElement() && xmlEvent.asEndElement().getName().getLocalPart().equals("tv")) {
                    break; //fin des programmes
                } else {
                    throw new ParsingException("xml mal formatté");
                }
            } catch (XMLStreamException e) {
                throw new ParsingException("Erreur pendant le chargement des chaines", e);
            }
        }

        ProgrammeParser programmeParser = new ProgrammeParser(chaineMap);

        while (xmlEventReader.hasNext()) {
            try {
                Programme programme = programmeParser.parseFrom(startElement, xmlEventReader);
                programmes.add(programme);
                xmlEventReader.nextEvent(); //\n
                XMLEvent event = xmlEventReader.nextEvent();
                if (event.isStartElement()) {
                    startElement = event.asStartElement();
                } else if (event.isEndElement() && event.asEndElement().getName().getLocalPart().equals("tv")) {
                    break; //fin des programmes
                } else {
                    throw new ParsingException("xml mal formatté");
                }
            } catch (XMLStreamException e) {
                throw new ParsingException("Erreur pendant le chargement des programmes", e);
            }
        }
    }
}
