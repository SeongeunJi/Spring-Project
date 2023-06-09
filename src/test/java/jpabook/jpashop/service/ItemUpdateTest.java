package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class ItemUpdateTest {

    @Autowired EntityManager em;
    @Test
    void updateTest() {
        //given
        Book book = em.find(Book.class, 1L);

        // TX
        book.setName("asdfsde");

        // 변경 감지 == dirty checking
        //TX commit
        //when

        //then


    }
}
