package com.edcccd.blog.service.serviceimpl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edcccd.blog.mapper.EnjoyMapper;
import com.edcccd.blog.entity.Enjoy;
import com.edcccd.blog.service.EnjoyService;
@Service
public class EnjoyServiceImpl extends ServiceImpl<EnjoyMapper, Enjoy> implements EnjoyService{

}
