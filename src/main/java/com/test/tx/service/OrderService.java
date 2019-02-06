package com.test.tx.service;

import com.test.tx.bean.Account;
import com.test.tx.bean.Book;
import com.test.tx.dao.AccountDao;
import com.test.tx.dao.BookDao;
import com.test.tx.exception.AccountBalanceException;
import com.test.tx.exception.BookStockException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * 添加事务注解，只能标注公有方法，可以在方法或类上添加
 *
 * 1.使用 propagation 指定事务的传播行为，即当前的事务方法被另外一个事务方法调用时
 *      REQUIRED，即使用调用方法的事务（默认）
 *      REQUIRES_NEW，使用新的事务，调用的事务方法的事务被挂起
 *
 * 2.使用 isolation 指定事务的隔离级别，最常用的取值为 READ_COMMITTED
 *
 * 3.使用 rollbackFor, noRollbackFor 指定抛出什么异常时回滚，默认情况下 Spring 的声明式事务对所有的运行时异常进行回滚
 *
 * 4.使用 readOnly 指定事务是否为只读，表示这个事务只能读取数据不能修改数据，这样可以帮助数据库引擎优化事务
 *
 * 5.使用 timeout 指定强制回滚之前事务可以占用的时间
 */
@Transactional(
    propagation = Propagation.REQUIRES_NEW,
    isolation = Isolation.READ_COMMITTED,
    rollbackFor = {Exception.class},
    readOnly = false,
    timeout = 3
)
@Service
public class OrderService {

    @Autowired
    BookDao bookDao;

    @Autowired
    AccountDao accountDao;

    /**
     * 单本买
     *
     * @param accountId
     * @param bookId
     * @throws Exception
     */
    public void buy(int accountId, int bookId) throws Exception {
        //减库存
        Book book = bookDao.getById(bookId);
        if (book.getStock() <= 0) {
            throw new BookStockException("book stock not enough...");
        }
        bookDao.updateStock(book.getId(), book.getStock() - 1);

        //减余额
        Account account = accountDao.getById(accountId);
        if (account.getBalance() < book.getPrice()) {
            throw new AccountBalanceException("account balance not enough...");
        }
        accountDao.updateBalance(account.getId(), account.getBalance() - book.getPrice());
    }

}
