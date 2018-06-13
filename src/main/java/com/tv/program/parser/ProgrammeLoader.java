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
import java.util.Iterator;
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

        Map<String, Chaine> chaineMap = new HashMap<>(); //va servir pour instancier les programmes

        ParsingStreamIterator<Chaine> chaineIterator = new ParsingStreamIterator<>(xmlEventReader, new ChaineParser());
        while (chaineIterator.hasNext()) {
            Chaine chaine = chaineIterator.next();
            chaines.add(chaine);
            chaineMap.put(chaine.getId(), chaine);
        }

        XMLEvent event = chaineIterator.getXmlEvent();
        if (!event.isStartElement() ||
                !event.asStartElement().getName().getLocalPart().equals(ProgrammeParser.START_ELEMENT_NAME)) {
            throw new ParsingException("Le fichier xml est mal formaté: les programmes sont censés" +
                    "se situer juste après les chaines");
        }

        ParsingStreamIterator<Programme> programmeIterator = new ParsingStreamIterator<>(xmlEventReader,
                new ProgrammeParser(chaineMap), event);

        while (programmeIterator.hasNext()) {
            programmes.add(programmeIterator.next());
        }
        try {
            xmlEventReader.close();
        } catch (XMLStreamException e) {
            throw new ParsingException("Erreur pendant la fermeture de l'eventReader", e);
        }
    }

    private static class ParsingStreamIterator<T> implements Iterator<T> {
        private StartElement startElement;
        private XMLEventReader xmlEventReader;
        private Parser<T> parser;
        private XMLEvent xmlEvent;

        ParsingStreamIterator(XMLEventReader xmlEventReader, Parser<T> parser, XMLEvent xmlEvent) {
            this.xmlEventReader = xmlEventReader;
            this.parser = parser;
            this.xmlEvent = xmlEvent;
            this.startElement = xmlEvent.asStartElement();
        }

        ParsingStreamIterator(XMLEventReader xmlEventReader, Parser<T> parser) {
            this.xmlEventReader = xmlEventReader;
            this.parser = parser;

            do { //cherche le début des elements
                try {
                    xmlEvent = xmlEventReader.nextEvent();
                } catch (XMLStreamException e) {
                    throw new ParsingException("Erreur pendant la recherche du début de " + parser.tagName(), e);
                }
                if (xmlEvent.isStartElement()) {
                    startElement = xmlEvent.asStartElement();
                }

            } while (startElement == null ||
                    !startElement.getName().getLocalPart()
                            .equals(parser.tagName()));
        }

        @Override
        public boolean hasNext() {
            if (!xmlEvent.isStartElement()) {
                return false;
            }
            startElement = xmlEvent.asStartElement();
            return startElement.getName().getLocalPart().equals(parser.tagName());
        }

        @Override
        public T next() {
            T object;
            try {
                object = parser.parseFrom(startElement, xmlEventReader);
                xmlEventReader.nextEvent(); //\n
                xmlEvent = xmlEventReader.nextEvent();
            } catch (XMLStreamException e) {
                throw new ParsingException(String.format("Erreur lors du parsing d'un élement de type '%s'",
                        parser.tagName()));
            }

            return object;
        }

        XMLEvent getXmlEvent() {
            return xmlEvent;
        }
    }
}
