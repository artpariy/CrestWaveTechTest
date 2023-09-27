package ru.pariy.webmodule.api.http;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.pariy.webmodule.api.jaxb.EnvelopeType;
import ru.pariy.webmodule.service.*;
import ru.pariy.webmodule.utils.PropertyUtils;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public class XmlParserServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(XmlParserServlet.class);
    private XmlParser xmlParser;
    private JsonConverter jsonConverter;
    private TcpClient tcpClient;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String xml = req.getParameter("xml");
        xmlParser = new XmlParser();
        jsonConverter = new JsonConverter();
        tcpClient = new TcpClient();
        NegativeResponse negativeResponse = null;

        try {
            EnvelopeType envelopeType = xmlParser.parsingXML(xml);
            String json = jsonConverter.convertToJson(envelopeType);
            tcpClient.tcpConnection(PropertyUtils.getPropertiesDestAddr(PropertyUtils.getPropertiesPath()),
                    PropertyUtils.getPropertiesDestPort(PropertyUtils.getPropertiesPath()), json);

        } catch (TcpClientException exception) {
              logger.info(exception.getMessage());
            negativeResponse = new NegativeResponse(3, exception.getMessage());
        } catch (JsonConverterException exception) {
            logger.info(exception.getMessage());
            negativeResponse = new NegativeResponse(2, exception.getMessage());
        } catch (XmlParserException exception) {
            logger.info(exception.getMessage());
            negativeResponse = new NegativeResponse(1, exception.getMessage());
        }

        if (negativeResponse == null) {
            resp.sendRedirect("/result.html");
        } else {
            resp.sendRedirect("/result.html?step=" + negativeResponse.getStep()
                    + "&error=" + negativeResponse.getErrorMessage());
        }



    }
}

