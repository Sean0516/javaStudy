package com.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;


/**
 * Created by Sean on 2019/3/26
 *
 * @author Sean
 */
public class CustomFilter extends Filter<ILoggingEvent> {
    public FilterReply decide(ILoggingEvent iLoggingEvent) {
        if ("com.logback.Test".equals(iLoggingEvent.getLoggerName())){
            return FilterReply.DENY;
        }else {
            return FilterReply.ACCEPT;
        }
    }
}
