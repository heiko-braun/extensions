/*
 * JBoss, Home of Professional Open Source
 * Copyright 2010, Red Hat, Inc., and individual contributors
 * by the @authors tag. See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,  
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.weld.extensions.bean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Set;

import javax.enterprise.inject.spi.InjectionPoint;
import javax.enterprise.inject.spi.PassivationCapable;

public class PassivationCapableBeanImpl<T> extends BeanImpl<T> implements PassivationCapable
{
   private final String id;

   PassivationCapableBeanImpl(String id, Class<?> beanClass, String name, Set<Annotation> qualifiers, Class<? extends Annotation> scope, Set<Class<? extends Annotation>> stereotypes, Set<Type> types, boolean alternative, boolean nullable, Set<InjectionPoint> injectionPoints, BeanLifecycle<T> beanLifecycle, String toString)
   {
      super(beanClass, name, qualifiers, scope, stereotypes, types, alternative, nullable, injectionPoints, beanLifecycle, toString);
      this.id = id;
   }

   public String getId()
   {
      return id;
   }

}
