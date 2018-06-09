package com.tv.program.parser;

import com.tv.program.model.Chaine;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

class ChaineParser implements Parser<Chaine> {
    static final String START_ELEMENT_NAME = "channel";

    @Override
    public Chaine parseFrom(StartElement startElement, XMLEventReader eventReader) throws XMLStreamException {
        String id = startElement.getAttributeByName(new QName("id"))
                .getValue();
        String nom = null;

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartElement()) {
                StartElement element = event.asStartElement();
                switch (element.getName().getLocalPart()) {
                    case "display-name":
                        event = eventReader.nextEvent();
                        nom = event.asCharacters().getData();
                        break;
                }
            }
            if(event.isEndElement()){
                EndElement endElement = event.asEndElement();
                if(endElement.getName().getLocalPart().equals(START_ELEMENT_NAME)){
                    return new Chaine(nom, id);
                }
            }
        }

        return null;
    }
}
