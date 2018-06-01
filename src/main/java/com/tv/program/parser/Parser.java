package com.tv.program.parser;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;

public interface Parser<T> {
    T parseFrom(StartElement startElement, XMLEventReader eventReader) throws XMLStreamException;
}
