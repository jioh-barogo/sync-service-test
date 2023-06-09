/*
 * Copyright (c) 2016-2021 Michael Zhang <yidongnan@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package serv.service;

import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.examples.lib.*;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @author Michael (yidongnan@gmail.com)
 * @since 2016/11/8
 */


@GrpcService
public class GrpcServerService extends SimpleGrpc.SimpleImplBase {

  @Override
  public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
    HelloReply reply = HelloReply.newBuilder().setMessage("Hello ==> " + req.getName()).build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }

  @Override
  public void testParam(TestRequest request, StreamObserver<TestResponse> responseObserver) {
    var reply = TestResponse.newBuilder()
        .setPartnerOrderId(request.getPartnerOrderId())
        .setOrderAgencyOrderId(request.getOrderAgencyOrderId())
        .setOrderAgencyTodayOrderId(request.getOrderAgencyTodayOrderId())
        .setPartnerStoreId(request.getPartnerStoreId())
        .setDropJibunAddress(request.getDropJibunAddress())
        .setDropRoadAddress(request.getDropRoadAddress())
        .setDropAddressDetail(request.getDropAddressDetail())
        .setDropLocation(request.getDropLocation())
        .setDropAddressInfo(request.getDropAddressInfo())
        .setCustomerPhone(request.getCustomerPhone())
        .setOrdererSubPhone(request.getOrdererSubPhone())
        .setCustomerName(request.getCustomerName())
        .setReceiverPhone(request.getReceiverPhone())
        .setReceiverSubPhone(request.getReceiverSubPhone())
        .setReceiverName(request.getReceiverName())
        .setPickupExpectedAt(request.getPickupExpectedAt())
        .setIsReservation(request.getIsReservation())
        .setDropExpectedAt(request.getDropExpectedAt())
        .setReservedOrderDisplayTime(request.getReservedOrderDisplayTime())
        .setTotalPayPrice(request.getTotalPayPrice())
        .setOrderAmount(request.getOrderAmount())
        .setPrePayAmount(request.getPrePayAmount())
        .setDeferredPrice(request.getDeferredPrice())
        .setPayCashAmount(request.getPayCashAmount())
        .setPayCardAmount(request.getPayCardAmount())
        .setTaxFreePayAmount(request.getTaxFreePayAmount())
        .setGoodsNames(request.getGoodsNames())
        .addAllOrderGoods(request.getOrderGoodsList())
        .setStoreOrderMemo(request.getStoreOrderMemo())
        .setMemo(request.getMemo())
        .setOrderAgencyOrderCreatedAt(request.getOrderAgencyOrderCreatedAt())
        .setOrderChannel(request.getOrderChannel())
        .setIsUntact(request.getIsUntact())
        .setShouldScanQRCodeForPickup(request.getShouldScanQRCodeForPickup())
        .setTimeOutAt(request.getTimeOutAt())
        .build();
    responseObserver.onNext(reply);
    responseObserver.onCompleted();
  }
}
