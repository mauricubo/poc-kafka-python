/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package com.aldisued.avro;

import org.apache.avro.generic.GenericArray;
import org.apache.avro.specific.SpecificData;
import org.apache.avro.util.Utf8;
import org.apache.avro.message.BinaryMessageEncoder;
import org.apache.avro.message.BinaryMessageDecoder;
import org.apache.avro.message.SchemaStore;

@org.apache.avro.specific.AvroGenerated
public class PizzaOrder extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 2626967050439313300L;


  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"PizzaOrder\",\"namespace\":\"com.aldisued.avro\",\"fields\":[{\"name\":\"id\",\"type\":\"int\"},{\"name\":\"shop\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"name\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"phoneNumber\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"address\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"pizzas\",\"type\":{\"type\":\"array\",\"items\":{\"type\":\"record\",\"name\":\"Pizza\",\"fields\":[{\"name\":\"pizzaName\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}},{\"name\":\"additionalToppings\",\"type\":[\"null\",{\"type\":\"array\",\"items\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]},{\"name\":\"price\",\"type\":\"int\"}]}}},{\"name\":\"timestamp\",\"type\":\"long\"}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }

  private static final SpecificData MODEL$ = new SpecificData();

  private static final BinaryMessageEncoder<PizzaOrder> ENCODER =
      new BinaryMessageEncoder<>(MODEL$, SCHEMA$);

  private static final BinaryMessageDecoder<PizzaOrder> DECODER =
      new BinaryMessageDecoder<>(MODEL$, SCHEMA$);

  /**
   * Return the BinaryMessageEncoder instance used by this class.
   * @return the message encoder used by this class
   */
  public static BinaryMessageEncoder<PizzaOrder> getEncoder() {
    return ENCODER;
  }

  /**
   * Return the BinaryMessageDecoder instance used by this class.
   * @return the message decoder used by this class
   */
  public static BinaryMessageDecoder<PizzaOrder> getDecoder() {
    return DECODER;
  }

  /**
   * Create a new BinaryMessageDecoder instance for this class that uses the specified {@link SchemaStore}.
   * @param resolver a {@link SchemaStore} used to find schemas by fingerprint
   * @return a BinaryMessageDecoder instance for this class backed by the given SchemaStore
   */
  public static BinaryMessageDecoder<PizzaOrder> createDecoder(SchemaStore resolver) {
    return new BinaryMessageDecoder<>(MODEL$, SCHEMA$, resolver);
  }

  /**
   * Serializes this PizzaOrder to a ByteBuffer.
   * @return a buffer holding the serialized data for this instance
   * @throws java.io.IOException if this instance could not be serialized
   */
  public java.nio.ByteBuffer toByteBuffer() throws java.io.IOException {
    return ENCODER.encode(this);
  }

  /**
   * Deserializes a PizzaOrder from a ByteBuffer.
   * @param b a byte buffer holding serialized data for an instance of this class
   * @return a PizzaOrder instance decoded from the given buffer
   * @throws java.io.IOException if the given bytes could not be deserialized into an instance of this class
   */
  public static PizzaOrder fromByteBuffer(
      java.nio.ByteBuffer b) throws java.io.IOException {
    return DECODER.decode(b);
  }

  private int id;
  private java.lang.String shop;
  private java.lang.String name;
  private java.lang.String phoneNumber;
  private java.lang.String address;
  private java.util.List<com.aldisued.avro.Pizza> pizzas;
  private long timestamp;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public PizzaOrder() {}

  /**
   * All-args constructor.
   * @param id The new value for id
   * @param shop The new value for shop
   * @param name The new value for name
   * @param phoneNumber The new value for phoneNumber
   * @param address The new value for address
   * @param pizzas The new value for pizzas
   * @param timestamp The new value for timestamp
   */
  public PizzaOrder(java.lang.Integer id, java.lang.String shop, java.lang.String name, java.lang.String phoneNumber, java.lang.String address, java.util.List<com.aldisued.avro.Pizza> pizzas, java.lang.Long timestamp) {
    this.id = id;
    this.shop = shop;
    this.name = name;
    this.phoneNumber = phoneNumber;
    this.address = address;
    this.pizzas = pizzas;
    this.timestamp = timestamp;
  }

  @Override
  public org.apache.avro.specific.SpecificData getSpecificData() { return MODEL$; }

  @Override
  public org.apache.avro.Schema getSchema() { return SCHEMA$; }

  // Used by DatumWriter.  Applications should not call.
  @Override
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return id;
    case 1: return shop;
    case 2: return name;
    case 3: return phoneNumber;
    case 4: return address;
    case 5: return pizzas;
    case 6: return timestamp;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  // Used by DatumReader.  Applications should not call.
  @Override
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: id = (java.lang.Integer)value$; break;
    case 1: shop = value$ != null ? value$.toString() : null; break;
    case 2: name = value$ != null ? value$.toString() : null; break;
    case 3: phoneNumber = value$ != null ? value$.toString() : null; break;
    case 4: address = value$ != null ? value$.toString() : null; break;
    case 5: pizzas = (java.util.List<com.aldisued.avro.Pizza>)value$; break;
    case 6: timestamp = (java.lang.Long)value$; break;
    default: throw new IndexOutOfBoundsException("Invalid index: " + field$);
    }
  }

  /**
   * Gets the value of the 'id' field.
   * @return The value of the 'id' field.
   */
  public int getId() {
    return id;
  }


  /**
   * Sets the value of the 'id' field.
   * @param value the value to set.
   */
  public void setId(int value) {
    this.id = value;
  }

  /**
   * Gets the value of the 'shop' field.
   * @return The value of the 'shop' field.
   */
  public java.lang.String getShop() {
    return shop;
  }


  /**
   * Sets the value of the 'shop' field.
   * @param value the value to set.
   */
  public void setShop(java.lang.String value) {
    this.shop = value;
  }

  /**
   * Gets the value of the 'name' field.
   * @return The value of the 'name' field.
   */
  public java.lang.String getName() {
    return name;
  }


  /**
   * Sets the value of the 'name' field.
   * @param value the value to set.
   */
  public void setName(java.lang.String value) {
    this.name = value;
  }

  /**
   * Gets the value of the 'phoneNumber' field.
   * @return The value of the 'phoneNumber' field.
   */
  public java.lang.String getPhoneNumber() {
    return phoneNumber;
  }


  /**
   * Sets the value of the 'phoneNumber' field.
   * @param value the value to set.
   */
  public void setPhoneNumber(java.lang.String value) {
    this.phoneNumber = value;
  }

  /**
   * Gets the value of the 'address' field.
   * @return The value of the 'address' field.
   */
  public java.lang.String getAddress() {
    return address;
  }


  /**
   * Sets the value of the 'address' field.
   * @param value the value to set.
   */
  public void setAddress(java.lang.String value) {
    this.address = value;
  }

  /**
   * Gets the value of the 'pizzas' field.
   * @return The value of the 'pizzas' field.
   */
  public java.util.List<com.aldisued.avro.Pizza> getPizzas() {
    return pizzas;
  }


  /**
   * Sets the value of the 'pizzas' field.
   * @param value the value to set.
   */
  public void setPizzas(java.util.List<com.aldisued.avro.Pizza> value) {
    this.pizzas = value;
  }

  /**
   * Gets the value of the 'timestamp' field.
   * @return The value of the 'timestamp' field.
   */
  public long getTimestamp() {
    return timestamp;
  }


  /**
   * Sets the value of the 'timestamp' field.
   * @param value the value to set.
   */
  public void setTimestamp(long value) {
    this.timestamp = value;
  }

  /**
   * Creates a new PizzaOrder RecordBuilder.
   * @return A new PizzaOrder RecordBuilder
   */
  public static com.aldisued.avro.PizzaOrder.Builder newBuilder() {
    return new com.aldisued.avro.PizzaOrder.Builder();
  }

  /**
   * Creates a new PizzaOrder RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new PizzaOrder RecordBuilder
   */
  public static com.aldisued.avro.PizzaOrder.Builder newBuilder(com.aldisued.avro.PizzaOrder.Builder other) {
    if (other == null) {
      return new com.aldisued.avro.PizzaOrder.Builder();
    } else {
      return new com.aldisued.avro.PizzaOrder.Builder(other);
    }
  }

  /**
   * Creates a new PizzaOrder RecordBuilder by copying an existing PizzaOrder instance.
   * @param other The existing instance to copy.
   * @return A new PizzaOrder RecordBuilder
   */
  public static com.aldisued.avro.PizzaOrder.Builder newBuilder(com.aldisued.avro.PizzaOrder other) {
    if (other == null) {
      return new com.aldisued.avro.PizzaOrder.Builder();
    } else {
      return new com.aldisued.avro.PizzaOrder.Builder(other);
    }
  }

  /**
   * RecordBuilder for PizzaOrder instances.
   */
  @org.apache.avro.specific.AvroGenerated
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<PizzaOrder>
    implements org.apache.avro.data.RecordBuilder<PizzaOrder> {

    private int id;
    private java.lang.String shop;
    private java.lang.String name;
    private java.lang.String phoneNumber;
    private java.lang.String address;
    private java.util.List<com.aldisued.avro.Pizza> pizzas;
    private long timestamp;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$, MODEL$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(com.aldisued.avro.PizzaOrder.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = other.fieldSetFlags()[0];
      }
      if (isValidValue(fields()[1], other.shop)) {
        this.shop = data().deepCopy(fields()[1].schema(), other.shop);
        fieldSetFlags()[1] = other.fieldSetFlags()[1];
      }
      if (isValidValue(fields()[2], other.name)) {
        this.name = data().deepCopy(fields()[2].schema(), other.name);
        fieldSetFlags()[2] = other.fieldSetFlags()[2];
      }
      if (isValidValue(fields()[3], other.phoneNumber)) {
        this.phoneNumber = data().deepCopy(fields()[3].schema(), other.phoneNumber);
        fieldSetFlags()[3] = other.fieldSetFlags()[3];
      }
      if (isValidValue(fields()[4], other.address)) {
        this.address = data().deepCopy(fields()[4].schema(), other.address);
        fieldSetFlags()[4] = other.fieldSetFlags()[4];
      }
      if (isValidValue(fields()[5], other.pizzas)) {
        this.pizzas = data().deepCopy(fields()[5].schema(), other.pizzas);
        fieldSetFlags()[5] = other.fieldSetFlags()[5];
      }
      if (isValidValue(fields()[6], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[6].schema(), other.timestamp);
        fieldSetFlags()[6] = other.fieldSetFlags()[6];
      }
    }

    /**
     * Creates a Builder by copying an existing PizzaOrder instance
     * @param other The existing instance to copy.
     */
    private Builder(com.aldisued.avro.PizzaOrder other) {
      super(SCHEMA$, MODEL$);
      if (isValidValue(fields()[0], other.id)) {
        this.id = data().deepCopy(fields()[0].schema(), other.id);
        fieldSetFlags()[0] = true;
      }
      if (isValidValue(fields()[1], other.shop)) {
        this.shop = data().deepCopy(fields()[1].schema(), other.shop);
        fieldSetFlags()[1] = true;
      }
      if (isValidValue(fields()[2], other.name)) {
        this.name = data().deepCopy(fields()[2].schema(), other.name);
        fieldSetFlags()[2] = true;
      }
      if (isValidValue(fields()[3], other.phoneNumber)) {
        this.phoneNumber = data().deepCopy(fields()[3].schema(), other.phoneNumber);
        fieldSetFlags()[3] = true;
      }
      if (isValidValue(fields()[4], other.address)) {
        this.address = data().deepCopy(fields()[4].schema(), other.address);
        fieldSetFlags()[4] = true;
      }
      if (isValidValue(fields()[5], other.pizzas)) {
        this.pizzas = data().deepCopy(fields()[5].schema(), other.pizzas);
        fieldSetFlags()[5] = true;
      }
      if (isValidValue(fields()[6], other.timestamp)) {
        this.timestamp = data().deepCopy(fields()[6].schema(), other.timestamp);
        fieldSetFlags()[6] = true;
      }
    }

    /**
      * Gets the value of the 'id' field.
      * @return The value.
      */
    public int getId() {
      return id;
    }


    /**
      * Sets the value of the 'id' field.
      * @param value The value of 'id'.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder setId(int value) {
      validate(fields()[0], value);
      this.id = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'id' field has been set.
      * @return True if the 'id' field has been set, false otherwise.
      */
    public boolean hasId() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'id' field.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder clearId() {
      fieldSetFlags()[0] = false;
      return this;
    }

    /**
      * Gets the value of the 'shop' field.
      * @return The value.
      */
    public java.lang.String getShop() {
      return shop;
    }


    /**
      * Sets the value of the 'shop' field.
      * @param value The value of 'shop'.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder setShop(java.lang.String value) {
      validate(fields()[1], value);
      this.shop = value;
      fieldSetFlags()[1] = true;
      return this;
    }

    /**
      * Checks whether the 'shop' field has been set.
      * @return True if the 'shop' field has been set, false otherwise.
      */
    public boolean hasShop() {
      return fieldSetFlags()[1];
    }


    /**
      * Clears the value of the 'shop' field.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder clearShop() {
      shop = null;
      fieldSetFlags()[1] = false;
      return this;
    }

    /**
      * Gets the value of the 'name' field.
      * @return The value.
      */
    public java.lang.String getName() {
      return name;
    }


    /**
      * Sets the value of the 'name' field.
      * @param value The value of 'name'.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder setName(java.lang.String value) {
      validate(fields()[2], value);
      this.name = value;
      fieldSetFlags()[2] = true;
      return this;
    }

    /**
      * Checks whether the 'name' field has been set.
      * @return True if the 'name' field has been set, false otherwise.
      */
    public boolean hasName() {
      return fieldSetFlags()[2];
    }


    /**
      * Clears the value of the 'name' field.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder clearName() {
      name = null;
      fieldSetFlags()[2] = false;
      return this;
    }

    /**
      * Gets the value of the 'phoneNumber' field.
      * @return The value.
      */
    public java.lang.String getPhoneNumber() {
      return phoneNumber;
    }


    /**
      * Sets the value of the 'phoneNumber' field.
      * @param value The value of 'phoneNumber'.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder setPhoneNumber(java.lang.String value) {
      validate(fields()[3], value);
      this.phoneNumber = value;
      fieldSetFlags()[3] = true;
      return this;
    }

    /**
      * Checks whether the 'phoneNumber' field has been set.
      * @return True if the 'phoneNumber' field has been set, false otherwise.
      */
    public boolean hasPhoneNumber() {
      return fieldSetFlags()[3];
    }


    /**
      * Clears the value of the 'phoneNumber' field.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder clearPhoneNumber() {
      phoneNumber = null;
      fieldSetFlags()[3] = false;
      return this;
    }

    /**
      * Gets the value of the 'address' field.
      * @return The value.
      */
    public java.lang.String getAddress() {
      return address;
    }


    /**
      * Sets the value of the 'address' field.
      * @param value The value of 'address'.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder setAddress(java.lang.String value) {
      validate(fields()[4], value);
      this.address = value;
      fieldSetFlags()[4] = true;
      return this;
    }

    /**
      * Checks whether the 'address' field has been set.
      * @return True if the 'address' field has been set, false otherwise.
      */
    public boolean hasAddress() {
      return fieldSetFlags()[4];
    }


    /**
      * Clears the value of the 'address' field.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder clearAddress() {
      address = null;
      fieldSetFlags()[4] = false;
      return this;
    }

    /**
      * Gets the value of the 'pizzas' field.
      * @return The value.
      */
    public java.util.List<com.aldisued.avro.Pizza> getPizzas() {
      return pizzas;
    }


    /**
      * Sets the value of the 'pizzas' field.
      * @param value The value of 'pizzas'.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder setPizzas(java.util.List<com.aldisued.avro.Pizza> value) {
      validate(fields()[5], value);
      this.pizzas = value;
      fieldSetFlags()[5] = true;
      return this;
    }

    /**
      * Checks whether the 'pizzas' field has been set.
      * @return True if the 'pizzas' field has been set, false otherwise.
      */
    public boolean hasPizzas() {
      return fieldSetFlags()[5];
    }


    /**
      * Clears the value of the 'pizzas' field.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder clearPizzas() {
      pizzas = null;
      fieldSetFlags()[5] = false;
      return this;
    }

    /**
      * Gets the value of the 'timestamp' field.
      * @return The value.
      */
    public long getTimestamp() {
      return timestamp;
    }


    /**
      * Sets the value of the 'timestamp' field.
      * @param value The value of 'timestamp'.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder setTimestamp(long value) {
      validate(fields()[6], value);
      this.timestamp = value;
      fieldSetFlags()[6] = true;
      return this;
    }

    /**
      * Checks whether the 'timestamp' field has been set.
      * @return True if the 'timestamp' field has been set, false otherwise.
      */
    public boolean hasTimestamp() {
      return fieldSetFlags()[6];
    }


    /**
      * Clears the value of the 'timestamp' field.
      * @return This builder.
      */
    public com.aldisued.avro.PizzaOrder.Builder clearTimestamp() {
      fieldSetFlags()[6] = false;
      return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public PizzaOrder build() {
      try {
        PizzaOrder record = new PizzaOrder();
        record.id = fieldSetFlags()[0] ? this.id : (java.lang.Integer) defaultValue(fields()[0]);
        record.shop = fieldSetFlags()[1] ? this.shop : (java.lang.String) defaultValue(fields()[1]);
        record.name = fieldSetFlags()[2] ? this.name : (java.lang.String) defaultValue(fields()[2]);
        record.phoneNumber = fieldSetFlags()[3] ? this.phoneNumber : (java.lang.String) defaultValue(fields()[3]);
        record.address = fieldSetFlags()[4] ? this.address : (java.lang.String) defaultValue(fields()[4]);
        record.pizzas = fieldSetFlags()[5] ? this.pizzas : (java.util.List<com.aldisued.avro.Pizza>) defaultValue(fields()[5]);
        record.timestamp = fieldSetFlags()[6] ? this.timestamp : (java.lang.Long) defaultValue(fields()[6]);
        return record;
      } catch (org.apache.avro.AvroMissingFieldException e) {
        throw e;
      } catch (java.lang.Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumWriter<PizzaOrder>
    WRITER$ = (org.apache.avro.io.DatumWriter<PizzaOrder>)MODEL$.createDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  @SuppressWarnings("unchecked")
  private static final org.apache.avro.io.DatumReader<PizzaOrder>
    READER$ = (org.apache.avro.io.DatumReader<PizzaOrder>)MODEL$.createDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

  @Override protected boolean hasCustomCoders() { return true; }

  @Override public void customEncode(org.apache.avro.io.Encoder out)
    throws java.io.IOException
  {
    out.writeInt(this.id);

    out.writeString(this.shop);

    out.writeString(this.name);

    out.writeString(this.phoneNumber);

    out.writeString(this.address);

    long size0 = this.pizzas.size();
    out.writeArrayStart();
    out.setItemCount(size0);
    long actualSize0 = 0;
    for (com.aldisued.avro.Pizza e0: this.pizzas) {
      actualSize0++;
      out.startItem();
      e0.customEncode(out);
    }
    out.writeArrayEnd();
    if (actualSize0 != size0)
      throw new java.util.ConcurrentModificationException("Array-size written was " + size0 + ", but element count was " + actualSize0 + ".");

    out.writeLong(this.timestamp);

  }

  @Override public void customDecode(org.apache.avro.io.ResolvingDecoder in)
    throws java.io.IOException
  {
    org.apache.avro.Schema.Field[] fieldOrder = in.readFieldOrderIfDiff();
    if (fieldOrder == null) {
      this.id = in.readInt();

      this.shop = in.readString();

      this.name = in.readString();

      this.phoneNumber = in.readString();

      this.address = in.readString();

      long size0 = in.readArrayStart();
      java.util.List<com.aldisued.avro.Pizza> a0 = this.pizzas;
      if (a0 == null) {
        a0 = new SpecificData.Array<com.aldisued.avro.Pizza>((int)size0, SCHEMA$.getField("pizzas").schema());
        this.pizzas = a0;
      } else a0.clear();
      SpecificData.Array<com.aldisued.avro.Pizza> ga0 = (a0 instanceof SpecificData.Array ? (SpecificData.Array<com.aldisued.avro.Pizza>)a0 : null);
      for ( ; 0 < size0; size0 = in.arrayNext()) {
        for ( ; size0 != 0; size0--) {
          com.aldisued.avro.Pizza e0 = (ga0 != null ? ga0.peek() : null);
          if (e0 == null) {
            e0 = new com.aldisued.avro.Pizza();
          }
          e0.customDecode(in);
          a0.add(e0);
        }
      }

      this.timestamp = in.readLong();

    } else {
      for (int i = 0; i < 7; i++) {
        switch (fieldOrder[i].pos()) {
        case 0:
          this.id = in.readInt();
          break;

        case 1:
          this.shop = in.readString();
          break;

        case 2:
          this.name = in.readString();
          break;

        case 3:
          this.phoneNumber = in.readString();
          break;

        case 4:
          this.address = in.readString();
          break;

        case 5:
          long size0 = in.readArrayStart();
          java.util.List<com.aldisued.avro.Pizza> a0 = this.pizzas;
          if (a0 == null) {
            a0 = new SpecificData.Array<com.aldisued.avro.Pizza>((int)size0, SCHEMA$.getField("pizzas").schema());
            this.pizzas = a0;
          } else a0.clear();
          SpecificData.Array<com.aldisued.avro.Pizza> ga0 = (a0 instanceof SpecificData.Array ? (SpecificData.Array<com.aldisued.avro.Pizza>)a0 : null);
          for ( ; 0 < size0; size0 = in.arrayNext()) {
            for ( ; size0 != 0; size0--) {
              com.aldisued.avro.Pizza e0 = (ga0 != null ? ga0.peek() : null);
              if (e0 == null) {
                e0 = new com.aldisued.avro.Pizza();
              }
              e0.customDecode(in);
              a0.add(e0);
            }
          }
          break;

        case 6:
          this.timestamp = in.readLong();
          break;

        default:
          throw new java.io.IOException("Corrupt ResolvingDecoder.");
        }
      }
    }
  }
}









