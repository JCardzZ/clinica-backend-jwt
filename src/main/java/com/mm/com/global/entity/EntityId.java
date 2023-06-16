package com.mm.com.global.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
public class EntityId {
    @Id
    protected int id;
}
