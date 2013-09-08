package ru.ykey.web.websocket;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.catalina.websocket.WsOutbound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestHandler;
import ru.ykey.service.IWebSocketService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;


/**
 * User: y.krivochurov
 * Date: 25.09.12
 * Time: 9:37
 */
@Component("webSocketServlet")
public class LightWebSocketServlet extends WebSocketServlet implements HttpRequestHandler {

    private static final long serialVersionUID = 1L;

    private static final Logger loggerWebsocket = LoggerFactory.getLogger("ru.ykey.websocket");
    private static final Logger logger = LoggerFactory.getLogger(TheWebSocket.class);

    public LightWebSocketServlet() {
        super();
    }

    @Autowired
    private IWebSocketService webSocketService;

    @Override
    protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest httpServletRequest) {
        return new TheWebSocket();
    }

    @Override
    public void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request, response);
    }

    public class TheWebSocket extends MessageInbound {
        private WsOutbound outbound;

        public TheWebSocket() {
            super();
        }

        @Override
        public void onOpen(WsOutbound outbound) {
            this.outbound = outbound;
        }

        @Override
        protected void onClose(int status) {
        }

        @Override
        protected void onBinaryMessage(ByteBuffer byteBuffer) throws IOException {
        }

        @Override
        protected void onTextMessage(CharBuffer buffer) throws IOException {
            String data = buffer.toString();
            loggerWebsocket.debug("> {} )", data);
            webSocketService.receive(outbound, data);
        }
    }
}