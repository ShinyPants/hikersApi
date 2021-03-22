package wyl.hikers.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wyl.hikers.config.SysConfig;
import wyl.hikers.model.RespBody;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

@Service
public class SvcFile {

    @Autowired
    private SysConfig sysConfig;

    @Autowired
    private SvcPermission permission;

    private String rename(String fileName) {
        Long time = System.currentTimeMillis();
        String uuid = UUID.randomUUID().toString().substring(0, 8);
        return time.toString() + uuid + "." + fileName.substring(fileName.lastIndexOf(".") + 1);
    }

    private void doSave(byte[] file, String path, String fileName) throws Exception {
        File targetFile = new File(path);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(path + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    private RespBody doSavePic(MultipartFile file) {
        // 文件夹是否为空
        if (file.isEmpty())
            return RespBody.failed("文件为空");
        // 修改文件名避免重复
        String fileName = file.getOriginalFilename();
        fileName = rename(fileName);
        // 保存
        try {
            doSave(file.getBytes(), sysConfig.getUploadPath(), fileName);
        } catch (Exception e) {
            return RespBody.failed("保存失败");
        }
        // 返回URL
        String url = "/hpics/" + fileName;
        return RespBody.ok(url);
    }

    public RespBody savePic(MultipartFile file, Integer uid, String pwd) {
        // 检查是否能传
        permission.checkPermission(uid, pwd, "SvcFile.savePic");
        // 进行保存
        return doSavePic(file);
    }

    public RespBody savePic(MultipartFile file) {
        // 进行保存
        return doSavePic(file);
    }
}
