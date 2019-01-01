package de.mhens.events.eventproducer.integration.transformers;

import org.camunda.bpm.engine.impl.history.event.HistoryEvent;

import de.mhens.events.dto.EventDto;

public interface HistoryEventTransformer<E extends HistoryEvent ,D extends EventDto> {
	
	D transform (E event);
	
	boolean isSuitable(String eventName);
}
