package jpabook.jpashop.service;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.consts.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired private EntityManager em;
    @Autowired private OrderService orderService;
    @Autowired private OrderRepository orderRepository;
    @Test
    void orderItem() {
        //given
        Member member = createMember();
        Item item = createBook();
        int orderCnt = 2;

        //when
        Long orderId = orderService.order(member.getId(), item.getId(), orderCnt);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(OrderStatus.ORDER, getOrder.getStatus(),"상품 주문 시 상태는 ORDER");
        assertEquals(1, getOrder.getOrderItems().size(), "주문 상품수는 정확해야 한다,");
        assertEquals(getOrder.getTotalPrice(), item.getPrice() * orderCnt,"총 주문 가격 : 가격 * 수량");
        assertEquals(item.getStockQuantity(), 10 - orderCnt, "주문 수량 만큼 재고 감소해야 한다.");
    }


    @Test
    void notEnoughStock() {
        //given
        Member member = createMember();
        Item book = createBook();

        //when
        int orderCnt = 11;

        //then
        assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), book.getId(),orderCnt));
    }
    @Test
    void cancelItem() {
        //given
        Member member = createMember();
        Item book = createBook();
        em.persist(member);
        em.persist(book);
        int orderCnt = 2;
        Long orderId = orderService.order(member.getId(), book.getId(), orderCnt);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findOne(orderId);
        assertEquals(getOrder.getStatus(), OrderStatus.CANCEL);
        assertEquals(book.getStockQuantity(), 10);

    }
    private Item createBook() {
        Item item = new Book();
        item.setName("그공사");
        item.setPrice(10000);
        item.setStockQuantity(10);
        em.persist(item);
        return item;
    }

    private Member createMember() {
        Member member = new Member();
        member.setName("tester1");
//        member.setAddress(new Address("seoul","1street","123456"));
        em.persist(member);
        return member;
    }

}