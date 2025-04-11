package org.example;

import jakarta.xml.bind.*;

import java.io.File;

public class Exporter {
    public static void exportToXml(Autopark autopark, String filename) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Autopark.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(autopark, new File(filename));
    }
}
