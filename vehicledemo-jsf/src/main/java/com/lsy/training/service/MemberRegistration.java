package com.lsy.training.service;

import com.lsy.training.model.Member;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

// The @Stateless annotation eliminates the need for manual transaction demarcation
@Stateless
public class MemberRegistration {

   @Inject
   private EntityManager em;

   @Inject
   private Event<Member> memberEventSrc;

   public void register(Member member) throws Exception {
      em.persist(member);
      memberEventSrc.fire(member);
   }
}
