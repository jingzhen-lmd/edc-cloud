package com.edcccd.demand.cloudfile;

import com.edcccd.common.util.Result;
import com.edcccd.demand.pojo.MyFile;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
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
@RestController
@RequestMapping("fileController")
public class FileController {

    @Value("${myRootPath}")
    String baseFilePath;
    @Value("${userPath}")
    String userPath;

    private void init() {
        File file = new File(baseFilePath + userPath);
        if (file.exists())
            return;

        boolean mkdirs = file.mkdirs();
        if (mkdirs)
            log.info("创建文件夹成功" + file.getAbsolutePath());
        log.info("创建文件夹失败" + file.getAbsolutePath());
    }

    @GetMapping("hello")
    public String hello() {
        return "hello";
    }

    // @PostMapping("file")
    // public String file(@RequestParam("file") List<MultipartFile> files) throws IOException {
    //     for (MultipartFile file : files) {
    //         // try (InputStream inputStream = file
    //         //         .getInputStream(); InputStreamReader reader = new InputStreamReader(inputStream); BufferedReader reader1 = new BufferedReader(reader)) {
    //         //
    //         //     while (reader1.ready()) {
    //         //         System.out.println(reader1.readLine());
    //         //     }
    //         //     // System.out.println(chars);
    //         // } catch (IOException e) {
    //         //     e.printStackTrace();
    //         // }
    //         String fileName = file.getOriginalFilename();
    //         file.transferTo(new File("F:\\ssss\\" + fileName));
    //     }
    //
    //     // System.out.println(file);
    //     return files.size() + "";
    // }

    // 上传单个文件
    @PostMapping("upload/{filePath}")
    public Result<String> file(@PathVariable("file") MultipartFile file, @PathVariable("filePath") String filePath) {
        try {
            String fileName = file.getOriginalFilename();

            file.transferTo(new File(baseFilePath + filePath + File.separator + fileName));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(file.getName());
    }

    /**
     * 获取当前地址的文件夹，文件名称
     */
    @GetMapping
    public Result<List<MyFile>> getFiles(@RequestParam(value = "filePath") String filePath) {
        init();
        File files = new File(baseFilePath + userPath + filePath);
        if (!files.exists())
            return Result.fail("500", "文件路径不存在");

        List<MyFile> collect = Arrays.stream(Objects.requireNonNull(files.listFiles()))
                                     .map(file -> new MyFile(file.hashCode(), file.getName(), file.isDirectory(), file
                                             .lastModified(), getRelativePath(file), file.length()))
                                     .collect(Collectors.toList());

        return Result.success(collect);
    }

    /**
     * 文件下载
     *
     * @param filePath 下载的文件路径
     */
    @GetMapping("downLoad")
    public Result<Void> download(@RequestParam("filePath") String filePath, HttpServletResponse response) {
        File file = new File(baseFilePath + userPath + filePath);
        if (!file.exists()) {
            return Result.fail("500", "文件路径不存在");
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

    @RequestMapping(value = "getVideo", method = RequestMethod.GET)
    public void getVido(HttpServletResponse response) {
        String file = "F:\\ssss\\ccd\\123";
        try {
            FileInputStream inputStream = new FileInputStream(file);
            byte[] data = new byte[inputStream.available()];
            inputStream.read(data);
            String diskfilename = "final.avi";
            response.setContentType("video/avi");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + diskfilename + "\"");
            System.out.println("data.length " + data.length);
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
    }

    /**
     * 删除文件
     */
    @DeleteMapping
    public Result<Void> deleteFile(@RequestParam(value = "filePath") String filePath) {
        File files = new File(baseFilePath + userPath + filePath);

        if (!files.exists())
            return Result.fail("500", "文件路径不存在");

        if (files.isDirectory()) {
            System.out.println("删除文件夹");
        } else {
            files.delete();
        }
        return Result.success();
    }

    // 工具类，获取相对路径（相对根路径）
    public String getRelativePath(File file) {
        if (!file.exists()) {
            System.out.println("文件不存在!!");
            return "";
        }
        File baseFile = new File(baseFilePath + userPath);
        try {
            return getRelativePath(baseFile, file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    //工具类，获取两个文件的相对路径
    public String getRelativePath(File shortFile, File longFile) throws Exception {
        if (!shortFile.exists() || !longFile.exists()) {
            throw new Exception("文件不存在!!");
        }
        String shortPath = shortFile.getAbsolutePath();
        String longPath = longFile.getAbsolutePath();
        if (longPath.length() <= shortPath.length()) {
            throw new Exception("长路径文件长度不正确");
        }
        if (!longPath.startsWith(shortPath)) {
            throw new Exception("两文件没有关联关系");
        }
        String substring = longPath.substring(shortPath.length());

        // 修正格式，取出最左边的分割符号
        return substring.startsWith(File.separator) ? substring.substring(1) : substring;
    }


    //    个人配置保存
}
