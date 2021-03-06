package com.zeng.gmalll.gmallusermanage.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.zeng.gmalll.gmallusermanage.mapper.UserAddressMapper;
import com.zeng.gmalll.gmallusermanage.mapper.UserInfoMapper;
import com.zengs.gmall.bean.UserAddress;
import com.zengs.gmall.bean.UserInfo;
import com.zengs.gmall.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class UserInfoServiceImpl implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private UserAddressMapper userAddressMapper;

    @Override
    public List<UserInfo> findAll() {
        return userInfoMapper.selectAll();
    }

    @Override
    public UserInfo getUserInfoByName(String name) {
        return null;
    }

    @Override
    public List<UserInfo> getUserInfoListByName(UserInfo userInfo) {
        // return userInfoMapper.select(userInfo);
        Example example = new Example(UserInfo.class);
        // 第一个参数：实体类的属性，还是数据库中的字段
        example.createCriteria().andEqualTo("name",userInfo.getName());
        // example :设置查询条件！
        return userInfoMapper.selectByExample(example);
    }

    @Override
    public List<UserInfo> getUserInfoListByNickName(UserInfo userInfo) {
        Example example = new Example(UserInfo.class);
        example.createCriteria().andLike("nickName","%" + userInfo.getNickName() + "%");
        return userInfoMapper.selectByExample(example);
    }

    @Override
    public void addUser(UserInfo userInfo) {
        userInfoMapper.insertSelective(userInfo);
    }

    @Override
    public void updUser(UserInfo userInfo) {
        // update from userInfo set nickName=? where name= ?
        // 第一个参数：userInfo 修改的内容
        // 第二个参数：example 设置条件
        Example example = new Example(UserInfo.class);
        example.createCriteria().andEqualTo("name",userInfo.getName());
        userInfoMapper.updateByExampleSelective(userInfo,example);

    }

    @Override
    public void delUser(UserInfo userInfo) {
        // delete from userInfo where name = ?

//        Example example = new Example(UserInfo.class);
//        example.createCriteria().andEqualTo("name",userInfo.getName());
//
//        userInfoMapper.deleteByExample(example);

        userInfoMapper.delete(userInfo);

    }

    @Override
    public List<UserAddress> getUserAddressByUserId(String userId) {
        Example example = new Example(UserAddress.class);
        example.createCriteria().andEqualTo("userId",userId);
        return userAddressMapper.selectByExample(example);
    }

    @Override
    public List<UserAddress> getUserAddressByUserId(UserAddress userAddress) {
        return userAddressMapper.select(userAddress);
    }
}
