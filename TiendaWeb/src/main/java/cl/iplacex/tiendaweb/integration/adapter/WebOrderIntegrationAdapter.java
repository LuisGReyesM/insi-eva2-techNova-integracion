package cl.iplacex.tiendaweb.integration.adapter;

import cl.iplacex.tiendaweb.integration.dto.WebOrderXml;
import cl.iplacex.tiendaweb.integration.jms.WebOrderJmsProducer;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

import java.io.StringWriter;

public class WebOrderIntegrationAdapter {

    private final WebOrderJmsProducer producer = new WebOrderJmsProducer();

    public void publish(WebOrderXml order) {

        try {
            JAXBContext context = JAXBContext.newInstance(WebOrderXml.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            StringWriter writer = new StringWriter();
            marshaller.marshal(order, writer);

            producer.send(writer.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
