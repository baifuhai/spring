package com.test.tx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService2 {

    @Autowired
    OrderService orderService;

    /**
     * 多本买
     * 必须写在不同的 service 里，要不然 Propagation.REQUIRES_NEW 效果出不来
     *
     * @param accountId
     * @param bookIds
     */
    @Transactional
    public void buys(int accountId, int[] bookIds) throws Exception {
        for (int bookId : bookIds) {
            orderService.buy(accountId, bookId);
        }
    }

}
