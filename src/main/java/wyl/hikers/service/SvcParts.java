package wyl.hikers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.exception.HikersException;
import wyl.hikers.exception.MysqlException;
import wyl.hikers.model.Part;
import wyl.hikers.dao.mapper.MpParts;
import wyl.hikers.dao.redis.RdsParts;

import java.util.List;

@Service
public class SvcParts{
    @Autowired
    private RdsParts redis;

    @Autowired
    private MpParts mysql;

    /**
     * 从MySQL同步内容到Redis
     * @return 同步的内容
     */
    public List<Part> refreshFromMysql() {
        List<Part> parts = mysql.getParts();
        redis.refresh(parts);
        return parts;
    }

    public List<Part> getParts() {
        return redis.getParts();
    }

    public List<Part> addPart(Part part) throws HikersException {
        // TODO: 检查身份
        // 添加到MySQL
        try {
            mysql.addPart(part);
        } catch (RuntimeException e) {
            throw MysqlException.insert("SvcParts.addPart", part);
        }
        // 更新到Redis并返回内容
        return refreshFromMysql();
    }

    public List<Part> updateParts(List<Part> partList) {
        // TODO: 检查身份
        // 更新到MySQL
        try {
            mysql.updateParts(partList);
        } catch (RuntimeException e) {
            throw MysqlException.update("SvcParts.updateParts", partList);
        }
        return refreshFromMysql();
    }
}
