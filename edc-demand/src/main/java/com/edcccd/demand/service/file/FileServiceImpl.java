package com.edcccd.demand.service.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import com.edcccd.common.util.Result;
import com.edcccd.demand.pojo.MyFile;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
public class FileServiceImpl {

  @Value("${myBasePath}")
  private String basePath;
  @Value("${userPath}")
  private String userPath;

  private String rootPath;

  @Value("${myBasePath}")
  private void init() {
    rootPath = basePath + userPath;
    File file = new File(rootPath);
    if (file.exists())
      return;

    String result = file.mkdirs() ? "创建文件夹成功" : "创建文件夹失败";
    log.info(result + file.getAbsolutePath());
  }

  /**
   * 1、构造方法会调用码？ 2、spring启动构造方法调用顺序？ 3、value注解可以计算
   */
  public void jiLu() {
    System.out.println("peizhi" + rootPath);
  }

  public Result<List<MyFile>> queryFiles(String filePath) {
    File files = new File(rootPath + filePath.trim());
    if (!files.exists())
      return Result.fail(500, "文件路径不存在");


    List<MyFile> collect = Arrays.stream(Objects.requireNonNull(files.listFiles()))
        .map(file -> new MyFile(file.hashCode(), file.getName(), file.isDirectory(), file
            .lastModified(), FileUtil.subPath(rootPath, file), file.length()))
        .collect(Collectors.toList());

    return Result.success(collect);
  }

  public Result<String> uploadFile(String filePath, MultipartFile file) {
    try {
      String fileName = file.getOriginalFilename();
      file.transferTo(new File(basePath + filePath + File.separator + fileName));
    } catch (IOException e) {
      e.printStackTrace();
    }
    return Result.success(file.getName());
  }


  public Result<Void> download(String filePath, HttpServletResponse response) {
    Assert.notBlank(filePath);
    Assert.notNull(response);

    File file = new File(basePath + userPath + filePath);
    if (!file.exists()) {
      return Result.fail(500, "文件路径不存在");
    }

    try {
      FileInputStream inputStream = new FileInputStream(file);
      byte[] data = new byte[inputStream.available()];
      inputStream.read(data);
      // System.out.println("data.length " + data.length);
      response.setContentLength(data.length);
      response.setHeader("Content-Range", "" + Integer.valueOf(data.length - 1));
      response.setHeader("Accept-Ranges", "bytes");
      response.setHeader("Etag", "W/\"9767057-1323779115364\"");
      OutputStream os = response.getOutputStream();

      os.write(data);
      //先声明的流后关掉！
      os.flush();
      os.close();
      inputStream.close();

    } catch (Exception e) {
      e.printStackTrace();

    }
    return Result.success();
  }

  public Result<Void> deleteFile(String filePath) {
    Assert.notBlank(filePath);

    File files = new File(rootPath + filePath);
    if (!files.exists())
      return Result.fail(500, "文件路径不存在");

    boolean del = FileUtil.del(filePath);

    if (!del) {
      log.warn("删除文件失败：" + filePath);
      return Result.fail(500, "删除文件失败：：" + filePath);
    }
    log.info("删除文件：" + filePath);
    return Result.success();
  }
}
