package org.eclipse.jetty.test;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.websocket.server.WebSocketServerFactory;
import org.eclipse.jetty.websocket.server.WebSocketServlet;

@SuppressWarnings("serial")
public class WebSocketEchoServlet extends WebSocketServlet
{
    private static final int KBYTE = 1024;
    private static final int MBYTE = KBYTE * KBYTE;
    
    @Override
    public void configure(WebSocketServerFactory factory)
    {
        // Test cases 9.x uses BIG frame sizes, let policy handle them.
        int bigFrameSize = 20 * MBYTE;

        factory.getPolicy().setBufferSize(bigFrameSize);
        factory.getPolicy().setMaxPayloadSize(bigFrameSize);
        factory.getPolicy().setMaxTextMessageSize(bigFrameSize);
        factory.getPolicy().setMaxBinaryMessageSize(bigFrameSize);

        factory.register(EchoSocket.class);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        resp.sendError(404);
    }
}
