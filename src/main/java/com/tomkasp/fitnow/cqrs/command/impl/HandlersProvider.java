package com.tomkasp.fitnow.cqrs.command.impl;

import com.tomkasp.fitnow.cqrs.command.handler.CommandHandler;


public interface HandlersProvider {
    CommandHandler<Object, Object> getHandler(Object command);
}
