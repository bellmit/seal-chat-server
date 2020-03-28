package com.mn.im.socket.netty;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.SpringAnnotationScanner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NettySocketIOConfig {

    @Bean
    public SocketIOServer socketIOServer() {
        com.corundumstudio.socketio.Configuration configuration = new com.corundumstudio.socketio.Configuration();
        //configuration.setHostname("localhost");
        configuration.setPort(9874);
        configuration.setMaxFramePayloadLength(1024 * 1024 * 1024);
        configuration.setMaxHttpContentLength(1024 * 1024 * 1024);
        SocketIOServer socketIOServer = new SocketIOServer(configuration);
        return socketIOServer;
    }

    @Bean
    public SpringAnnotationScanner springAnnotationScanner() {
        return new SpringAnnotationScanner(socketIOServer());
    }

}
