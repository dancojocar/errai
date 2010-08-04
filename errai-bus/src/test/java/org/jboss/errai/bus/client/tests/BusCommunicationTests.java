/*
 * Copyright 2010 JBoss, a divison Red Hat, Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jboss.errai.bus.client.tests;

import org.jboss.errai.bus.client.api.Message;
import org.jboss.errai.bus.client.api.MessageCallback;
import org.jboss.errai.bus.client.api.base.MessageBuilder;
import org.jboss.errai.bus.client.protocols.MessageParts;

/**
 * User: christopherbrock
 * Date: 26-Jul-2010
 * Time: 3:21:22 PM
 */
public class BusCommunicationTests extends AbstractErraiTest {

    @Override
    public String getModuleName() {
        return "org.jboss.errai.bus.ErraiBusTests";
    }

    public void testBasicRoundTrip() {
        runAfterInit(new Runnable() {
            public void run() {
                bus.subscribe("MyTestService", new MessageCallback() {
                    public void callback(Message message) {
                        System.out.println("GOT ECHO");
                        finishTest();
                    }
                });

                MessageBuilder.createMessage()
                        .toSubject("ServerEchoService")
                        .with(MessageParts.ReplyTo, "MyTestService")
                        .done().sendNowWith(bus);
            }
        });
    }

}
