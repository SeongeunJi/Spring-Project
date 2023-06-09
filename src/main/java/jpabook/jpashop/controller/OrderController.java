package jpabook.jpashop.controller;

import jpabook.jpashop.SessionConst;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.consts.Country;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.OrderSearch;
import jpabook.jpashop.service.ItemService;
import jpabook.jpashop.service.MemberService;
import jpabook.jpashop.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final MemberService memberService;
    private final ItemService itemService;
    private final Map<String, Country> countryMap;

    @GetMapping("/order")
    public String createForm(Model model,
                             @ModelAttribute(name = "form") OrderForm orderForm,
                             @SessionAttribute(name = SessionConst.LOGIN_MEMBER) Long memberId) {
        Member member = memberService.findOne(memberId);
        List<Item> items = itemService.findItems();

        model.addAttribute("member", member);
        model.addAttribute("items", items);
        model.addAttribute("countryMap", countryMap);
        return "order/orderForm";
    }

    @PostMapping("/order")
    public String orderItem(@SessionAttribute(name = SessionConst.LOGIN_MEMBER) Long memberId,
                            Model model,
                            @Validated @ModelAttribute(name = "form") OrderForm orderForm,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("member", memberService.findOne(memberId));
            model.addAttribute("items", itemService.findItems());
            model.addAttribute("countryMap", countryMap);
            return "order/orderForm";
        }

        orderService.order(memberId, orderForm.getItemId(), orderForm.getCount());
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String orderList(@ModelAttribute OrderSearch orderSearch, Model model) {
        List<Order> orders = orderService.findOrders(orderSearch);
        model.addAttribute("orders", orders);
        return "order/orderList";
    }

    @PostMapping("/orders/{orderId}/cancel")
    public String cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);
        return "redirect:/orders";
    }
}
