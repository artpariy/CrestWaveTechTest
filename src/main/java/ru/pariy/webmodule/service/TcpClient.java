package ru.pariy.webmodule.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.pariy.webmodule.utils.HexUtils;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class TcpClient {

    private static final Logger logger = LogManager.getLogger(TcpClient.class);

    public void tcpConnection(String ip, int port, String json) {
        logger.info("Starting TCP send ...");
        Header header = new Header();
        byte[] jsonBody = json.getBytes();

        header.setLength(ByteBuffer.allocate(4).putInt(jsonBody.length).array());


        try (Socket socket = new Socket(ip, port)) {
            OutputStream outToServer = socket.getOutputStream();
            DataOutputStream out = new DataOutputStream(outToServer);

            out.write(header.getMagicByte());
            logger.info(HexUtils.encodeHexString(header.getMagicByte()));
            out.write(header.getLength());
            logger.info(HexUtils.encodeHexString(header.getLength()));

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(jsonBody);
            byte[] filebuffer = new byte[100];
            int read = 0;
            while(( read = byteArrayInputStream.read(filebuffer)) > 0){
                out.write(filebuffer,0, read);
                out.flush();
                logger.info(HexUtils.encodeHexString(filebuffer));
            }

            out.close();
            byteArrayInputStream.close();

            logger.info("TCP send done");
        } catch (Exception exception) {
            throw new TcpClientException("No connection established", exception);
        }
    }
}
