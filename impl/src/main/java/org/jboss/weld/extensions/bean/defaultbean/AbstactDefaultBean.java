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
package org.jboss.weld.extensions.bean.defaultbean;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.spi.Bean;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.PassivationCapable;

import org.jboss.weld.extensions.bean.ForwardingBean;

/**
 * A helper class for implementing default bean functionality
 * 
 * @author Stuart Douglas
 * 
 */
abstract class AbstactDefaultBean<T> extends ForwardingBean<T> implements PassivationCapable
{

   private final Bean<T> delegate;
   private final Set<Annotation> qualifiers;
   private final Set<Type> types;
   private final BeanManager beanManager;
   private final Type declaringBeanType;

   protected AbstactDefaultBean(Bean<T> delegate, Type declaringBeanType, Set<Type> types, Set<Annotation> qualifiers, BeanManager beanManager)
   {
      this.delegate = delegate;
      this.beanManager = beanManager;
      this.qualifiers = new HashSet<Annotation>(qualifiers);
      this.types = new HashSet<Type>(types);
      this.declaringBeanType = declaringBeanType;
   }
   
   protected BeanManager getBeanManager()
   {
      return beanManager;
   }

   @Override
   protected Bean<T> delegate()
   {
      return delegate;
   }

   protected Type getDeclaringBeanType()
   {
      return declaringBeanType;
   }

   @Override
   public Set<Annotation> getQualifiers()
   {
      return qualifiers;
   }

   public String getId()
   {
      if (delegate instanceof PassivationCapable)
      {
         return DefaultBean.class.getName() + "-" + ((PassivationCapable) delegate).getId();
      }
      return DefaultBean.class.getName() + "-" + types.toString() + "-QUALIFIERS-" + delegate.getQualifiers().toString();
   }

   @Override
   public String toString()
   {
      return "Default Bean with types " + types + " and qualifiers " + qualifiers;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = super.hashCode();
      result = prime * result + ((qualifiers == null) ? 0 : qualifiers.hashCode());
      result = prime * result + ((types == null) ? 0 : types.hashCode());
      return result;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
         return true;
      if (getClass() != obj.getClass())
         return false;
      AbstactDefaultBean other = (AbstactDefaultBean) obj;
      if (qualifiers == null)
      {
         if (other.qualifiers != null)
            return false;
      }
      else if (!qualifiers.equals(other.qualifiers))
         return false;
      if (types == null)
      {
         if (other.types != null)
            return false;
      }
      else if (!types.equals(other.types))
         return false;
      return delegate.equals(other.delegate);
   }
}