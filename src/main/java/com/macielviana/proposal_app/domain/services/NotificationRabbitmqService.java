package com.macielviana.proposal_app.domain.services;

import com.macielviana.proposal_app.domain.entities.Proposal;

public interface NotificationRabbitmqService {

    void notificationRabbitmq(Proposal proposal, String  exchange);
}
