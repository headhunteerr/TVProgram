package com.tv.program.parser;

import com.tv.program.model.Chaine;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

/**
 * Classe permettant de parser des chaines dans un fichier xml
 */
class ChaineParser implements Parser<Chaine> {
    static final String START_ELEMENT_NAME = "channel";

    /**
     *
     * @param startElement la balise de debut d'un element channel
     * @param eventReader un event reader permettant de parcourir le fichier xml
     * @return la chaine parsé dans le fichier
     * @throws XMLStreamException en cas d'exception lors de la lecture d'une chaine
     */
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
        throw new ParsingException("Le fichier est mal formatté");
    }


    /**
     *
     * @return le nom d'une tag d'un objet de type chaine
     */
    @Override
    public String tagName() {
        return START_ELEMENT_NAME;
    }
}
