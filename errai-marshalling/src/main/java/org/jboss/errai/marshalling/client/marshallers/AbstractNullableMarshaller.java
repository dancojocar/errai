package org.jboss.errai.marshalling.client.marshallers;

import org.jboss.errai.marshalling.client.api.Marshaller;
import org.jboss.errai.marshalling.client.api.MarshallingSession;
import org.jboss.errai.marshalling.client.api.json.EJValue;

/**
 * Handles nulling of types as a simple wrapper.
 *
 * @author Mike Brock
 */
public abstract class AbstractNullableMarshaller<T> implements Marshaller<T> {
  @Override
  public final T demarshall(EJValue o, MarshallingSession ctx) {
    if (o.isNull()) {
      return null;
    }
    else {
      return doNotNullDemarshall(o, ctx);
    }
  }

  @Override
  public final String marshall(T o, MarshallingSession ctx) {
    if (o == null) {
      return "null";
    }
    else {
      return doNotNullMarshall(o, ctx);
    }
  }

  public abstract T doNotNullDemarshall(EJValue o, MarshallingSession ctx);

  public abstract String doNotNullMarshall(T o, MarshallingSession ctx);
}
