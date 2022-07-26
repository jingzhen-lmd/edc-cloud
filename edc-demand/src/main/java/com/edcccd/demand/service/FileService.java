package com.edcccd.demand.service;

import com.edcccd.common.util.Result;
import com.edcccd.demand.pojo.MyFile;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 文件传输服务
 */
public interface FileService {

  Result<List<MyFile>> queryFiles(String filePath);

  Result<String> uploadFile(String filePath, MultipartFile file);

  Result<Void> download(String filePath, HttpServletResponse response);

  Result<Void> deleteFile(List<String> filePath);

  Result<Void> rename(String filePath, String name);

  Result<Void> createFile(String filePath, String fileName);

  Result<Void> moveFile(String sourcePath, String targetPath);
}
