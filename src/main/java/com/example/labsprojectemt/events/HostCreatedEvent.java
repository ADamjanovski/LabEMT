package com.example.labsprojectemt.events;

import com.example.labsprojectemt.domain.Host;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class HostCreatedEvent extends ApplicationEvent {

    private final LocalDateTime when;


    public HostCreatedEvent(Host source){
        super(source);
        when=LocalDateTime.now();
    }
}
