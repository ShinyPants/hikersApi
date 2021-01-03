package wyl.hikers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import wyl.hikers.exception.HikersException;
import wyl.hikers.exception.MysqlException;
import wyl.hikers.model.Part;
import wyl.hikers.repository.mapper.MpParts;
import wyl.hikers.repository.redis.RdsParts;

import java.util.List;

@Service
public class SvcParts{
    @Autowired
    private RdsParts redis;

    @Autowired
    private MpParts mysql;

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
        // 更新到Redis
        List<Part> parts = mysql.getParts();
        redis.refresh(parts);
        return parts; // 重新更新内容
    }

    public List<Part> updateParts(List<Part> partList) {
        // TODO: 检查身份
        // 更新到MySQL
        try {
            mysql.updateParts(partList);
        } catch (RuntimeException e) {
            throw MysqlException.update("SvcParts.updateParts", partList);
        }
        // 更新到Redis
        List<Part> parts = mysql.getParts();
        redis.refresh(parts);
        return parts;
    }
}
