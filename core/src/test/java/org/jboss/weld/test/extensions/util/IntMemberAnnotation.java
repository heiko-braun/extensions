package org.jboss.weld.test.extensions.util;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface IntMemberAnnotation
{
   int value();

   int someMember();
}
