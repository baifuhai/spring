package com.test.genericDependencyInject.service;

import com.test.genericDependencyInject.base.BaseService;
import com.test.genericDependencyInject.bean.User;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {
}
