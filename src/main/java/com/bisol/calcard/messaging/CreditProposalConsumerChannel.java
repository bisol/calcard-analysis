package com.bisol.calcard.messaging;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.Input;
import org.springframework.messaging.SubscribableChannel;

public interface CreditProposalConsumerChannel {

    String CHANNEL = "creditProposalChannel";

	@Input
    @Qualifier(CHANNEL)
	SubscribableChannel creditProposalChannel();
}