/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */
package org.jboss.as.cli.parsing.operation;

import org.jboss.as.cli.CommandFormatException;
import org.jboss.as.cli.parsing.CharacterHandler;
import org.jboss.as.cli.parsing.ExpressionBaseState;
import org.jboss.as.cli.parsing.GlobalCharacterHandlers;
import org.jboss.as.cli.parsing.OutputTargetState;
import org.jboss.as.cli.parsing.ParsingContext;
import org.jboss.as.cli.parsing.WordCharacterHandler;


/**
 *
 * @author Alexey Loubyansky
 */
public final class OperationNameState extends ExpressionBaseState {

    public static final String ID = "OP_NAME";
    public static final OperationNameState INSTANCE = new OperationNameState();

    public OperationNameState() {
        super(ID);
        setIgnoreWhitespaces(true);
        setEnterHandler(new CharacterHandler(){
            @Override
            public void handle(ParsingContext ctx) throws CommandFormatException {
                WordCharacterHandler.IGNORE_LB_ESCAPE_ON.handle(ctx);
            }});
        setDefaultHandler(WordCharacterHandler.IGNORE_LB_ESCAPE_ON);
        putHandler('(', GlobalCharacterHandlers.LEAVE_STATE_HANDLER);
        putHandler('{', GlobalCharacterHandlers.LEAVE_STATE_HANDLER);
        putHandler(OutputTargetState.OUTPUT_REDIRECT_CHAR, GlobalCharacterHandlers.LEAVE_STATE_HANDLER);
    }
}