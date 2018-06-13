package com.tv.program.parser;

import com.tv.program.model.Chaine;
import com.tv.program.model.Duree;
import com.tv.program.model.Personne;
import com.tv.program.model.programmes.Programme;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

class ProgrammeParser implements Parser<Programme> {
    static final String START_ELEMENT_NAME = "programme";

    private final Map<String, Chaine> chaineMap;
    private final SimpleDateFormat sdf;

    ProgrammeParser(Map<String, Chaine> chaineMap) {
        this.chaineMap = chaineMap;
        sdf = new SimpleDateFormat("yyyyMMddHHmmss X");
        sdf.setTimeZone(TimeZone.getDefault());
    }

    @Override
    public Programme parseFrom(StartElement startElement, XMLEventReader eventReader) throws XMLStreamException {
        Date start = parseDate(startElement, "start");
        Date stop = parseDate(startElement, "stop");
        String chaineId = startElement.getAttributeByName(new QName("channel"))
                .getValue();
        Chaine chaine = chaineMap.get(chaineId);
        String title = null;
        String sousTitre = null;
        String description = null;
        List<Personne> personnes = new ArrayList<>();
        int annee = 0;
        String type = null;
        Duree duree = null;
        String pays = null;
        String aspect = null;
        String qualite = null;
        String note = null;

        while (eventReader.hasNext()) {
            XMLEvent event = eventReader.nextEvent();
            if (event.isStartElement()) {
                StartElement element = event.asStartElement();
                switch (element.getName().getLocalPart()) {
                    case "title":
                        event = eventReader.nextEvent();
                        title = event.asCharacters().getData();
                        break;
                    case "sub-title":
                        event = eventReader.nextEvent();
                        sousTitre = event.asCharacters().getData();
                        break;
                    case "desc":
                        event = eventReader.nextEvent();
                        description = event.asCharacters().getData();
                        break;
                    case "credits":
                        eventReader.nextEvent(); // \n
                        event = eventReader.nextEvent();//first credit
                        while (event.isStartElement())  {
                            String role = event.asStartElement().getName().getLocalPart();
                            event = eventReader.nextEvent();
                            personnes.add(new Personne(event.asCharacters().getData(), role));
                            eventReader.nextEvent(); // </>
                            eventReader.nextEvent(); // \n
                            event = eventReader.nextEvent(); //next credit or end of credit
                        } ;
                        break;
                    case "date": // annee
                        event = eventReader.nextEvent();
                        annee = Integer.parseInt(event.asCharacters().getData());
                        break;
                    case "category":
                        event = eventReader.nextEvent();
                        type = event.asCharacters().getData();
                        break;
                    case "length":
                        String unit = event.asStartElement()
                                .getAttributeByName(new QName("units"))
                                .getValue();
                        event = eventReader.nextEvent();
                        duree = new Duree(unit, Integer.parseInt(event.asCharacters().getData()));
                        break;
                    case "country":
                        event = eventReader.nextEvent();
                        pays = event.asCharacters().getData();
                        break;
                    case "video":
                        eventReader.nextEvent();
                        break;
                    case "aspect":
                        event = eventReader.nextEvent();
                        aspect = event.asCharacters().getData();
                        break;
                    case "quality":
                        event = eventReader.nextEvent();
                        qualite = event.asCharacters().getData();
                        break;
                    case "rating":
                        eventReader.nextEvent(); //\n
                        eventReader.nextEvent(); //<value>
                        event = eventReader.nextEvent();// note
                        note = event.asCharacters().getData();
                        break;
                }
            }

            if(event.isEndElement()){
                EndElement endElement = event.asEndElement();
                if(endElement.getName().getLocalPart().equals(START_ELEMENT_NAME)){
                    return Programme.of(chaine, title, sousTitre, description, annee,
                            type, personnes, start, stop, duree, pays, aspect,
                            qualite, note);
                }
            }
        }
        throw new ParsingException("Le fichier est mal formatté");
    }

    private Date parseDate(StartElement startElement, String name) {
        String sDate = startElement.getAttributeByName(new QName(name))
                .getValue();
        try {
            return sdf.parse(sDate);
        } catch (ParseException e) {
            throw new ParsingException("La date est mal formattée");
        }
    }

    @Override
    public String tagName() {
        return START_ELEMENT_NAME;
    }
}
