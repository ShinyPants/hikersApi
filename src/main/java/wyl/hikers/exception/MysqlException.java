package wyl.hikers.exception;

public class MysqlException extends HikersException {

    private MysqlException(String message, String position, Object data) {
        super(message, getInfo(message, position, data));
    }

    private static String getInfo(String message, String position, Object data) {
        String info = "MysqlException => " + message + ", 位置为：[" + position + "] 数据为：[" + data + "]";
        return info;
    }

    public static MysqlException insert(String position, Object data) {
        return new MysqlException("添加失败", position, data);
    }

    public static MysqlException delete(String position, Object data) {
        return new MysqlException("删除失败", position, data);
    }

    public static MysqlException update(String position, Object data) {
        return new MysqlException("更新失败", position, data);
    }

    public static MysqlException select(String position, Object data) {
        return new MysqlException("查询失败", position, data);
    }
}
