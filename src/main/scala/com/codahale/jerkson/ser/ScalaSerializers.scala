package com.codahale.jerkson.ser

import org.codehaus.jackson.`type`.JavaType
import com.codahale.jerkson.AST.JValue
import org.codehaus.jackson.map._

class ScalaSerializers extends Serializers.None {
  override def findSerializer(config: SerializationConfig, javaType: JavaType, beanDesc: BeanDescription, beanProp: BeanProperty) = {
    val ser: Object = if (classOf[Option[_]].isAssignableFrom(beanDesc.getBeanClass)) {
        new OptionSerializer
    } else if (classOf[StringBuilder].isAssignableFrom(beanDesc.getBeanClass)) {
      new StringBuilderSerializer
    } else if (classOf[collection.Map[_,_]].isAssignableFrom(beanDesc.getBeanClass)) {
      new MapSerializer
    } else if (classOf[Range].isAssignableFrom(beanDesc.getBeanClass)) {
      new RangeSerializer
    } else if (classOf[Iterable[_]].isAssignableFrom(beanDesc.getBeanClass)) {
        new IterableSerializer
    } else if (classOf[Iterator[_]].isAssignableFrom(beanDesc.getBeanClass)) {
      new IteratorSerializer
    } else if (classOf[JValue].isAssignableFrom(beanDesc.getBeanClass)) {
      new JValueSerializer
    } else if (classOf[Either[_,_]].isAssignableFrom(beanDesc.getBeanClass)) {
      new EitherSerializer
    } else if (classOf[Product].isAssignableFrom(beanDesc.getBeanClass)) {
      new CaseClassSerializer(beanDesc.getBeanClass)
    } else {
      null
    }
    ser.asInstanceOf[JsonSerializer[Object]]
  }
}
