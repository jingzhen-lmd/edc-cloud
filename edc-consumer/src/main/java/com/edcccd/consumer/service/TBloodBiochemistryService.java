package com.edcccd.consumer.service;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.edcccd.consumer.feign.BloodBiochemistryFeign;
import com.edcccd.consumer.pojo.TBloodBiochemistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

@Service
public class TBloodBiochemistryService {
  @Autowired
  BloodBiochemistryFeign feign;
  @Autowired
  StringRedisTemplate template;


  public TBloodBiochemistry getById(long id) {
    String key = "basic:blood:" + id;
//    1.查缓存
    String data = template.opsForValue().get(key);
//    2.有返回
    if (!StrUtil.isBlank(data)) {
      return JSONUtil.toBean(data, TBloodBiochemistry.class);
    }
    // 3.没有就查数据库
    TBloodBiochemistry blood = feign.bloodBiochemistry(id);
    System.out.println("查数据库了");
    if (blood != null) {
      template.opsForValue().setIfAbsent(key, JSONUtil.toJsonPrettyStr(blood),
          10, TimeUnit.MINUTES);
    }
    return blood;
  }

  @Transactional(rollbackFor = Exception.class)
  public Boolean bloodBiochemistry(TBloodBiochemistry tBloodBiochemistry) {
    if (tBloodBiochemistry.getId() == null)
      return false;

    // 修改数据库
    Boolean isSuccess = feign.bloodBiochemistry(tBloodBiochemistry);

    if (isSuccess) {
      Boolean delete = template.delete("basic:blood:" + tBloodBiochemistry.getId());
      System.out.println(1 / 0);
      if (delete == null || !delete) {
        throw new RuntimeException("redis删除失败");
      }
    }

    return true;
  }
}
