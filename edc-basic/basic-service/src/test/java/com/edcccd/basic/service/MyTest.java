package com.edcccd.basic.service;

import com.edcccd.basic.api.pojo.table.TVisitDay;
import com.edcccd.basic.service.mapper.TVisitDayMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

/**
 * 测试类
 */
@SpringBootTest
public class MyTest {

    @Autowired
    TVisitDayMapper mapper;

//    @Autowired
    RestTemplate restTemplate =new RestTemplate();


    @Test
    public void t01() {
        // TVisitDay tVisitDay = mapper.selectById("002");

        TVisitDay tVisitDay = mapper.selectById("002");
        System.out.println(tVisitDay);
    }

    @Test
    public void tdinsert() {
        // for (int i = 0; i < 200; i++) {
        //     TVisitDay tVisitDay = new TVisitDay();
        //     tVisitDay.setId(RandomUtil.randomLong());
        //     tVisitDay.setVisitDate(new Date());
        //     tVisitDay.setTester(RandomUtil.randomString(5));
        //     tVisitDay.setAMenuItem(RandomUtil.randomString(3));
        //
        //     mapper.insert(tVisitDay);
        // }
    }

    @Test
    public void queryByPage() {
        // QueryWrapper<TVisitDay> wrapper = new QueryWrapper<>();
        // wrapper.lt("id", 0);
        // wrapper.lambda().lt(TVisitDay::getId, 0);

        // LambdaQueryWrapper<TVisitDay> wrapper = new LambdaQueryWrapper<>();
        // wrapper.gt(TVisitDay::getId, 0);
        //
        //
        // IPage<TVisitDay> page = new Page<>(1, 10);
        // mapper.selectPage(page, wrapper);
        //
        // System.out.println(page.getRecords().stream().map(TVisitDay::getId).collect(Collectors.toList()));
    }

    @Test
    void ceshi(){
        ResponseEntity<String> entity =
            restTemplate.getForEntity("http://account-service/hello", String.class);


        String list =  entity.getBody();
        System.out.println(list);
    }

}
