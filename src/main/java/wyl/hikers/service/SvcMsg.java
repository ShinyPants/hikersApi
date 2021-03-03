package wyl.hikers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.dao.mapper.MpMsg;
import wyl.hikers.model.Message;
import wyl.hikers.model.MessageShot;
import wyl.hikers.model.RespBody;

import java.util.List;

@Service
public class SvcMsg {

    @Autowired
    private MpMsg mysql;

    @Autowired
    private SvcPermission permission;

    public RespBody addMsg(Message msg) {
        permission.checkPermission(msg.getFuid(), msg.getPwd(), "SvcMsg.addMsg");
        mysql.addMsg(msg);
        return null;
    }

    public RespBody getTalkingMsg(Integer fuid, Integer tuid) {
        List<Message> list = mysql.getTalkingMsg(fuid, tuid);
        return RespBody.ok(list);
    }

    public RespBody getTalkList(Integer uid) {
        List<MessageShot> list = mysql.getTalkList(uid);
        return RespBody.ok(list);
    }
}
