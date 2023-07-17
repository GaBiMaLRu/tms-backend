package com.trans.tms.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trans.tms.entity.AddressBook;
import com.trans.tms.mapper.AddressBookMapper;
import com.trans.tms.service.IAddressBookService;
import org.springframework.stereotype.Service;

/**
 * 地址簿服务类实现
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements IAddressBookService {

}