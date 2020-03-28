package com.mn.im.socket.netty;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnDisconnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageEventHandler {




    /**
     * @Title:
     * @Description:   客户端连接的时候触发
     * @param client
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 10:28:46
     */
    @OnConnect
    public void onConnect(SocketIOClient client) {

    }

    /**
     * @Title:
     * @Description:  客户端关闭连接时触发
     * @param client
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 10:28:55
     */
    @OnDisconnect
    public void onDisconnect(SocketIOClient client) {

    }

    /**
     * @Title:
     * @Description:   客户端发来请求
     * @param client 客户端信息
     * @param request 请求信息
     * @param data 客户端发送数据
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 10:36:33
     */
    @OnEvent(value = "USER_MSG")
    public void onEvent(SocketIOClient client, AckRequest request, Message data) {

    }

    /**
     * @Title:
     * @Description:  发送消息
     * @param token
     * @param msg
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 10:35:19
     */
    public void sendMsg(String token , String msg) {

    }

    /**
     * @Title:
     * @Description:   给用户发送在线通知
     * @param userId
     * @param msg
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 01:01:22
     */
    public void sendMsgToUserId(String userId , String msg) {

    }


    /**
     * @Title:
     * @Description:   给用户发送任务通知
     * @param userId
     * @param msg
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 01:01:22
     */
    public void sendTaskCastToUserId(String userId , String msg) {

    }

    /**
     * @Title:
     * @Description:   广播消息
     * @param msg
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2019/12/17 10:32:35
     */
    public void sendBroadcast(String msg) {

    }


    /**
     * @Title:
     * @Description:   注销
     * @param token
     * @return
     * @throws
     * @author qiaomengnan
     * @date 2020/02/02 08:54:31
     */
    public void logout(String token) {

    }



}
