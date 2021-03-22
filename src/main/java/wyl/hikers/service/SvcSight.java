package wyl.hikers.service;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.dao.mapper.MpSight;
import wyl.hikers.model.RespBody;
import wyl.hikers.model.Sight;

import java.util.List;

@Service
public class SvcSight {
    @Autowired
    private MpSight mysql;

    public RespBody addSight(Sight sight) {
        if ((sight.getPics() != null ? sight.getPics().length : 0) > 0)
            sight.setPicsJSON(JSON.toJSONString(sight.getPics()));
        mysql.addSight(sight);
        return RespBody.ok(null);
    }

    public RespBody getSights() {
        List<Sight> list = mysql.getSights();
        return RespBody.ok(list);
    }

    public RespBody editSight(Sight sight) {
        mysql.editSight(sight);
        return RespBody.ok(null);
    }
}
