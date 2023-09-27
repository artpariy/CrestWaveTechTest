package ru.pariy.webmodule.service;


import ru.pariy.webmodule.api.jaxb.EnvelopeType;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class XmlParser {

    public EnvelopeType parsingXML(String xml) {

        try {
            String modXml = removeXmlStringNamespaceAndPreamble(xml);
            String dataString = new String(modXml.getBytes(StandardCharsets.UTF_8), Charset.defaultCharset());

            JAXBContext jaxbContext = JAXBContext.newInstance(EnvelopeType.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader xmlReader = new StringReader(dataString);
            return (EnvelopeType) unmarshaller.unmarshal(xmlReader);
        } catch (Exception exception) {
            throw new XmlParserException("Not valid XML", exception);
        }

    }

    private String removeXmlStringNamespaceAndPreamble(String xmlString) {
        return xmlString.replaceAll("(<\\?[^<]*\\?>)?", ""). /* remove preamble */
                replaceAll("xmlns.*?(\"|').*?(\"|')", "") /* remove xmlns declaration */
                .replaceAll("(<)(\\w+:)(.*?>)", "$1$3") /* remove opening tag prefix */
                .replaceAll("(</)(\\w+:)(.*?>)", "$1$3"); /* remove closing tags prefix */
    }
}