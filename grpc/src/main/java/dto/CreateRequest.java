package dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequest {

  private String partnerOrderId;                   // 파트너 아이디
  private String orderAgencyOrderId;           // 주문 채널 주문 아이디
  private String orderAgencyTodayOrderId;      // 주문 채널 주문 당일 번호
  private String partnerStoreId;                     // 파트너 상점 id
  private String dropJibunAddress;
  private String dropRoadAddress;
  private String dropAddressDetail;
  private String dropLocation;
  private String dropAddressInfo;    // 도착지 주소
  private String customerPhone;                   // 주문자 전화번호
  private String ordererSubPhone;
  private String customerName;                    // 주문자 성명
  private String receiverPhone;                  // 수령자 전화번호
  private String receiverSubPhone;
  private String receiverName;                   // 수령자 성명
  private Long pickupExpectedAt;                     // 픽업 희망 시간
  private Boolean isReservation;
  private Long dropExpectedAt;                       // 도착지 도착 요청 일시
  private Long reservedOrderDisplayTime;         // 예약 주문이 worker에게 노출되는 시간(분)
  private Integer totalPayPrice;                 // 주문자 총 결제 금액
  private BigDecimal orderAmount;                // 주문자 실제 결제 금액
  private BigDecimal prePayAmount;                  // 주문자 선결제 금액
  private Integer deferredPrice;                 // 라이더 총 결제 금액
  private BigDecimal payCashAmount;              // 라이더 현금 결제 금액
  private BigDecimal payCardAmount;              // 라이더 카드 결제 금액
  private BigDecimal taxFreePayAmount;
  private String goodsNames;                // 상품 정보 요약
  private List<String> orderGoods;           // 상품 목록
  private String storeOrderMemo;                 // 상점에서 작성한 주문 메모
  private String memo;               // 주문자가 작성 주문 메모
  private Long orderAgencyOrderCreatedAt;        // 주문채널 주문 생성 시간
  private String orderChannel;                   // 주문 유입 채널
  private Boolean isUntact;              // 비대면 여부
  private Boolean shouldScanQRCodeForPickup;   // 픽업시 QR 코드 스캔해야 해야하는 배달인지 여부
  private Long timeOutAt;              // 타임아웃 시간 (response 가 해당시간보다 이후인 경우 타임아웃으로 취소 처리.)
}