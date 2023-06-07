package serv.service;

import dto.CreateRequest;
import dto.CreateResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

  @GetMapping("/{index}")
  public String getIndex(@PathVariable Long index) {
    return index.toString();
  }

  @PostMapping
  public CreateResponse createRequest(@RequestBody CreateRequest createRequest) {
    return CreateResponse.builder()
        .partnerOrderId(createRequest.getPartnerOrderId())
        .orderAgencyOrderId(createRequest.getOrderAgencyOrderId())
        .orderAgencyTodayOrderId(createRequest.getOrderAgencyTodayOrderId())
        .partnerStoreId(createRequest.getPartnerStoreId())
        .dropJibunAddress(createRequest.getDropJibunAddress())
        .dropRoadAddress(createRequest.getDropRoadAddress())
        .dropAddressDetail(createRequest.getDropAddressDetail())
        .dropLocation(createRequest.getDropLocation())
        .dropAddressInfo(createRequest.getDropAddressInfo())
        .customerPhone(createRequest.getCustomerPhone())
        .ordererSubPhone(createRequest.getOrdererSubPhone())
        .customerName(createRequest.getCustomerName())
        .receiverPhone(createRequest.getReceiverPhone())
        .receiverSubPhone(createRequest.getReceiverSubPhone())
        .receiverName(createRequest.getReceiverName())
        .pickupExpectedAt(createRequest.getPickupExpectedAt())
        .isReservation(createRequest.getIsReservation())
        .dropExpectedAt(createRequest.getDropExpectedAt())
        .reservedOrderDisplayTime(createRequest.getReservedOrderDisplayTime())
        .totalPayPrice(createRequest.getTotalPayPrice())
        .orderAmount(createRequest.getOrderAmount())
        .prePayAmount(createRequest.getPrePayAmount())
        .deferredPrice(createRequest.getDeferredPrice())
        .payCashAmount(createRequest.getPayCashAmount())
        .payCardAmount(createRequest.getPayCardAmount())
        .taxFreePayAmount(createRequest.getTaxFreePayAmount())
        .goodsNames(createRequest.getGoodsNames())
        .orderGoods(createRequest.getOrderGoods())
        .storeOrderMemo(createRequest.getStoreOrderMemo())
        .memo(createRequest.getMemo())
        .orderAgencyOrderCreatedAt(createRequest.getOrderAgencyOrderCreatedAt())
        .orderChannel(createRequest.getOrderChannel())
        .isUntact(createRequest.getIsUntact())
        .shouldScanQRCodeForPickup(createRequest.getShouldScanQRCodeForPickup())
        .timeOutAt(createRequest.getTimeOutAt())
        .build();
  }


  public static void main(String[] args) throws UnsupportedEncodingException {
    System.out.println("韓".getBytes("ms949").length);
    System.out.println("韓".getBytes("euc-kr").length);
  }
}

