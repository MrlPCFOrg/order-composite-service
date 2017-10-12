package com.oms.order.model.domain;

import java.util.List;

public class EventResponse {

    public List<EventDTO> getEventDTOS() {
        return eventDTOS;
    }

    public EventResponse setEventDTOS(List<EventDTO> eventDTOS) {
        this.eventDTOS = eventDTOS;
        return this;
    }

    private List<EventDTO> eventDTOS;


}
