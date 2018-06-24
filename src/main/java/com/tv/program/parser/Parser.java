package com.tv.program.parser;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;

/**
 * parse un element d'un fichier xml pour le convertir en objet Java
 * @param <T> type d'objet généré par le parser
 */
public interface Parser<T> {
    T parseFrom(StartElement startElement, XMLEventReader eventReader) throws XMLStreamException;
    String tagName();
}
