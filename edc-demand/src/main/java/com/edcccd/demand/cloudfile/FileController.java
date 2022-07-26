package com.edcccd.demand.cloudfile;

import cn.hutool.core.lang.Assert;
import com.edcccd.common.util.Result;
import com.edcccd.demand.pojo.MyFile;
import com.edcccd.demand.service.file.FileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("fileController")
public class FileController {

  @Autowired
  FileServiceImpl service;

  @GetMapping("hello")
  public String hello() {
    return "hello";
  }

  /**
   * 查询目录下文件
   */
  @GetMapping
  public Result<List<MyFile>> queryFiles(@RequestParam(value = "filePath", required = false) String filePath) {

    return service.queryFiles(filePath);
  }

  /**
   * 上传文件，按次
   *
   * @param file
   *     文件实体
   */
  @PostMapping("upload/{filePath}")
  public Result<String> uploadFile(@PathVariable("filePath") String filePath,
      @RequestParam("file") MultipartFile file) {
    Assert.notBlank(filePath);
    Assert.notNull(file);

    return service.uploadFile(filePath, file);
  }

  /**
   * 文件下载
   *
   * @param filePath
   *     下载的文件路径
   */
  @GetMapping("downLoad")
  public Result<Void> download(@RequestParam("filePath") String filePath, HttpServletResponse response) {
    Assert.notBlank(filePath);
    Assert.notNull(response);

    return service.download(filePath, response);
  }

  /**
   * 删除文件（批量）
   *
   * @param filePaths
   *     下载的文件路径
   */
  @DeleteMapping
  public Result<Void> deleteFile(@RequestParam(value = "filePaths") List<String> filePaths) {
    Assert.notNull(filePaths);

    return service.deleteFile(filePaths);
  }

  /**
   * 重命名
   *
   * @param filePath
   *     源文件相对路径(含自身)
   * @param newName
   *     新名称
   */
  @PutMapping
  public Result<Void> rename(@RequestParam(value = "filePath") String filePath,
      @RequestParam(value = "newName") String newName) {
    Assert.notBlank(filePath);
    Assert.notBlank(newName);
    return service.rename(filePath.trim(), newName.trim());
  }

  /**
   * 新增文件夹
   *
   * @param filePath
   *     新增文件
   */
  @PostMapping
  public Result<Void> createFile(@RequestParam(value = "filePath", required = false) String filePath,
      @RequestParam(value = "fileName") String fileName) {
    Assert.notBlank(fileName);

    return service.createFile(filePath, fileName);
  }

  /**
   * 移动文件/夹
   *
   * @param sourcePath
   *     来源文件
   * @param targetPath
   *     目标文件
   */
  @PostMapping("move")
  public Result<Void> moveFile(@RequestParam(value = "sourcePath") String sourcePath,
      @RequestParam(value = "targetPath") String targetPath) {
    Assert.notBlank(sourcePath);
    Assert.notBlank(targetPath);

    return service.moveFile(sourcePath, targetPath);
  }


  //  @RequestMapping(value = "getVideo", method = RequestMethod.GET)
  //  public void getVido(HttpServletResponse response) {
  //    String file = "F:\\ssss\\ccd\\123";
  //    try {
  //      FileInputStream inputStream = new FileInputStream(file);
  //      byte[] data = new byte[inputStream.available()];
  //      inputStream.read(data);
  //      String diskfilename = "final.avi";
  //      response.setContentType("video/avi");
  //      response.setHeader("Content-Disposition", "attachment; filename=\"" + diskfilename + "\"");
  //      System.out.println("data.length " + data.length);
  //      response.setContentLength(data.length);
  //      response.setHeader("Content-Range", "" + Integer.valueOf(data.length - 1));
  //      response.setHeader("Accept-Ranges", "bytes");
  //      response.setHeader("Etag", "W/\"9767057-1323779115364\"");
  //      OutputStream os = response.getOutputStream();
  //
  //      os.write(data);
  //      //先声明的流后关掉！
  //      os.flush();
  //      os.close();
  //      inputStream.close();
  //
  //    } catch (Exception e) {
  //      e.printStackTrace();
  //
  //    }
  //  }


//   @PostMapping("file")  //多文件一次上传
//   public String file(@RequestParam("file") List<MultipartFile> files) throws IOException {
//       for (MultipartFile file : files) {
//            try (InputStream inputStream = file
//                    .getInputStream(); InputStreamReader reader = new InputStreamReader(inputStream);
//           BufferedReader reader1 = new BufferedReader(reader)) {
//
//                while (reader1.ready()) {
//                    System.out.println(reader1.readLine());
//                }
//                // System.out.println(chars);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//           String fileName = file.getOriginalFilename();
//           file.transferTo(new File("F:\\ssss\\" + fileName));
//       }
//
//       // System.out.println(file);
//       return files.size() + "";
//   }

}
