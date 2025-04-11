package org.example;

import jakarta.xml.bind.*;

import java.io.File;

public class Importer {
    public static Autopark importFromXml(String filename) throws Exception {
        JAXBContext context = JAXBContext.newInstance(Autopark.class);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        return (Autopark) unmarshaller.unmarshal(new File(filename));
    }
}

