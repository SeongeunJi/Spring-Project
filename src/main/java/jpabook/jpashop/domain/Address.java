package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jpabook.jpashop.domain.consts.Country;
import lombok.Getter;

// 값 타입은 변경 불가능하게 설계해야 함.
@Embeddable
@Getter
public class Address {
    @Enumerated(EnumType.STRING)
    private Country country;
    private String city;
    private String street;
    private String zip;

    // JPA 에선, protected 까지 허용.
    // 값타입은 기본 생성자 없으면 리플렉션 API 사용 불가능.
    // 기본 생성자 생성하자.
    protected Address() {
    }
    public Address(Country country, String city, String street, String zip) {
        this.country = country;
        this.city = city;
        this.street = street;
        this.zip = zip;
    }
}
