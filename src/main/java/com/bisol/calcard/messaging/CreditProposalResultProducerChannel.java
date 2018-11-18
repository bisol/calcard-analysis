package com.bisol.calcard.messaging;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface CreditProposalResultProducerChannel {

    String CHANNEL = "creditProposalResultChannel";

    @Output
    @Qualifier(CHANNEL)
	MessageChannel creditProposalResultChannel();
}