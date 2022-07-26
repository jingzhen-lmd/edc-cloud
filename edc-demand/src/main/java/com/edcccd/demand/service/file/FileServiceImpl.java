package com.edcccd.demand.service.file;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import com.edcccd.common.util.Result;
import com.edcccd.demand.pojo.MyFile;
import com.edcccd.demand.service.FileService;
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
public class FileServiceImpl implements FileService {

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
      log.warn("下载文件异常：" + e);
    }
    return Result.success();
  }

//  public Result<Void> download2(String filePath, HttpServletResponse response) {
//    Assert.notBlank(filePath);
//    Assert.notNull(response);
//
//    File file = new File(basePath + userPath + filePath);
//    if (!file.exists()) {
//      return Result.fail(500, "文件路径不存在");
//    }
//
//    FileUtil.writeToStream();
//
//
//    return null;
//  }

  public Result<Void> deleteFile(List<String> filePaths) {
    Assert.notNull(filePaths);
    if (filePaths.isEmpty()) {
      return Result.success();
    }

    for (String filePath : filePaths) {
      File files = new File(rootPath + filePath);
      if (!files.exists())
        return Result.fail(500, "文件路径不存在");

      if (!FileUtil.del(files)) {
        log.warn("删除文件失败：" + filePath);
        return Result.fail(500, "删除文件失败：：" + filePath);
      }
      log.info("删除文件：" + filePath);
    }
    return Result.success();
  }

  public Result<Void> rename(String filePath, String name) {
    Assert.notBlank(filePath);
    Assert.notBlank(name);

    File file = new File(rootPath + filePath);
    if (!file.exists()) {
      Result.fail(500, "文件不存在");
    }

    FileUtil.rename(file, name, true, false);
    return Result.success();
  }

  public Result<Void> createFile(String filePath, String fileName) {
    Assert.notBlank(fileName);

    String path = StrUtil.isBlank(filePath) ? rootPath + fileName : rootPath + filePath + File.separator + fileName;

    File file = new File(path);

    if (StrUtil.isBlank(getSubPath(rootPath, path))) {
      return Result.fail(500, "非法的文件路径");
    }
    boolean mkdirs = file.mkdirs();

    log.info("文件：{}创建{}", file.getAbsolutePath(), mkdirs ? "成功" : "失败");
    return Result.success();
  }

  @Override
  public Result<Void> moveFile(String sourcePath, String targetPath) {
    Assert.notBlank(sourcePath);
    Assert.notBlank(targetPath);

    File sourceFile = new File(rootPath + sourcePath);
    if (!sourceFile.exists()) {
      Result.fail(500, "来源文件不存在");
    }
    File targetFile = new File(rootPath + targetPath);

    FileUtil.move(sourceFile, targetFile, false);
    return Result.success();
  }

  /* 获取相对路径*/
  public String getSubPath(String path1, String path2) {
    Assert.notBlank(path1);
    Assert.notBlank(path2);

    // 取出尾部分割符
    String path1Last = path1.substring(path1.length() - 1);
    path1 = path1Last.equals(File.separator) ? path1.substring(path1.length() - 1) : path1;
    String path2Last = path2.substring(path2.length() - 1);
    path2 = path2Last.equals(File.separator) ? path2.substring(path2.length() - 1) : path2;

    // 计算多余前缀
    String longPath = path1.length() > path2.length() ? path1 : path2;
    String shortPath = path1.length() > path2.length() ? path2 : path1;
    if (!longPath.startsWith(shortPath)) {
      return "";
    }
    String substring = longPath.substring(shortPath.length());

    // 取出前面分割符
    return substring.startsWith(File.separator) ? substring.substring(1) : substring;
  }
}
