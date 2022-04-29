package org.jobsl.cgames.cchess.factory;

import com.google.common.eventbus.EventBus;
import org.jobsl.cgames.cchess.event.GameEventListener;

/**
 * 事件总线工厂
 *
 * @author jobslee
 */
public class EventBusFactory {
    private static final EventBus GAME_EVENT_BUS = new EventBus("GameBus");
    private static final Object GAME_EVENT_LISTENER = new GameEventListener();

    private EventBusFactory() {
        GAME_EVENT_BUS.register(GAME_EVENT_LISTENER);
    }

    public static EventBus getSingleBus() {
        return GAME_EVENT_BUS;
    }
}
