package wyl.hikers.exception;

public class PermissionException extends HikersException{
    private PermissionException(String message, String info) {
        super(message, info);
    }

    private static PermissionException doConstruct(String message, String errType, String position, Integer uid, String pwd) {
        String _detail = String.format("[uid = %s, pwd = %s]", uid, pwd);
        String info = String.format("权限错误: [类型 = %s, 位置 = %s, 详情 = %s]", errType, position, _detail);
        return new PermissionException(message, info);
    }

    public static PermissionException notOnline(String position, Integer uid, String pwd) {
        return doConstruct("没有登录", "未登录", position, uid, pwd);
    }

    public static PermissionException codeError(String position, Integer uid, String pwd) {
        return doConstruct("请重新登录", "口令错误", position, uid, pwd);
    }

    public static PermissionException timeOut(String position, Integer uid, String pwd) {
        return doConstruct("请重新登录", "超时", position, uid, pwd);
    }
}
