// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: helloworld.proto

package net.devh.boot.grpc.examples.lib;

public final class HelloWorldProto {
  private HelloWorldProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_HelloRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_HelloRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_HelloReply_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_HelloReply_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TestRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TestRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_TestResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_TestResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\020helloworld.proto\"\034\n\014HelloRequest\022\014\n\004na" +
      "me\030\001 \001(\005\"\035\n\nHelloReply\022\017\n\007message\030\001 \001(\t\"" +
      "\337\006\n\013TestRequest\022\026\n\016partnerOrderId\030\001 \001(\t\022" +
      "\032\n\022orderAgencyOrderId\030\002 \001(\t\022\037\n\027orderAgen" +
      "cyTodayOrderId\030\003 \001(\t\022\026\n\016partnerStoreId\030\004" +
      " \001(\t\022\030\n\020dropJibunAddress\030\005 \001(\t\022\027\n\017dropRo" +
      "adAddress\030\006 \001(\t\022\031\n\021dropAddressDetail\030\007 \001" +
      "(\t\022\024\n\014dropLocation\030\010 \001(\t\022\027\n\017dropAddressI" +
      "nfo\030\t \001(\t\022\025\n\rcustomerPhone\030\n \001(\t\022\027\n\017orde" +
      "rerSubPhone\030\013 \001(\t\022\024\n\014customerName\030\014 \001(\t\022" +
      "\025\n\rreceiverPhone\030\r \001(\t\022\030\n\020receiverSubPho" +
      "ne\030\016 \001(\t\022\024\n\014receiverName\030\017 \001(\t\022\030\n\020pickup" +
      "ExpectedAt\030\020 \001(\005\022\025\n\risReservation\030\021 \001(\010\022" +
      "\026\n\016dropExpectedAt\030\022 \001(\003\022 \n\030reservedOrder" +
      "DisplayTime\030\023 \001(\003\022\025\n\rtotalPayPrice\030\024 \001(\005" +
      "\022\023\n\013orderAmount\030\025 \001(\004\022\024\n\014prePayAmount\030\026 " +
      "\001(\004\022\025\n\rdeferredPrice\030\027 \001(\005\022\025\n\rpayCashAmo" +
      "unt\030\030 \001(\004\022\025\n\rpayCardAmount\030\031 \001(\004\022\030\n\020taxF" +
      "reePayAmount\030\032 \001(\004\022\022\n\ngoodsNames\030\033 \001(\t\022\022" +
      "\n\norderGoods\030\034 \003(\t\022\026\n\016storeOrderMemo\030\035 \001" +
      "(\t\022\014\n\004memo\030\036 \001(\t\022!\n\031orderAgencyOrderCrea" +
      "tedAt\030\037 \001(\003\022\024\n\014orderChannel\030  \001(\t\022\020\n\010isU" +
      "ntact\030! \001(\010\022!\n\031shouldScanQRCodeForPickup" +
      "\030\" \001(\010\022\021\n\ttimeOutAt\030# \001(\003\"\340\006\n\014TestRespon" +
      "se\022\026\n\016partnerOrderId\030\001 \001(\t\022\032\n\022orderAgenc" +
      "yOrderId\030\002 \001(\t\022\037\n\027orderAgencyTodayOrderI" +
      "d\030\003 \001(\t\022\026\n\016partnerStoreId\030\004 \001(\t\022\030\n\020dropJ" +
      "ibunAddress\030\005 \001(\t\022\027\n\017dropRoadAddress\030\006 \001" +
      "(\t\022\031\n\021dropAddressDetail\030\007 \001(\t\022\024\n\014dropLoc" +
      "ation\030\010 \001(\t\022\027\n\017dropAddressInfo\030\t \001(\t\022\025\n\r" +
      "customerPhone\030\n \001(\t\022\027\n\017ordererSubPhone\030\013" +
      " \001(\t\022\024\n\014customerName\030\014 \001(\t\022\025\n\rreceiverPh" +
      "one\030\r \001(\t\022\030\n\020receiverSubPhone\030\016 \001(\t\022\024\n\014r" +
      "eceiverName\030\017 \001(\t\022\030\n\020pickupExpectedAt\030\020 " +
      "\001(\005\022\025\n\risReservation\030\021 \001(\010\022\026\n\016dropExpect" +
      "edAt\030\022 \001(\003\022 \n\030reservedOrderDisplayTime\030\023" +
      " \001(\003\022\025\n\rtotalPayPrice\030\024 \001(\005\022\023\n\013orderAmou" +
      "nt\030\025 \001(\004\022\024\n\014prePayAmount\030\026 \001(\004\022\025\n\rdeferr" +
      "edPrice\030\027 \001(\005\022\025\n\rpayCashAmount\030\030 \001(\004\022\025\n\r" +
      "payCardAmount\030\031 \001(\004\022\030\n\020taxFreePayAmount\030" +
      "\032 \001(\004\022\022\n\ngoodsNames\030\033 \001(\t\022\022\n\norderGoods\030" +
      "\034 \003(\t\022\026\n\016storeOrderMemo\030\035 \001(\t\022\014\n\004memo\030\036 " +
      "\001(\t\022!\n\031orderAgencyOrderCreatedAt\030\037 \001(\003\022\024" +
      "\n\014orderChannel\030  \001(\t\022\020\n\010isUntact\030! \001(\010\022!" +
      "\n\031shouldScanQRCodeForPickup\030\" \001(\010\022\021\n\ttim" +
      "eOutAt\030# \001(\0032^\n\006Simple\022(\n\010SayHello\022\r.Hel" +
      "loRequest\032\013.HelloReply\"\000\022*\n\tTestParam\022\014." +
      "TestRequest\032\r.TestResponse\"\000B4\n\037net.devh" +
      ".boot.grpc.examples.libB\017HelloWorldProto" +
      "P\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_HelloRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_HelloRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_HelloRequest_descriptor,
        new java.lang.String[] { "Name", });
    internal_static_HelloReply_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_HelloReply_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_HelloReply_descriptor,
        new java.lang.String[] { "Message", });
    internal_static_TestRequest_descriptor =
      getDescriptor().getMessageTypes().get(2);
    internal_static_TestRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TestRequest_descriptor,
        new java.lang.String[] { "PartnerOrderId", "OrderAgencyOrderId", "OrderAgencyTodayOrderId", "PartnerStoreId", "DropJibunAddress", "DropRoadAddress", "DropAddressDetail", "DropLocation", "DropAddressInfo", "CustomerPhone", "OrdererSubPhone", "CustomerName", "ReceiverPhone", "ReceiverSubPhone", "ReceiverName", "PickupExpectedAt", "IsReservation", "DropExpectedAt", "ReservedOrderDisplayTime", "TotalPayPrice", "OrderAmount", "PrePayAmount", "DeferredPrice", "PayCashAmount", "PayCardAmount", "TaxFreePayAmount", "GoodsNames", "OrderGoods", "StoreOrderMemo", "Memo", "OrderAgencyOrderCreatedAt", "OrderChannel", "IsUntact", "ShouldScanQRCodeForPickup", "TimeOutAt", });
    internal_static_TestResponse_descriptor =
      getDescriptor().getMessageTypes().get(3);
    internal_static_TestResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_TestResponse_descriptor,
        new java.lang.String[] { "PartnerOrderId", "OrderAgencyOrderId", "OrderAgencyTodayOrderId", "PartnerStoreId", "DropJibunAddress", "DropRoadAddress", "DropAddressDetail", "DropLocation", "DropAddressInfo", "CustomerPhone", "OrdererSubPhone", "CustomerName", "ReceiverPhone", "ReceiverSubPhone", "ReceiverName", "PickupExpectedAt", "IsReservation", "DropExpectedAt", "ReservedOrderDisplayTime", "TotalPayPrice", "OrderAmount", "PrePayAmount", "DeferredPrice", "PayCashAmount", "PayCardAmount", "TaxFreePayAmount", "GoodsNames", "OrderGoods", "StoreOrderMemo", "Memo", "OrderAgencyOrderCreatedAt", "OrderChannel", "IsUntact", "ShouldScanQRCodeForPickup", "TimeOutAt", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
